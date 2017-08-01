package com.atguigu.bookstore.beans;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BBSDB {
	
	/**
	 * 保存内容
	 */
	private static Map<String, String>  map = new LinkedHashMap<>();
	
	public static void save(String username,String content){
		map.put(username, content);
	}

	/**
	 * 获取所有的帖子内容
	 * @return
	 */
	public static Map<String, String> getMap() {
		return map;
	}
	
	

}
