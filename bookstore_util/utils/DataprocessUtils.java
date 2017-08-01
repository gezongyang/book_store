package com.atguigu.bookstore.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataprocessUtils {

	/**
	 * 生成日志表名
	 * @param offset
	 * 	偏移量：以当月为基准的偏移数值
	 * 		-1:上一个月
	 * 		0:这个月
	 * 		1:下一个月
	 * @return  manager_log_2017_11
	 */
	public static String generateTableName(int offset) {
		
		//2016_12→2017_01
		//2016_01→2015_12
		
		//1.获取当前系统时间的日历对象
		Calendar calendar = Calendar.getInstance();
		
		//2.附加偏移量
		calendar.add(Calendar.MONTH, offset);
		
		//3.获取Date对象
		Date time = calendar.getTime();
		
		//4.格式化日期为字符串
		
		return "manager_log_" + new SimpleDateFormat("yyyy_MM").format(time);
	}
}
