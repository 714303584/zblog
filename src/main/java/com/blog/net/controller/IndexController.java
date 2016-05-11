package com.blog.net.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blog.dao.ArticleDaoImpl;
import com.blog.dao.NavigationDaoImpl;
import com.blog.entity.Article;
import com.blog.entity.Navigation;


@Controller("companyIndexController")
@RequestMapping({"/company"})
public class IndexController {
	
	@Autowired(required = true)
	NavigationDaoImpl navigationDaoImpl;
	
	@Autowired(required = true)
	ArticleDaoImpl  articleDaoImpl;
	
	@RequestMapping(value={"/index"},method={RequestMethod.GET})
	public String index(ModelMap model){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("parent", 0);
		param.put("sortColumns", "orders desc");
		List<Navigation> list =  navigationDaoImpl.findListBy(param);
		
		Map<Navigation, List<Navigation>> menus = new LinkedHashMap<Navigation,List<Navigation>>();
		
		Iterator<Navigation> it =  list.iterator();
		while(it.hasNext()){
			Navigation navigation = it.next();
				param.put("parent", navigation.getid());
				List<Navigation> childs =  navigationDaoImpl.findListBy(param);
				menus.put(navigation, childs);
		}
		List<Article> articles = articleDaoImpl.findListBy(null);
		model.put("articles", articles);
		model.put("navigations", menus);
		return "clients/index";
	}

}
