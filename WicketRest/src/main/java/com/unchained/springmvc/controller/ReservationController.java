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

import com.unchained.springmvc.model.Reservation;
import com.unchained.springmvc.model.ReservationFilter;
import com.unchained.springmvc.model.Trip;
import com.unchained.springmvc.service.ReservationService;
import com.unchained.springmvc.service.TripService;

@RestController
public class ReservationController {

	private static final Logger LOG = LoggerContext.getContext().getLogger("org.apache.wicket");

	@Autowired
	ReservationService reservationService;

	@Autowired
	TripService tripService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/reservationlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReservationFilter>> listReservations() {
		List<Reservation> reservations = null;
		try {
			//TODO
			List<ReservationFilter> found = reservationService.findReservations();
			reservations = reservationService.findAllReservations();
		} catch (Exception e) {
			LOG.error(e);
			return new ResponseEntity<List<ReservationFilter>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (reservations != null && !reservations.isEmpty()) {
			List<ReservationFilter> reservationList = new ArrayList<ReservationFilter>();
			for (Reservation reservation : reservations) {
				reservationList.add(new ReservationFilter(reservation));
			}
			LOG.info(reservationList);
			return new ResponseEntity<List<ReservationFilter>>(reservationList, HttpStatus.OK);
		}
		return new ResponseEntity<List<ReservationFilter>>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/reservation/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> getReservation(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<Reservation>(HttpStatus.BAD_REQUEST);
		}
		Reservation reservation = null;
		try {
			reservation = reservationService.findReservationById(id);
		} catch (Exception e) {
			LOG.error(e);
			return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (reservation != null) {
			LOG.info(reservation);
			return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
		}
		return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/reservation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> postReservation(@RequestBody ReservationFilter reservation,
			UriComponentsBuilder uriBuilder) {

		LOG.info(reservation);
		if (reservation == null || StringUtils.isEmpty(reservation.getReservationId())
				|| StringUtils.isEmpty(reservation.getTripId())) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		try {
			Reservation reservationByReservationId = reservationService
					.findReservationByReservationId(reservation.getReservationId());
			if (reservationByReservationId != null) {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
			Trip tripByTripId = tripService.findTripByTripId(reservation.getTripId());
			if (tripByTripId == null) {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
			reservation.setId(null);
			reservationService.saveReservation(reservation);
		} catch (Exception e) {
			LOG.error(e);
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriBuilder.path("/reservation/{id}").buildAndExpand(reservation.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/reservation/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> putReservation(@PathVariable Long id,
			@RequestBody ReservationFilter reservation) {

		LOG.info(reservation);
		if (id == null || reservation == null || reservation.getId() == null || !reservation.getId().equals(id)) {
			return new ResponseEntity<Reservation>(HttpStatus.BAD_REQUEST);
		}
		try {
			Reservation reservationById = reservationService.findReservationById(reservation.getId());
			if (reservationById == null) {
				return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
			}
			if (!reservationById.getReservationId().equals(reservation.getReservationId())) {
				Reservation reservationByReservationId = reservationService
						.findReservationByReservationId(reservation.getReservationId());
				if (reservationByReservationId != null) {
					return new ResponseEntity<Reservation>(HttpStatus.CONFLICT);
				}
			}
			reservationService.update(reservation);
		} catch (Exception e) {
			LOG.error(e);
			return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
	}

	@RequestMapping(value = "/reservation/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> deleteReservation(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<Reservation>(HttpStatus.BAD_REQUEST);
		}
		try {
			Reservation reservationById = reservationService.findReservationById(id);
			if (reservationById == null) {
				return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
			}
			reservationService.deleteReservationById(id);
		} catch (Exception e) {
			LOG.error(e);
			return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Reservation>(HttpStatus.OK);
	}

}
