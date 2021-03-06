package com.blog.net.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.dao.ArticleCommentDaoImpl;
import com.blog.dao.ArticleDaoImpl;
import com.blog.dao.NavigationDaoImpl;
import com.blog.entity.Article;
import com.blog.entity.Navigation;
import com.blog.util.DataStatus;
import com.blog.util.page.DefaultPage;

@Controller("adminArticleController")
@RequestMapping("/admin/article")
public class ArticleController {

	@Autowired(required = true)
	ArticleDaoImpl  articleDaoImpl;
	
	@Autowired(required = true)
	NavigationDaoImpl navigationDaoImpl;
	
	
	@Autowired(required = true)
	ArticleCommentDaoImpl articleCommentDaoImpl;
	

	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	public String save(Article article, ModelMap model) {
		article.setcreateDate(new Date());
		article.setmodifyDate(new Date());
		articleDaoImpl.save(article);
		List<Article> articles = articleDaoImpl.findListBy(null);
		model.put("articles", articles);
		return "article/list";
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public String edit(Article article, ModelMap model) {
		article.setmodifyDate(new Date());
		Article old =  articleDaoImpl.getById(article.getid());
		article.setcreateDate(old.getcreateDate());
		articleDaoImpl.update(article);
		List<Article> articles = articleDaoImpl.findListBy(null);
		model.put("articles", articles);
		return "article/list";
	}

	@RequestMapping(value = "/deleteByIds", method = { RequestMethod.POST })
	public String deleteByIds(long[] ids, ModelMap model) {
		if (ids != null) {
			articleDaoImpl.deleteByIds(ids);
		}
		List<Article> navigations = articleDaoImpl.findListBy(null);
		model.put("articles", navigations);
		return "article/list";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public String list(@RequestParam Map<String, Object> filter, ModelMap model) {
		
		DefaultPage<Article> pages = new DefaultPage<Article>();
		
		Object pageVal =  filter.get("page");
		if(pageVal != null){
		 int page = Integer.parseInt(pageVal.toString()) - 1;
			filter.remove("page");
			pages.setPageNo(page);
		}
		
		Object pageSizeVal = filter.get("pageSize");
		if(pageSizeVal != null){
		  int pageSize = Integer.parseInt(pageSizeVal.toString());
			filter.remove("pageSize");
			pages.setPageSize(pageSize);
		}
		
		int total = articleDaoImpl.getCountBy(filter);
		
		pages.setTotalCount(total);
		
		filter.put("page", pages.pageSql());
		List<Article> articles = articleDaoImpl.findListBy(null);
		
	 	Iterator<Article> it = articles.iterator();
		
	 	Map<Long, Integer> cout = new HashMap<Long, Integer>();
	 	Map<String, Object> filterParam = new HashMap<String,Object>();
	 	while (it.hasNext()) {
	 		long aid = it.next().getid();
			filterParam.put("aid", aid);
			filterParam.put("status", DataStatus.CREATED);
			int count = articleCommentDaoImpl.getCountBy(filterParam);
			cout.put(aid, count);
		}
		
		
		pages.setElements(articles);
		model.put("pages", pages);
		model.put("countMap", cout);
		return "article/list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET })
	public String add(ModelMap model) {
		List<Article> articles = articleDaoImpl.findListBy(null);
		model.put("article", articles);
		
		List<Navigation> navigations = navigationDaoImpl.findListBy(null);
		model.put("navigations", navigations);
		
		return "article/add";
	}

	@RequestMapping(value = "/show/{id}", method = { RequestMethod.GET })
	public String add(@PathVariable long id, ModelMap model) {
		Article article = articleDaoImpl.getById(id);
		Navigation navigation = navigationDaoImpl.getById(id);
		List<Navigation> navigations = navigationDaoImpl.findListBy(null);
		model.put("navigations", navigations);
		model.put("navigation", navigation);
		model.put("article", article);
		return "article/edit";
	}

	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.GET })
	public String deleteById(@PathVariable long id, ModelMap model) {
		long[] ids = new long[1];
		ids[0] = id;
		articleDaoImpl.deleteByIds(ids);
		List<Article> articles = articleDaoImpl.findListBy(null);
		model.put("articles", articles);
		return "article/list";
	}

}
