package com.blog.net.controller.admin;

import java.util.Iterator;
import java.util.Properties;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blog.util.BaseDataCacheUtil;


@Controller("adminSettingController ")
@RequestMapping("/admin/setting")
public class SettingController {
	
	
	
	@RequestMapping(value="/show",method={RequestMethod.GET})
	public String show( ModelMap model){
		Properties webSiteProp = BaseDataCacheUtil.webSiteProp;
		Iterator<Object> it = webSiteProp.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next().toString();
			String value = webSiteProp.getProperty(key);
			System.out.println(value);
			model.put(key, value);
		}
		return "setting/edit";
	
	
	}
	
	@RequestMapping(value="/edit",method={RequestMethod.POST})
	public String edit( ModelMap model){
		Properties webSiteProp = BaseDataCacheUtil.webSiteProp;
		Iterator<Object> it = webSiteProp.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next().toString();
			String value = webSiteProp.getProperty(key);
			System.out.println(value);
			model.put(key, value);
		}
		return "setting/edit";
	}
	
	
	

}
