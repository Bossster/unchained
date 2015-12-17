package com.unchained.springmvc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unchained.springmvc.dao.TripDao;
import com.unchained.springmvc.model.Trip;

@Service("tripService")
@Transactional
public class TripServiceImpl implements TripService {

	@Autowired
	TripDao tripDao;

	@Override
	public Trip findTripById(Long id) {
		if (id == null) {
			return null;
		}
		return tripDao.findById(id);
	}

	@Override
	public List<Trip> findAllTrips() {
		return tripDao.findAll();
	}

	@Override
	public void saveTrip(Trip trip) {
		tripDao.save(trip);
	}

	@Override
	public void update(Trip trip) {
		Trip found = tripDao.findById(trip.getId());
		if (found != null) {
			found.setTripDateFrom(trip.getTripDateFrom());
			found.setTripDateTo(trip.getTripDateFrom());
			found.setTripFrom(trip.getTripFrom());
			found.setTripTo(trip.getTripTo());
			found.setTripId(trip.getTripId());
			found.setTripPrice(trip.getTripPrice());
			found.setBus(trip.getBus());
			found.setReservations(trip.getReservations());
		}
	}

	@Override
	public void deleteTripById(Long id) {
		tripDao.deleteById(id);
	}

}
