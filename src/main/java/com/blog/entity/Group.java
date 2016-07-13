package com.blog.entity;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author zhuss
 */
@SuppressWarnings("serial")
public class Group implements Serializable {

		
			private  long id;
			private  String name;
			private  String displayName;
			private  long parentId;
			private  Date createTime;
			private  int status;
			private  String description;
		
		public Group() {

		}
		
		
		public Group(long id, String name, String displayName, long parentId, Date createTime, int status, String description  ) {
				super();
			 this.id = id;
			 this.name = name;
			 this.displayName = displayName;
			 this.parentId = parentId;
			 this.createTime = createTime;
			 this.status = status;
			 this.description = description;
		}
		
		
				public  long  getid() {
					return id;
				}
			
				public void setid(long  id) {
					this.id =  id;
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
				public  long  getparentId() {
					return parentId;
				}
			
				public void setparentId(long  parentId) {
					this.parentId =  parentId;
				}
				public  Date  getcreateTime() {
					return createTime;
				}
			
				public void setcreateTime(Date  createTime) {
					this.createTime =  createTime;
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
	@Override
	public String toString() {
		return "Group ["+
			" id:"+ id+
			" name:"+ name+
			" displayName:"+ displayName+
			" parentId:"+ parentId+
			" createTime:"+ createTime+
			" status:"+ status+
			" description:"+ description+
		"]";
	}
}