package com.atguigu.bookstore.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 创建订单模型
 * @author lfy
 *
 */
public class Order {
	
	//订单的id
	private String id;
	private String createTime;//创建日期
	private int status = 0;//订单状态
	//0-未发货      1-已发货    2-交易完成（用户确认收货了）
	private Integer userId;//订单关联的用户
	private double totalMoney;//订单总额
	
	
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime.toString();
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Order(String id, String createTime, int status, Integer userId) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.status = status;
		this.userId = userId;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", createTime=" + createTime + ", status="
				+ status + ", userId=" + userId + "]";
	}
	
}
