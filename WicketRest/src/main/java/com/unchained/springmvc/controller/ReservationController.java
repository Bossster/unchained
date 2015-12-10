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

import com.unchained.springmvc.model.Reservation;
import com.unchained.springmvc.service.ReservationService;

@RestController
public class ReservationController {

	private static final Logger LOG = LoggerContext.getContext().getLogger("org.apache.wicket");

	@Autowired
	ReservationService reservationService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/reservationlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Reservation>> listReservations() {
		List<Reservation> reservations = reservationService.findAllReservations();
		LOG.info(reservations);
		if (reservations.isEmpty()) {
			return new ResponseEntity<List<Reservation>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
	}

	@RequestMapping(value = "/reservation/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> getReservation(@PathVariable Long id) {
		Reservation reservation = reservationService.findReservationById(id);
		if (reservation == null) {
			return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
		}
		LOG.info(reservation);
		return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
	}

	@RequestMapping(value = "/reservation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> postReservation(@RequestBody Reservation reservation, UriComponentsBuilder uriBuilder) {
		LOG.info(reservation);
		Reservation reservationById = reservationService.findReservationById(reservation.getId());
		if (reservationById != null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		reservation.setId(null);
		reservationService.saveReservation(reservation);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriBuilder.path("/reservation/{id}").buildAndExpand(reservation.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/reservation/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> putReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
		LOG.info(reservation);
		if (reservation.getId() == null || id == null || !reservation.getId().equals(id)) {
			return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
		}
		Reservation reservationById = reservationService.findReservationById(reservation.getId());
		if (reservationById == null) {
			return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
		}
		reservationService.update(reservation);
		return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
	}

	@RequestMapping(value = "/reservation/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> deleteReservation(@PathVariable Long id) {
		Reservation reservationById = reservationService.findReservationById(id);
		if (reservationById == null) {
			return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
		}
		reservationService.deleteReservationById(id);
		return new ResponseEntity<Reservation>(HttpStatus.OK);
	}

}
