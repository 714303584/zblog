package com.blog.net.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	@RequestMapping(value={"/article/{id}/info"},method={RequestMethod.GET})
	public String articleShow(@PathVariable long id,ModelMap model){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("parent", 0);
		param.put("sortColumns", "orders desc");
		List<Navigation> list =  navigationDaoImpl.findListBy(param);
		
		Map<Navigation, List<Navigation>> menus = new LinkedHashMap<Navigation,List<Navigation>>();
		
		Article article = articleDaoImpl.getById(id);
		
		Iterator<Navigation> it =  list.iterator();
		Navigation arParent = null;
		Navigation arChild = null;
		while(it.hasNext()){
			Navigation navigation = it.next();
				param.put("parent", navigation.getid());
				List<Navigation> childs =  navigationDaoImpl.findListBy(param);
				menus.put(navigation, childs);
				for (int i = 0; i < childs.size(); i++) {
					Navigation child =  childs.get(i);
					if(child.getid() == article.getarticleCategory()){
						arChild = child;
						arParent = navigation;
					}
				}
				
				if(navigation.getid() == article.getarticleCategory()){
					arParent = navigation;
				}
		}
		
		model.put("parent", arParent);
		model.put("child", arChild);
		model.put("article", article);
		model.put("navigations", menus);
		return "clients/article";
	}
	
	
	@RequestMapping(value={"/navigation/{id}/list"},method={RequestMethod.GET})
	public String navigationList(@PathVariable long id,ModelMap model){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("parent", 0);
		param.put("sortColumns", "orders desc");
		List<Navigation> list =  navigationDaoImpl.findListBy(param);
		
		Map<Navigation, List<Navigation>> menus = new LinkedHashMap<Navigation,List<Navigation>>();
		
		Navigation demo = null;
		Navigation arParent = null;
		Navigation archild = null;
		Iterator<Navigation> it =  list.iterator();
		while(it.hasNext()){
			Navigation navigation = it.next();
			param.put("parent", navigation.getid());
			List<Navigation> childs =  navigationDaoImpl.findListBy(param);
			menus.put(navigation, childs);
			if(navigation.getid() == id)   {
				demo = navigation;
				arParent = navigation;
			}
		}
		
		Map<String, Object> articleParam = new HashMap<String,Object>();
		articleParam.put("articleCategory", id);
		List<Article> articles = articleDaoImpl.findListBy(articleParam);
		
		if(demo != null){
			List<Navigation> children =  menus.get(demo);
			Iterator<Navigation> childIt = children.iterator();
			while (childIt.hasNext()) {
				Navigation child =  childIt.next();
				articleParam.put("articleCategory", child.getid());
				articles.addAll(articleDaoImpl.findListBy(articleParam));
				if(child.getid() == id) {
					demo = child;
					archild = child;
				}
			}
		}else{
			Iterator<Navigation> it2 =  list.iterator();
			while(it2.hasNext()){
				Navigation navigation = it2.next();
				if(navigation.getid() == id)  {
					arParent = navigation;
				}
			}
		}
		model.put("parent", arParent);
		model.put("child", archild);
		model.put("navigation", demo);
		model.put("articles", articles);
		model.put("navigations", menus);
		return "clients/navigation";
	}
	

	
	
	
	
	
	

}
