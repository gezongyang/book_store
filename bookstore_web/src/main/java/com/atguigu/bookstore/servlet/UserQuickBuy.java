package com.atguigu.bookstore.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bookstore.service.UserQuickBuyService;

@Controller
public class UserQuickBuy {
   
	@Autowired
	private UserQuickBuyService userQuickBuyService;
	@RequestMapping("/quickBuy.do")
	@ResponseBody
	public void quickbuy(String userid, String prodid, HttpServletResponse response){
		
		boolean if_success=userQuickBuyService.do_miaosha(userid,prodid);
		try {
			response.getWriter().print(if_success);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
