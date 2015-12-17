package com.unchained.springmvc.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReservationFilter extends Reservation {

	public ReservationFilter(Reservation reservation) {
		setId(reservation.getId());
		setReservationDate(reservation.getReservationDate());
		setReservationId(reservation.getReservationId());
		setTemporary(reservation.getTemporary());
		setSeats(reservation.getSeats());
		setTrip(reservation.getTrip());
	}

}
