package com.blog.net.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blog.dao.ArticleCommentDaoImpl;
import com.blog.dao.ArticleDaoImpl;
import com.blog.dao.NavigationDaoImpl;
import com.blog.dao.UserDaoImpl;
import com.blog.entity.Article;
import com.blog.entity.ArticleComment;
import com.blog.entity.Navigation;
import com.blog.entity.User;
import com.blog.util.Checker;
import com.blog.util.DataStatus;


@Controller("companyIndexController")
@RequestMapping({"/company"})
public class IndexController {
	
	@Autowired(required = true)
	NavigationDaoImpl navigationDaoImpl;
	
	@Autowired(required = true)
	ArticleDaoImpl  articleDaoImpl;
	
	@Autowired(required = true)
	UserDaoImpl  userDaoImpl;
	
	
	@Autowired(required = true)
	ArticleCommentDaoImpl  articleCommentDaoImpl;
	
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
		param.put("parent", -1);
		List<Navigation> bots = navigationDaoImpl.findListBy(param);
		model.put("articles", articles);
		model.put("navigations", menus);
		model.put("bottoms", bots);
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
		
		Map<String, Object> commentParam = new HashMap<String,Object>();
		commentParam.put("aid", id);
		
		
	 	List<ArticleComment> comments = articleCommentDaoImpl.findListBy(commentParam);
		
		param.put("parent", -1);
		List<Navigation> bots = navigationDaoImpl.findListBy(param);
		model.put("bottoms", bots);
		model.put("parent", arParent);
		model.put("child", arChild);
		model.put("article", article);
		model.put("navigations", menus);
		model.put("comments", comments);
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
				List<Navigation> childs = menus.get(navigation);
				
				Iterator<Navigation> childsIt = childs.iterator();
				while (childsIt.hasNext()) {
					Navigation child  = childsIt.next();
					if(child.getid() == id){
						arParent = navigation;
						archild = child;
						demo = child;
						break;
					}
				}
				if(arParent != null && archild != null) break;
				
			}
		}
		
		param.put("parent", -1);
		List<Navigation> bots = navigationDaoImpl.findListBy(param);
		model.put("bottoms", bots);
		model.put("parent", arParent);
		model.put("child", archild);
		model.put("navigation", demo);
		model.put("articles", articles);
		model.put("navigations", menus);
		return "clients/navigation";
	}
	
	@RequestMapping(value={"/admin/login"},method={RequestMethod.GET})
	public String navigationList(ModelMap model){
		return "login";
	}
	
	@RequestMapping(value={"/admin/login"},method={RequestMethod.POST})
	public String login(String username,String password, ModelMap model){
		
		if(Checker.isEmpty(username) || Checker.isEmpty(password)){
			
			model.put("mesage", "登录失败:请检查您的用户名和密码！");
		}
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("username", username);
		List<User> users = userDaoImpl.findListBy(map);
		
		if(users.size() > 0){
			
			User user = users.get(0);
			if(user.getpassword().equals(password)){
				//从SecurityUtils中取得subject 
				Subject subject = SecurityUtils.getSubject();
				//创建Token
				UsernamePasswordToken token = new UsernamePasswordToken(user.getusername(),user.getpassword());
				
				// 使用token登录
				subject.login(token);
				return  "redirect:/admin/index.html";
			}else{
				model.put("mesage", "登录失败:请检查您的用户名和密码！");
			}
			
		}else{
			model.put("mesage", "登录失败:请检查您的用户名和密码！");
		}
		return "login";
	}
	
	
	@RequestMapping(value={"/article/comment"},method={RequestMethod.POST})
	public String articleComment(ArticleComment articleComment, ModelMap model){
		articleComment.setcreateDate(new Date());
		articleComment.setstatus(DataStatus.CREATED);
		articleCommentDaoImpl.save(articleComment);
		return  "redirect:/company/article/"+articleComment.getaid()+"/info.html";
	}
	
	
	
	
	
	
	
	
	
	
	

}
