package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mo.ShopCar_ViewMo;
import mo.UserMo;
import service.ShopCarService;
import service.UserService;

@Controller
@RequestMapping("/shopcar")
public class ShopCarController {

	@Autowired
	private ShopCarService shopCarService;
	@Autowired
	private UserService userService;

	@RequestMapping("/joinShopCar")
	public String joinShopCar(Model model, HttpSession session, int comm_id, int number, double comm_price)
			throws Exception {
		UserMo userMo = (UserMo) session.getAttribute("loginer");
		shopCarService.JoinShopCar(comm_id, comm_price, userMo.getCust_id(), number);

		ShopCar_ViewMo carMo = new ShopCar_ViewMo();
		carMo = shopCarService.JoinShopCarSuccess(comm_id, userMo.getCust_id());
		int shoppingnumber = userService.getShoppingNumber(userMo.getCust_id());

		session.setAttribute("shoppingnumber", shoppingnumber);
		model.addAttribute("number", number);
		model.addAttribute("carMo", carMo);
		model.addAttribute("carMo", carMo);
		return "/front/jionshopcar.jsp";
	}

	@RequestMapping("/showMyShopCar")
	public String showMyShopCar(Model model, HttpSession session) throws Exception {
		List<ShopCar_ViewMo> shopcarList = new ArrayList<ShopCar_ViewMo>();
		UserMo userMo = (UserMo) session.getAttribute("loginer");
		shopcarList = shopCarService.getMyShoppingCar(userMo.getCust_id());
		int shoppingnumber = userService.getShoppingNumber(userMo.getCust_id());
		session.setAttribute("shoppingnumber", shoppingnumber);//刷新时页面数量更改
		model.addAttribute("shopcarList", shopcarList);
		return "/front/shopcar.jsp";
	}

}
