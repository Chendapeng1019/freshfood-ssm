package dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mapper.ShopCarMapper;
import mo.ShopCar_ViewMo;
import mo.ShoppingCarMo;

public class ShopCarDaoImpl implements ShopCarDao {

	@Autowired
	private ShopCarMapper shopCarMapper;
	
	public ShoppingCarMo CheckShopCar(ShoppingCarMo shoppingCarMo) throws Exception {
		
		return shopCarMapper.findShopCar(shoppingCarMo);
	}

	
	public ShoppingCarMo GetShopCar(ShoppingCarMo shoppingCarMo) throws Exception {
		
		return shopCarMapper.findShopCar(shoppingCarMo);
	}
	
	
	public void AddShopCar(ShoppingCarMo carMo) throws Exception {
		
		shopCarMapper.addShopCar(carMo);
	}


	
	public void UpdateShopCar(ShoppingCarMo carMo) throws Exception {
		
		shopCarMapper.updateShopCar(carMo);
	}


	
	public ShopCar_ViewMo JoinSucceed(ShopCar_ViewMo shopCar_ViewMo) throws Exception {
		
		return shopCarMapper.getJoinShopCommodity(shopCar_ViewMo);
	}


	
	public List<ShopCar_ViewMo> getMyShoppingCar(int cust_id) throws Exception {
		
		return shopCarMapper.getMyShoppingCar(cust_id);
	}


	
	public void DeleteShopCar(ShoppingCarMo shoppingCarMo) throws Exception {
		
		shopCarMapper.deleteShoppingCar(shoppingCarMo);
	}







	

	
	
}
