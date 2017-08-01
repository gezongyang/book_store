package com.atguigu.bookstore.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFeedBackMapper {
    
	/**
	 * 存储用户评论
	 * @param username
	 * @param content
	 * @return
	 */
	int saveFeedBackContent(@Param("username") String username, @Param("content") String content);

	List<Map<String, Object>> selectFeedbackOfCurrentUser(@Param("username") String username);
}
