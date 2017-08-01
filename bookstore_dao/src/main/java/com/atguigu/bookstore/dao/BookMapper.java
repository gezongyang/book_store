package com.atguigu.bookstore.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;

@Repository
public interface BookMapper {

	/**
	 * 向数据库中插入一本图书
	 * 
	 * @param book
	 */
	public void saveBook(@Param("book") Book book);

	/**
	 * 根据图书的ID删除一本图书
	 * 
	 * @param bookId
	 */
	public void deleteBook(@Param("bookId") String bookId);

	/**
	 * 更新一本图书
	 * 
	 * @param bookId
	 */
	public void updateBook(@Param("book") Book book);

	/**
	 * 根据图书的ID查询一本图书
	 * 
	 * @param bookId
	 * @return
	 */
	public Book getBookById(@Param("bookId") String bookId);

	/**
	 * 获取所有的图书
	 * 
	 * @return
	 */
	public List<Book> getBookList();

	/**
	 * 传入一个Page<Book>对象，经过加工后再返回Page<Book>对象 注意：需要先设置总记录数然后再设置List集合
	 * 
	 * @param pageNo
	 * @return
	 */
	public Page<Book> getPageBook(@Param("page") Page<Book> page);

	/**
	 * 根据图书价格范围查询图书
	 * 
	 * @param book
	 * @param min
	 * @param max
	 * @return
	 */
	public List<Book> getPageBookByPrice(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("min") double min, @Param("max") double max);
	
	/**
	 * 修改库存和销量
	 * book：
	 * 	id：根据id修改
	 * 	stock：最新的库存
	 *  sales：最新的销量
	 */
	void updateSalesAndStock(@Param("book")Book book);
	
	/**
	 * 查询价格区间内的价格总数量
	 * @param min
	 * @param max
	 * @return
	 */
	Integer selectTotalCountByPrice(@Param("min") double min, @Param("max") double max);
	
	/**
	 * 查询图书总数量
	 * @return
	 */
	Integer selectBookTotalCount();
	
	/**
	 * 查询图书列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Book> selectBookList(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

	
	/**
	 * 批量插入图书信息
	 * @param list
	 */
	public void batchInsertBooks(List<Map<String, Object>> list); 
}
