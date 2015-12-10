package com.unchained.springmvc.controller;

import java.util.List;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.unchained.springmvc.model.Message;
import com.unchained.springmvc.service.BlogService;

@RestController
public class ResourceController {

	private static final Logger LOG = LoggerContext.getContext().getLogger("org.apache.wicket");

	@Autowired
	BlogService blogService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/hellorest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Message helloWorld() {
		Message message = new Message(null, messageSource.getMessage("helloMessage", null, null));
		LOG.info(message);
		return message;
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Message>> getHello() {
		List<Message> messages = blogService.findAllHelloTexts();
		LOG.info(messages);
		if (messages.isEmpty()) {
			return new ResponseEntity<List<Message>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
	}

	@RequestMapping(value = "/hello/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> getHello(@PathVariable String id) {
		String text = blogService.findHelloText(id);
		if (StringUtils.isEmpty(text)) {
			return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
		}
		Message message = new Message(id, "Hello " + blogService.findHelloText(id));
		LOG.info(message);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "/hello", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> postHello(@RequestBody Message message, UriComponentsBuilder uriBuilder) {
		LOG.info(message);
		if (blogService.helloTextExists(message.getId(), message.getMessage())) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		blogService.updateHelloText(message.getId(), message.getMessage());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriBuilder.path("/hello/{id}").buildAndExpand(message.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/hello/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> putHello(@PathVariable String id, @RequestBody Message message) {
		LOG.info(message);
		String text = blogService.findHelloText(id);
		if (StringUtils.isEmpty(text)) {
			return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
		}
		blogService.updateHelloText(message.getId(), message.getMessage());
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "/hello/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> deleteHello(@PathVariable String id) {
		String text = blogService.findHelloText(id);
		if (StringUtils.isEmpty(text)) {
			return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
		}
		blogService.deleteHelloText(id);
		return new ResponseEntity<Message>(HttpStatus.OK);
	}

}
