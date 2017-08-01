package com.atguigu.bookstore.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.dao.BookMapper;
import com.atguigu.bookstore.service.CreateExcelService;


@Service
public class CreateExcelServiceImpl implements CreateExcelService {
	@Autowired
	private BookMapper bookMapper;

	
	@Override
	public String createExcel(String path,String fileName) throws Exception {
		// String path = "D:\\testexcel\\";
		File pathFile = new File(path);
		// 不存在就创建
		if(!pathFile.exists()){
			pathFile.mkdirs();
		}
		// 文件路径
		String filePath = path + fileName;
		
		// 0、创建工作簿
		Workbook wb = new HSSFWorkbook();
		CreationHelper createHelper = wb.getCreationHelper();
		// 1、创建工作表
		// 1.1、根据time值获取表名字符串
		String excelName = helpCreateExcelName();
		// 工作表命名易读性非常高
		Sheet sheet = wb.createSheet(excelName);
		
		
		// 2、创建一个行对象
		Row row = sheet.createRow((short)0);
		// 2.1、创建表头-----"积分导入流水","业务发生时间","积分供应商","导入积分","积分兑换比例","兑换手续费率","结算金额","平台利润","结算状态","结算日期","导出状态","导出时间"
		String [] str = {"书名","作者","价格","销量","库存"};
		for (int i = 0; i < str.length; i++) {
			row.createCell(i).setCellValue(
					createHelper.createRichTextString(str[i]));
		}
		
		/**
		 * --------下面代码：作者wangyanrui--------
		 * helpCreateRow 和 helpCreateExcelName
		 */
		// 3、获取List，查询报表返回的值
		 List<Book> list = bookMapper.getBookList();
		for (int i = 1; i < list.size() + 1; i++) {
			// 3.1、遍历List，不要忘了遍历的时候有行对象
			// 行对象：索引从1开始，因为上面的表头占了一行
			row = sheet.createRow(i);

			// 获取到自定义的业务实体
			Book book = list.get(i - 1);
			// 拿到数组
			str = helpCreateRow(book);
			// 3.2 通过循环字符串数组，填充内容行
			for (int j = 0; j < str.length; j++) {
				row.createCell(j).setCellValue(
						createHelper.createRichTextString(str[j]));
			}
		}
		/**
		 * --------上面面代码：作者wangyanrui--------
		 */
		
		
		// 4、输出流
	    FileOutputStream fileOut = new FileOutputStream(filePath);
	    wb.write(fileOut);
	    // 4.1、关闭文件流
	    fileOut.close();
	    
		
		return filePath;
	}
	/**
	 * @方法名: helpCreateRow  
	 * @功能描述: 构建内容行
	 * @param exchangeInfo
	 * @return
	 * @作者 wangyanrui
	 * @日期 2016年10月21日
	 */
	private String[] helpCreateRow(Book book) {
		String[] strs = { book.getTitle(), book.getAuthor(),
				book.getPrice().toString(), book.getSales().toString(),
				book.getStock().toString()
				 };
		return strs;
	}
	
	/**
	 * @方法名: helpCreateExcelName
	 * @功能描述: 设置表名，以当前时间为准
	 * @return
	 * @作者 vory
	 * @日期 2016年10月21日
	 */
	private String helpCreateExcelName() {
		String name = JSON.toJSONStringWithDateFormat(new Date(),
				"yyyy-MM-dd HH时mm分");
		return name;
	}
	
	
	/*@Override
	public String createExcel() throws Exception {
		// TODO Auto-generated method stub
		String path = "D:\\testexcel\\";
		File pathFile = new File(path);
		// 不存在就创建
		if(!pathFile.exists()){
			pathFile.mkdirs();
		}
		// 
		String filePath = path + "workbook.xls";
		
		Workbook wb = new HSSFWorkbook();
		// 输出流
	    FileOutputStream fileOut = new FileOutputStream(filePath);
	    wb.write(fileOut);
	    // 关闭文件流
	    fileOut.close();
	    
		
		return filePath;
	}*/

}
