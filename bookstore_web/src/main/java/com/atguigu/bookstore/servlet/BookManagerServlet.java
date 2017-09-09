package com.atguigu.bookstore.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.utils.ConfigUtils;
import com.atguigu.bookstore.utils.FileDownLoadUtils;
import com.atguigu.bookstore.utils.MultiThreadDownLoad;
import com.atguigu.bookstore.utils.WebUtils;

/**
 * 管理图书的Servlet
 */
@RequestMapping("/guest")
@Controller
public class BookManagerServlet  {
   
	 private static final Logger logger = LogManager.getLogger(BookManagerServlet.class);
	@Autowired
	private BookService iBookService;
	
	@Autowired
	private FileDownLoadUtils fileDownLoadUtils;
	
	// 分页查询图书信息
	@RequestMapping("/getPageBook.do")
	protected void getPageBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取请求路径
		String path = WebUtils.getPath(request);
		// 获取页码
		String pageNo = request.getParameter("pageNo");
		// 查询当前页的图书
		Page<Book> pageBook = iBookService.getPageBook(pageNo);
		//将请求地址设置到pageBook中
		pageBook.setPath(path);
		// 将pageBook放到request域中
		request.setAttribute("page", pageBook);
		//转发到显示图书的页面
		request.getRequestDispatcher("/pages/manager/book_manager.jsp")
				.forward(request, response);
	}

	// 查询数据库中所有图书的方法
	@RequestMapping("/bookList.do")
	protected void bookList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取数据库中所有的图书
		List<Book> bookList = iBookService.getBookList();
		// 将bookList放到request域中
		request.setAttribute("bookList", bookList);
		// 转发到显示所有图书的页面
		request.getRequestDispatcher("/pages/manager/book_manager.jsp")
		.forward(request, response);
	}
    
	/**
	 * 存储或更新图书
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/saveOrUpdate.do",method=RequestMethod.POST)
	public void  saveOrUpdate( Book book,HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 判断bookId是否为空
		if (null == book.getId()) {
			// 如果bookId为空就证明是添加图书的操作
			iBookService.saveBook(book);
		} else {
			// 如果bookId不为空就证明是更新图书的操作
			iBookService.updateBook(book);
		}
		response.sendRedirect(request.getContextPath()
				+ "/guest/getPageBook.do");
	}

	// 删除图书的方法
	@RequestMapping(value="/deleteBook.do")
	public void deleteBook(HttpServletRequest request,
			HttpServletResponse response,String bookId) throws ServletException, IOException {
		// 获取图书的ID
		//String bookId = request.getParameter("bookId");
		// 通过图书的ID删除图书
		iBookService.deleteBook(bookId);
		// 重定向到manager/BookManagerServlet?method=bookList
		response.sendRedirect(request.getContextPath()
				+ "/guest/getPageBook.do");
	}

	// 编辑图书的方法
	@RequestMapping("/editBook.do")
	public void editBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取图书ID
		String bookId = request.getParameter("bookId");
		// 通过图书ID从数据库中查出该图书信息
		Book bookById = iBookService.getBookById(bookId);
		// 将图书放到request域中
		request.setAttribute("bookById", bookById);
		// 转发到book_edit.jsp页面
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(
				request, response);
	}
	
	/**
	 * 图书模板的下载
	 * @param response
	 */
	@RequestMapping("/templateDownLoad.do")
	public void templateDownLoad(HttpServletResponse response){
		// TODO Auto-generated method stub
			
			    String filename = "template.xlsx";
			    //1、告诉浏览器响应的字符编码
			    response.setCharacterEncoding("utf-8");
				//2、告诉浏览器文件的内容类型，是一个流
				response.setContentType("application/octet-stream");
				//3、告诉浏览器文件的处理方式
				response.setHeader("Content-Disposition", "attachment;filename="+filename);
				//4、将文件流交给浏览器
				try {
					//4.找到要下载的文件的真实路径
					String realPath = new ClassPathResource("/template").getFile().getPath() + File.separator + "template.xlsx";
					System.out.println("下载的路径"+realPath);
					FileInputStream is = new FileInputStream(realPath);
					//4、获取输出流   在Java中调用reponse.getOutputStream()方法会自动激活下载操作
					ServletOutputStream os = response.getOutputStream();
					//5、将流写出去
					IOUtils.copy(is, os);
					//6、关闭流
					is.close();os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
	
	/**
	 * 文件的通用下载方式，支持断点下载
	 * @param request
	 * @param response
	 * @param path
	 */
	@RequestMapping("/fileCommonDownLoad.do")
	public void fileCommonDownLoad(HttpServletRequest request, 
			                       HttpServletResponse response,
			                       @RequestParam("path") String path){
		logger.debug(path);
		String fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
		String realPath = null;
		try {
			realPath = new ClassPathResource("/template").getFile().getPath() + File.separator + fileName;//\bookstore_web\WEB-INF\classes\template\news3.wav
			//realPath = request.getServletContext().getRealPath(path);  这种方式拿到的realPath是项目未编译时的存储位置 \bookstore_web\template\news3.wav
            logger.debug("realPath " + realPath);    
            fileDownLoadUtils.download(fileName, realPath, request, response);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	/**
	 * 把excel表格中的数据导入到数据库中
	 * @param file
	 */
	@RequestMapping("/bookInfoImport.do")
	@ResponseBody
	public String bookInfoImport(@RequestParam("file") CommonsMultipartFile file){
		String result =null;
        try {
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream is=file.getInputStream();
            result = iBookService.bookInfoImport(is);
         
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
	}
	
	/**
	 * 大文本多线程分段下载
	 * @param path
	 * @return
	 */
	@RequestMapping("/maxFileDown.do")
	@ResponseBody
	public String bigFiledownLoad(@RequestParam("fileName") String fileName,HttpServletRequest request){
		MultiThreadDownLoad downLoad = new MultiThreadDownLoad(3,fileName);
		int flag = downLoad.downloadByMultiThread();
		return String.valueOf(flag);
	}
	
}
