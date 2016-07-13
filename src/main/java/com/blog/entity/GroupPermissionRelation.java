package com.blog.entity;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author zhuss
 */
@SuppressWarnings("serial")
public class GroupPermissionRelation implements Serializable {

		
			private  long id;
			private  long gid;
			private  long pid;
			private  int status;
			private  String description;
		
		public GroupPermissionRelation() {

		}
		
		
		public GroupPermissionRelation(long id, long gid, long pid, int status, String description  ) {
				super();
			 this.id = id;
			 this.gid = gid;
			 this.pid = pid;
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
				public  long  getpid() {
					return pid;
				}
			
				public void setpid(long  pid) {
					this.pid =  pid;
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
		return "GroupPermissionRelation ["+
			" id:"+ id+
			" gid:"+ gid+
			" pid:"+ pid+
			" status:"+ status+
			" description:"+ description+
		"]";
	}
}