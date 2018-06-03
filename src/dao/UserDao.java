package dao;

import mo.UserMo;

public interface UserDao {

	public int CheckUser(String cust_phone) throws Exception;

	public int CheckPassword(String cust_phone, String cust_password) throws Exception;

	public UserMo getUser(String cust_phone) throws Exception;

	public int getShoppingNumber(int cust_id) throws Exception;

	public void addUser(UserMo userMo) throws Exception;

}
