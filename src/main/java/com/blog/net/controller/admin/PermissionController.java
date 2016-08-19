package com.blog.net.controller.admin;

import java.util.Date;
import java.util.HashMap;
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
import com.blog.entity.Permission;
import com.blog.entity.Urls;
import com.blog.util.page.DefaultPage;

@Controller("adminPermissionController ")
@RequestMapping("/admin/permission")
public class PermissionController {

	@Autowired(required = true)
	PermissionDaoImpl permissionDaoImpl;
	@Autowired(required = true)
	UrlsDaoImpl urlDaoImpl;
	
	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	public String save(Permission permission, ModelMap model) {
		permission.setcreateTime(new Date());
		permissionDaoImpl.save(permission);
		return "redirect:/admin/permission/list.html";
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public String edit(Permission Permission, ModelMap model) {
		permissionDaoImpl.update(Permission);
		return "redirect:/admin/permission/list.html";
	}

	@RequestMapping(value = "/deleteByIds", method = { RequestMethod.POST })
	public String deleteByIds(long[] ids, ModelMap model) {
		if (ids != null) {
			permissionDaoImpl.deleteByIds(ids);
		}
		return "redirect:/admin/permission/list.html";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public String list(@RequestParam Map<String, Object> filter, ModelMap model) {
		DefaultPage<Permission> pages = new DefaultPage<Permission>();
		
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
		
		int total = permissionDaoImpl.getCountBy(filter);
		
		pages.setTotalCount(total);
		
		filter.put("page", pages.pageSql());
		List<Permission> permissions = permissionDaoImpl.findPageBy(filter);
		pages.setElements(permissions);
		model.put("pages", pages);
		return "permission/list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET })
	public String add(ModelMap model) {
		List<Permission> permissions = permissionDaoImpl.findListBy(null);
		model.put("permissions", permissions);
		return "permission/add";
	}

	@RequestMapping(value = "/show", method = { RequestMethod.GET })
	public String add(long id, ModelMap model) {
		Permission Permission = permissionDaoImpl.getById(id);
		List<Permission> Permissions = permissionDaoImpl.findListBy(null);
		
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("pid", Permission.getid());
		List<Urls> urls = urlDaoImpl.findListBy(filter);
		
		model.put("permissions", Permissions);
		model.put("p", Permission);
		model.put("urls", urls);
		return "permission/edit";
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.GET })
	public String deleteById(long id, ModelMap model) {
		long[] ids = new long[1];
		ids[0] = id;
		permissionDaoImpl.deleteByIds(ids);
		return "redirect:/admin/permission/list.html";
	}

}
