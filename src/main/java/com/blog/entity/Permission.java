package com.blog.entity;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author zhuss
 */
@SuppressWarnings("serial")
public class Permission implements Serializable {

		
			private  long id;
			private  long parentId;
			private  String name;
			private  String displayName;
			private  int status;
			private  String description;
			private  String url;
			private  Date createTime;
			private  String pname;
		
		public Permission() {

		}
		
		
		public Permission(long id, long parentId, String name, String displayName, int status, String description, String url, Date createTime, String pname  ) {
				super();
			 this.id = id;
			 this.parentId = parentId;
			 this.name = name;
			 this.displayName = displayName;
			 this.status = status;
			 this.description = description;
			 this.url = url;
			 this.createTime = createTime;
			 this.pname = pname;
		}
		
		
				public  long  getid() {
					return id;
				}
			
				public void setid(long  id) {
					this.id =  id;
				}
				public  long  getparentId() {
					return parentId;
				}
			
				public void setparentId(long  parentId) {
					this.parentId =  parentId;
				}
				public  String  getname() {
					return name;
				}
			
				public void setname(String  name) {
					this.name =  name;
				}
				public  String  getdisplayName() {
					return displayName;
				}
			
				public void setdisplayName(String  displayName) {
					this.displayName =  displayName;
				}
				public  int  getstatus() {
					return status;
				}
			
				public void setstatus(int  status) {
					this.status =  status;
				}
				public  String  getdescription() {
					return description;
				}
			
				public void setdescription(String  description) {
					this.description =  description;
				}
				public  String  geturl() {
					return url;
				}
			
				public void seturl(String  url) {
					this.url =  url;
				}
				public  Date  getcreateTime() {
					return createTime;
				}
			
				public void setcreateTime(Date  createTime) {
					this.createTime =  createTime;
				}
				public  String  getpname() {
					return pname;
				}
			
				public void setpname(String  pname) {
					this.pname =  pname;
				}
	@Override
	public String toString() {
		return "Permission ["+
			" id:"+ id+
			" parentId:"+ parentId+
			" name:"+ name+
			" displayName:"+ displayName+
			" status:"+ status+
			" description:"+ description+
			" url:"+ url+
			" createTime:"+ createTime+
			" pname:"+ pname+
		"]";
	}
}