package com.atguigu.bookstore.enums;

public enum TemplateEnum {

	
	BOOKTEMPLATE("书名","作者","单价","销量","库存");//图书模板
	
	private String title;
	private String author;
	private String price;
	private String sales;
	private String stock;
	
	private TemplateEnum(String title, String author, String price, String sales, String stock) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.sales = sales;
		this.stock = stock;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getPrice() {
		return price;
	}

	public String getSales() {
		return sales;
	}

	public String getStock() {
		return stock;
	}
	
	
	
	
}
