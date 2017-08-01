package com.atguigu.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bookstore.dao.LogMapper;
import com.atguigu.bookstore.service.LogService;

@Service
public class LogServiceImpl implements LogService{
    
	@Autowired
	private LogMapper logMapper;
	
	@Override
	public void createTable(String tableName) {
		logMapper.createTable(tableName);
	}

	
}
