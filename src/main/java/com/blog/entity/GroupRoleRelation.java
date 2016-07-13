package com.blog.entity;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author zhuss
 */
@SuppressWarnings("serial")
public class GroupRoleRelation implements Serializable {

		
			private  long id;
			private  long gid;
			private  long rid;
			private  int type;
			private  int status;
			private  String description;
		
		public GroupRoleRelation() {

		}
		
		
		public GroupRoleRelation(long id, long gid, long rid, int type, int status, String description  ) {
				super();
			 this.id = id;
			 this.gid = gid;
			 this.rid = rid;
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
				public  long  getgid() {
					return gid;
				}
			
				public void setgid(long  gid) {
					this.gid =  gid;
				}
				public  long  getrid() {
					return rid;
				}
			
				public void setrid(long  rid) {
					this.rid =  rid;
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
		return "GroupRoleRelation ["+
			" id:"+ id+
			" gid:"+ gid+
			" rid:"+ rid+
			" type:"+ type+
			" status:"+ status+
			" description:"+ description+
		"]";
	}
}