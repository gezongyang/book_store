package com.atguigu.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.service.IJedisService;
import com.sdonkey.score.util.MD5;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
/**
 * redis 操作类
 * @author gezongyang
 *
 */
@Service
public class JedisServiceImpl implements IJedisService{

 
	@Autowired
	private JedisPool jedisPool;
	 
	 /**
	  * 把用户信息放入redis中
	  * @param user
	  * @return
	  */
	 @Override
	 public String putUserToRedis(User user) {
	        Jedis jedis = null;
	        try {
	            jedis = jedisPool.getResource();
	            jedis.select(1);
	           
	            // 将用户信息加密，存入redis
	            String key = MD5.encode(user.getId().toString()); 
	            System.out.println("redis中存储的用户的key:==========" + key);
	            String username = user.getUsername();
	            String password = user.getPassword();
	           
	            jedis.hset(key, "username", username);
	            jedis.hset(key, "password", password);
	            // 设置过期时间为3天
	            jedis.expire(key, 60 * 60 * 24 * 3);
	            return key;
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        } finally {
	            if (jedis != null) {
	                jedis.close();
	            }
	        }
	    }
}
