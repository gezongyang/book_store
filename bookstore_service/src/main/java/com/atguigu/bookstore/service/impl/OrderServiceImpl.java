package com.atguigu.bookstore.service.impl;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Cart;
import com.atguigu.bookstore.beans.CartItem;
import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.beans.OrderItem;
import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.dao.BookMapper;
import com.atguigu.bookstore.dao.OrderItemMapper;
import com.atguigu.bookstore.dao.OrderMapper;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.utils.WebUtils;

/**
 * 订单方法实现
 * @author lfy
 *
 */
@Service
public class OrderServiceImpl implements OrderService{
	

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private OrderItemMapper itemMapper;
	
	@Transactional
	@Override
	public String checkout(Cart cart, User user) {
		// TODO Auto-generated method stub
		//当前的线程
		Thread t =  Thread.currentThread();
		//线程的唯一标识
		long id = t.getId();
		System.out.println("当前的线程："+t+"-->标识号："+id);
		
		//1、先要将购物车信息转化为对应的订单
		//获取订单号
		String orderId = WebUtils.createOrderId(user);
		Order order = new Order();
		order.setId(orderId);
		order.setCreateTime(LocalDateTime.now());
		order.setStatus(0);
		//设置订单总额
		order.setTotalMoney(cart.getTotalMoney());
		order.setUserId(user.getId());
		//保存订单
		orderMapper.saveOrder(order);
		
		//2、再讲购物车中每一个购物项转化为订单项。每一个购物项最终都要保存成订单项
		List<CartItem> items = cart.getAllItems();
		
		//将要保存的订单项的集合
		List<OrderItem> orderItems = new ArrayList<>();
		for (CartItem cartItem : items) {
			//取出每一个购物项，将其转为订单项
			Book book = cartItem.getBook();
			
			
			//创建OrderItem对象
			OrderItem orderItem = new OrderItem();
			orderItem.setAuthor(book.getAuthor());//图书的作者
			orderItem.setCount(cartItem.getCount());//当前项购买的数量
			orderItem.setOrderId(orderId);//设置关联的订单
			orderItem.setPrice(book.getPrice());//图书的价格
			orderItem.setTitle(book.getTitle());//图书的名字
			orderItem.setTotalPrice(cartItem.getTotalPrice());//获取当前项的金额
			//将要保存的订单项的集合
			orderItems.add(orderItem);
			//保存到数据库
			//将订单项的保存转为批量处理操作
			//itemMapper.save(orderItem);
			//3、修改每本图书对应的库存和销量  bookMapper
			//当前的销量加上购买的数量
			book.setSales(book.getSales()+cartItem.getCount());
			book.setStock(book.getStock()-cartItem.getCount());
			//4、保存修改后的图书
			bookMapper.updateSalesAndStock(book);
		}
		
		//批量保存订单项
		itemMapper.saveItems(orderItems);
		//5、返回订单号
		return orderId;
	}

	@Override
	public List<Order> getAll() {
		// TODO Auto-generated method stub
		return orderMapper.getAllOrders();
	}

	@Override
	public List<Order> getMy(User user) {
		// TODO Auto-generated method stub
		return orderMapper.getMyOrders(user);
	}

	@Override
	public void send(String orderId) {
		// TODO Auto-generated method stub
		Order order = new Order();
		order.setId(orderId);
		//设置为发货状态
		order.setStatus(1);
		orderMapper.updateStatus(order);
	}

	@Override
	public void received(String orderId) {
		// TODO Auto-generated method stub
		Order order = new Order();
		order.setId(orderId);
		//设置订单状态为交易完成
		order.setStatus(2);
		orderMapper.updateStatus(order);
	}

	@Override
	public Order getDetailsOfOrder(String oid) {
		return orderMapper.selectOrderByOrderid(oid);
	}

}
