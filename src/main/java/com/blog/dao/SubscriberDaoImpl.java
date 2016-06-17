package com.blog.dao;

import com.blog.entity.Subscriber;
import java.util.List;
import java.util.Map;


public interface SubscriberDaoImpl {
	
	public Subscriber getById(long id);
	
	public void save(Subscriber entity);
	
	public List<Subscriber> findListBy(Map<String, Object> map);
	
	public List<Subscriber> findPageBy(Map<String, Object> map);
	
	public void deleteByIds(long[] ids);
	
	public void update(Subscriber entity);
	
	public int getCountBy(Map<String, Object> map);

}
	
