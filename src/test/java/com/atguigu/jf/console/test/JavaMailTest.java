package com.atguigu.jf.console.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.junit.Test;


import com.sun.org.apache.bcel.internal.generic.NEW;




public class JavaMailTest {

	@Test
	public void sendEmail() throws MessagingException {
		
		//通过session创建邮件的配置信息，
		
	Properties properties  = new Properties();
	
	//配置一些发送前的信息
		properties.setProperty("mail.host", "smtp.sina.com");
		properties.setProperty("mail.transport.protocol", "smtp");
	
	//开启认证
		properties.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties);
		
	//调试完毕后，需要注释掉
	session.setDebug(true);
	//2.创建代表邮件内容的message
	MimeMessage message = new MimeMessage(session);
	//发件人
	message.setFrom(new InternetAddress("kqj0717@sina.com"));
	//设置主题
	message.setSubject("测试发送邮件功能！");
	//设置收件人
	message.setRecipient(RecipientType.TO, new InternetAddress("CN_HLJ_AZP@163.com"));
	// 设置正文
	// 纯文本的邮件
	message.setText("这是在班里测试的发送邮件的功能");	
	/**
	 * 3、创建Transport对象、连接服务器、发送Message、关闭连接
	 */
	
	/*//附件
	MultipartFile multipartFile
	MimeBodyPart bodyPart = new MimeBodyPart();*/
	//设置附件的正文

	Transport tran = session.getTransport();
	tran.connect("smtp.sina.com", "kqj0717@sina.com", "2016071718");
	tran.sendMessage(message, message.getAllRecipients());
	// 关闭连接
	tran.close();
		
	}

	//测试带附件的邮件
	@Test
	public void sendEmail2() throws MessagingException {
		
		//通过session创建邮件的配置信息，
		
	Properties properties  = new Properties();
	
	//配置一些发送前的信息
		properties.setProperty("mail.host", "smtp.sina.com");
		properties.setProperty("mail.transport.protocol", "smtp");
	
	//开启认证
		properties.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties);
		
	//调试完毕后，需要注释掉
	session.setDebug(true);
	//2.创建代表邮件内容的message
	MimeMessage message = new MimeMessage(session);
	//发件人
	message.setFrom(new InternetAddress("kqj0717@sina.com"));
	//设置主题
	message.setSubject("测试发送邮件功能！");
	//设置收件人
	message.setRecipient(RecipientType.TO, new InternetAddress("CN_HLJ_AZP@163.com"));
	// 设置正文
	// 纯文本的邮件
	message.setText("这是在班里测试的发送邮件的功能");	
	/**
	 * 3、创建Transport对象、连接服务器、发送Message、关闭连接
	 */
	
	//包含文本和附件
	MimeMultipart multipart = new MimeMultipart();
	MimeBodyPart bodyPart = new MimeBodyPart();
	bodyPart.setDataHandler(new DataHandler(new FileDataSource(new File("C:\\testExcel\\Import_Log_2016_12_03_16_49_14.xls"))));

	// 定义文件的名称
	bodyPart.setFileName("import_Log_test.xls");
	multipart.addBodyPart(bodyPart);
			
	// 放入MimeMultipart对象
	message.setContent(multipart);
	
	Transport tran = session.getTransport();
	tran.connect("smtp.sina.com", "kqj0717@sina.com", "2016071718");
	tran.sendMessage(message, message.getAllRecipients());
	// 关闭连接
	tran.close();
		
	}
}
