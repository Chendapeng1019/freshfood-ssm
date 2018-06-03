package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import mo.UserMo;

public class LoginInterceptor implements HandlerInterceptor {

	// 在执行handler之后执行的
	// 做统一异常处理，进行方法执行性能监控，在preHandle中设置一个时间点，在afterCompletion设置一个时间，两个时间点的差就是执行时长
	// 实现系统统一日志记录
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
			throws Exception {
		
	}

	// 在执行handler返回modelAndView之前执行
	// 如果需要像页面提供一些公用的数据或配置一些视图信息，使用此方法modelAndView入手
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		

	}

	// 在执行handler之前执行的
	// 用于用户认证校验，用户权限校验
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		//得到请求的url
		String url=request.getRequestURI();
		String[] strs=url.split("/");
		
		
		//判断是否是公开地址
		//实际开发中需要公开地址配置在配置文件中
		if(strs[2].equals("home")||strs[2].equals("ajax")||strs[2].equals("front")) {
			//如果是公开地址则放行
			return true;
		}
		
		//判断用户身份在session是否存在
		HttpSession session=request.getSession();
		UserMo userMo=(UserMo) session.getAttribute("loginer");
		if(userMo!=null) {
			return true;
		}
		
		//执行到这里拦截，跳转到登陆页面，用户进行身份认证
		request.getRequestDispatcher("/front/NoLogin.jsp?").forward(request, response);;
		return false;
	}

}
