package com.blog.net.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.blog.dao.UserDaoImpl;
import com.blog.entity.User;

@Controller("adminUserController ")
@RequestMapping("/admin/user")
public class UserController {
	
	@Autowired(required=true)
	UserDaoImpl userDaoImpl;
	
	
	@RequestMapping(value="/save",method={RequestMethod.POST})
	public String add(User user,ModelMap model){
		user.setcreateDate(new Date());
		user.setmodifyDate(new Date());
		user.setisEnabled(0);
		user.setisLocked(0);
		userDaoImpl.save(user);
		List<User> users = userDaoImpl.findListBy(null);
		model.put("users", users);
		return "user/list";
	}
	
	
	@RequestMapping(value="/list",method={RequestMethod.GET})
	public String list(User user,ModelMap model){
		List<User> users = userDaoImpl.findListBy(null);
		model.put("users", users);
		return "user/list";
	}
	
	
	@RequestMapping(value="/find/{id}",method={RequestMethod.GET})
	public String getById(@PathVariable Integer id,ModelMap model){
		User user = userDaoImpl.getById(id);
		model.put("user", user);
		return "index";
	}
	
	@RequestMapping(value="/add",method={RequestMethod.GET})
	public String addUser(){
		return "user/add";
	}
	
	@RequestMapping(value="/delete/{id}",method={RequestMethod.GET})
	public String deleteUser(@PathVariable long id,ModelMap model){
		long[] ids = new long[1];
		ids[0] = id;
		userDaoImpl.deleteByIds(ids);
		List<User> users = userDaoImpl.findListBy(null);
		model.put("users", users);
		return "user/list";
	}
	
	
	
	
	
	
	

}
