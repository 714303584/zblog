package com.blog.entity;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author zhuss
 */
@SuppressWarnings("serial")
public class ArticleComment implements Serializable {

		
			private  long id;
			private  String name;
			private  String email;
			private  Date createDate;
			private  String content;
			private  long aid;
			private  int status;
		
		public ArticleComment() {

		}
		
		
		public ArticleComment(long id, String name, String email, Date createDate, String content, long aid, int status  ) {
				super();
			 this.id = id;
			 this.name = name;
			 this.email = email;
			 this.createDate = createDate;
			 this.content = content;
			 this.aid = aid;
			 this.status = status;
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
				public  String  getemail() {
					return email;
				}
			
				public void setemail(String  email) {
					this.email =  email;
				}
				public  Date  getcreateDate() {
					return createDate;
				}
			
				public void setcreateDate(Date  createDate) {
					this.createDate =  createDate;
				}
				public  String  getcontent() {
					return content;
				}
			
				public void setcontent(String  content) {
					this.content =  content;
				}
				public  long  getaid() {
					return aid;
				}
			
				public void setaid(long  aid) {
					this.aid =  aid;
				}
				public  int  getstatus() {
					return status;
				}
			
				public void setstatus(int  status) {
					this.status =  status;
				}
	@Override
	public String toString() {
		return "ArticleComment ["+
			" id:"+ id+
			" name:"+ name+
			" email:"+ email+
			" createDate:"+ createDate+
			" content:"+ content+
			" aid:"+ aid+
			" status:"+ status+
		"]";
	}
}