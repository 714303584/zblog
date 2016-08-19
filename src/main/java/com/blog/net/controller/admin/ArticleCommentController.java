package com.blog.net.controller.admin;

import java.util.Date;
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
import com.blog.entity.ArticleComment;
import com.blog.entity.Navigation;
import com.blog.util.page.DefaultPage;

@Controller("adminArticleCommentController")
@RequestMapping("/admin/acomment")
public class ArticleCommentController {

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
	public String deleteByIds(long[] ids,long aid , int page,int pageSize, ModelMap model) {
		if (ids != null) {
			articleCommentDaoImpl.deleteByIds(ids);
		}
		
		return "redirect:/admin/articlecomment/list.html?aid="+aid+"&page="+page+"&pageSize="+pageSize;
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public String list(@RequestParam Map<String, Object> filter, ModelMap model) {
		
		DefaultPage<ArticleComment> pages = new DefaultPage<ArticleComment>();
		
		
		Object pageVal =  filter.get("page");
		if(pageVal != null){
			
		 int page = Integer.parseInt(pageVal.toString());
		 if(page >= 1) page = page -1;
		 
			filter.remove("page");
			pages.setPageNo(page);
		}
		
		Object pageSizeVal = filter.get("pageSize");
		if(pageSizeVal != null){
		  int pageSize = Integer.parseInt(pageSizeVal.toString());
			filter.remove("pageSize");
			pages.setPageSize(pageSize);
		}
		
		int total = articleCommentDaoImpl.getCountBy(filter);
		
		pages.setTotalCount(total);
		
		Article  article = articleDaoImpl.getById(Long.parseLong(filter.get("aid").toString()));
		
		
		filter.put("page", pages.pageSql());
		filter.put("sortColumns", "create_date desc");
		List<ArticleComment> articleComments = articleCommentDaoImpl.findPageBy(filter);
		
		pages.setElements(articleComments);
		
		model.put("pages", pages);
		model.put("article", article);
		return "articlecomment/list";
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

	@RequestMapping(value = "/delete", method = { RequestMethod.GET })
	public String deleteById(long id,long aid , int page,int pageSize, ModelMap model) {
		long[] ids = new long[1];
		ids[0] = id;
		articleCommentDaoImpl.deleteByIds(ids);
		return "redirect:/admin/acomment/list.html?aid="+aid+"&page="+page+"&pageSize="+pageSize;
	}

}
