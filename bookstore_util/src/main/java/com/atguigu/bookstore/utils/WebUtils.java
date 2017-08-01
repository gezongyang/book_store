package com.atguigu.bookstore.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.beans.User;

public class WebUtils {

	public static String getPath(HttpServletRequest request) {
		// BookStore04/manager/BookManagerServlet?
		//method=getPageBook&min=0&max=5&pageNo=3
		String path=null;
		// 获取本次请求地址
		String requestURI = request.getRequestURI();
		StringBuffer requestURL = request.getRequestURL();
		System.out.println("uri："+requestURI);
		System.out.println("url："+requestURL);
		// 获取请求字符串
		String queryString = request.getQueryString();
		System.out.println("查询字符串："+queryString);
		// 判断queryString中是否含有&pageNo
		if(queryString!=null){
			if (queryString.contains("&pageNo")) {
				// 如果含有&pageNo就将它截取
				queryString = queryString.substring(0,
						queryString.indexOf("&pageNo"));
				// 拼接请求地址和请求字符串
				path = requestURI + "?" + queryString;
			}
		}
				
		path = requestURI;
		System.out.println(path);
		return path;
	}
	
	public static String  createOrderId(User user){
		//订单id：
		//时间戳加上用户id
		long millis = System.currentTimeMillis();
		String oid = millis +""+ user.getId();
		return oid;
	}
	
	/**
	 * 返回当前系统用户
	 * @return
	 */
	public static User getCurrentUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		return user;
	}
}
