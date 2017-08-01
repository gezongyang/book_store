package com.atguigu.bookstore.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 获取连接以及释放连接的工具类
 * @author HanYanBing
 *
 */
public class JDBCUtils {
	//获取数据源
	private static DataSource dataSource = new ComboPooledDataSource();
	//使用map来保存每个线程对应的连接
	private static Map<Long, Connection> map = new HashMap<Long, Connection>();
	private static ThreadLocal<Connection> local = new ThreadLocal<>();
	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConnection(){
		//当前的线程
		Thread t =  Thread.currentThread();
		//线程的唯一标识
		long id = t.getId();
		System.out.println("当前的线程："+t+"-->标识号："+id);
		Connection connection = null;
		//1、先从map中获取
		//connection = map.get(id);
		connection = local.get();
		//2、判断，第一次获取是没有的。
		if(connection == null){
			try {
				//获取一条新连接
				connection = dataSource.getConnection();
				//保存到map方便下次获取。
				//map.put(id, connection);
				local.set(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		return connection;
	}
	/**
	 * 释放连接
	 * @param conn
	 */
	public static void releaseConnection(){
		//当前的线程
		Thread t =  Thread.currentThread();
		//线程的唯一标识
		long id = t.getId();
		System.out.println("当前的线程："+t+"-->标识号："+id);
		Connection connection = JDBCUtils.getConnection();
		//关闭连接
		try {
			connection.close();
			//移除map中的对应数据。否则map可能会异常庞大
			//map.remove(id);
			local.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
