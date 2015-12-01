package com.unchained.springmvc.controller;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unchained.springmvc.model.Message;
import com.unchained.springmvc.service.BlogService;

@RestController
public class ResourceController {

	private static final Logger LOG = LoggerContext.getContext().getLogger("org.apache.wicket");
	
	@Autowired
	BlogService blogService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message hello() {
        Message message = new Message(null, messageSource.getMessage("helloMessage", null, null));
		LOG.info(message);
        return message;
    }
	
	@RequestMapping(value = "/hello/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> hello(@PathVariable String id) {
		String text = blogService.findHelloText(id);
		if (StringUtils.isEmpty(text)) {
			return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
		}
        Message message = new Message(id, "Hello " + blogService.findHelloText(id));
		LOG.info(message);
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/hello", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> postHello(@RequestBody Message message) {
		LOG.info(message);
		String text = blogService.findHelloText(message.getId());
		if (!StringUtils.isEmpty(text)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		blogService.updateHelloText(message.getId(), message.getMessage());
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
}
