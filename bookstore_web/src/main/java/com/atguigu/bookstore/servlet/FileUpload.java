package com.atguigu.bookstore.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.atguigu.bookstore.service.impl.FastDFSFile;
import com.atguigu.bookstore.service.impl.FileManager;

@Controller
public class FileUpload {

	/**
	 * 文件上传
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws MyException
	 */
	@RequestMapping(value = "/uploadFile.do", method = RequestMethod.POST)
	public @ResponseBody String add(@RequestParam("file") CommonsMultipartFile attach, HttpServletRequest request)
	        throws IOException {
	   
	    // 获取文件后缀名 
	    String ext = attach.getOriginalFilename().substring(attach.getOriginalFilename().lastIndexOf(".")+1);
	    FastDFSFile file = new FastDFSFile(attach.getBytes(),ext);
	    NameValuePair[] meta_list = new NameValuePair[4];
	    meta_list[0] = new NameValuePair("fileName", attach.getOriginalFilename());
	    meta_list[1] = new NameValuePair("fileLength", String.valueOf(attach.getSize()));
	    meta_list[2] = new NameValuePair("fileExt", ext);
	    meta_list[3] = new NameValuePair("fileAuthor", "gezongyang");
	    String filePath = FileManager.upload(file,meta_list);
	    System.out.println("文件： " + filePath + "上传成功了！");
	    
	    return "success";
	}
	
	/**
	 * fastDfs文件下载
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws MyException
	 */
	@RequestMapping(value = "/downloadFile.do", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(String filePath,HttpServletResponse response) throws IOException, MyException {

	    String substr = filePath.substring(filePath.indexOf("group"));
	    String group = substr.split("/")[0];
	    String remoteFileName = substr.substring(substr.indexOf("/")+1);
	    //新文件名
	    String specFileName = System.currentTimeMillis() + substr.substring(substr.indexOf("."));
	    return FileManager.download(group, remoteFileName,specFileName);
	}
}
