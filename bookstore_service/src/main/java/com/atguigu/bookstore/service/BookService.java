package com.atguigu.bookstore.service;

import java.io.InputStream;
import java.util.List;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;

public interface BookService {

	/**
	 * 向数据库中插入一本图书
	 * 
	 * @param book
	 */
	public void saveBook(Book book);

	/**
	 * 根据图书的ID删除一本图书
	 * 
	 * @param bookId
	 */
	public void deleteBook(String bookId);

	/**
	 * 更新一本图书
	 * 
	 * @param bookId
	 */
	public void updateBook(Book book);

	/**
	 * 根据图书的ID查询一本图书
	 * 
	 * @param bookId
	 * @return
	 */
	public Book getBookById(String bookId);

	/**
	 * 获取所有的图书
	 * 
	 * @return
	 */
	public List<Book> getBookList();

	/**
	 * 根据传入的页码获取Page<Book>类
	 * 
	 * @param pageNo
	 * @return
	 */
	public Page<Book> getPageBook(String pageNo);

	/**
	 * 根据价格范围查询图书
	 * 
	 * @param pageNo
	 * @param min
	 * @param max
	 * @return
	 */
	public Page<Book> getPageBookByPrice(String pageNo, String min, String max);
    
	/**
	 * 图书信息导入
	 * @param is //图书信息对应的流
	 * @return
	 */
	public String bookInfoImport(InputStream is);
}
