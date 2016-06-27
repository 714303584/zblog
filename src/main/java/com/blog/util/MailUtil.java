package com.blog.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailUtil {
	
	public static final String MAIL_HOST = "mail.host";
	public static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
	public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	
	public static final String MAIL_USER  = "subscriber.mail.user";
	public static final String MAIL_PASSWORD = "subscriber.mail.password";
	
	
	public static Session session = null;
	
	private static Properties mailConfProperties ;
	
	
	
	
	
	static {
		
		mailConfProperties = BaseDataCacheUtil.mailProperties;
		Properties prop = new Properties();
		prop.setProperty(MAIL_HOST, mailConfProperties.getProperty("subscriber.mail.stmp"));
		prop.setProperty(MAIL_TRANSPORT_PROTOCOL, "smtp");
		prop.setProperty(MAIL_SMTP_AUTH, "true");
		
		session = Session.getInstance(prop);
		
		session.setDebug(true);
		
		Transport ts;
		try {
			ts = session.getTransport();
			ts.connect("smtp.sina.com","zss19920514@sina.com","123*-&zhu");
			
			
			Message message = createTextMail(session);
			
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} catch (NoSuchProviderException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public static void sendMessage(MimeMessage message){
		try {
			Transport ts = session.getTransport();
			ts.connect(mailConfProperties.getProperty(MAIL_HOST),
					mailConfProperties.getProperty(MAIL_USER),
					mailConfProperties.getProperty(MAIL_PASSWORD));
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void main(String[] args) {
		
		Properties prop = new Properties();
		prop.setProperty("mail.host", "smtp.sina.com");
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		
		
		
		
		
	}
	
	
	
	
	
	public static MimeMessage createTextMail(Session session) throws AddressException, MessagingException{
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("zss19920514@sina.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("714303584@qq.com"));

		message.setSubject("邮件测试");
		
		message.setContent("你好啊", "text/html;charset=UTF-8");
		
		
		return message;
		
	}
	
	
	
	public static MimeMessage createMixMail(Session session , String recipient, String title, String html, Map<String, String> images ,Map<String, String> files){
		
		MimeMessage message = new MimeMessage(session);
		try {
			
			message.setSubject(title);
			message.setFrom("zss19920514@sina.com");
			
			 message.setRecipients(RecipientType.TO,  
		                new Address[] { 
		                // 参数1：邮箱地址，参数2：姓名（在客户端收件只显示姓名，而不显示邮件地址），参数3：姓名中文字符串编码 
		                new InternetAddress(recipient, "utf-8"),
		            } );
			
			
			MimeBodyPart mbp = new MimeBodyPart();
			MimeMultipart mmp = new MimeMultipart("related");
			
			
			
			mbp.setContent(html, "text/html;charset=UTF-8");
			
			
			Iterator<String> it = images.keySet().iterator();
			mmp.addBodyPart(mbp);
			while (it.hasNext()) {
				
				String key = it.next();
				String value = images.get(key);
				MimeBodyPart imagePart = new MimeBodyPart(); 
				DataSource ds = new FileDataSource(new File(value)); 
			    DataHandler dh = new DataHandler(ds); 
			    imagePart.setDataHandler(dh); 
			    imagePart.setContentID(key);
			    mmp.addBodyPart(imagePart);
			}
			
			message.setContent(mmp);
			message.saveChanges();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return message;
	}

}
