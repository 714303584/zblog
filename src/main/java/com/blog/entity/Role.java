package com.blog.entity;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author zhuss
 */
@SuppressWarnings("serial")
public class Role implements Serializable {

		
			private  long id;
			private  long parentId;
			private  String name;
			private  String dispalyName;
			private  int status;
			private  Date createTime;
			private  String description;
		
		public Role() {

		}
		
		
		public Role(long id, long parentId, String name, String dispalyName, int status, Date createTime, String description  ) {
				super();
			 this.id = id;
			 this.parentId = parentId;
			 this.name = name;
			 this.dispalyName = dispalyName;
			 this.status = status;
			 this.createTime = createTime;
			 this.description = description;
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
				public  String  getdispalyName() {
					return dispalyName;
				}
			
				public void setdispalyName(String  dispalyName) {
					this.dispalyName =  dispalyName;
				}
				public  int  getstatus() {
					return status;
				}
			
				public void setstatus(int  status) {
					this.status =  status;
				}
				public  Date  getcreateTime() {
					return createTime;
				}
			
				public void setcreateTime(Date  createTime) {
					this.createTime =  createTime;
				}
				public  String  getdescription() {
					return description;
				}
			
				public void setdescription(String  description) {
					this.description =  description;
				}
	@Override
	public String toString() {
		return "Role ["+
			" id:"+ id+
			" parentId:"+ parentId+
			" name:"+ name+
			" dispalyName:"+ dispalyName+
			" status:"+ status+
			" createTime:"+ createTime+
			" description:"+ description+
		"]";
	}
}