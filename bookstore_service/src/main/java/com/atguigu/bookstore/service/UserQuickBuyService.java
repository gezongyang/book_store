package com.atguigu.bookstore.service;

public interface UserQuickBuyService {
    
	/**
	 * 秒杀案例
	 * @param uid
	 * @param prodid
	 * @return
	 */
	boolean  do_miaosha(String uid,String prodid);
}
