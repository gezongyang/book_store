package com.atguigu.bookstore.beans;

import java.math.BigDecimal;

/**
 * 购物项模型
 * @author lfy
 *
 */
public class CartItem {
	
	//购买的图书
	private Book book;
	
	//购买的数量
	private int count;
	
	//购买的总价   需要计算
	private double totalPrice;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 获取总价的时候进行计算
	 * @return
	 */
	public double getTotalPrice() {
		//
		Double price = getBook().getPrice();
		int c= getCount();
		//进行运算
		BigDecimal bigDecimal = new BigDecimal(price.toString()).multiply(new BigDecimal(c));
		//将计算呢后的结果返回为一个double类型
		return bigDecimal.doubleValue();
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public CartItem(Book book, int count, double totalPrice) {
		super();
		this.book = book;
		this.count = count;
		this.totalPrice = totalPrice;
	}

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + ", totalPrice="
				+ totalPrice + "]";
	}
	
	
}
