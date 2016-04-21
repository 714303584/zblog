package com.blog.dao;

import java.util.List;
import java.util.Map;
import com.blog.entity.Navigation;

public interface NavigationDaoImpl {
	
	public Navigation getById(long id);
	
	public void save(Navigation entity);
	
	public List<Navigation> findListBy(Map<String, Object> map);
	
	public void deleteByIds(long[] ids);
	
	public void update(Navigation entity);

}
	
