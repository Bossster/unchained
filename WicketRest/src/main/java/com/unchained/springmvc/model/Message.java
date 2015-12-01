package com.unchained.springmvc.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
	
	private String id;
	
	private String message;
	
	public Message() {
		super();
	}
	
	public Message(String id, String message) {
		this.id = id;
		this.message = message;
	}

	@XmlElement
	public String getId() {
		return id;
	}

	@XmlElement
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return message;
	}

}
