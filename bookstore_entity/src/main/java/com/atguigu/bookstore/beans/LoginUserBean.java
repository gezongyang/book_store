package com.atguigu.bookstore.beans;

import java.util.Date;

public class LoginUserBean {
   private Integer id;
   private String loginUserName;
   private Date loginDate;

   public LoginUserBean() {
	super();
}
   

   public LoginUserBean(Integer id, String loginUserName, Date loginDate) {
	super();
	this.id = id;
	this.loginUserName = loginUserName;
	this.loginDate = loginDate;
}


	public Integer getId() {
		return id;
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getLoginUserName() {
		return loginUserName;
	}
	
	
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	
	
	public Date getLoginDate() {
		return loginDate;
	}
	
	
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
	@Override
	public String toString() {
		return "LoginUserBean [id=" + id + ", loginUserName=" + loginUserName
				+ ", loginDate=" + loginDate + 
				 "]";
	}
    
   
}
