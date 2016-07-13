package com.blog.dao;

import com.blog.entity.Permission;
import java.util.List;
import java.util.Map;


public interface PermissionDaoImpl {
	
	public Permission getById(long id);
	
	public void save(Permission entity);
	
	public List<Permission> findListBy(Map<String, Object> map);
	
	public List<Permission> findPageBy(Map<String, Object> map);
	
	public void deleteByIds(long[] ids);
	
	public void update(Permission entity);
	
	public int getCountBy(Map<String, Object> map);

}
	
