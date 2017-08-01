package com.atguigu.bookstore.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.atguigu.bookstore.beans.OrderItem;

@Repository
public interface OrderItemMapper {
	/**
	 * 保存某个订单项
	 * @param item
	 */
	void saveItem(@Param("item") OrderItem item);
	
	/**
	 * 批量保存订单项
	 * @param items
	 */
	void saveItems(@Param("items") List<OrderItem> items);
	
	/**
	 * 根据订单号查询订单
	 * @param orderId
	 * @return
	 */
	List<OrderItem> getItems(@Param("orderId") String orderId);
}
