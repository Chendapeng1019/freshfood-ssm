package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.dsna.util.images.ValidateCode;
import mo.UserMo;
import service.UserService;

@Controller
// 定义url的根路径，访问时根路径+方法的url
@RequestMapping("/front")
public class LoginRegisterController {

	// 注入service
	@Autowired
	private UserService userService;

	@RequestMapping("/CodeLogin")
	public String codeLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO 自动生成的方法存根
		// 告诉客户端不缓存
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("expires", "0");

		ValidateCode vc = new ValidateCode(140, 42, 4, 20);

		// 向session保存验证码
		HttpSession session = request.getSession();
		session.setAttribute("login_code", vc.getCode());
		vc.write(response.getOutputStream());
		return null;
	}

	@RequestMapping("/CodeRegister")
	public String codeRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO 自动生成的方法存根
		// 告诉客户端不缓存
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("expires", "0");

		ValidateCode vc = new ValidateCode(140, 42, 4, 20);

		// 向session保存验证码
		HttpSession session = request.getSession();
		session.setAttribute("register_code", vc.getCode());
		vc.write(response.getOutputStream());
		return null;
	}
	
	
	@RequestMapping("/Login")
	public String login(Model model,UserMo userMo,HttpServletRequest request,String code) throws Exception{
		String flag;
		HttpSession session=request.getSession();
		String Code=(String) session.getAttribute("login_code");
		int shoppingnumber=0;
		if (code == null || code.trim().equals("")) {
			flag = "CodeNull";
			model.addAttribute("flag", flag);
			return "/front/error.jsp";
		}

		if (!code.equalsIgnoreCase(Code)) {
			flag = "CodeError";
			model.addAttribute("flag", flag);
			return "/front/error.jsp";
		}
		
		flag=userService.login(userMo);
		if(flag.equals("success")) {
			UserMo uMo=userService.getUser(userMo.getCust_phone());
			shoppingnumber=userService.getShoppingNumber(uMo.getCust_id());
			session.setAttribute("loginer", uMo);
			session.setAttribute("shoppingnumber", shoppingnumber);
			
			return "redirect:/index.jsp";
		}
		
		model.addAttribute("flag", flag);
		return "/front/error.jsp";
	}
	
	
	@RequestMapping("/register")
	public String register(Model model,HttpServletRequest request,String username,String userphone,
			String verify_value, String userpassword,	String repassword) throws Exception{
		
		String flag=null;
		if (username == null || username.trim().equals("")) {
			flag = "UserNameIsNull";
			model.addAttribute("flag", flag);
			return "/front/register_info.jsp";
		}

		if (userphone == null || userphone.trim().equals("")) {
			flag = "UserPhoneIsNull";
			model.addAttribute("flag", flag);
			return "/front/register_info.jsp";
		}

		if (verify_value == null || verify_value.trim().equals("")) {
			flag = "CodeIsNull";
			model.addAttribute("flag", flag);
			return "/front/register_info.jsp";
		}
		if (userpassword == null || userpassword.trim().equals("")) {
			flag = "UserPasswordIsNull";
			model.addAttribute("flag", flag);
			return "/front/register_info.jsp";
		}

		if (repassword == null || repassword.trim().equals("")) {
			flag = "RePasswordIsNull";
			model.addAttribute("flag", flag);
			return "/front/register_info.jsp";
		}
		
		HttpSession session=request.getSession();
		String Code = (String) session.getAttribute("register_code");
		
		if (!verify_value.equalsIgnoreCase(Code)) {
			flag = "CodeError";
			model.addAttribute("flag", flag);
			return "/front/register_info.jsp";
		}

		if (!userpassword.equals(repassword)) {
			flag = "PasswordIsDifferent";
			model.addAttribute("flag", flag);
			return "/front/register_info.jsp";
		}
      
		
		int flag1 =userService.CheckUser(userphone);
		if (flag1 == 1) {
			flag = "UserIsExist";
			model.addAttribute("flag", flag);
			return "/front/register_info.jsp";
		}
		
		UserMo userMo=new UserMo();
		userMo.setCust_name(username);
		userMo.setCust_phone(userphone);
		userMo.setCust_password(userpassword);
		userService.addUser(userMo);
		flag = "RegisterOK";
		model.addAttribute("flag", flag);
		return "/front/register_info.jsp";
	}
	
	@RequestMapping("/select_login")
	public String selectLogin(Model model) throws Exception{
		model.addAttribute("select", "login");
		return "/front/register_login.jsp";
	}
	
	@RequestMapping("/select_register")
	public String selectRegister(Model model) throws Exception{
		model.addAttribute("select", "register");
		return "/front/register_login.jsp";
	}
	
	
	@RequestMapping("/exit")
	public String exit(Model model,HttpSession session) throws Exception{
		
		//session 失效
		session.invalidate();
		//重定向到查询页面
		return "redirect:/front/register_login.jsp";
	}

}
