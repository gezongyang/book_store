package com.atguigu.bookstore.service;

import com.atguigu.bookstore.beans.User;

public interface IJedisService {

	String putUserToRedis(User user);

}
