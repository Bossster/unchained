package com.unchained.springmvc.service;

import java.util.List;

import com.unchained.springmvc.model.Message;

public interface BlogService {

	List<Message> findAllHelloTexts();
	
	String findHelloText(String key);
	
	boolean helloTextExists(String key, String text);
	
	void updateHelloText(String key, String text);
	
	void deleteHelloText(String key);
	
}
