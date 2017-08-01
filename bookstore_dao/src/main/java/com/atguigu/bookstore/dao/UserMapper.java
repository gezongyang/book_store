package com.atguigu.bookstore.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.atguigu.bookstore.beans.User;

@Repository
public interface UserMapper {

	/**
	 * 通过用户名和密码获取一个对象
	 * 
	 * @param user
	 * @return user不为空：登录成功 user为空：登录失败
	 */
	public User getUser(@Param("user") User user);

	/**
	 * 验证用户输入的用户名是否可用
	 * 
	 * @param user
	 * @return true：可用 false：不可用
	 */
	public boolean checkUsername(@Param("user") User user);

	/**
	 * 保存用户对象
	 * 
	 * @param user
	 * @return 1：保存成功 0：保存失败
	 */
	public void saveUser(User user);
	
	/**
	 * 通过用户名获取用户
	 * @param username
	 * @return
	 */
	public User getUserByUserName(@Param("username") String username);

	/**
	 * 判断用户是否存在
	 * @param userName
	 * @param password
	 */
	public boolean isExist(@Param("userName") String userName,@Param("password") String password);
    
	/**
	 * 根据用户名密码查询用户
	 * @param userName
	 * @param password
	 * @return
	 */
	public User selectUserByUserNameAndPassword(@Param("userName")String userName,@Param("password")String password);
	
	/**
	 * 存储用户的登录信息
	 * @param loginUserName
	 * @param monthlyLoginTime
	 */
	public void saveCurrentUser(@Param("loginUserName") String loginUserName, @Param("tableName") String tableName);
	
	
}
