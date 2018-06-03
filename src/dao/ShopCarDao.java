package dao;

import java.util.List;

import mo.ShopCar_ViewMo;
import mo.ShoppingCarMo;

public interface ShopCarDao {

	public ShoppingCarMo CheckShopCar(ShoppingCarMo shoppingCarMo) throws Exception;
	
	public ShoppingCarMo GetShopCar(ShoppingCarMo shoppingCarMo) throws Exception;

	public void AddShopCar(ShoppingCarMo carMo) throws Exception;

	public void UpdateShopCar(ShoppingCarMo carMo) throws Exception;

	public ShopCar_ViewMo JoinSucceed(ShopCar_ViewMo shopCar_ViewMo) throws Exception;

	public List<ShopCar_ViewMo> getMyShoppingCar(int cust_id) throws Exception;

	public void DeleteShopCar(ShoppingCarMo shoppingCarMo) throws Exception;



}
