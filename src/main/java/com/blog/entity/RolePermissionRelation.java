package com.blog.entity;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author zhuss
 */
@SuppressWarnings("serial")
public class RolePermissionRelation implements Serializable {

		
			private  long id;
			private  long rid;
			private  long pid;
			private  int type;
			private  int status;
			private  String description;
		
		public RolePermissionRelation() {

		}
		
		
		public RolePermissionRelation(long id, long rid, long pid, int type, int status, String description  ) {
				super();
			 this.id = id;
			 this.rid = rid;
			 this.pid = pid;
			 this.type = type;
			 this.status = status;
			 this.description = description;
		}
		
		
				public  long  getid() {
					return id;
				}
			
				public void setid(long  id) {
					this.id =  id;
				}
				public  long  getrid() {
					return rid;
				}
			
				public void setrid(long  rid) {
					this.rid =  rid;
				}
				public  long  getpid() {
					return pid;
				}
			
				public void setpid(long  pid) {
					this.pid =  pid;
				}
				public  int  gettype() {
					return type;
				}
			
				public void settype(int  type) {
					this.type =  type;
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
		return "RolePermissionRelation ["+
			" id:"+ id+
			" rid:"+ rid+
			" pid:"+ pid+
			" type:"+ type+
			" status:"+ status+
			" description:"+ description+
		"]";
	}
}