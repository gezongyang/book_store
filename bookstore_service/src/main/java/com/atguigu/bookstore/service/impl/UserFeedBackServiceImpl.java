package com.atguigu.bookstore.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bookstore.dao.UserFeedBackMapper;
import com.atguigu.bookstore.service.UserFeedBackService;

@Service
public class UserFeedBackServiceImpl implements UserFeedBackService {

	@Autowired
	private UserFeedBackMapper userFeedBackMapper;
	@Override
	public int saveFeedbackContent(String username, String content) {
		return userFeedBackMapper.saveFeedBackContent(username, content);
	}
	
	@Override
	public List<Map<String, Object>> getFeedbackByUsername(String username) {
		return userFeedBackMapper.selectFeedbackOfCurrentUser(username);
	}

}
