package com.blog.entity;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author zhuss
 */
@SuppressWarnings("serial")
public class Navigation implements Serializable {

		
			private  long id;
			private  Date createDate;
			private  Date modifyDate;
			private  int orders;
			private  int isBlankTarget;
			private  String name;
			private  long parent;
			private  int type;
			private  int position;
			private  String url;
			private  String parentname;
		
		public Navigation() {

		}
		
		
		public Navigation(long id, Date createDate, Date modifyDate, int orders, int isBlankTarget, String name, long parent, int type, int position, String url, String parentname  ) {
				super();
			 this.id = id;
			 this.createDate = createDate;
			 this.modifyDate = modifyDate;
			 this.orders = orders;
			 this.isBlankTarget = isBlankTarget;
			 this.name = name;
			 this.parent = parent;
			 this.type = type;
			 this.position = position;
			 this.url = url;
			 this.parentname = parentname;
		}
		
		
				public  long  getid() {
					return id;
				}
			
				public void setid(long  id) {
					this.id =  id;
				}
				public  Date  getcreateDate() {
					return createDate;
				}
			
				public void setcreateDate(Date  createDate) {
					this.createDate =  createDate;
				}
				public  Date  getmodifyDate() {
					return modifyDate;
				}
			
				public void setmodifyDate(Date  modifyDate) {
					this.modifyDate =  modifyDate;
				}
				public  int  getorders() {
					return orders;
				}
			
				public void setorders(int  orders) {
					this.orders =  orders;
				}
				public  int  getisBlankTarget() {
					return isBlankTarget;
				}
			
				public void setisBlankTarget(int  isBlankTarget) {
					this.isBlankTarget =  isBlankTarget;
				}
				public  String  getname() {
					return name;
				}
			
				public void setname(String  name) {
					this.name =  name;
				}
				public  long  getparent() {
					return parent;
				}
			
				public void setparent(long  parent) {
					this.parent =  parent;
				}
				public  int  gettype() {
					return type;
				}
			
				public void settype(int  type) {
					this.type =  type;
				}
				public  int  getposition() {
					return position;
				}
			
				public void setposition(int  position) {
					this.position =  position;
				}
				public  String  geturl() {
					return url;
				}
			
				public void seturl(String  url) {
					this.url =  url;
				}
				public  String  getparentname() {
					return parentname;
				}
			
				public void setparentname(String  parentname) {
					this.parentname =  parentname;
				}
	@Override
	public String toString() {
		return "Navigation ["+
			" id:"+ id+
			" createDate:"+ createDate+
			" modifyDate:"+ modifyDate+
			" orders:"+ orders+
			" isBlankTarget:"+ isBlankTarget+
			" name:"+ name+
			" parent:"+ parent+
			" type:"+ type+
			" position:"+ position+
			" url:"+ url+
			" parentname:"+ parentname+
		"]";
	}
}