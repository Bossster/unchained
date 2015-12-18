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
import com.unchained.springmvc.model.Trip;
import com.unchained.springmvc.model.TripFilter;
import com.unchained.springmvc.service.BusService;
import com.unchained.springmvc.service.TripService;

@RestController
public class TripController {

	private static final Logger LOG = LoggerContext.getContext().getLogger("org.apache.wicket");

	@Autowired
	TripService tripService;

	@Autowired
	BusService busService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/triplist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TripFilter>> listTrips() {
		List<Trip> trips = null;
		try {
			trips = tripService.findAllTrips();
		} catch (Exception e) {
			LOG.error(e);
			return new ResponseEntity<List<TripFilter>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (trips != null && !trips.isEmpty()) {
			List<TripFilter> tripList = new ArrayList<TripFilter>();
			for (Trip trip : trips) {
				tripList.add(new TripFilter(trip));
			}
			LOG.info(tripList);
			return new ResponseEntity<List<TripFilter>>(tripList, HttpStatus.OK);
		}
		return new ResponseEntity<List<TripFilter>>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/trip/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Trip> getTrip(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<Trip>(HttpStatus.BAD_REQUEST);
		}
		Trip trip = null;
		try {
			trip = tripService.findTripById(id);
		} catch (Exception e) {
			LOG.error(e);
			return new ResponseEntity<Trip>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (trip != null) {
			LOG.info(trip);
			return new ResponseEntity<Trip>(trip, HttpStatus.OK);
		}
		return new ResponseEntity<Trip>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/trip", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> postTrip(@RequestBody TripFilter trip, UriComponentsBuilder uriBuilder) {
		LOG.info(trip);
		if (trip == null || StringUtils.isEmpty(trip.getTripId()) || StringUtils.isEmpty(trip.getTripBusType())) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		try {
			Trip tripByTripId = tripService.findTripByTripId(trip.getTripId());
			if (tripByTripId != null) {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
			Bus busByBusType = busService.findBusByBusType(trip.getTripBusType());
			if (busByBusType == null) {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
			trip.setId(null);
			tripService.saveTrip(trip);
		} catch (Exception e) {
			LOG.error(e);
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriBuilder.path("/trip/{id}").buildAndExpand(trip.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/trip/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Trip> putTrip(@PathVariable Long id, @RequestBody TripFilter trip) {
		LOG.info(trip);
		if (id == null || trip == null || trip.getId() == null || !trip.getId().equals(id)) {
			return new ResponseEntity<Trip>(HttpStatus.BAD_REQUEST);
		}
		try {
			Trip tripById = tripService.findTripById(trip.getId());
			if (tripById == null) {
				return new ResponseEntity<Trip>(HttpStatus.NOT_FOUND);
			}
			if (!tripById.getTripId().equals(trip.getTripId())) {
				Trip tripByTripId = tripService.findTripByTripId(trip.getTripId());
				if (tripByTripId != null) {
					return new ResponseEntity<Trip>(HttpStatus.CONFLICT);
				}
			}
			tripService.update(trip);
		} catch (Exception e) {
			LOG.error(e);
			return new ResponseEntity<Trip>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Trip>(trip, HttpStatus.OK);
	}

	@RequestMapping(value = "/trip/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Trip> deleteTrip(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<Trip>(HttpStatus.BAD_REQUEST);
		}
		try {
			Trip tripById = tripService.findTripById(id);
			if (tripById == null) {
				return new ResponseEntity<Trip>(HttpStatus.NOT_FOUND);
			}
			tripService.deleteTripById(id);
		} catch (Exception e) {
			LOG.error(e);
			return new ResponseEntity<Trip>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Trip>(HttpStatus.OK);
	}

}
