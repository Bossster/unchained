package com.unchained.springmvc.controller;

import java.util.List;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unchained.springmvc.model.Bus;
import com.unchained.springmvc.service.BusService;

@RestController
public class BusController {

	private static final Logger LOG = LoggerContext.getContext().getLogger("org.apache.wicket");

	@Autowired
	BusService busService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/buslist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Bus>> listBuses() {
		List<Bus> buses = busService.findAllBuses();
		LOG.info(buses);
		if (buses.isEmpty()) {
			return new ResponseEntity<List<Bus>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Bus>>(buses, HttpStatus.OK);
	}

}
