package com.blog.dao;

import com.blog.entity.GroupRoleRelation;
import java.util.List;
import java.util.Map;


public interface GroupRoleRelationDaoImpl {
	
	public GroupRoleRelation getById(long id);
	
	public void save(GroupRoleRelation entity);
	
	public List<GroupRoleRelation> findListBy(Map<String, Object> map);
	
	public List<GroupRoleRelation> findPageBy(Map<String, Object> map);
	
	public void deleteByIds(long[] ids);
	
	public void update(GroupRoleRelation entity);
	
	public int getCountBy(Map<String, Object> map);

}
	
