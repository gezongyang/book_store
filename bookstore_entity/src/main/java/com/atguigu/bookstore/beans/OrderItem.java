package com.atguigu.bookstore.beans;

/**
 * 订单项模型
 * @author lfy
 *
 */
public class OrderItem {
	private Integer id;//订单项的主键
	
	//购买的图书的信息==========
	private String title;//购买的书名
	private String author;//图书的作者
	private double price;//购买的单价
	//====================
	
	private double totalPrice;//某些的购买总价
	private int count;//购买的数量
	
	private String orderId;//关联的订单号

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", title=" + title + ", author="
				+ author + ", price=" + price + ", totalPrice=" + totalPrice
				+ ", count=" + count + ", orderId=" + orderId + "]";
	}
	
	
	
	
}
