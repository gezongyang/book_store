package com.atguigu.bookstore.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.atguigu.bookstore.service.LogService;
/**
 * 创建ioc容器的监听器 父容器spring 的ioc容器先启动，子容器 springmvc的容器后启动
 * @author gzy
 *
 */
public class LogListener implements ApplicationListener<ContextRefreshedEvent>{
    
	@Autowired
	private LogService logService;
	
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		ApplicationContext ioc = event.getApplicationContext();
		ApplicationContext parent = ioc.getParent();
		if (parent==null) {
			//当spring的ioc容器被创建以后，创建当月以及以后三个月的日志表
//			String tableName = DataprocessUtils.generateTableName(0);
//			logService.createTable(tableName);
//			
//			tableName = DataprocessUtils.generateTableName(1);
//			logService.createTable(tableName);
//			
//			tableName = DataprocessUtils.generateTableName(2);
//			logService.createTable(tableName);
//			
//			tableName = DataprocessUtils.generateTableName(3);
//			logService.createTable(tableName);
			
			System.out.println("Spring 的 ioc容器启动了！");
		} else {
			System.out.println("springMVC 的ioc 容器启动了！");
		}
	}

}
