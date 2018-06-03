package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mo.JieSuan_ViewMo;

import mo.Order_Details_ViewMo;
import mo.Order_ViewMo;
import mo.ProvincesMo;
import mo.Receive_InfoMo;
import mo.ShouHuoInfo_ViewMo;
import mo.UserMo;
import service.OrderService;
import service.UserService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;

	@RequestMapping("/JoinOrder")
	public String joinOrder(HttpSession session, int[] comm_id) throws Exception {
		UserMo userMo = (UserMo) session.getAttribute("loginer");
		int order_id = orderService.JoinOrder(userMo.getCust_id());
		orderService.JoinOrderDetails(userMo.getCust_id(), order_id, comm_id);
		return "redirect:/order/JieSuan.action?order_id=" + order_id;
	}

	@RequestMapping("/JieSuan")
	public String jieSuan(Model model, int order_id, HttpSession session) throws Exception {
		UserMo userMo = (UserMo) session.getAttribute("loginer");
		List<JieSuan_ViewMo> orderList = new ArrayList<JieSuan_ViewMo>();
		List<Receive_InfoMo> userAddsList = new ArrayList<Receive_InfoMo>();
		List<ProvincesMo> provincesList = new ArrayList<ProvincesMo>();
		double totalprice = 0;

		orderList = orderService.GetOrderList(order_id);
		userAddsList = orderService.GetUserAddList(userMo.getCust_id());
		provincesList = orderService.GetProvinces();
		totalprice = orderService.CountTotalPrice(orderList);

		model.addAttribute("orderList", orderList);
		model.addAttribute("userAddsList", userAddsList);
		model.addAttribute("provincesList", provincesList);
		model.addAttribute("order_id", order_id);
		model.addAttribute("totalprice", totalprice);
		return "/front/pay.jsp";
	}

	@RequestMapping("/addReceive")
	public String addReceive(int order_id, int cust_id, String receiveName, String receiveTel, String sheng, String shi,
			String xianqu, String add) throws Exception {
		Receive_InfoMo receive_InfoMo = new Receive_InfoMo();
		receive_InfoMo.setCust_id(cust_id);
		receive_InfoMo.setRece_name(receiveName);
		receive_InfoMo.setRece_tel(receiveTel);
		receive_InfoMo.setXianqu(xianqu);
		receive_InfoMo.setRece_add(add);
		orderService.AddReceive(receive_InfoMo, sheng, shi);

		return "redirect:/order/JieSuan.action?order_id=" + order_id;
	}

	@RequestMapping("/Pay")
	public String pay(Model model, HttpSession session, int out_trade_no) throws Exception {
       
		UserMo userMo = (UserMo) session.getAttribute("loginer");
		int order_id=out_trade_no;
		String pay_no="15079011662";
		ShouHuoInfo_ViewMo paysuccess=new ShouHuoInfo_ViewMo();
		paysuccess=orderService.Pay(order_id, pay_no, userMo.getCust_id());
		 
		int shoppingnumber = userService.getShoppingNumber(userMo.getCust_id());
		session.setAttribute("shoppingnumber", shoppingnumber);
		model.addAttribute("paysuccess",paysuccess);
		return "/front/success.jsp";
	}
	
	
  @RequestMapping("/AllOrder")
  public String allOrder(Model model,HttpSession session) throws Exception{
	     List<Order_ViewMo> allOrderInfo=new ArrayList<Order_ViewMo>();
		 List<Order_ViewMo> noPayOrderInfo=new ArrayList<Order_ViewMo>();
		 List<Order_ViewMo> noSendOrderInfo=new ArrayList<Order_ViewMo>();
		 List<Order_ViewMo> noReceiveOrderInfo=new ArrayList<Order_ViewMo>();
		 List<Order_ViewMo> noCommentOrderInfo=new ArrayList<Order_ViewMo>();  
		 
		 UserMo userMo = (UserMo) session.getAttribute("loginer");
		 orderService.UpdateUserOrder(userMo.getCust_id());
		 allOrderInfo=orderService.OrderInfo(userMo.getCust_id(),"allOrder");
		 noPayOrderInfo=orderService.OrderInfo(userMo.getCust_id(),"noPayOrder");
		 noSendOrderInfo=orderService.OrderInfo(userMo.getCust_id(),"noSendOrder");
		 noReceiveOrderInfo=orderService.OrderInfo(userMo.getCust_id(),"noReceiveOrder");
		 noCommentOrderInfo=orderService.OrderInfo(userMo.getCust_id(),"noCommentOrder");
		 
		 model.addAttribute("allOrderInfo", allOrderInfo);
		 model.addAttribute("noPayOrderInfo", noPayOrderInfo);
		 model.addAttribute("noSendOrderInfo", noSendOrderInfo);
		 model.addAttribute("noReceiveOrderInfo", noReceiveOrderInfo);
		 model.addAttribute("noCommentOrderInfo", noCommentOrderInfo);
		 return "/front/order.jsp"; 
  }
  
  @RequestMapping("/OrderInfo")
  public String orderInfo(Model model,int order_id) throws Exception{
	  Order_Details_ViewMo orderInfo=new Order_Details_ViewMo();
	  orderInfo=orderService.GetOrderInfo(order_id);
	  model.addAttribute("orderInfo", orderInfo);
	  return "/front/orderinfo.jsp";
  }
}
