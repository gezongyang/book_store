package com.atguigu.bookstore.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author gzy
 * 最好把登录用户存储在redis中
 *
 */
public class LoginUserMap {
	
	private static Map<String, User> map =new HashMap<>();
	
	/**
	 * 存储登陆用户
	 * @param user
	 */
	public static void addUser(User user){
		map.put(user.getUsername(),user);
	}
	
	/**
	 * 注销用户
	 * @param user
	 */
	public static void removeUser(User user){
		map.remove(user.getUsername());
	}
	
	/**
	 * 获取所有登陆的用户
	 * @return
	 */
	public static List<User> getAll(){
		return new ArrayList<>(map.values());
	}
}
