package com.ssopay.qywx.mail;

import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/** 
* 简单邮件（不带附件的邮件）发送器 
*/ 
public class SimpleMailSender  {
	private static MailSenderInfo mailInfo = new MailSenderInfo();
	private static ResourceBundle bundle = ResourceBundle.getBundle("mail");
	private final static String HOST = bundle.getString("mail.smtp.host");
	private final static String PORT = bundle.getString("mail.smtp.port");
	private final static String USER = bundle.getString("mail.smtp.user");
	private final static String PASS = bundle.getString("mail.smtp.pass");
	public final static String ADDRESS = bundle.getString("mail.address");
	public final static String SUBJECT = bundle.getString("mail.subject");
	public final static String CONTENT = bundle.getString("mail.content");
	static {
		mailInfo.setMailServerHost(HOST); 
		mailInfo.setMailServerPort(PORT); 
		mailInfo.setValidate(true); 
		mailInfo.setUserName(USER); 
		mailInfo.setPassword(PASS);//您的邮箱密码 
		mailInfo.setFromAddress(USER);
	}
	
	/**
	 * 以文本格式发送邮件 
	 * @param toAddress 接收邮件地址
	 * @param subject 标题
	 * @param content 内容
	 * @return 发送是否成功
	 */
	public static boolean sendTextMail(String toAddress, String subject, String content) {
		mailInfo.setToAddress(toAddress); 
		mailInfo.setSubject(subject); 
		mailInfo.setContent(content); 
		// 判断是否需要身份认证 
		MyAuthenticator authenticator = null; 
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) { 
		// 如果需要身份认证，则创建一个密码验证器 
		authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword()); 
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session 
		Session sendMailSession = Session.getDefaultInstance(pro,authenticator); 
		try { 
		// 根据session创建一个邮件消息 
		Message mailMessage = new MimeMessage(sendMailSession); 
		// 创建邮件发送者地址 
		Address from = new InternetAddress(mailInfo.getFromAddress()); 
		// 设置邮件消息的发送者 
		mailMessage.setFrom(from); 
		// 创建邮件的接收者地址，并设置到邮件消息中 
		Address to = new InternetAddress(mailInfo.getToAddress()); 
		mailMessage.setRecipient(Message.RecipientType.TO,to); 
		// 设置邮件消息的主题 
		mailMessage.setSubject(mailInfo.getSubject()); 
		// 设置邮件消息发送的时间 
		mailMessage.setSentDate(new Date()); 
		// 设置邮件消息的主要内容 
		String mailContent = mailInfo.getContent(); 
		mailMessage.setText(mailContent); 
		// 发送邮件 
		Transport.send(mailMessage);
		return true; 
		} catch (MessagingException ex) { 
			ex.printStackTrace(); 
		} 
		return false; 
	} 
	
	/**
	 * 以HTML格式发送邮件 
	 * @param toAddress 接收邮件地址
	 * @param subject 标题
	 * @param content 内容
	 * @return 发送是否成功
	 */
	public static boolean sendHtmlMail(MailSenderInfo mailInfo){ 
		// 判断是否需要身份认证 
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		//如果需要身份认证，则创建一个密码验证器  
		if (mailInfo.isValidate()) { 
		authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		} 
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session 
		Session sendMailSession = Session.getDefaultInstance(pro,authenticator); 
		try { 
		// 根据session创建一个邮件消息 
		Message mailMessage = new MimeMessage(sendMailSession); 
		// 创建邮件发送者地址 
		Address from = new InternetAddress(mailInfo.getFromAddress()); 
		// 设置邮件消息的发送者 
		mailMessage.setFrom(from); 
		// 创建邮件的接收者地址，并设置到邮件消息中 
		Address to = new InternetAddress(mailInfo.getToAddress()); 
		// Message.RecipientType.TO属性表示接收者的类型为TO 
		mailMessage.setRecipient(Message.RecipientType.TO,to); 
		// 设置邮件消息的主题 
		mailMessage.setSubject(mailInfo.getSubject()); 
		// 设置邮件消息发送的时间 
		mailMessage.setSentDate(new Date()); 
		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象 
		Multipart mainPart = new MimeMultipart(); 
		// 创建一个包含HTML内容的MimeBodyPart 
		BodyPart html = new MimeBodyPart(); 
		// 设置HTML内容 
		html.setContent(mailInfo.getContent(), "text/html; charset=utf-8"); 
		mainPart.addBodyPart(html); 
		// 将MiniMultipart对象设置为邮件内容 
		mailMessage.setContent(mainPart); 
		// 发送邮件 
		Transport.send(mailMessage); 
		return true; 
		} catch (MessagingException ex) { 
			ex.printStackTrace(); 
		} 
		return false; 
	} 
} 

