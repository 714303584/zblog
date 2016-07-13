package com.blog.net.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.dao.PermissionDaoImpl;
import com.blog.dao.UrlsDaoImpl;
import com.blog.entity.Permission;
import com.blog.entity.Urls;
import com.blog.util.page.DefaultPage;

@Controller("adminUrlsController ")
@RequestMapping("/admin/urls")
public class UrlsController {

	@Autowired(required = true)
	UrlsDaoImpl urlsDaoImpl;
	
	@Autowired(required = true)
	 PermissionDaoImpl permissionDaoImpl;

	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	public String save(Urls Urls, ModelMap model) {
		urlsDaoImpl.save(Urls);
		return "redirect:/admin/urls/list.html";
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public String edit(Urls Urls, ModelMap model) {
		urlsDaoImpl.update(Urls);
		return "redirect:/admin/urls/list.html";
	}

	@RequestMapping(value = "/deleteByIds", method = { RequestMethod.POST })
	public String deleteByIds(long[] ids, ModelMap model) {
		if (ids != null) {
			urlsDaoImpl.deleteByIds(ids);
		}
		return "redirect:/admin/urls/list.html";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public String list(@RequestParam Map<String, Object> filter, ModelMap model) {
		DefaultPage<Urls> pages = new DefaultPage<Urls>();
		
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
		
		int total = urlsDaoImpl.getCountBy(filter);
		
		pages.setTotalCount(total);
		
		filter.put("page", pages.pageSql());
		List<Urls> Urlss = urlsDaoImpl.findPageBy(filter);
		pages.setElements(Urlss);
		model.put("pages", pages);
		return "urls/list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET })
	public String add(ModelMap model) {
		List<Permission> permissions = permissionDaoImpl.findListBy(null);
		model.put("permissions", permissions);
		return "urls/add";
	}

	@RequestMapping(value = "/show", method = { RequestMethod.GET })
	public String add(long id, ModelMap model) {
		Urls Urls = urlsDaoImpl.getById(id);
		List<Urls> Urlss = urlsDaoImpl.findListBy(null);
		model.put("Urlss", Urlss);
		model.put("Urls", Urls);
		return "urls/edit";
	}

	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.GET })
	public String deleteById(@PathVariable long id, ModelMap model) {
		long[] ids = new long[1];
		ids[0] = id;
		urlsDaoImpl.deleteByIds(ids);
		return "redirect:/admin/urls/list.html";
	}

}
