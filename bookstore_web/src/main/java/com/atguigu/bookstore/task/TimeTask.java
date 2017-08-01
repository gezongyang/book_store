package com.atguigu.bookstore.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.atguigu.bookstore.service.LogService;
import com.atguigu.bookstore.utils.DataprocessUtils;

@Component
public class TimeTask {

	@Autowired
	private LogService logService;
	
	/**
	 *  
     * cron = "0 8 18 * * ?"
     *秒    分   时   日  月  年         
     */

	@Scheduled(cron="0 42 11 * * ?")
	public void task(){
		String tableName = DataprocessUtils.generateTableName(0);
		System.out.println("---------------------------------------到时间了");
		logService.createTable(tableName);
	}
}
