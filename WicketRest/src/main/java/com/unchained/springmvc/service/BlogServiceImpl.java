package com.unchained.springmvc.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {
	
	private Map<String, String> entries = new HashMap<String, String>();
	
	@Autowired
	public BlogServiceImpl(MessageSource messageSource) {
		entries.put("world", messageSource.getMessage("helloText", null, null));
	}

	@Override
	public String findHelloText(String key) {
		String result = entries.get(key);
		if (StringUtils.isEmpty(result)) {
			return result;
		}
		return result;
	}

	@Override
	public void updateHelloText(String key, String text) {
		entries.put(key, text);
	}

}
