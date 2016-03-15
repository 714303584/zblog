package com.blog.net.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller("shopArticleController")
@RequestMapping({"/blog"})
public class IndexController {
	
	@RequestMapping(value={"/index"},method={RequestMethod.GET})
	public String index(ModelMap model){
		model.put("username", "zhuss");
		return "index";
	}

}
