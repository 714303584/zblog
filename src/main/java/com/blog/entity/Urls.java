package com.blog.entity;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author zhuss
 */
@SuppressWarnings("serial")
public class Urls implements Serializable {

		
			private  long id;
			private  long pid;
			private  String name;
			private  String url;
			private  String description;
			private  String pname;
		
		public Urls() {

		}
		
		
		public Urls(long id, long pid, String name, String url, String description, String pname  ) {
				super();
			 this.id = id;
			 this.pid = pid;
			 this.name = name;
			 this.url = url;
			 this.description = description;
			 this.pname = pname;
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
				public  String  getname() {
					return name;
				}
			
				public void setname(String  name) {
					this.name =  name;
				}
				public  String  geturl() {
					return url;
				}
			
				public void seturl(String  url) {
					this.url =  url;
				}
				public  String  getdescription() {
					return description;
				}
			
				public void setdescription(String  description) {
					this.description =  description;
				}
				public  String  getpname() {
					return pname;
				}
			
				public void setpname(String  pname) {
					this.pname =  pname;
				}
	@Override
	public String toString() {
		return "Urls ["+
			" id:"+ id+
			" pid:"+ pid+
			" name:"+ name+
			" url:"+ url+
			" description:"+ description+
			" pname:"+ pname+
		"]";
	}
}