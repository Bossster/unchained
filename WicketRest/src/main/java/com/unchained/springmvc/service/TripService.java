package com.unchained.springmvc.service;

import java.util.List;

import com.unchained.springmvc.model.Trip;
import com.unchained.springmvc.model.TripFilter;

public interface TripService {

	Trip findTripById(Long id) throws Exception;

	Trip findTripByTripId(String tripId) throws Exception;

	List<Trip> findAllTrips() throws Exception;

	void saveTrip(TripFilter trip) throws Exception;

	void update(TripFilter trip) throws Exception;

	void deleteTripById(Long id) throws Exception;
	
	Integer getAvailableSeats(String tripId) throws Exception;

}
