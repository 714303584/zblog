package com.blog.mail;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class MailMessage {

	
	public static final String CHARSET = "utf-8";
	// 邮件标题
	private String title;

	// 邮件正文
	private String content;

	// 邮件发送者
	private String sender;

	// 发送人列表
	private List<String> recipients = new ArrayList<String>();

	// 抄送列表
	private List<String> copyTos = new ArrayList<String>();

	// 密送列表
	private List<String> blindCarbonCopy = new ArrayList<String>();

	// 正文所带的图片
	private Map<String, String> images = new HashMap<String, String>();

	// 正文所带的附件
	private Map<String, String> appendixs = new HashMap<String, String>();
	
	
	public MailMessage(){
		
	}
	

	 public MailMessage(String title, String content, String sender,
			List<String> recipients, List<String> copyTos,
			List<String> blindCarbonCopy, Map<String, String> images,
			Map<String, String> appendixs) {
		super();
		this.title = title;
		this.content = content;
		this.sender = sender;
		this.recipients = recipients;
		this.copyTos = copyTos;
		this.blindCarbonCopy = blindCarbonCopy;
		this.images = images;
		this.appendixs = appendixs;
	}

	public List<Address> getRecipientsAddress(){
		 Iterator<String> it = recipients.iterator();
		 
		 List<Address> result = new ArrayList<Address>();
		 
		 while (it.hasNext()) {
			try {
				result.add(new InternetAddress(it.next()));
			}  catch (AddressException e) {
				e.printStackTrace();
			}
		}
		 return result;
	 }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}

	public List<String> getCopyTos() {
		return copyTos;
	}

	public void setCopyTos(List<String> copyTos) {
		this.copyTos = copyTos;
	}

	public List<String> getBlindCarbonCopy() {
		return blindCarbonCopy;
	}

	public void setBlindCarbonCopy(List<String> blindCarbonCopy) {
		this.blindCarbonCopy = blindCarbonCopy;
	}

	public Map<String, String> getImages() {
		return images;
	}

	public void setImages(Map<String, String> images) {
		this.images = images;
	}

	public Map<String, String> getAppendixs() {
		return appendixs;
	}

	public void setAppendixs(Map<String, String> appendixs) {
		this.appendixs = appendixs;
	}


}
