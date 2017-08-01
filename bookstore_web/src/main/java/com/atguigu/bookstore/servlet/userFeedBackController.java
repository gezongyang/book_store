package com.atguigu.bookstore.servlet;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atguigu.bookstore.beans.BBSDB;
import com.atguigu.bookstore.service.UserFeedBackService;
@RequestMapping("/guest")
@Controller
public class userFeedBackController {
    
	@Autowired
	private UserFeedBackService userFeedBackService;
	
	        @RequestMapping(value="/publishAdvice.do", method = RequestMethod.POST)
	        public void publishAdvice(HttpServletRequest request,HttpServletResponse response, 
	        		                  Map<String, Object> map) throws Exception{
	        	
	        //1、获取用户以及发帖的内容
			String username = request.getParameter("username");
			String content = request.getParameter("content");
			
			System.out.println("内容："+content);
			//2、保存内容
			int count = userFeedBackService.saveFeedbackContent(username,content);
			//3.获取当前用户的评论列表
			List<Map<String, Object>> list =userFeedBackService.getFeedbackByUsername(username);
			map.put("callback", list);
			
			
			//3、回到首页
			request.getRequestDispatcher("/pages/userFeedBack/feedback.jsp").forward(request, response);
	        }
		
}
