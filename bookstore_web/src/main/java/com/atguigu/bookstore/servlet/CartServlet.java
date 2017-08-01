package com.atguigu.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Cart;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

/**
 * Servlet implementation class CartServlet
 */
@RequestMapping("/guest")
@Controller
public class CartServlet{
    
    @Autowired
    private BookService bookService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 用户将图书加入购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/addCartItem.do")
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1、获取到要加入购物车的图书。用户需要携带图书id过来
		String bookId = request.getParameter("id");
		//从数据库中查出需要加到购物车中的图书
		Book book = bookService.getBookById(bookId);
		
		//2、加入购物车。购物车对象是放在session中
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		//第一次获取cart为null。
		if(cart==null){
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		//把图书加入到购物车
		cart.addBook2Cart(book);
		
		session.setAttribute("lastAdd", book);
		//referer：获取请求的来源
		//借助referer实现哪里来的回哪里去
		String refer = request.getHeader("referer");
		//3、返回页面
		response.sendRedirect(refer);
	}
	
	/**
	 * 处理清空购物车请求
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/clearCart.do")
	protected void clear(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(request, response);
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		//清空购物车
		cart.clear();
		//重定向回到cart.jsp
		response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
	}
	
	/**
	 * 处理用户删除某个购物项的请求
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/deleteCartItem.do")
	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(request, response);
		//1、获取到要删除的图书的id
		String bookId = request.getParameter("id");
		//2、删除这个购物项
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		cart.deleteItem(bookId);
		//3、返回到cart.jsp页面
		response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
		
	}
	
	/**
	 * 修改购物车数量
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/updateCountOfCartItem.do")
	protected void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(request, response);
		//1、获取要修改的图书id
		String id = request.getParameter("id");
		//2、获取最终修改的数量
		String count = request.getParameter("count");
		//3、修改
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		cart.updateItem(id, count);
		//4、返回购物车页面
		response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
	}

}
