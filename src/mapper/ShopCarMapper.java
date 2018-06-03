package mapper;

import java.util.List;

import mo.ShopCar_ViewMo;
import mo.ShoppingCarMo;

public interface ShopCarMapper {

	public ShoppingCarMo findShopCar(ShoppingCarMo shoppingCarMo) throws Exception;//查找用户购物车是否存在该商品
	
	public void addShopCar(ShoppingCarMo shoppingCarMo) throws Exception;//购物车添加商品
	
	public void updateShopCar(ShoppingCarMo shoppingCarMo) throws Exception;//更新购物车商品数量
	
	public ShopCar_ViewMo getJoinShopCommodity(ShopCar_ViewMo shopCar_ViewMo) throws Exception;//获得加入购物车商品的信息
	
	public List<ShopCar_ViewMo> getMyShoppingCar(int cust_id) throws Exception;//获取顾客的购物车商品

    public void deleteShoppingCar(ShoppingCarMo shoppingCarMo) throws Exception;//删除购物车内的商品
}
