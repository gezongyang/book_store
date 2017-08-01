package com.atguigu.bookstore.beans;

import java.io.Serializable;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class User implements Serializable,HttpSessionBindingListener{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String username;
	private String password;
	private String email;
	private String role;
	private String codeArray;
	private static Integer totalCount=0;//在线人数

	public User() {
		super();
	}
    
	
	public User(Integer id, String username, String password, String email, String role, String codeArray) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.codeArray = codeArray;
	}


	public User(Integer id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
	public static Integer getTotalCount() {
		return totalCount;
	}

	public static void setTotalCount(Integer totalCount) {
		User.totalCount = totalCount;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", role="
				+ role + ", codeArray=" + codeArray + "]";
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getCodeArray() {
		return codeArray;
	}


	public void setCodeArray(String codeArray) {
		this.codeArray = codeArray;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/**
     * 当用户被绑定在session中时
     */
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
	    //获取被绑定的user对象
		User user =(User) event.getValue();
		LoginUserMap.addUser(user);
		totalCount+=1;
		
	}
    
	/**
	 * 当用户被从session中解绑时
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		User user = (User) event.getValue();
		LoginUserMap.removeUser(user);
		totalCount-=1;
	}

}
