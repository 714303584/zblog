package com.blog.util;

import java.io.InputStream;
import java.util.Properties;

public class BaseDataCacheUtil {
	
	public static Properties webSiteProp = null;
	
	public static Properties  mailProperties = null;
	
	
	static {
		
		try {
			ClassLoader loader = BaseDataCacheUtil.class.getClassLoader();
			InputStream in = loader.getResourceAsStream("blog.properties");
			webSiteProp = new Properties();
			webSiteProp.load(in);
			
			
			
			InputStream mail = loader.getResourceAsStream("mail/mail.properties");
			mailProperties = new Properties();
			mailProperties.load(mail);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
