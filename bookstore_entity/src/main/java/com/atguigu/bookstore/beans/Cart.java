package com.atguigu.bookstore.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车模型
 * 
 * @author lfy
 * 
 */
public class Cart {

	// 保存所有的购物项
	private Map<Integer, CartItem> map = new LinkedHashMap<>();

	// 总数量-需要计算
	private int totalCount;

	// 总金额-需要计算
	private double totalMoney;

	public Cart() {
		super();
	}
	/**
	 * 计算总数量
	 * 
	 * @return
	 */
	public int getTotalCount() {
		List<CartItem> items = getAllItems();
		int count = 0;
		for (CartItem cartItem : items) {
			count += cartItem.getCount();
		}
		return count;
	}

	/**
	 * 计算总金额
	 * 
	 * @return
	 */
	public double getTotalMoney() {
		List<CartItem> items = getAllItems();
		BigDecimal money = new BigDecimal("0.0");
		for (CartItem cartItem : items) {
			//money += cartItem.getTotalPrice();
			money = money.add(new BigDecimal(cartItem.getTotalPrice()+""));
		}
		return money.doubleValue();
	}

	// ===============购物车操作方法=========================
	/**
	 * 将图书添加到购物车
	 * 
	 * @param book
	 */
	public void addBook2Cart(Book book) {
		// 1、将图书作为购物项保存在map中
		// /map.put(book.getId(), book);
		// 2、保存在map中
		// 3、判断，如果购物车中已经存在了这个购物项，只需要数量加1即可
		// 如果不存在才是保存操作
		CartItem item = map.get(book.getId());
		if (item == null) {
			// 还没有这个购物项
			item = new CartItem();
			item.setBook(book);
			item.setCount(1);
			map.put(book.getId(), item);
		} else {
			// 已经有了这个购物项，数量+1
			item.setCount(item.getCount() + 1);
		}
	}

	/**
	 * 删除某个购物项
	 * 
	 * @param bookId
	 */
	public void deleteItem(String bookId) {
		int bid = 0;
		try {
			bid = Integer.parseInt(bookId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		map.remove(bid);
	}

	/**
	 * 修改某个购物项
	 * 
	 * @param book
	 * @param count
	 */
	public void updateItem(String bookId, String count) {
		
		int bid = 0;
		try {
			bid = Integer.parseInt(bookId);
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}
		
		//按照id获取到要修改购物项
		CartItem item = map.get(bid);
		int c = item.getCount();
		try {
			//数量转化失败应该是默认值
			c = Integer.parseInt(count);
			if(c<1){
				c = item.getCount();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		item.setCount(c);
	}

	/**
	 * 清空购物车
	 */
	public void clear() {
		// 将map中的内容清空
		map.clear();
	}

	/**
	 * 获取所有购物项
	 * 
	 * @return
	 */
	public List<CartItem> getAllItems() {
		// 获取到所有的购物项
		Collection<CartItem> values = map.values();
		// 将其转为List
		return new ArrayList<>(values);
	}

}
