package com.blog.dao;

import com.blog.entity.UserGroupRelation;
import java.util.List;
import java.util.Map;


public interface UserGroupRelationDaoImpl {
	
	public UserGroupRelation getById(long id);
	
	public void save(UserGroupRelation entity);
	
	public List<UserGroupRelation> findListBy(Map<String, Object> map);
	
	public List<UserGroupRelation> findPageBy(Map<String, Object> map);
	
	public void deleteByIds(long[] ids);
	
	public void update(UserGroupRelation entity);
	
	public int getCountBy(Map<String, Object> map);

}
	
