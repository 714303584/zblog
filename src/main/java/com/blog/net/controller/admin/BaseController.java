package com.blog.net.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("adminBaseController ")
@RequestMapping("/admin")
public class BaseController {
	
	@RequestMapping(value="/index",method={RequestMethod.GET})
	public String index(){
		return "main";
	}
	
	
	@RequestMapping(value="/left",method={RequestMethod.GET})
	public String left(){
		return "left";
	}
	
	@RequestMapping(value="/info",method={RequestMethod.GET})
	public String systemInfo(){
		return "info";
	}
	
	
	
	

}
