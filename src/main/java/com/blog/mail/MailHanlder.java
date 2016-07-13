package com.blog.mail;

import java.io.File;
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
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailHanlder {

	public static final String MAIL_HOST = "mail.host";
	public static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
	public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";

	public static final String MAIL_USER = "subscriber.mail.user";
	public static final String MAIL_PASSWORD = "subscriber.mail.password";

	private Session session;

	private MailMessage mailMessage;
	private Properties config;

	public MailHanlder(Properties config) {
		try {
			this.config = config;
			Properties prop = new Properties();
			prop.setProperty(MAIL_HOST,
					config.getProperty("subscriber.mail.stmp"));
			prop.setProperty(MAIL_TRANSPORT_PROTOCOL, "smtp");
			prop.setProperty(MAIL_SMTP_AUTH, "true");

			session = Session.getInstance(prop);

			session.setDebug(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MailMessage getMailMessage() {
		return mailMessage;
	}

	public void setMailMessage(MailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public MimeMessage createMixMail() {

		MimeMessage message = new MimeMessage(session);
		try {

			message.setSubject(mailMessage.getTitle());
			message.setFrom(mailMessage.getSender());

			// new Address[] {
			// // 参数1：邮箱地址，参数2：姓名（在客户端收件只显示姓名，而不显示邮件地址），参数3：姓名中文字符串编码
			// // new InternetAddress(recipient, "utf-8"),
			// }

			List<Address> toAddrss = mailMessage.getRecipientsAddress();
			Address[] to = new Address[toAddrss.size()];
			toAddrss.toArray(to);

			message.setRecipients(RecipientType.TO, to);

			MimeBodyPart mbp = new MimeBodyPart();
			MimeMultipart mmp = new MimeMultipart("related");

			mbp.setContent(mailMessage.getContent(), "text/html;charset=UTF-8");
			Map<String, String> images = mailMessage.getImages();
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
		}
		return message;
	}

	public void sendMessage() {
		try {
			Transport ts = session.getTransport();
			ts.connect(config.getProperty(MAIL_HOST),
					config.getProperty(MAIL_USER),
					config.getProperty(MAIL_PASSWORD));
			Message message = createMixMail();
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
