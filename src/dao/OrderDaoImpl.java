package dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mapper.OrderMapper;
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

public class OrderDaoImpl implements OrderDao {
  
	@Autowired
	private OrderMapper orderMapper;

	
	public void AddOrder(OrderMo orderMo) throws Exception {
		
		orderMapper.addOrder(orderMo);
	}


	
	public void AddOrderDetails(OrderdetailsMo orderdetailsMo) throws Exception {
		
		orderMapper.addOrderDetails(orderdetailsMo);
	}



	
	public List<JieSuan_ViewMo> GetOrderList(int order_id) throws Exception {
		
		return orderMapper.getOrderList(order_id);
	}



	
	public List<Receive_InfoMo> GetUserAddList(int cust_id) throws Exception {
		
		return orderMapper.getUserAddList(cust_id);
	}



	
	public List<ProvincesMo> GetProvinces() throws Exception {
		
		return orderMapper.getProvinces();
	}



	
	public List<CitiesMo> GetCity(String provinceid) throws Exception {
		
		return orderMapper.getCities(provinceid);
	}



	
	public List<AreasMo> GetArea(String cityid) throws Exception {
		
		return orderMapper.getAreas(cityid);
	}



	
	public ProvincesMo GetProvincesById(String sheng) throws Exception {
		
		return orderMapper.getProvincesById(sheng);
	}



	
	public CitiesMo GetCityById(String shi) throws Exception {
		
		return orderMapper.getCityById(shi);
	}



	
	public void AddReceive(Receive_InfoMo receive_InfoMo) throws Exception {
	 
		orderMapper.addReceive(receive_InfoMo);	
	}



	
	public void SetMoRen0(int cust_id) throws Exception {
		
		orderMapper.updateSetMoren0(cust_id);
	}

	public void SetMoRen1(int rece_id) throws Exception {
		
		orderMapper.updateSetMoren1(rece_id);
	}

	
	public void DeleteReceiveInfo(int rece_id) throws Exception {
		
		orderMapper.deleteReceive(rece_id);
	}



	
	public void UpdateOrderReceive(OrderMo orderMo) throws Exception {
		
		orderMapper.updateOrderReceive(orderMo);
	}



	
	public void UpdateOrderPayMethod(OrderMo orderMo) throws Exception {
		
		orderMapper.updateOrderPayMethod(orderMo);
	}



	
	public void UpdateOrder(OrderMo orderMo) throws Exception {
		
		orderMapper.updateOrder(orderMo);
	}



	
	public List<OrderdetailsMo> GetOrderDetailsList(int order_id) throws Exception {
		
		return orderMapper.getOrderDetails(order_id);
	}



	
	public void DeleteShopCar(ShoppingCarMo shoppingCarMo) throws Exception {
		
		orderMapper.deleteShopCar(shoppingCarMo);
	}



	
	public CommodityMo GetCommodity(int comm_id) throws Exception {
		
		return orderMapper.getCommodity(comm_id);
	}



	
	public void UpdateCommodity(CommodityMo commodityMo) throws Exception {
		
		orderMapper.updateCommodity(commodityMo);
	}



	
	public ShouHuoInfo_ViewMo PaySuccess(int order_id) throws Exception {
		
		return orderMapper.getShouHuo(order_id);
	}



	
	public List<OrderMo> GetUserOrder(int cust_id) throws Exception {
		
		return orderMapper.getOrder(cust_id);
	}



	
	public void UpdateOrderSend(OrderMo orderMo) throws Exception {
		
		orderMapper.updateOrderSend(orderMo);
	}



	
	public void UpdateOrderSendReceive(OrderMo orderMo) throws Exception {
		
		orderMapper.updateOrderSendReceive(orderMo);
	}



	
	public List<Order_ViewMo> OrderInfo(int cust_id, String select) throws Exception {
		
		List<Order_ViewMo> orderInfoList=new ArrayList<Order_ViewMo>();
		if(select.equals("allOrder")) {
			orderInfoList=orderMapper.getAllOrderInfo(cust_id);
		}
		
		if(select.equals("noPayOrder")) {
			orderInfoList=orderMapper.getNoPayOrderInfo(cust_id);
		}
		
		if(select.equals("noSendOrder")) {
			orderInfoList=orderMapper.getNoSendOrderInfo(cust_id);
		}
		

		if(select.equals("noReceiveOrder")) {
			orderInfoList=orderMapper.getNoReceiveOrderInfo(cust_id); 
		}
		
		if(select.equals("noCommentOrder")) {
			orderInfoList=orderMapper.getNoCommentOrderInfo(cust_id); 
		}
		return orderInfoList;
	}



	
	public List<Order_Commodity_ViewMo> OrderCommodity(int order_id) throws Exception {
		
		return orderMapper.getOrderCommodity(order_id);
	}



	
	public Order_Details_ViewMo GetOrderDetails(int order_id) throws Exception {
		
		return orderMapper.getOrderInfo(order_id);
	}
	
	
	
	
	
	
	
	
}
