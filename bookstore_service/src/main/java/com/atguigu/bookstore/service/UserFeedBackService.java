package com.atguigu.bookstore.service;

import java.util.List;
import java.util.Map;

public interface UserFeedBackService {

  int saveFeedbackContent(String username, String content);

   List<Map<String, Object>> getFeedbackByUsername(String username);
  
}
