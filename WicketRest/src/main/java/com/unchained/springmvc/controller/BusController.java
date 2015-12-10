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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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

	@RequestMapping(value = "/bus/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bus> getBus(@PathVariable Long id) {
		Bus bus = busService.findBusById(id);
		if (bus == null) {
			return new ResponseEntity<Bus>(HttpStatus.NOT_FOUND);
		}
		LOG.info(bus);
		return new ResponseEntity<Bus>(bus, HttpStatus.OK);
	}

	@RequestMapping(value = "/bus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> postBus(@RequestBody Bus bus, UriComponentsBuilder uriBuilder) {
		LOG.info(bus);
		Bus busByBusType = busService.findBusByBusType(bus.getBusType());
		if (busByBusType != null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		bus.setId(null);
		busService.saveBus(bus);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriBuilder.path("/bus/{id}").buildAndExpand(bus.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/bus/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bus> putBus(@PathVariable Long id, @RequestBody Bus bus) {
		LOG.info(bus);
		if (bus.getId() == null || id == null || !bus.getId().equals(id)) {
			return new ResponseEntity<Bus>(HttpStatus.NOT_FOUND);
		}
		Bus busById = busService.findBusById(bus.getId());
		if (busById == null) {
			return new ResponseEntity<Bus>(HttpStatus.NOT_FOUND);
		}
		busService.update(bus);
		return new ResponseEntity<Bus>(bus, HttpStatus.OK);
	}

	@RequestMapping(value = "/bus/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bus> deleteBus(@PathVariable Long id) {
		Bus busById = busService.findBusById(id);
		if (busById == null) {
			return new ResponseEntity<Bus>(HttpStatus.NOT_FOUND);
		}
		busService.deleteBusById(id);
		return new ResponseEntity<Bus>(HttpStatus.OK);
	}

}
