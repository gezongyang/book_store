package com.atguigu.bookstore.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.dao.BookMapper;
import com.atguigu.bookstore.enums.TemplateEnum;
import com.atguigu.bookstore.service.BookService;
@Service
public class BookServiceImpl implements BookService {

	// 创建bookMapper对象
	@Autowired
	private BookMapper bookMapper;
	

	
	@Override
	public void saveBook(Book book) {
		bookMapper.saveBook(book);
	}

	@Override
	public void deleteBook(String bookId) {
		bookMapper.deleteBook(bookId);
	}

	@Override
	public void updateBook(Book book) {
		bookMapper.updateBook(book);
	}

	@Override
	public Book getBookById(String bookId) {
		return bookMapper.getBookById(bookId);
	}

	@Override
	public List<Book> getBookList() {
		return bookMapper.getBookList();
	}
    
	/**
	 * 获取分页的图书信息
	 */
	@Transactional
	@Override
	public Page<Book> getPageBook(String pageNo) {
		Integer totalCount = bookMapper.selectBookTotalCount();
		Page<Book> page = new Page<Book>();
		page.setTotalRecord(totalCount);
		// 设置一个默认值
		int number = 1;
		try {
			number = Integer.parseInt(pageNo);
		} catch (NumberFormatException e) {
		}
	
		List<Book> bookList = bookMapper.selectBookList(number, Page.PAGE_SIZE);
		page.setList(bookList);

		return page;
	}
    
	@Transactional
	@Override
	public Page<Book> getPageBookByPrice(String pageNo, String min, String max) {
		// 设置默认页码
		int number = 1;
		try {
			number = Integer.parseInt(pageNo);
		} catch (NumberFormatException e) {
		}

		Page<Book> page = new Page<Book>();
		page.setPageNo(number);

		// 设置默认传入的价格
		double minPrice = 0;
		double maxPrice = Double.MAX_VALUE;

		try {
			minPrice = Double.parseDouble(min);
		} catch (Exception e) {}

		try {
			maxPrice = Double.parseDouble(max);
		} catch (Exception e) {}
		
		//查询在价格范围内的总记录数
		Integer count = bookMapper.selectTotalCountByPrice(minPrice, maxPrice);
		page.setTotalRecord(count);
		List<Book> bookByPrice = bookMapper.getPageBookByPrice((page.getPageNo()-1)*Page.PAGE_SIZE,Page.PAGE_SIZE, minPrice, maxPrice);
		page.setList(bookByPrice); 
		return page;
	}

	@Override
	public String bookInfoImport(InputStream is) {
		// 构造 XSSFWorkbook 对象
		// XSSFWorkbook xwb = new XSSFWorkbook(filePath);
		XSSFWorkbook xwb = null;
		try {
			xwb = new XSSFWorkbook(is);
			// 读取第一章表格内容
			XSSFSheet sheet = xwb.getSheetAt(0);
			// 定义 row、cell
			XSSFRow row;
			String cell;
	        
			//获取当前excel表格的总行数
			int rowTotle = sheet.getPhysicalNumberOfRows();
			//创建集合保存图书信息
			List<Map<String, Object>> list = new ArrayList<>(rowTotle); 

			if (rowTotle <= 0) {
				
				return "一本书都没有";
			}
			// 获取第一行判断模板是否规范
			row = sheet.getRow(sheet.getFirstRowNum());
			// 判断列数是否为 5
			int cellnum = row.getPhysicalNumberOfCells();
			// 模板不规范
			if (cellnum != 5) {
				return "文档格式不匹配";
			}
	        //模板首行验证
			String rowFirstName1 = getCell(row.getCell(0));
			String rowFirstName2 = getCell(row.getCell(1));
			String rowFirstName3 = getCell(row.getCell(2));
			String rowFirstName4 = getCell(row.getCell(3));
			String rowFirstName5 = getCell(row.getCell(4));
			
			if (StringUtils.isEmpty(rowFirstName1) || !TemplateEnum.BOOKTEMPLATE.getTitle().equals(rowFirstName1)) {
				return "文档格式不匹配";
			}
			if (StringUtils.isEmpty(rowFirstName2) || !TemplateEnum.BOOKTEMPLATE.getAuthor().equals(rowFirstName2)) {
				
				return "文档格式不匹配";
			}
			if (StringUtils.isEmpty(rowFirstName3) || !TemplateEnum.BOOKTEMPLATE.getPrice().equals(rowFirstName3)) {
				
				return "文档格式不匹配";
			}
			if (StringUtils.isEmpty(rowFirstName4) || !TemplateEnum.BOOKTEMPLATE.getSales().equals(rowFirstName4)) {

				return "文档格式不匹配";
			}
			if (StringUtils.isEmpty(rowFirstName5) || !TemplateEnum.BOOKTEMPLATE.getStock().equals(rowFirstName5)) {
				return "文档格式不匹配";
			}
			
			for (int i = sheet.getFirstRowNum() + 1; i < rowTotle; i++) {
				Map<String, Object> record = new HashMap<>();
				row = sheet.getRow(i);
					String title = getCell(row.getCell(0)).trim();
					if (!StringUtils.isEmpty(title) ) {					
						record.put("title", title);
					}
					String author = getCell(row.getCell(1)).trim();
					if (!StringUtils.isEmpty(author)) {					
						record.put("author", author);
					}
					String price = getCell(row.getCell(2)).trim();
					if (!StringUtils.isEmpty(price)) {					
						record.put("price", price);
					}
					String sales = getCell(row.getCell(3)).trim();
					if (!StringUtils.isEmpty(sales)) {					
						record.put("sales", sales);
					}
					String stock = getCell(row.getCell(4)).trim();
					if (!StringUtils.isEmpty(stock)) {					
						record.put("stock", stock);
					}
					list.add(record);
					
			}
			//把数据批量存储到数据库中
			bookMapper.batchInsertBooks(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "success";
		
		
	}
	
	

	/**
	 * 判断cell类型，然后读取值
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCell(Cell cell) {
		if (cell == null)
			return "";
		String cellValue = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC://数字
			cellValue = cell.getNumericCellValue() + "";
			cellValue = cellValue.replaceAll("0+?$", "");// 去掉多余的0
			cellValue = cellValue.replaceAll("[.]$", "");// 如最后一位是.则去掉
			break;
		case Cell.CELL_TYPE_STRING://字符串
			cellValue = cell.getStringCellValue();      
            if(cellValue.trim().equals("")||cellValue.trim().length()<=0)      
                cellValue="";      
			break;
		case Cell.CELL_TYPE_FORMULA: //公式 
			cellValue = cell.getCellFormula();
            break;
		case Cell.CELL_TYPE_BLANK:
            cellValue = "";
            break;
		case Cell.CELL_TYPE_BOOLEAN:
            break;
		case Cell.CELL_TYPE_ERROR:
            break;
		default:
			break;
		}
		return cellValue;
	}

}
