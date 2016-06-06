package com.blog.net.controller.admin;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.dao.NavigationDaoImpl;
import com.blog.entity.Navigation;
import com.blog.entity.User;
import com.blog.util.page.DefaultPage;

@Controller("adminNavigationController ")
@RequestMapping("/admin/navigation")
public class NavigationController {

	@Autowired(required = true)
	NavigationDaoImpl navigationDaoImpl;

	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	public String save(Navigation navigation, ModelMap model) {
		navigation.setcreateDate(new Date());
		navigation.setmodifyDate(new Date());
		navigationDaoImpl.save(navigation);
		return "redirect:/admin/navigation/list.html";
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public String edit(Navigation navigation, ModelMap model) {
		navigation.setmodifyDate(new Date());
		navigationDaoImpl.update(navigation);
		return "redirect:/admin/navigation/list.html";
	}

	@RequestMapping(value = "/deleteByIds", method = { RequestMethod.POST })
	public String deleteByIds(long[] ids, ModelMap model) {
		if (ids != null) {
			navigationDaoImpl.deleteByIds(ids);
		}
		return "redirect:/admin/navigation/list.html";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public String list(@RequestParam Map<String, Object> filter, ModelMap model) {
		DefaultPage<Navigation> pages = new DefaultPage<Navigation>();
		
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
		
		int total = navigationDaoImpl.getCountBy(filter);
		
		pages.setTotalCount(total);
		
		filter.put("page", pages.pageSql());
		List<Navigation> navigations = navigationDaoImpl.findPageBy(filter);
		pages.setElements(navigations);
		model.put("pages", pages);
		return "navigation/list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "navigation/add";
	}

	@RequestMapping(value = "/show/{id}", method = { RequestMethod.GET })
	public String add(@PathVariable long id, ModelMap model) {
		Navigation navigation = navigationDaoImpl.getById(id);
		List<Navigation> navigations = navigationDaoImpl.findListBy(null);
		model.put("navigations", navigations);
		model.put("navigation", navigation);
		return "navigation/edit";
	}

	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.GET })
	public String deleteById(@PathVariable long id, ModelMap model) {
		long[] ids = new long[1];
		ids[0] = id;
		navigationDaoImpl.deleteByIds(ids);
		return "redirect:/admin/navigation/list.html";
	}

}
