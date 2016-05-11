package com.blog.util;

import java.io.InputStream;
import java.util.Properties;

public class BaseDataCacheUtil {
	
	public static Properties webSiteProp = null;
	
	
	static {
		
		try {
			ClassLoader loader = BaseDataCacheUtil.class.getClassLoader();
			InputStream in = loader.getResourceAsStream("blog.properties");
			webSiteProp = new Properties();
			webSiteProp.load(in);
		} catch (Exception e) {
			System.exit(100);
		}
		
	}

}
