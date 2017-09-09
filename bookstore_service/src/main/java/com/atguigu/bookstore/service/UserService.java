package com.atguigu.bookstore.service;

import com.atguigu.bookstore.beans.User;

public interface UserService {
	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return user：登录成功 null:登录失败
	 */
	public User login(User user);

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return true：注册成功 false：用户名已存在
	 */
	public boolean register(User user);

	/**
	 * 将用户保存到数据库中
	 * 
	 * @param user
	 */
	public void savaUser(User user);

	/**
	 * 根据用户名查询用户
	 * 
	 * @param username
	 * @return
	 */
	User getUserByName(String username);
	
	/**
	 * 验证用户是否存在
	 * @param userName
	 * @param password
	 * @return
	 */
	boolean isExist(String userName,String password);
	
	/**
	 * 根据用户名密码查询用户
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	User getUserByUserNameAndPassword(String userName,String password);
    
	/**
	 * 
	 * 当前登录用户的信息存储在当月的表中
	 * @param username
	 */
	public void saveLoginUser(String username, String tableName);
    
	/**
	 * 判断邮件地址是否存在
	 * @param email
	 * @return
	 */
	public Integer hasEmail(String email);
    
	/**
	 * 查询用户
	 * @param id
	 * @return
	 */
	public User getUser(Integer id);
	
}
