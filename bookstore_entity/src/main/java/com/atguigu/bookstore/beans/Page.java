package com.atguigu.bookstore.beans;

import java.util.List;

public class Page<T> {
	private List<T> list; // 从数据库中查询的集合
	public static final int PAGE_SIZE = 4; // 每页显示多少条记录
	private int pageNo; // 当前页
	private int totalPageNo; // 总页数
	private int totalRecord; // 总记录数
	private String path;// 获取请求地址

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageNo() {
		if (pageNo < 1) {
			return 1;
		} else if (pageNo > getTotalPageNo()) {
			return getTotalPageNo();
		} else {
			return pageNo;
		}
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	// 总页数需要通过总记录数和每页显示的记录数计算后得到
	public int getTotalPageNo() {
		if (getTotalRecord() % PAGE_SIZE == 0) {
			return getTotalRecord() / PAGE_SIZE;
		} else {
			return getTotalRecord() / PAGE_SIZE + 1;
		}
	}

	// public void setTotalPageNo(int totalPageNo) {
	// this.totalPageNo = totalPageNo;
	// }

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	// 判断是否有上一页
	public boolean hasPrev() {
		return pageNo > 1;
	}

	// 获取上一页
	public int getPrev() {
		return hasPrev() ? pageNo - 1 : 1;
	}

	// 判断是否有下一页
	public boolean hasNext() {
		return pageNo < getTotalPageNo();
	}

	// 获取下一页
	public int getNext() {
		return hasNext() ? pageNo + 1 : getTotalPageNo();
	}
}
