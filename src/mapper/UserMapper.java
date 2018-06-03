package mapper;

import java.math.BigDecimal;

import mo.ShoppingNumberMo;
import mo.UserMo;

public interface UserMapper {
  
	public UserMo findUserByPhone(String cust_phone) throws Exception;//通过手机号码查找是否存在用户
	
	public ShoppingNumberMo getShoppingNumber(int cust_id) throws Exception; //查找用户购物车内商品数量
	
	public void addUser(UserMo userMo) throws Exception;//用户注册
	
}
