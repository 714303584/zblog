package com.blog.dao;

import java.util.List;
import java.util.Map;

import com.blog.entity.User;

public interface UserDaoImpl {
	
	public User getById(long id);
	
	public void save(User user);
	
	public List<User> findListBy(Map<String, Object> map);
	
	public void deleteByIds(long[] ids);

}
