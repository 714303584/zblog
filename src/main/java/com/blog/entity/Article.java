package com.blog.entity;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author zhuss
 */
@SuppressWarnings("serial")
public class Article implements Serializable {

		
			private  long id;
			private  Date createDate;
			private  Date modifyDate;
			private  String author;
			private  String content;
			private  long hits;
			private  String cover;
			private  int isPublication;
			private  int isTop;
			private  String seoDescription;
			private  String seoKeywords;
			private  String seoTitle;
			private  String title;
			private  long articleCategory;
		
		public Article() {

		}
		
		
		public Article(long id, Date createDate, Date modifyDate, String author, String content, long hits, String cover, int isPublication, int isTop, String seoDescription, String seoKeywords, String seoTitle, String title, long articleCategory  ) {
				super();
			 this.id = id;
			 this.createDate = createDate;
			 this.modifyDate = modifyDate;
			 this.author = author;
			 this.content = content;
			 this.hits = hits;
			 this.cover = cover;
			 this.isPublication = isPublication;
			 this.isTop = isTop;
			 this.seoDescription = seoDescription;
			 this.seoKeywords = seoKeywords;
			 this.seoTitle = seoTitle;
			 this.title = title;
			 this.articleCategory = articleCategory;
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
				public  String  getauthor() {
					return author;
				}
			
				public void setauthor(String  author) {
					this.author =  author;
				}
				public  String  getcontent() {
					return content;
				}
			
				public void setcontent(String  content) {
					this.content =  content;
				}
				public  long  gethits() {
					return hits;
				}
			
				public void sethits(long  hits) {
					this.hits =  hits;
				}
				public  String  getcover() {
					return cover;
				}
			
				public void setcover(String  cover) {
					this.cover =  cover;
				}
				public  int  getisPublication() {
					return isPublication;
				}
			
				public void setisPublication(int  isPublication) {
					this.isPublication =  isPublication;
				}
				public  int  getisTop() {
					return isTop;
				}
			
				public void setisTop(int  isTop) {
					this.isTop =  isTop;
				}
				public  String  getseoDescription() {
					return seoDescription;
				}
			
				public void setseoDescription(String  seoDescription) {
					this.seoDescription =  seoDescription;
				}
				public  String  getseoKeywords() {
					return seoKeywords;
				}
			
				public void setseoKeywords(String  seoKeywords) {
					this.seoKeywords =  seoKeywords;
				}
				public  String  getseoTitle() {
					return seoTitle;
				}
			
				public void setseoTitle(String  seoTitle) {
					this.seoTitle =  seoTitle;
				}
				public  String  gettitle() {
					return title;
				}
			
				public void settitle(String  title) {
					this.title =  title;
				}
				public  long  getarticleCategory() {
					return articleCategory;
				}
			
				public void setarticleCategory(long  articleCategory) {
					this.articleCategory =  articleCategory;
				}
	@Override
	public String toString() {
		return "Article ["+
			" id:"+ id+
			" createDate:"+ createDate+
			" modifyDate:"+ modifyDate+
			" author:"+ author+
			" content:"+ content+
			" hits:"+ hits+
			" cover:"+ cover+
			" isPublication:"+ isPublication+
			" isTop:"+ isTop+
			" seoDescription:"+ seoDescription+
			" seoKeywords:"+ seoKeywords+
			" seoTitle:"+ seoTitle+
			" title:"+ title+
			" articleCategory:"+ articleCategory+
		"]";
	}
}