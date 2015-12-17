package com.unchained.springmvc.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TripFilter extends Trip {

	public TripFilter(Trip trip) {
		setId(trip.getId());
		setTripId(trip.getTripId());
		setTripDateFrom(trip.getTripDateFrom());
		setTripDateTo(trip.getTripDateTo());
		setTripFrom(trip.getTripFrom());
		setTripTo(trip.getTripTo());
		setTripPrice(trip.getTripPrice());
		setBus(trip.getBus());
		setReservations(trip.getReservations());
	}

}
