package com.blog.entity;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author zhuss
 */
@SuppressWarnings("serial")
public class Subscriber implements Serializable {

		
			private  int id;
			private  String email;
		
		public Subscriber() {

		}
		
		
		public Subscriber(int id, String email  ) {
				super();
			 this.id = id;
			 this.email = email;
		}
		
		
				public  int  getid() {
					return id;
				}
			
				public void setid(int  id) {
					this.id =  id;
				}
				public  String  getemail() {
					return email;
				}
			
				public void setemail(String  email) {
					this.email =  email;
				}
	@Override
	public String toString() {
		return "Subscriber ["+
			" id:"+ id+
			" email:"+ email+
		"]";
	}
}