package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

/**
 * Servlet implementation class OrderManagerServlet
 */
@RequestMapping("/manager")
@Controller
public class OrderManagerServlet{
	

    @Autowired	
	private OrderService orderService;

	/**
	 * 显示所有订单
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/getOrderList.do")
	protected void all(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1、查出所有订单
		List<Order> all = orderService.getAll();
		// 2、放在域中
		request.setAttribute("orders", all);
		// 3、来到页面显示/pages/manager/order_manager.jsp
		request.getRequestDispatcher("/pages/manager/order_manager.jsp")
				.forward(request, response);

	}
	
	/**
	 * 处理点击发货功能
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/send.do")
	protected void send(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(request, response);
		//1、获取订单号
		String oid = request.getParameter("oid");
		//2、发货
		orderService.send(oid);
		//3、回到列表显示页面
		response.sendRedirect(request.getContextPath()+"/manager/getOrderList.do");
	}

}
