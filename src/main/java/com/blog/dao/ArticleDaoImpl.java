package com.blog.dao;

import com.blog.entity.Article;
import com.blog.entity.Navigation;

import java.util.List;
import java.util.Map;


public interface ArticleDaoImpl {
	
	public Article getById(long id);
	
	public void save(Article entity);
	
	public List<Article> findListBy(Map<String, Object> map);
	
	public void deleteByIds(long[] ids);
	
	public void update(Article entity);
	
	
	public List<Navigation> findPageBy(Map<String, Object> map);
	
	
	public void update(Navigation entity);
	
	public int getCountBy(Map<String, Object> map);

}
	
