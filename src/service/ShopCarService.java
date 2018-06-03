package service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dao.ShopCarDao;
import mo.ShopCar_ViewMo;
import mo.ShoppingCarMo;

@Controller
public class ShopCarService {
 
	@Autowired
	private ShopCarDao shopCarDao;

	public void JoinShopCar(int comm_id, double comm_price, int cust_id, int number) throws Exception{
		ShoppingCarMo shoppingCarMo=new ShoppingCarMo();
		shoppingCarMo.setComm_id(comm_id);
		shoppingCarMo.setCust_id(cust_id);
		ShoppingCarMo carMo=shopCarDao.CheckShopCar(shoppingCarMo);
		if (carMo == null) {
			carMo= new ShoppingCarMo();
			carMo.setComm_id(comm_id);
			carMo.setComm_price(comm_price);
			carMo.setCust_id(cust_id);
			carMo.setComm_number(number);
			carMo.setJoin_time(new Timestamp(new Date().getTime()));
			shopCarDao.AddShopCar(carMo);

		} else {
			int comm_number = carMo.getComm_number() + number;
			carMo.setComm_number(comm_number);
			shopCarDao.UpdateShopCar(carMo);
		}
		
	}


	public ShopCar_ViewMo JoinShopCarSuccess(int comm_id, int cust_id)  throws Exception{
		ShopCar_ViewMo shopCar_ViewMo=new ShopCar_ViewMo();
		shopCar_ViewMo.setComm_id(comm_id);
		shopCar_ViewMo.setCust_id(cust_id);
		return shopCarDao.JoinSucceed(shopCar_ViewMo);
	}


	public List<ShopCar_ViewMo> getMyShoppingCar(int cust_id) throws Exception{
		
		return shopCarDao.getMyShoppingCar(cust_id);
	}


	public String ChangNumber(int cust_id, int comm_id, int comm_number) throws Exception{
		ShoppingCarMo shoppingCarMo=new ShoppingCarMo();
		shoppingCarMo.setComm_id(comm_id);
		shoppingCarMo.setCust_id(cust_id);
		ShoppingCarMo carMo=shopCarDao.GetShopCar(shoppingCarMo);
		carMo.setComm_number(comm_number);
		shopCarDao.UpdateShopCar(carMo);
		String flag="OK";
		return flag;
	}


	public void DeleteShopCar(int cust_id, int comm_id) throws Exception{
		ShoppingCarMo shoppingCarMo=new ShoppingCarMo();
		shoppingCarMo.setComm_id(comm_id);
		shoppingCarMo.setCust_id(cust_id);
		shopCarDao.DeleteShopCar(shoppingCarMo);
	}
	
}
