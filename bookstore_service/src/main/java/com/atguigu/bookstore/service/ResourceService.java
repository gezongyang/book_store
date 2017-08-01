package com.atguigu.bookstore.service;

import com.atguigu.bookstore.beans.Resource;

public interface ResourceService {

	int getMaxResCodeOfResPos(int resPos);
    
	boolean checkResExists(String servletPath);

	Integer getMaxPos();

	int saveSource(Resource res);

	Resource getResourceByServletPath(String servletPath);
}
