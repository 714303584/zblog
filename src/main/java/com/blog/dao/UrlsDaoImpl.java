package com.blog.dao;

import com.blog.entity.Urls;
import java.util.List;
import java.util.Map;


public interface UrlsDaoImpl {
	
	public Urls getById(long id);
	
	public void save(Urls entity);
	
	public List<Urls> findListBy(Map<String, Object> map);
	
	public List<Urls> findPageBy(Map<String, Object> map);
	
	public void deleteByIds(long[] ids);
	
	public void update(Urls entity);
	
	public int getCountBy(Map<String, Object> map);

}
	
