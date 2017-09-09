package com.atguigu.test;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmailTest {
	
	/**
	 * @方法名: sendAttEmail  
	 * @功能描述: 发送带附件的邮件
	 * @作者  
	 * @日期 2016年10月21日
	 */
	@Test
	public void sendAttEmail(){
		/**
		 * 1、通过session创建邮件的配置信息
		 */
		try {
			
		// 配置项参考API，路径/javadocs/index.html
		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.qq.com");
		props.setProperty("mail.transport.protocol", "smtp");
		// 要经过认证
		props.setProperty("mail.smtp.auth", "true");
		// 创建session
		Session session = Session.getInstance(props);
		// 使我们在发送邮件的过程中，看到邮件发送的状态信息
		session.setDebug(true);
		
		/**
		 * 2、创建代表邮件内容的Message对象（JavaMail创建的邮件是基于MIME协议的）
		 */
		Message msg = new MimeMessage(session);
		// 设置发件人
		msg.setFrom(new InternetAddress("767608439@qq.com"));
		// 设置标题
		msg.setSubject("你好，葛宗阳");
		// 设置收件人
		msg.setRecipient(RecipientType.TO, new InternetAddress("767608439@qq.com"));
		//msg.setRecipient(RecipientType.CC, new InternetAddress("ltzhou@atguigu.com"));
		
		// 设置正文
		// 复合的内容，包含附件和正文
		Multipart multipart = new MimeMultipart();
		
		// ① 正文部分
		BodyPart bodyPart = new MimeBodyPart();
		// 正文部分和msg.setContent
		bodyPart.setContent("<span style='color:red;'>乐天</span>，请查收。12345", "text/html;charset=utf-8");
		/*multipart.addBodyPart(bodyPart);*/
		
		// ② 附件部分
		bodyPart = new MimeBodyPart();
		// DataHandler FileDataSource，参数File对象
		/*bodyPart.setDataHandler(new DataHandler(new FileDataSource(new File("D:\\testexcel\\workbook.xls"))));*/
		// 文件名设置bodyPart.setFileName，不然文件名就不你控制的
		/*bodyPart.setFileName("abc.xls");
		multipart.addBodyPart(bodyPart);*/
		
		// 把multipart放入到message对象当中
		/*msg.setContent(multipart);*/
		
		
		/**
		 * 3、创建Transport对象、连接服务器、发送Message、关闭连接
		 */
		//2、通过session得到transport对象  
		Transport tran = session.getTransport();
		//3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。  
		tran.connect("smtp.sina.com", "767608439@qq.com", "274java195GZY$");
		// 获取到message对象中的收件人信息，msg.getAllRecipients()
		tran.sendMessage(msg, msg.getAllRecipients());
		// 关闭连接
		tran.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void sendEmail(){
		/**
		 * 1、通过session创建邮件的配置信息
		 */
		try {
			
		// 配置项参考API，路径/javadocs/index.html
		Properties props = new Properties();
		//你的邮箱服务是否是你的的发送邮箱 
		//例如：你的邮箱服务为：smtp.163.com，而你的发送邮箱就必须是xxxx@163.com;接收邮箱随便
		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.transport.protocol", "smtp");
		// 要经过认证
		props.setProperty("mail.smtp.auth", "true");
		// 创建session
		Session session = Session.getInstance(props);
		// 使我们在发送邮件的过程中，看到邮件发送的状态信息
		session.setDebug(true);
		
		/**
		 * 2、创建代表邮件内容的Message对象（JavaMail创建的邮件是基于MIME协议的）
		 */
		Message msg = new MimeMessage(session);
		// 设置发件人
		msg.setFrom(new InternetAddress("15121875276@163.com"));
		// 设置标题
		msg.setSubject("你好，这是本次的报表");
		// 设置收件人
		//MimeMessage的setRecipients方法设置邮件的收件人，其中Message.RecipientType.TO常量表示收件人类型是邮件接收者，Message.RecipientType.<br>CC常量表示收件人类型是抄送者，Message.RecipientType.BCC常量表示收件人的类型是密送着。
		msg.setRecipient(RecipientType.TO, new InternetAddress("15121875276@163.com"));
		//msg.setRecipient(RecipientType.CC, new InternetAddress("ltzhou@atguigu.com"));
		
		// 设置正文
		// 1.纯文本的
		msg.setText("请查收。");
		// 2.html格式
		msg.setContent("<span style='color:red;'>乐天</span>，请查收。12345", "text/html;charset=utf-8");
		
		
		/**
		 * 3、创建Transport对象、连接服务器、发送Message、关闭连接
		 */
		Transport tran = session.getTransport();
		tran.connect("smtp.163.com", "15121875276@163.com", "gezongyang123");
		// 获取到message对象中的收件人信息，msg.getAllRecipients()
		tran.sendMessage(msg, msg.getAllRecipients());
		// 关闭连接
		tran.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
