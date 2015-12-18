package com.unchained.springmvc.controller;

import java.util.ArrayList;
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

import com.unchained.springmvc.model.Bus;
import com.unchained.springmvc.model.BusFilter;
import com.unchained.springmvc.service.BusService;

@RestController
public class BusController {

	private static final Logger LOG = LoggerContext.getContext().getLogger("org.apache.wicket");

	@Autowired
	BusService busService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/buslist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BusFilter>> listBuses() {
		List<Bus> buses = null;
		try {
			buses = busService.findAllBuses();
		} catch (Exception e) {
			LOG.error(e);
			return new ResponseEntity<List<BusFilter>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (buses != null && !buses.isEmpty()) {
			List<BusFilter> busList = new ArrayList<BusFilter>();
			for (Bus bus : buses) {
				busList.add(new BusFilter(bus));
			}
			LOG.info(busList);
			return new ResponseEntity<List<BusFilter>>(busList, HttpStatus.OK);
		}
		return new ResponseEntity<List<BusFilter>>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/bus/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bus> getBus(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<Bus>(HttpStatus.BAD_REQUEST);
		}
		Bus bus = null;
		try {
			bus = busService.findBusById(id);
		} catch (Exception e) {
			LOG.error(e);
			return new ResponseEntity<Bus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (bus != null) {
			LOG.info(bus);
			return new ResponseEntity<Bus>(bus, HttpStatus.OK);
		}
		return new ResponseEntity<Bus>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/bus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> postBus(@RequestBody Bus bus, UriComponentsBuilder uriBuilder) {
		LOG.info(bus);
		if (bus == null || StringUtils.isEmpty(bus.getBusType()) || StringUtils.isEmpty(bus.getMaxSeats())) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		try {
			Bus busByBusType = busService.findBusByBusType(bus.getBusType());
			if (busByBusType != null) {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
			bus.setId(null);
			busService.saveBus(bus);
		} catch (Exception e) {
			LOG.error(e);
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriBuilder.path("/bus/{id}").buildAndExpand(bus.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/bus/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bus> putBus(@PathVariable Long id, @RequestBody Bus bus) {
		LOG.info(bus);
		if (id == null || bus == null || bus.getId() == null || !bus.getId().equals(id)) {
			return new ResponseEntity<Bus>(HttpStatus.BAD_REQUEST);
		}
		try {
			Bus busById = busService.findBusById(bus.getId());
			if (busById == null) {
				return new ResponseEntity<Bus>(HttpStatus.NOT_FOUND);
			}
			if (!busById.getBusType().equals(bus.getBusType())) {
				Bus busByBusType = busService.findBusByBusType(bus.getBusType());
				if (busByBusType != null) {
					return new ResponseEntity<Bus>(HttpStatus.CONFLICT);
				}
			}
			busService.update(bus);
		} catch (Exception e) {
			LOG.error(e);
			return new ResponseEntity<Bus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Bus>(bus, HttpStatus.OK);
	}

	@RequestMapping(value = "/bus/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bus> deleteBus(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<Bus>(HttpStatus.BAD_REQUEST);
		}
		try {
			Bus busById = busService.findBusById(id);
			if (busById == null) {
				return new ResponseEntity<Bus>(HttpStatus.NOT_FOUND);
			}
			busService.deleteBusById(id);
		} catch (Exception e) {
			LOG.error(e);
			return new ResponseEntity<Bus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Bus>(HttpStatus.OK);
	}

}
