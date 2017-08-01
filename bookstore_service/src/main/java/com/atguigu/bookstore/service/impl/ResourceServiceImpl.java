package com.atguigu.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bookstore.beans.Resource;
import com.atguigu.bookstore.dao.ResourceMapper;
import com.atguigu.bookstore.service.ResourceService;
@Service
public class ResourceServiceImpl implements ResourceService{

	@Autowired
	private ResourceMapper resourceDao;
	
	@Override
	public int getMaxResCodeOfResPos(int resPos) {
       
		return resourceDao.selectMaxResCodeOfResPos(resPos);
	}

	@Override
	public boolean checkResExists(String servletPath) {
       
		return resourceDao.checkResExists(servletPath);
	}

	@Override
	public Integer getMaxPos() {
      
		return resourceDao.selectMaxPos();
	}

	@Override
	public int saveSource(Resource res) {
      
		return resourceDao.insertSource(res);
	}

	@Override
	public Resource getResourceByServletPath(String servletPath) {
       
		return resourceDao.selectResourceByServletPath(servletPath);
	}

}
