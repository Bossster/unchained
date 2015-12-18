package com.unchained.springmvc.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TripFilter extends Trip {

	private String tripBusType;
	
	public TripFilter() {
		super();
	}
	
	public TripFilter(Trip trip) {
		setId(trip.getId());
		setTripId(trip.getTripId());
		setTripDateFrom(trip.getTripDateFrom());
		setTripDateTo(trip.getTripDateTo());
		setTripFrom(trip.getTripFrom());
		setTripTo(trip.getTripTo());
		setTripPrice(trip.getTripPrice());
		setBus(trip.getBus());
	}

	@XmlElement
	public String getTripBusType() {
		return tripBusType;
	}

	public void setTripBusType(String tripBusType) {
		this.tripBusType = tripBusType;
	}
	
}
