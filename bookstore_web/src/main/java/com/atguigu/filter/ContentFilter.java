package com.atguigu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.atguigu.bookstore.patten.RequestDecoratePattern;

/**
 * Servlet Filter implementation class ContentFilter
 * 内容过滤器
 */
public class ContentFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ContentFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//1、获取用户提交的内容
//		String content = request.getParameter("content");
//		System.out.println("用户提交的内容："+content);
		//2、过滤非法内容
//		String replaceAll = content.replaceAll("宋老师", "雷老师");
		HttpServletRequest req = (HttpServletRequest) request;
		RequestDecoratePattern myRequest = new RequestDecoratePattern(req);
		
		//装饰模式、包装模式
		//request.
		//3、放行请求
		//移魂大法：应该servlet再去获取的参数是filter修改后
		//造一个假的request对象，传递给一个自己创造的request。偷梁换柱。
		//new MyRequest  我们应该创建一个request对象，他是ServletRequest分支下的
		// pass the request along the filter chain
		//传递的request对象，只要是SevrletRequest或者HttpServletRequset的实现类即可
		chain.doFilter(myRequest, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
