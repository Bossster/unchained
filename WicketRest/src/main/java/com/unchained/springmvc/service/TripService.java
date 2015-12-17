package com.unchained.springmvc.service;

import java.util.List;

import com.unchained.springmvc.model.Trip;

public interface TripService {

	Trip findTripById(Long id);

	List<Trip> findAllTrips();

	void saveTrip(Trip trip);

	void update(Trip trip);

	void deleteTripById(Long id);

}
