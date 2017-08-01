package com.atguigu.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.utils.WebUtils;

/**
 * 客户端浏览图书的Servlet
 */
@RequestMapping("/guest")
@Controller
public class BookClientServlet {

	@Autowired
	private BookService ibookService;

	// 根据价格范围查询图书信息的方法
	@RequestMapping(value="/getPageBookByPrice.do")
	protected void getPageBookByPrice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取页码和价格
		String pageNo = request.getParameter("pageNo");
		String minPrice = request.getParameter("min");
		String maxPrice = request.getParameter("max");
		if(pageNo==null){
			pageNo="1";
		}
		// 查询图书
		Page<Book> bookByPrice = ibookService.getPageBookByPrice(pageNo,
				
				minPrice, maxPrice);
		
		//设置请求路径
		bookByPrice.setPath(WebUtils.getPath(request));
		// 将pageBook放到request域中
		request.setAttribute("page", bookByPrice);
		// 转发到显示图书的页面
		request.getRequestDispatcher("/pages/book/book_list.jsp").forward(request, response);
	}

	// 获取分页图书信息的方法
	@RequestMapping(value="/client/getPageBook.do")
	protected void getPageBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取请求路径
		String path = WebUtils.getPath(request);
		// 获取页码
		String pageNo = request.getParameter("pageNo");
		if(pageNo==null){
			pageNo="1";
		}
		// 查询当前页的图书
		Page<Book> pageBook = ibookService.getPageBook(pageNo);
		// 给pageBook设置请求路径
		pageBook.setPath(path);
		// 将pageBook放到request域中
		request.setAttribute("page", pageBook);
		// 转发到显示图书的页面
		request.getRequestDispatcher("/pages/book/book_list.jsp").forward(request, response);
	}
}
