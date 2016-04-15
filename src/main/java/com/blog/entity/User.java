package com.blog.entity;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author zhuss
 */
@SuppressWarnings("serial")
public class User implements Serializable {

		
			private  long id;
			private  Date createDate;
			private  Date modifyDate;
			private  String department;
			private  String email;
			private  int isEnabled;
			private  int isLocked;
			private  Date lockedDate;
			private  Date loginDate;
			private  int loginFailureCount;
			private  String loginIp;
			private  String name;
			private  String password;
			private  String username;
		
		public User() {

		}
		
		
		public User(long id, Date createDate, Date modifyDate, String department, String email, int isEnabled, int isLocked, Date lockedDate, Date loginDate, int loginFailureCount, String loginIp, String name, String password, String username  ) {
				super();
			 this.id = id;
			 this.createDate = createDate;
			 this.modifyDate = modifyDate;
			 this.department = department;
			 this.email = email;
			 this.isEnabled = isEnabled;
			 this.isLocked = isLocked;
			 this.lockedDate = lockedDate;
			 this.loginDate = loginDate;
			 this.loginFailureCount = loginFailureCount;
			 this.loginIp = loginIp;
			 this.name = name;
			 this.password = password;
			 this.username = username;
		}
		
		
				public  long  getid() {
					return id;
				}
			
				public void setid(long  id) {
					this.id =  id;
				}
				public  Date  getcreateDate() {
					return createDate;
				}
			
				public void setcreateDate(Date  createDate) {
					this.createDate =  createDate;
				}
				public  Date  getmodifyDate() {
					return modifyDate;
				}
			
				public void setmodifyDate(Date  modifyDate) {
					this.modifyDate =  modifyDate;
				}
				public  String  getdepartment() {
					return department;
				}
			
				public void setdepartment(String  department) {
					this.department =  department;
				}
				public  String  getemail() {
					return email;
				}
			
				public void setemail(String  email) {
					this.email =  email;
				}
				public  int  getisEnabled() {
					return isEnabled;
				}
			
				public void setisEnabled(int  isEnabled) {
					this.isEnabled =  isEnabled;
				}
				public  int  getisLocked() {
					return isLocked;
				}
			
				public void setisLocked(int  isLocked) {
					this.isLocked =  isLocked;
				}
				public  Date  getlockedDate() {
					return lockedDate;
				}
			
				public void setlockedDate(Date  lockedDate) {
					this.lockedDate =  lockedDate;
				}
				public  Date  getloginDate() {
					return loginDate;
				}
			
				public void setloginDate(Date  loginDate) {
					this.loginDate =  loginDate;
				}
				public  int  getloginFailureCount() {
					return loginFailureCount;
				}
			
				public void setloginFailureCount(int  loginFailureCount) {
					this.loginFailureCount =  loginFailureCount;
				}
				public  String  getloginIp() {
					return loginIp;
				}
			
				public void setloginIp(String  loginIp) {
					this.loginIp =  loginIp;
				}
				public  String  getname() {
					return name;
				}
			
				public void setname(String  name) {
					this.name =  name;
				}
				public  String  getpassword() {
					return password;
				}
			
				public void setpassword(String  password) {
					this.password =  password;
				}
				public  String  getusername() {
					return username;
				}
			
				public void setusername(String  username) {
					this.username =  username;
				}
	@Override
	public String toString() {
		return "User ["+
			" id:"+ id+
			" createDate:"+ createDate+
			" modifyDate:"+ modifyDate+
			" department:"+ department+
			" email:"+ email+
			" isEnabled:"+ isEnabled+
			" isLocked:"+ isLocked+
			" lockedDate:"+ lockedDate+
			" loginDate:"+ loginDate+
			" loginFailureCount:"+ loginFailureCount+
			" loginIp:"+ loginIp+
			" name:"+ name+
			" password:"+ password+
			" username:"+ username+
		"]";
	}
	
	
//	public Json
}