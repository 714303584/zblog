package com.blog.net.controller.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blog.dao.NavigationDaoImpl;
import com.blog.entity.Navigation;
import com.blog.entity.User;

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
		List<Navigation> navigations = navigationDaoImpl.findListBy(null);
		model.put("navigations", navigations);
		return "navigation/list";
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public String edit(Navigation navigation, ModelMap model) {
		navigation.setmodifyDate(new Date());
		navigationDaoImpl.update(navigation);
		List<Navigation> navigations = navigationDaoImpl.findListBy(null);
		model.put("navigations", navigations);
		return "navigation/list";
	}

	@RequestMapping(value = "/deleteByIds", method = { RequestMethod.POST })
	public String deleteByIds(long[] ids, ModelMap model) {
		if (ids != null) {
			navigationDaoImpl.deleteByIds(ids);
		}
		List<Navigation> navigations = navigationDaoImpl.findListBy(null);
		model.put("navigations", navigations);
		return "navigation/list";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public String list(Navigation navigation, ModelMap model) {
		List<Navigation> navigations = navigationDaoImpl.findListBy(null);
		model.put("navigations", navigations);
		return "navigation/list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET })
	public String add(ModelMap model) {
		List<Navigation> navigations = navigationDaoImpl.findListBy(null);
		model.put("navigations", navigations);
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
		List<Navigation> navigations = navigationDaoImpl.findListBy(null);
		model.put("navigations", navigations);
		return "navigation/list";
	}

}
