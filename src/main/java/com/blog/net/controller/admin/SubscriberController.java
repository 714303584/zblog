package com.blog.net.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.dao.SubscriberDaoImpl;
import com.blog.entity.Subscriber;
import com.blog.mail.MailHanlder;
import com.blog.mail.MailMessage;
import com.blog.util.BaseDataCacheUtil;
import com.blog.util.page.DefaultPage;

@Controller("adminSubscriberController ")
@RequestMapping("/admin/subscriber")
public class SubscriberController {

	@Autowired(required = true)
	SubscriberDaoImpl subscriberDaoImpl;

	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	public String save(Subscriber subscriber, ModelMap model) {
		subscriberDaoImpl.save(subscriber);
		return "redirect:/admin/subscriber/list.html";
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.POST })
	public String edit(Subscriber subscriber, ModelMap model) {
		subscriberDaoImpl.update(subscriber);
		return "redirect:/admin/subscriber/list.html";
	}

	@RequestMapping(value = "/deleteByIds", method = { RequestMethod.POST })
	public String deleteByIds(long[] ids, ModelMap model) {
		if (ids != null) {
			subscriberDaoImpl.deleteByIds(ids);
		}
		return "redirect:/admin/subscriber/list.html";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public String list(@RequestParam Map<String, Object> filter, ModelMap model) {
		DefaultPage<Subscriber> pages = new DefaultPage<Subscriber>();
		
		Object pageVal =  filter.get("page");
		if(pageVal != null){
		 int page = Integer.parseInt(pageVal.toString()) - 1;
			filter.remove("page");
			pages.setPageNo(page);
		}
		
		Object pageSizeVal = filter.get("pageSize");
		if(pageSizeVal != null){
		  int pageSize = Integer.parseInt(pageSizeVal.toString());
			filter.remove("pageSize");
			pages.setPageSize(pageSize);
		}
		
		int total = subscriberDaoImpl.getCountBy(filter);
		
		pages.setTotalCount(total);
		
		filter.put("page", pages.pageSql());
		List<Subscriber> subscribers = subscriberDaoImpl.findPageBy(filter);
		pages.setElements(subscribers);
		model.put("pages", pages);
		return "subscriber/list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "subscriber/add";
	}

	@RequestMapping(value = "/show/{id}", method = { RequestMethod.GET })
	public String add(@PathVariable long id, ModelMap model) {
		Subscriber subscriber = subscriberDaoImpl.getById(id);
		model.put("subscriber", subscriber);
		return "subscriber/edit";
	}

	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.GET })
	public String deleteById(@PathVariable long id, ModelMap model) {
		long[] ids = new long[1];
		ids[0] = id;
		subscriberDaoImpl.deleteByIds(ids);
		return "redirect:/admin/subscriber/list.html";
	}

	
	
	@RequestMapping(value = "/sendmail", method = { RequestMethod.GET })
	public String sendMailPage(long id, ModelMap model) {
		Subscriber subscriber = subscriberDaoImpl.getById(id);
		model.put("subscriber", subscriber);
		return "subscriber/send";
	}
	
	
	@RequestMapping(value = "/send", method = { RequestMethod.POST })
	public String send(MailMessage message, ModelMap model) {
		
		
		Document doc = Jsoup.parse(message.getContent());
		
		Elements imgs =  doc.select("img");
		String userDir = BaseController.webAppRoot.substring(0, BaseController.webAppRoot.length() - 1);
		
		
		
		Map<String, String> images = new HashMap<String, String>();
		Iterator<Element> it = imgs.iterator();
		while (it.hasNext()) {
		 	Element img = it.next();
		 	
		 	String srcValue = img.attr("src");
		 	
		 	if (srcValue.startsWith("/ueditor/jsp/upload/image/")) {
		 		
					UUID imageUuid = UUID.randomUUID();
					String imageUuidV = imageUuid.toString();
					img.attr("src",  "cid:" + imageUuidV);
					images.put(imageUuidV, userDir+srcValue);
				}
		}
		
		System.out.println(doc.html());
		message.setContent(doc.html());
		message.setImages(images);
		message.setSender("zss19920514@sina.com");
		
		MailHanlder hanlder = new MailHanlder(BaseDataCacheUtil.mailProperties);
		hanlder.setMailMessage(message);
		hanlder.sendMessage();
		
		return "redirect:/admin/subscriber/list.html";
	}

}
