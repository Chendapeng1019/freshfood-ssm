package service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dao.UserDao;
import mo.UserMo;
@Controller
public class UserService {
   
	@Autowired
	private UserDao userDao;
	
	public String login(UserMo userMo) throws Exception {
		int flag1;
		flag1=userDao.CheckUser(userMo.getCust_phone());
		if (flag1 == 0) {

			return "UserIsNotExist";
		}
		
		flag1 = userDao.CheckPassword(userMo.getCust_phone(), userMo.getCust_password());
		if (flag1 == 0) {

			return "PasswordError";
		}
		return "success";
	}

	public UserMo getUser(String cust_phone) throws Exception {
		
		return userDao.getUser(cust_phone);
	}

	public int getShoppingNumber(int cust_id) throws Exception {
		
		return userDao.getShoppingNumber(cust_id);
	}

	public int CheckUser(String loginphone) throws Exception{
		
		return userDao.CheckUser(loginphone);
	}

	public void addUser(UserMo userMo)  throws Exception{
		
		userDao.addUser( userMo);
	}
 
}
