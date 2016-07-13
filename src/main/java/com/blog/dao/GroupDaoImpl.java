package com.blog.dao;

import com.blog.entity.Group;
import java.util.List;
import java.util.Map;


public interface GroupDaoImpl {
	
	public Group getById(long id);
	
	public void save(Group entity);
	
	public List<Group> findListBy(Map<String, Object> map);
	
	public List<Group> findPageBy(Map<String, Object> map);
	
	public void deleteByIds(long[] ids);
	
	public void update(Group entity);
	
	public int getCountBy(Map<String, Object> map);

}
	
