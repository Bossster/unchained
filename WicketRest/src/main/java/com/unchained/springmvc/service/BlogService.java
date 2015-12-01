package com.unchained.springmvc.service;

public interface BlogService {

	String findHelloText(String key);
	
	void updateHelloText(String key, String text);
	
}
