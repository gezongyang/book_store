package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.beans.Cart;
import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.beans.User;

public interface OrderService {
	
	/**
	 * 结账方法
	 * @param cart
	 * @param user
	 * @return
	 */
	String checkout(Cart cart,User user);
	
	/**
	 * 查出所有订单-管理使用
	 * @return
	 */
	List<Order> getAll();

	/**
	 * 查出自己的订单
	 * @return
	 */
	List<Order> getMy(User user);
	
	/**
	 * 订单发货
	 * @param orderId
	 */
	void send(String orderId);
	
	/**
	 * 确认收货
	 * @param orderId
	 */
	void received(String orderId);
    
	/**
	 * 查询订单详情
	 * @param oid
	 * @return
	 */
	Order getDetailsOfOrder(String oid);
}
