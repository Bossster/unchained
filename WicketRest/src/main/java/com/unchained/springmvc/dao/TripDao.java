package com.unchained.springmvc.dao;

import java.util.List;

import com.unchained.springmvc.model.Trip;

public interface TripDao extends AbstractHibernateDao<Long, Trip> {

	@Override
	Trip findById(Long id);

	Trip findByTripId(String tripId);

	@Override
	List<Trip> findAll();

	@Override
	void save(Trip trip);

	@Override
	void deleteById(Long id);

}
