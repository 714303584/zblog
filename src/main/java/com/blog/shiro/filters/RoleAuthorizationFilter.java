package com.blog.shiro.filters;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.blog.dao.UrlsDaoImpl;
import com.blog.entity.Urls;
/**
 * 权限过滤
 * 主要实现方式为：获取请求的URL， 根据URL获取此URL所对应的权限编码，然后在subjec中进行权限匹配，
 * 若subject中拥有此权限，则允许访问。
 * 若subject中拥无此权限，则禁止访问。
 * @author zhuss
 */
public class RoleAuthorizationFilter extends AuthorizationFilter {
	
	//键----String--- 存储需授权的资源地址（url）
	//值----Long ----存储此资源地址所属的权限编码（id）
	public static Map<String, Long> uriRelationPermission = new HashMap<String, Long>(); 
	
	@Autowired(required = true)
	public static  UrlsDaoImpl urlsDaoImpl;
	//在项目启动时初始化权限
	static{
	 	List<Urls> urls = urlsDaoImpl.findAll();
	 	Iterator<Urls> it = urls.iterator();
	 	while (it.hasNext()) {
	 		Urls url = it.next();
	 		uriRelationPermission.put(url.geturl(), url.getpid());
	 	}
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object object) throws Exception {
		// 获取请求的资源（即请求的URL）
		HttpServletRequest httpReq = (HttpServletRequest) request;
		String url = httpReq.getRequestURI();
		//获取权限编码
		Long id = uriRelationPermission.get(url);
		
		//根据请求响应获取已登录的Subject对象。
		Subject subject = getSubject(request, response);
		//判断此用户是否拥有权限
		return  subject.isPermitted(id.toString());
	}
	
	

}
