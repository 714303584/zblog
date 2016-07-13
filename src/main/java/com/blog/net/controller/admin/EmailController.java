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

@Controller("adminEmailController ")
@RequestMapping("/admin/email")
public class EmailController {

	@Autowired(required = true)
	SubscriberDaoImpl subscriberDaoImpl;

	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	public String save(Subscriber subscriber, ModelMap model) {
		subscriberDaoImpl.save(subscriber);
		return "redirect:/admin/subscriber/list.html";
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
		
		List<String> recipients = new ArrayList<String>();
		recipients.add("714303584@qq.com");
		message.setImages(images);
		message.setSender("zss19920514@sina.com");
		message.setRecipients(recipients);
		
		MailHanlder hanlder = new MailHanlder(BaseDataCacheUtil.mailProperties);
		hanlder.setMailMessage(message);
		hanlder.sendMessage();
		
		return "redirect:/admin/email/list.html";
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
		return "email/list";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "email/add";
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

//	public  String extractText(Parser parser) throws Exception {
//		StringBuffer text = new StringBuffer();
//		NodeList nodes = parser.extractAllNodesThatMatch(null);
//
//		for (int i = 0; i < nodes.size(); i++) {
//			Node nodet = nodes.elementAt(i);
//			text.append(new String(nodet.toPlainTextString().getBytes("utf8"))
//					+ "\r\n");
//
//		}
//
//		return text.toString();
//	}

}
