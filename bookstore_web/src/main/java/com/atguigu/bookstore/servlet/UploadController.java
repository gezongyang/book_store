package com.atguigu.bookstore.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.bookstore.utils.FileUploadUtils;

@RequestMapping("/guest")
@Controller
public class UploadController {
    
	@RequestMapping(value="/uploadImg.do",method=RequestMethod.POST)
	public void uploadCover(HttpServletRequest request, 
			                  HttpServletResponse response, 
			                  @RequestParam("photo") MultipartFile coverFile
			                  ) throws Exception{
		
		HttpSession session = request.getSession();
		String url = FileUploadUtils.upload(coverFile, new String[]{FileUploadUtils.getTimeStamp()});
		
		if (url != null && url !="") {
			session.setAttribute("url", url);
		}
	
		response.sendRedirect(request.getContextPath()+ "/pages/manager/book_edit.jsp");
	}
}
