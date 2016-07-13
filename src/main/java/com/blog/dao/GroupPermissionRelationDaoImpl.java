package com.blog.dao;

import com.blog.entity.GroupPermissionRelation;
import java.util.List;
import java.util.Map;


public interface GroupPermissionRelationDaoImpl {
	
	public GroupPermissionRelation getById(long id);
	
	public void save(GroupPermissionRelation entity);
	
	public List<GroupPermissionRelation> findListBy(Map<String, Object> map);
	
	public List<GroupPermissionRelation> findPageBy(Map<String, Object> map);
	
	public void deleteByIds(long[] ids);
	
	public void update(GroupPermissionRelation entity);
	
	public int getCountBy(Map<String, Object> map);

}
	
