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

import com.unchained.springmvc.model.Message;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {
	
	private Map<String, String> entries = new HashMap<String, String>();
	
	@Autowired
	public BlogServiceImpl(MessageSource messageSource) {
		entries.put("world", messageSource.getMessage("helloText", null, null));
	}

	@Override
	public List<Message> findAllHelloTexts() {
		List<Message> result = new ArrayList<Message>();
		Set<Entry<String, String>> entrySet = entries.entrySet();
		for (Entry<String, String> entry : entrySet) {
			result.add(new Message(entry.getKey(), entry.getValue()));
		}
		return result;
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
	public boolean helloTextExists(String key, String text) {
		if (entries.get(key) != null) {
			return true;
		}
		Collection<String> values = entries.values();
		if (values.contains(text)) {
			return true;
		}
		return false;
	}

	@Override
	public void updateHelloText(String key, String text) {
		entries.put(key, text);
	}

	@Override
	public void deleteHelloText(String key) {
		entries.remove(key);
	}

}
