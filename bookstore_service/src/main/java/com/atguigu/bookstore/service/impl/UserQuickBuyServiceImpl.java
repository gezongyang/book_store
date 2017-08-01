package com.atguigu.bookstore.service.impl;


import org.springframework.stereotype.Service;


import com.atguigu.bookstore.service.UserQuickBuyService;

@Service
public class UserQuickBuyServiceImpl implements UserQuickBuyService{

//	@Autowired
//    private JedisPool jedisPool;
	@Override
	public boolean do_miaosha(String uid, String prodid) {
		
//		Jedis jedis = jedisPool.getResource();
//		
//		System.out.println("connection is OK==========>: "+jedis.ping());
//		jedis.watch(prodid);
//		Integer  num   = Integer.parseInt(jedis.get(prodid));
//		try {                                                                                                                                                                                                                                                                                                                               
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		if(num.intValue()==0){
//		    jedis.unwatch();
//			return false;
//		    }else{
//		//获取redis的事务
//		Transaction transaction = jedis.multi();
//		transaction.decr(prodid);
//		transaction.lpush(prodid+"-usr", uid);
//		List list=transaction.exec();
//		         if(list==null){
//		         	    System.out.println("秒杀失败");
//		         }else{
//		              System.out.println(uid+":秒杀成功");
//		         }
//		         jedis.close();
		         return true;
//		    }

	}
}


