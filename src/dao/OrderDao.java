package dao;

import java.util.List;

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

public interface OrderDao {

	public void AddOrder(OrderMo orderMo) throws Exception;

	public void AddOrderDetails(OrderdetailsMo orderdetailsMo) throws Exception;

	public List<JieSuan_ViewMo> GetOrderList(int order_id) throws Exception;

	public List<Receive_InfoMo> GetUserAddList(int cust_id) throws Exception;

	public List<ProvincesMo> GetProvinces() throws Exception;

	public List<CitiesMo> GetCity(String provinceid) throws Exception;

	public List<AreasMo> GetArea(String cityid) throws Exception;

	public ProvincesMo GetProvincesById(String sheng) throws Exception;

	public CitiesMo GetCityById(String shi) throws Exception;

	public void AddReceive(Receive_InfoMo receive_InfoMo) throws Exception;

	public void DeleteReceiveInfo(int rece_id) throws Exception;

	public void SetMoRen0(int cust_id) throws Exception;

	public void SetMoRen1(int rece_id) throws Exception;

	public void UpdateOrderReceive(OrderMo orderMo) throws Exception;

	public void UpdateOrderPayMethod(OrderMo orderMo) throws Exception;

	public void UpdateOrder(OrderMo orderMo) throws Exception;

	public List<OrderdetailsMo> GetOrderDetailsList(int order_id) throws Exception;

	public void DeleteShopCar(ShoppingCarMo shoppingCarMo) throws Exception;

	public CommodityMo GetCommodity(int comm_id) throws Exception;

	public void UpdateCommodity(CommodityMo commodityMo) throws Exception;

	public ShouHuoInfo_ViewMo PaySuccess(int order_id) throws Exception;

	public List<OrderMo> GetUserOrder(int cust_id) throws Exception;

	public void UpdateOrderSend(OrderMo orderMo) throws Exception;

	public void UpdateOrderSendReceive(OrderMo orderMo) throws Exception;

	public List<Order_ViewMo> OrderInfo(int cust_id, String select) throws Exception;

	public List<Order_Commodity_ViewMo> OrderCommodity(int order_id) throws Exception;

	public Order_Details_ViewMo GetOrderDetails(int order_id) throws Exception;
	

}
