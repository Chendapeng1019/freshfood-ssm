package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mapper.UserMapper;
import mo.ShoppingNumberMo;
import mo.UserMo;

public class UserDaoImpl implements UserDao {

	// 注入mapper
	@Autowired
	private UserMapper userMapper;

	public int CheckUser(String cust_phone) throws Exception {
		int flag = 0;
		UserMo userMo = userMapper.findUserByPhone(cust_phone);
		if (userMo != null ) {
			flag = 1;
		}
		return flag;
	}

	public int CheckPassword(String cust_phone, String cust_password) throws Exception {
		int flag = 0;
		UserMo userMo = userMapper.findUserByPhone(cust_phone);
		if (userMo.getCust_password().equals(cust_password)) {
			flag = 1;
		}

		return flag;
	}

	public UserMo getUser(String cust_phone) throws Exception {
		UserMo userMo =  userMapper.findUserByPhone(cust_phone);
		
		return userMo;
	}

	
	public int getShoppingNumber(int cust_id) throws Exception {
		int shoppingnumber=0;
		ShoppingNumberMo shoppingNumberMo=userMapper.getShoppingNumber(cust_id);
       if(shoppingNumberMo==null) {
    	   shoppingnumber=0;
       }
       else {
    	   shoppingnumber=shoppingNumberMo.getShoppingnumber().intValue();
       }
		return shoppingnumber;
	}

	
	public void addUser(UserMo userMo) throws Exception {
		userMapper.addUser(userMo);	
	}
	
	
  	

}
