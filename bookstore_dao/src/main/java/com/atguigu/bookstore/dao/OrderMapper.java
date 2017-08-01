package com.atguigu.bookstore.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.beans.User;

@Repository
public interface OrderMapper {
	/**
	 * 查出所有订单，给管理员用
	 * @return
	 */
	List<Order> getAllOrders();
	
	/**
	 * 查询当前用户自己的订单
	 * @param user
	 * @return
	 */
	List<Order> getMyOrders(@Param("user") User user);
	
	/**
	 * 保存订单
	 * @param order
	 */
	void saveOrder(@Param("order") Order order);
	
	/**
	 * 修改订单的状态
	 * @param order
	 */
	void updateStatus(@Param("order") Order order);
    
	/**
	 * 查询订单详情
	 * @param oid
	 * @return
	 */
	Order selectOrderByOrderid(@Param("oid") String oid);
	
	
	int insertDateToDataBase(@Param("createDate") String createDate);
	
}
