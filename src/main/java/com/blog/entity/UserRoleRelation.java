package com.blog.entity;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author zhuss
 */
@SuppressWarnings("serial")
public class UserRoleRelation implements Serializable {

		
			private  long id;
			private  long rid;
			private  long uid;
			private  int type;
		
		public UserRoleRelation() {

		}
		
		
		public UserRoleRelation(long id, long rid, long uid, int type  ) {
				super();
			 this.id = id;
			 this.rid = rid;
			 this.uid = uid;
			 this.type = type;
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
		return "UserRoleRelation ["+
			" id:"+ id+
			" rid:"+ rid+
			" uid:"+ uid+
			" type:"+ type+
		"]";
	}
}