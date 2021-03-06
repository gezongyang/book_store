package com.atguigu.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bookstore.beans.LoginUserMap;
import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.service.IJedisService;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.utils.DataprocessUtils;
import com.atguigu.bookstore.utils.Encrypt;
import com.atguigu.bookstore.vo.UserVo;
import com.google.code.kaptcha.Constants;
import com.sdonkey.score.util.ValidateCodeUtil;

/**
 * 处理用户请求的Servlet
 */
@Controller
public class UserServlet {

	private static final Logger logger = LogManager.getLogger(UserServlet.class);
	
	@Autowired
	private UserService iUserService;
	@Autowired
	private IJedisService iJedisService;
	/**
	 * 用户登陆
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	@ResponseBody
	protected Object login(@RequestBody UserVo userVo,
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String code =userVo.getCode();
		logger.debug("用户输入的验证码========="+code);
		String confirmPassword = Encrypt.md5(userVo.getPassword(), userVo.getUsername());
		HttpSession session = request.getSession();
		String codes  = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		System.out.println("服务器生成 的验证码======"+codes);
		
		//判断验证码
		if (!code.equals(codes)) {
			// 验证码验证失败
			// 将错误消息放到request域中
			request.setAttribute("message", "验证码错误！");
			// 转发到登录页面
			request.getRequestDispatcher("/pages/user/login.jsp").forward(
								request, response);
			return null;
		}
		User user = null;
		try {
			user = iUserService.getUserByUserNameAndPassword(userVo.getUsername(), confirmPassword);
			if(user==null){
				request.setAttribute("errorInfo", "对不起，用户名或密码错误，登录失败");
			}else{
				session.setAttribute("loginUser", user);
				iJedisService.putUserToRedis(user);
				System.out.println("当前登陆用户===================================="+LoginUserMap.getAll());
				System.out.println("当前登录用户的数量================================****"+User.getTotalCount());
				//把当前登陆用户的信息存储在当月的表中
				String tableName = DataprocessUtils.generateTableName(0);
				iUserService.saveLoginUser(user.getUsername(), tableName);

			}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
			    System.out.println(e.getMessage());
				return null;
			}
		        return user;
			
//			response.sendRedirect(request.getContextPath()
//					+ "/pages/user/login_success.jsp");
		
	}
    
	@RequestMapping(value="/regist.do",method=RequestMethod.POST)
	protected void regist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取用户输入的用户名、密码、邮箱
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = Encrypt.md5(password, userName);
		
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		//String 
		//String telphone = request.getParameter("telphone");
		//用户输入的验证码
//		String code = request.getParameter("code");
//		System.out.println("用户输入的验证码：====================="+code);
//		HttpSession session = request.getSession();
//		String tcode = (String) session.getAttribute(telphone);
//		System.out.println("服务器端生成的验证码：====================="+tcode);
		//String code = request.getParameter("code");
//		System.out.println("浏览器带来的验证码..." + code);
//		HttpSession session = request.getSession();
//		String codes = (String) session
//				.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		//验证码使用一次以后移除。如果不移除
		//用户可以拿着一个验证码使用无数次
//		session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
//		System.out.println("服务器获取到的验证码：" + codes);
//		if (!code.equals(tcode)) {
//			// 验证码验证失败
//			// 将错误消息放到request域中
//			request.setAttribute("message", "验证码错误！");
//			 //转发到注册页面
//			request.getRequestDispatcher("/pages/user/regist.jsp").forward(
//								request, response);
//			return ;
//		}
		// 将用户信息封装为User对象
		User user = new User(null, userName, confirmPassword, email ,role,null);
		// 注册用户
		boolean register = iUserService.register(user);
		// 判断是否注册成功
		if (!register) {
			// 将用户保存到数据库
			iUserService.savaUser(user);
			// 重定向到注册成功页面
			response.sendRedirect(request.getContextPath()
					+ "/pages/user/regist_success.jsp");
		} else {
			// 将错误消息放到request域中
			request.setAttribute("message", "用户名已存在！");
			// 转发到注册页面
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(
					request, response);
		}
	}

	/**
	 * 用户注销方法
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/logout.do")
	protected void logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(request, response);
		// 1、从session移除登陆的用户即可
		HttpSession session = request.getSession();
		// 移除登陆的用户
		session.removeAttribute("loginUser");
		
		System.out.println("当前登陆用户======================================"+LoginUserMap.getAll());
		System.out.println("当前登录用户的数量================================****"+User.getTotalCount());
		// 2、来到首页
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	
	
	 /**
     * 发送手机验证码
     *
     * @param mobile  用户手机
     * @param session
     * @return 用户id
     */
//    @RequestMapping(value = "/ajaxGetCodeToMobile.do")
//    @ResponseBody
//    public String ajaxGetCodeToMobile(String mobile, HttpSession session) {
//        
//           int i = ValidateCodeUtil.writeCodeToMobile(mobile, session);
//          
//           if (i == 0) {
//        	   return "1";
//           } else {
//        	   return "0";
//           }
//           
//    }
	
	/**
	 * 用户邮箱验证
	 * @param email
	 * @return
	 */
	@RequestMapping("/ajaxGetCodeToEmail.do")
	@ResponseBody
	public String writrMessageToMail(String email,HttpSession session){
		Integer id = iUserService.hasEmail(email);
        if (id != null) {
            User user = iUserService.getUser(id);
            ValidateCodeUtil.writeCodeToEmail(email, user.getUsername(), session);
            session.setAttribute("sid", id);
            return "1";
        }
        return "-1";
	}
}
