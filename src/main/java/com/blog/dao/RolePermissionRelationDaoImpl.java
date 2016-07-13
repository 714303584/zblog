package com.blog.dao;

import com.blog.entity.RolePermissionRelation;
import java.util.List;
import java.util.Map;


public interface RolePermissionRelationDaoImpl {
	
	public RolePermissionRelation getById(long id);
	
	public void save(RolePermissionRelation entity);
	
	public List<RolePermissionRelation> findListBy(Map<String, Object> map);
	
	public List<RolePermissionRelation> findPageBy(Map<String, Object> map);
	
	public void deleteByIds(long[] ids);
	
	public void update(RolePermissionRelation entity);
	
	public int getCountBy(Map<String, Object> map);

}
	
