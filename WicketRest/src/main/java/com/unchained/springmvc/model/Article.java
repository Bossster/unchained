package com.unchained.springmvc.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Article {
	
	private String id;
	
	private String title;
	
	private String body;
	
	public Article() {
		super();
	}
	
	public Article(String id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}

	@XmlElement
	public String getId() {
		return id;
	}

	@XmlElement
	public String getTitle() {
		return title;
	}
	
	@XmlElement
	public String getBody() {
		return body;
	}
	
	@Override
	public String toString() {
		return "Article " + id + " " + title + " " + body;
	}

}