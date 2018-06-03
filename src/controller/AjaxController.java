package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mapper.ShopCarMapper;
import mo.AreasMo;
import mo.CitiesMo;
import mo.OrderMo;
import mo.ShoppingCarMo;
import mo.UserMo;
import service.OrderService;
import service.ShopCarService;
import service.UserService;

@Controller
@RequestMapping("/ajax")
public class AjaxController {

	@Autowired
	private UserService userService;
	@Autowired
	private ShopCarService shopCarService;
	@Autowired
	private OrderService orderService;

	@RequestMapping("/LoginAjax")
	@ResponseBody
	public String login_ajax(String loginphone) throws Exception {
		String logininfo = null;
		int login = userService.CheckUser(loginphone);
		if (login == 1) {
			logininfo = "OK";
		} else {
			logininfo = "NO";
		}

		return logininfo;
	}

	@RequestMapping("/RegisterAjax")
	@ResponseBody
	public String register_ajax(String registerphone) throws Exception {
		String registerinfo = null;
		int register = userService.CheckUser(registerphone);
		if (register == 1) {
			registerinfo = "OK";
		} else {
			registerinfo = "NO";
		}

		return registerinfo;
	}

	@RequestMapping("/ChangNumber")
	@ResponseBody
	public String changNumber(int comm_number, int comm_id, HttpSession session) throws Exception {
		String flag = null;
		UserMo userMo = (UserMo) session.getAttribute("loginer");
		flag = shopCarService.ChangNumber(userMo.getCust_id(), comm_id, comm_number);
		return flag;
	}

	@RequestMapping("/deleteShopCar")
	@ResponseBody
	public String deleteShopCar(int comm_id, HttpSession session) throws Exception {

		UserMo userMo = (UserMo) session.getAttribute("loginer");
		shopCarService.DeleteShopCar(userMo.getCust_id(), comm_id);
		String flag = "OK";
		return flag;
	}

	@RequestMapping("/getCity")
	@ResponseBody
	public List<CitiesMo> getCity(String provinceid) throws Exception {
		List<CitiesMo> citiesList = new ArrayList<CitiesMo>();
		citiesList = orderService.GetCity(provinceid);
		return citiesList;
	}

	@RequestMapping("/getArea")
	@ResponseBody
	public List<AreasMo> getArea(String cityid) throws Exception {
		List<AreasMo> areasList = new ArrayList<AreasMo>();
		areasList = orderService.GetArea(cityid);
		return areasList;
	}

	@RequestMapping("/setMoRen")
	@ResponseBody
	public String setMoRen(int rece_id, HttpSession session) throws Exception {
		UserMo userMo = (UserMo) session.getAttribute("loginer");
		orderService.SetMoRen(rece_id, userMo.getCust_id());
		String flag = "OK";
		return flag;
	}

	@RequestMapping("/DeleteReceiveInfo")
	@ResponseBody
	public String deleteReceiveInfo(String rece_id) throws Exception {
		int Rece_id=Integer.parseInt(rece_id);
		orderService.DeleteReceiveInfo(Rece_id);
		String flag = "OK";
		return flag;
	}

	@RequestMapping("/SaveReceiveInfo")
	@ResponseBody
	public String saveReceiveInfo(int order_id, String rece_name, String rece_tel, String rece_add) throws Exception {
		OrderMo orderMo = new OrderMo();
		orderMo.setOrder_id(order_id);
		orderMo.setRece_name(rece_name);
		orderMo.setRece_tel(rece_tel);
		orderMo.setRece_add(rece_add);
		orderService.UpdateOrderReceive(orderMo);
		String flag = "OK";
		return flag;
	}
	
	@RequestMapping("/SavePayMethod")
	@ResponseBody
	public String savePayMethod(int order_id,String paymethod) throws Exception{
		
		OrderMo orderMo = new OrderMo();
		orderMo.setOrder_id(order_id);
		orderMo.setPay_method(paymethod);
		orderService.UpdateOrderPayMethod(orderMo);
		String flag = "OK";
		return flag;
	}
}
