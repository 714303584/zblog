package com.blog.dao;

import com.blog.entity.Role;
import java.util.List;
import java.util.Map;


public interface RoleDaoImpl {
	
	public Role getById(long id);
	
	public void save(Role entity);
	
	public List<Role> findListBy(Map<String, Object> map);
	
	public List<Role> findPageBy(Map<String, Object> map);
	
	public void deleteByIds(long[] ids);
	
	public void update(Role entity);
	
	public int getCountBy(Map<String, Object> map);

}
	
