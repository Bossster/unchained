package com.unchained.springmvc.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.unchained.springmvc.model.Article;
import com.unchained.springmvc.model.Message;

@Service("blogService")
@Transactional
public class BlogServiceImpl implements BlogService {
	
	private Map<String, String> messages = new HashMap<String, String>();
	
	private Map<Long, Article> articles = new HashMap<Long, Article>();

	private MessageSource messageSource;
	
	@Autowired
	public BlogServiceImpl(MessageSource messageSource) {
		this.messageSource = messageSource;
		
		initMessages();
		initArticles();
	}
	
	private void initMessages() {
		messages.put("world", messageSource.getMessage("helloText", null, null));
	}
	
	private void initArticles() {
		articles.put(1L, new Article(1L, "title", "body"));
	}

	@Override
	public List<Message> findAllHelloTexts() {
		List<Message> result = new ArrayList<Message>();
		Set<Entry<String, String>> entrySet = messages.entrySet();
		for (Entry<String, String> entry : entrySet) {
			result.add(new Message(entry.getKey(), entry.getValue()));
		}
		return result;
	}

	@Override
	public String findHelloText(String key) {
		String result = messages.get(key);
		if (StringUtils.isEmpty(result)) {
			return result;
		}
		return result;
	}

	@Override
	public boolean helloTextExists(String key, String text) {
		if (messages.get(key) != null) {
			return true;
		}
		Collection<String> values = messages.values();
		if (values.contains(text)) {
			return true;
		}
		return false;
	}

	@Override
	public void updateHelloText(String key, String text) {
		messages.put(key, text);
	}

	@Override
	public void deleteHelloText(String key) {
		messages.remove(key);
	}

}
