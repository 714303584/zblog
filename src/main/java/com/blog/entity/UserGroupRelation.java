package com.blog.entity;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author zhuss
 */
@SuppressWarnings("serial")
public class UserGroupRelation implements Serializable {

		
			private  long id;
			private  long gid;
			private  long uid;
			private  int type;
		
		public UserGroupRelation() {

		}
		
		
		public UserGroupRelation(long id, long gid, long uid, int type  ) {
				super();
			 this.id = id;
			 this.gid = gid;
			 this.uid = uid;
			 this.type = type;
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
		return "UserGroupRelation ["+
			" id:"+ id+
			" gid:"+ gid+
			" uid:"+ uid+
			" type:"+ type+
		"]";
	}
}