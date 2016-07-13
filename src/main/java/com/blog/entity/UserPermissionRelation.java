package com.blog.entity;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author zhuss
 */
@SuppressWarnings("serial")
public class UserPermissionRelation implements Serializable {

		
			private  long id;
			private  long pid;
			private  long uid;
			private  int type;
		
		public UserPermissionRelation() {

		}
		
		
		public UserPermissionRelation(long id, long pid, long uid, int type  ) {
				super();
			 this.id = id;
			 this.pid = pid;
			 this.uid = uid;
			 this.type = type;
		}
		
		
				public  long  getid() {
					return id;
				}
			
				public void setid(long  id) {
					this.id =  id;
				}
				public  long  getpid() {
					return pid;
				}
			
				public void setpid(long  pid) {
					this.pid =  pid;
				}
				public  long  getuid() {
					return uid;
				}
			
				public void setuid(long  uid) {
					this.uid =  uid;
				}
				public  int  gettype() {
					return type;
				}
			
				public void settype(int  type) {
					this.type =  type;
				}
	@Override
	public String toString() {
		return "UserPermissionRelation ["+
			" id:"+ id+
			" pid:"+ pid+
			" uid:"+ uid+
			" type:"+ type+
		"]";
	}
}