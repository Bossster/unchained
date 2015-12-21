package com.unchained.springmvc.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TripFilter extends Trip {

	private String tripBusType;
	
	private Integer tripTotalPrice;

	private Integer tripSeatCount;

	public TripFilter() {
		super();
	}

	public TripFilter(Trip trip, Integer seatCount, Integer tripSeatCount) {
		setId(trip.getId());
		setTripId(trip.getTripId());
		setTripDateFrom(trip.getTripDateFrom());
		setTripDateTo(trip.getTripDateTo());
		setTripFrom(trip.getTripFrom());
		setTripTo(trip.getTripTo());
		setTripPrice(trip.getTripPrice());
		setBus(trip.getBus());
		
		setTripTotalPrice(getTripPrice() * seatCount);
		setTripSeatCount(tripSeatCount);
	}

	@XmlElement
	public String getTripBusType() {
		return tripBusType;
	}

	public void setTripBusType(String tripBusType) {
		this.tripBusType = tripBusType;
	}
	
	@XmlElement
	public Integer getTripTotalPrice() {
		return tripTotalPrice;
	}

	public void setTripTotalPrice(Integer tripTotalPrice) {
		this.tripTotalPrice = tripTotalPrice;
	}

	@XmlElement
	public Integer getTripSeatCount() {
		return tripSeatCount;
	}

	public void setTripSeatCount(Integer tripSeatCount) {
		this.tripSeatCount = tripSeatCount;
	}

}
