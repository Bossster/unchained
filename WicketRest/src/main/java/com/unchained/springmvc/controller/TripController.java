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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.unchained.springmvc.model.Trip;
import com.unchained.springmvc.model.TripFilter;
import com.unchained.springmvc.service.TripService;

@RestController
public class TripController {

	private static final Logger LOG = LoggerContext.getContext().getLogger("org.apache.wicket");

	@Autowired
	TripService tripService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/triplist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TripFilter>> listTrips() {
		List<Trip> trips = tripService.findAllTrips();
		if (trips.isEmpty()) {
			return new ResponseEntity<List<TripFilter>>(HttpStatus.NO_CONTENT);
		}
		List<TripFilter> tripList = new ArrayList<TripFilter>();
		for (Trip trip : trips) {
			tripList.add(new TripFilter(trip));
		}
		LOG.info(tripList);
		return new ResponseEntity<List<TripFilter>>(tripList, HttpStatus.OK);
	}

	@RequestMapping(value = "/trip/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Trip> getTrip(@PathVariable Long id) {
		Trip trip = tripService.findTripById(id);
		if (trip == null) {
			return new ResponseEntity<Trip>(HttpStatus.NOT_FOUND);
		}
		LOG.info(trip);
		return new ResponseEntity<Trip>(trip, HttpStatus.OK);
	}

	@RequestMapping(value = "/trip", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> postTrip(@RequestBody Trip trip, UriComponentsBuilder uriBuilder) {
		LOG.info(trip);
		Trip tripById = tripService.findTripById(trip.getId());
		if (tripById != null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		trip.setId(null);
		tripService.saveTrip(trip);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriBuilder.path("/trip/{id}").buildAndExpand(trip.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/trip/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Trip> putTrip(@PathVariable Long id, @RequestBody Trip trip) {
		LOG.info(trip);
		if (trip.getId() == null || id == null || !trip.getId().equals(id)) {
			return new ResponseEntity<Trip>(HttpStatus.NOT_FOUND);
		}
		Trip tripById = tripService.findTripById(trip.getId());
		if (tripById == null) {
			return new ResponseEntity<Trip>(HttpStatus.NOT_FOUND);
		}
		tripService.update(trip);
		return new ResponseEntity<Trip>(trip, HttpStatus.OK);
	}

	@RequestMapping(value = "/trip/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Trip> deleteTrip(@PathVariable Long id) {
		Trip tripById = tripService.findTripById(id);
		if (tripById == null) {
			return new ResponseEntity<Trip>(HttpStatus.NOT_FOUND);
		}
		tripService.deleteTripById(id);
		return new ResponseEntity<Trip>(HttpStatus.OK);
	}

}
