package com.atguigu.bookstore.patten;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestDecoratePattern extends HttpServletRequestWrapper{

	public RequestDecoratePattern(HttpServletRequest request) {
		super(request);
	}

	/**
	 * 重写getParameter方法
	 */
	public String getParameter(String name){
		//1、判断帖子的内容是否需要过滤
		String result ="";
		if(name.equals("content")){
			//表示获取的是帖子内容
			//获取原来的内容
			result = super.getParameter(name);
			result = result.replaceAll("日你妈", "***");
			System.out.println("内容："+result);
			System.out.println("修改的内容："+result);
		}else{
			result = super.getParameter(name);
		}
		return result;
	}
}
