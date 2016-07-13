package com.blog.dao;

import com.blog.entity.UserPermissionRelation;
import java.util.List;
import java.util.Map;


public interface UserPermissionRelationDaoImpl {
	
	public UserPermissionRelation getById(long id);
	
	public void save(UserPermissionRelation entity);
	
	public List<UserPermissionRelation> findListBy(Map<String, Object> map);
	
	public List<UserPermissionRelation> findPageBy(Map<String, Object> map);
	
	public void deleteByIds(long[] ids);
	
	public void update(UserPermissionRelation entity);
	
	public int getCountBy(Map<String, Object> map);

}
	
