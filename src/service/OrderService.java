package service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.core.config.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dao.OrderDao;
import dao.ShopCarDao;
import mo.AreasMo;
import mo.CitiesMo;
import mo.CommodityMo;
import mo.JieSuan_ViewMo;
import mo.OrderMo;
import mo.Order_Commodity_ViewMo;
import mo.Order_Details_ViewMo;
import mo.Order_ViewMo;
import mo.OrderdetailsMo;
import mo.ProvincesMo;
import mo.Receive_InfoMo;
import mo.ShoppingCarMo;
import mo.ShouHuoInfo_ViewMo;

@Controller
public class OrderService {
  
	@Autowired
	private OrderDao orderDao;
	
	@Autowired 
	private ShopCarDao shopCarDao;

	public int JoinOrder(int cust_id) throws Exception{
		OrderMo orderMo=new OrderMo();
		orderMo.setCust_id(cust_id);
		orderMo.setOrder_state("未支付");
		orderMo.setOrder_time(new Timestamp(new Date().getTime()));
		orderDao.AddOrder(orderMo);
		return orderMo.getOrder_id();
	}

	public void JoinOrderDetails(int cust_id, int order_id, int[] comm_id) throws Exception{
		
		for(int i=0;i<comm_id.length;i++) {
			ShoppingCarMo carMo=new ShoppingCarMo();
			carMo.setComm_id(comm_id[i]);
			carMo.setCust_id(cust_id);
			ShoppingCarMo shoppingCarMo=shopCarDao.GetShopCar(carMo);
			OrderdetailsMo orderdetailsMo=new OrderdetailsMo();
			orderdetailsMo.setOrder_id(order_id);
			orderdetailsMo.setComm_id(shoppingCarMo.getComm_id());
			orderdetailsMo.setComm_number(shoppingCarMo.getComm_number());
			orderdetailsMo.setComm_price(shoppingCarMo.getComm_price());
			orderdetailsMo.setIs_comment(0);
			orderDao.AddOrderDetails(orderdetailsMo);
		}
	}

	public List<JieSuan_ViewMo> GetOrderList(int order_id) throws Exception{
		
		return orderDao.GetOrderList(order_id);
	}

	public List<Receive_InfoMo> GetUserAddList(int cust_id) throws Exception{
		
		return orderDao.GetUserAddList(cust_id);
	}

	public List<ProvincesMo> GetProvinces() throws Exception{
		
		return orderDao.GetProvinces();
	}

	public double CountTotalPrice(List<JieSuan_ViewMo> orderList) throws Exception {
		double totalprice=0;
		for(JieSuan_ViewMo list:orderList) {
			totalprice=totalprice+list.getSum_price();
		}
		return totalprice;
	}

	public List<CitiesMo> GetCity(String provinceid) throws Exception {
		
		return orderDao.GetCity(provinceid);
	}

	public List<AreasMo> GetArea(String cityid)  throws Exception{
		
		return orderDao.GetArea(cityid);
	}

	public void AddReceive(Receive_InfoMo receive_InfoMo, String sheng, String shi) 
			throws Exception{
		ProvincesMo provincesMo=orderDao.GetProvincesById(sheng);
		CitiesMo citiesMo=orderDao.GetCityById(shi);
		receive_InfoMo.setShengfen(provincesMo.getProvince());
		receive_InfoMo.setShi(citiesMo.getCity());
		orderDao.AddReceive(receive_InfoMo);
		
	}

	public void SetMoRen(int rece_id, int cust_id)   throws Exception{
		
		orderDao.SetMoRen0(cust_id);
		orderDao.SetMoRen1(rece_id);
	}

	public void DeleteReceiveInfo(int rece_id) throws Exception{
		
		orderDao.DeleteReceiveInfo(rece_id);
	}

	public void UpdateOrderReceive(OrderMo orderMo) throws Exception{
		
		orderDao.UpdateOrderReceive(orderMo);
	}

	public void UpdateOrderPayMethod(OrderMo orderMo) throws Exception{
		
		orderDao.UpdateOrderPayMethod( orderMo);
	}

	public ShouHuoInfo_ViewMo Pay(int order_id, String pay_no, int cust_id) throws Exception {
		OrderMo orderMo=new OrderMo();
		orderMo.setOrder_id(order_id);
		orderMo.setOrder_state("已支付");
		orderMo.setIs_send(0);
		orderMo.setIs_receive(0);
		orderMo.setPaymoney_time(new Timestamp(new Date().getTime()));
		orderDao.UpdateOrder(orderMo);
		List<OrderdetailsMo> orderdetailsList=orderDao.GetOrderDetailsList(order_id);
		for(OrderdetailsMo orderdetailsMo:orderdetailsList) {
			ShoppingCarMo shoppingCarMo=new ShoppingCarMo();
			shoppingCarMo.setCust_id(cust_id);
			shoppingCarMo.setComm_id(orderdetailsMo.getComm_id());
			orderDao.DeleteShopCar(shoppingCarMo);
			
			CommodityMo cMo=orderDao.GetCommodity(orderdetailsMo.getComm_id());
			CommodityMo commodityMo=new CommodityMo();
		    commodityMo.setComm_id(cMo.getComm_id());
		    commodityMo.setComm_number(cMo.getComm_number()-orderdetailsMo.getComm_number());
		    commodityMo.setSales_number(cMo.getSales_number()+orderdetailsMo.getComm_number());
		    orderDao.UpdateCommodity(commodityMo);
		}
		return orderDao.PaySuccess(order_id);
	}

	public void UpdateUserOrder(int cust_id) throws Exception{
		
		List<OrderMo> orderMoList=orderDao.GetUserOrder(cust_id);
		for(OrderMo orderMo:orderMoList) {
			int order_id=orderMo.getOrder_id();
			String order_state=orderMo.getOrder_state();
			Timestamp  paymoney_time=orderMo.getPaymoney_time();
			int is_send=orderMo.getIs_send();
			int is_receive=orderMo.getIs_receive();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date nowtime=null;
			Date Paying_time=null;
			
			nowtime=df.parse(df.format(new Date()).toString());
			
			if(order_state.equals("已支付")) {
				String Paymoney_time=df.format(paymoney_time);
				Paying_time=df.parse(Paymoney_time);
				
				long time_lag=nowtime.getTime()-Paying_time.getTime();
				long days=time_lag/(1000*60*60*24);
				//long hours=time_lag/(1000*60*60);
				long minutes=time_lag/(1000*60);
				
				if(is_send==0 && minutes>10&&days<3) {
					
					orderDao.UpdateOrderSend(orderMo);
				}
				
				if(is_receive==0 &&days>=3) {
					orderDao.UpdateOrderSendReceive(orderMo);
				}
		    }
		}
		
	}

	public List<Order_ViewMo> OrderInfo(int cust_id, String select) throws Exception {
		List<Order_ViewMo> orderInfoList=orderDao.OrderInfo(cust_id,select);
		for(Order_ViewMo order:orderInfoList) {
			List<Order_Commodity_ViewMo> orderCommodityList=orderDao.OrderCommodity(order.getOrder_id());
			order.setCommodityList(orderCommodityList);
		}
		return orderInfoList;
	}

	public Order_Details_ViewMo GetOrderInfo(int order_id)  throws Exception{
		Order_Details_ViewMo orderDetailsMo=orderDao.GetOrderDetails(order_id);
		List<Order_Commodity_ViewMo> orderCommodityList=orderDao.OrderCommodity(orderDetailsMo.getOrder_id());
		orderDetailsMo.setCommodityList(orderCommodityList);
		return orderDetailsMo;
	}
	
	
	
	
}
