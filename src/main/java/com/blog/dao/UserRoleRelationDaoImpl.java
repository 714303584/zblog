package com.blog.dao;

import com.blog.entity.UserRoleRelation;
import java.util.List;
import java.util.Map;


public interface UserRoleRelationDaoImpl {
	
	public UserRoleRelation getById(long id);
	
	public void save(UserRoleRelation entity);
	
	public List<UserRoleRelation> findListBy(Map<String, Object> map);
	
	public List<UserRoleRelation> findPageBy(Map<String, Object> map);
	
	public void deleteByIds(long[] ids);
	
	public void update(UserRoleRelation entity);
	
	public int getCountBy(Map<String, Object> map);

}
	
