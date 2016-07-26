package com.blog.dao;

import com.blog.entity.ArticleComment;
import java.util.List;
import java.util.Map;


public interface ArticleCommentDaoImpl {
	
	public ArticleComment getById(long id);
	
	public void save(ArticleComment entity);
	
	public List<ArticleComment> findListBy(Map<String, Object> map);
	
	public List<ArticleComment> findPageBy(Map<String, Object> map);
	
	public void deleteByIds(long[] ids);
	
	public void update(ArticleComment entity);
	
	public int getCountBy(Map<String, Object> map);

}
	
