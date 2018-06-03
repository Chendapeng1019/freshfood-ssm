package mapper;

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

public interface OrderMapper {
  
	public void addOrder(OrderMo orderMo) throws Exception;//新增订单并返回主键order_id
	
	public void addOrderDetails(OrderdetailsMo orderdetailsMo) throws Exception;//新增订单详情
	
	public List<JieSuan_ViewMo> getOrderList(int order_id) throws Exception;//订单内的商品
	
	public List<Receive_InfoMo> getUserAddList(int cust_id) throws Exception;//获取顾客收货地址
	
	public List<ProvincesMo> getProvinces() throws Exception;//获取所有省份
	
	public List<CitiesMo> getCities(String provinceid) throws Exception;//获取某省的城市
	
	public List<AreasMo> getAreas(String cityid) throws Exception;//获取某城市的县区
	
	public ProvincesMo getProvincesById(String provinceid) throws Exception;//获取省份名
	
	public CitiesMo getCityById(String cityid) throws Exception;//获取城市名
	
	public void addReceive(Receive_InfoMo receive_InfoMo) throws Exception;//新增收货地址
	
	public void updateSetMoren0(int cust_id) throws Exception;//设置默认地址把之前的设置为0
	
	public void updateSetMoren1(int rece_id) throws Exception;//设置默认地址设置为0
	
	public void deleteReceive(int rece_id) throws Exception;//删除收货信息
	
	public void updateOrderReceive(OrderMo orderMo) throws Exception;//更新订单收货地址
	
	public void updateOrderPayMethod(OrderMo orderMo) throws Exception;//更新付款方式
	
	public void updateOrder(OrderMo orderMo) throws Exception;//付款后更新订单
	
	public List<OrderdetailsMo> getOrderDetails(int order_id) throws Exception;//获取订单内的商品编号和数量
	
	public void deleteShopCar(ShoppingCarMo shoppingCarMo) throws Exception;//用户购买商品后删除购物车内的该商品
	
	public CommodityMo getCommodity(int comm_id) throws Exception;//获取商品数量和销量
	
	public void updateCommodity(CommodityMo commodityMo) throws Exception;//购买商品后更新商品数量和销量
	
	public ShouHuoInfo_ViewMo getShouHuo(int order_id) throws Exception;//获取订单的收货信息
	
	public List<OrderMo> getOrder(int cust_id) throws Exception;//获取订单信息
	
	public void updateOrderSend(OrderMo orderMo) throws Exception;//更新订单发送
	
	public void updateOrderSendReceive(OrderMo orderMo) throws Exception;//更新订单发送和接收
	
	public List<Order_ViewMo> getAllOrderInfo(int cust_id) throws Exception;//获取用户所有订单信息
	
	public List<Order_ViewMo> getNoPayOrderInfo(int cust_id) throws Exception;//获取用户未付款订单信息
	
	public List<Order_ViewMo> getNoSendOrderInfo(int cust_id) throws Exception;//获取用户付款未发货订单信息
	
	public List<Order_ViewMo> getNoReceiveOrderInfo(int cust_id) throws Exception;//获取用户付款未收货订单信息
	
	public List<Order_ViewMo> getNoCommentOrderInfo(int cust_id) throws Exception;//获取用户未评论订单信息
	
	public List<Order_Commodity_ViewMo> getOrderCommodity(int order_id) throws Exception;//获取订单内商品信息
	
	public Order_Details_ViewMo getOrderInfo(int order_id) throws Exception;//获取某一定单信息
	
}
