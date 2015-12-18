package com.unchained.springmvc.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReservationFilter extends Reservation {

	public ReservationFilter(Reservation reservation) {
		setId(reservation.getId());
		setReservationDate(reservation.getReservationDate());
		setReservationId(reservation.getReservationId());
		setTripId(reservation.getTripId());
		setTemporary(reservation.getTemporary());
		setBus(reservation.getBus());
		setSeats(reservation.getSeats());
	}

}
