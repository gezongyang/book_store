package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bookstore.beans.Cart;
import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.dao.OrderMapper;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.utils.WebUtils;

/**
 * Servlet implementation class OrderClientServlet
 */
@RequestMapping("/guest")
@Controller
public class OrderClientServlet{

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderMapper orderMapper;

	/**
	 * 处理用户结账请求
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/checkout.do")
	protected void checkout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 当前的线程
		Thread t = Thread.currentThread();
		// 线程的唯一标识
		long id = t.getId();

		System.out.println("当前的线程：" + t + "-->标识号：" + id);
		// 0、判断用户是否登陆，如果登陆则进行结账逻辑
		// 否则回到登陆页面提示登陆
		// 从session中获取登陆用户
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		// 用户已经登陆
		// 1、获取购物车
		Cart cart = (Cart) session.getAttribute("cart");
		// 2、获取用户
		// 3、结账。成功返回订单号

		String orderId = null;

		orderId = orderService.checkout(cart, user);
		// 结账成功。提交事务

		// 4、来到显示订单号页面pages/cart/checkout.jsp
		request.setAttribute("orderId", orderId);
		request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(
				request, response);
		/*
		 * else { // 1、用户没登录回到登陆页面提示登陆 request.setAttribute("message",
		 * "此操作需要登陆！请先登陆！");
		 * request.getRequestDispatcher("/pages/user/login.jsp").forward(
		 * request, response); }
		 */

	}

	/**
	 * 处理显示用户所有订单请求
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/orders.do")
	protected void orders(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// super.doPost(request, response);
		// 1、获取用户
		User user = WebUtils.getCurrentUser(request);
		// 2、获取对应的订单
		List<Order> list = orderService.getMy(user);
		// 3、将订单放到域中
		request.setAttribute("orders", list);
		// 4、转到页面显示pages/order/order.jsp
		request.getRequestDispatcher("/pages/order/order.jsp").forward(request,
				response);

	}

	/**
	 * 处理和用户确认收货请求
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/received.do")
	protected void received(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// super.doPost(request, response);
		// 1、拿到订单号
		String oid = request.getParameter("oid");
		// 2、确认收货
		orderService.received(oid);
		// 3、回到订单列表页面
		// orders(request,response);
		response.sendRedirect(request.getContextPath()
				+ "/guest/orders.do");
	}
	
	@RequestMapping("/getDetailsOfOrder.do")
	public void getDetailsOfOrder(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		String oid = request.getParameter("id");
		HttpSession session = request.getSession();
		
		Order order = orderService.getDetailsOfOrder(oid);
		
		session.setAttribute("order", order);
		
		request.getRequestDispatcher("/pages/order/order_details.jsp").forward(request,
				response);
	}
	
	
	@RequestMapping("/getDate.do")
	@ResponseBody
	public String getDate(){
		int count = orderMapper.insertDateToDataBase(LocalDateTime.now().toString());
		return "0";
	}

}
