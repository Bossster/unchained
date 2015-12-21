package com.unchained.springmvc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unchained.springmvc.dao.BusDao;
import com.unchained.springmvc.dao.SeatDao;
import com.unchained.springmvc.dao.TripDao;
import com.unchained.springmvc.model.Bus;
import com.unchained.springmvc.model.Seat;
import com.unchained.springmvc.model.Trip;
import com.unchained.springmvc.model.TripFilter;

@Service("tripService")
@Transactional
public class TripServiceImpl implements TripService {

	@Autowired
	TripDao tripDao;

	@Autowired
	BusDao busDao;

	@Autowired
	SeatDao seatDao;

	@Override
	public Trip findTripById(Long id) throws Exception {
		if (id == null) {
			return null;
		}
		return tripDao.findById(id);
	}

	@Override
	public Trip findTripByTripId(String tripId) throws Exception {
		if (StringUtils.isEmpty(tripId)) {
			return null;
		}
		return tripDao.findByTripId(tripId);
	}

	@Override
	public List<Trip> findAllTrips() throws Exception {
		return tripDao.findAll();
	}

	@Override
	public void saveTrip(TripFilter trip) throws Exception {
		Bus bus = busDao.findByBusType(trip.getTripBusType());
		Trip entity = new Trip();
		entity.setBus(bus);
		entity.setTripDateFrom(trip.getTripDateFrom());
		entity.setTripDateTo(trip.getTripDateFrom());
		entity.setTripFrom(trip.getTripFrom());
		entity.setTripTo(trip.getTripTo());
		entity.setTripId(trip.getTripId());
		entity.setTripPrice(trip.getTripPrice());
		tripDao.save(entity);
	}

	@Override
	public void update(TripFilter trip) throws Exception {
		Trip found = tripDao.findById(trip.getId());
		if (found != null) {
			Bus bus = busDao.findByBusType(trip.getTripBusType());
			found.setBus(bus);
			found.setTripDateFrom(trip.getTripDateFrom());
			found.setTripDateTo(trip.getTripDateFrom());
			found.setTripFrom(trip.getTripFrom());
			found.setTripTo(trip.getTripTo());
			found.setTripId(trip.getTripId());
			found.setTripPrice(trip.getTripPrice());
		}
	}

	@Override
	public void deleteTripById(Long id) throws Exception {
		tripDao.deleteById(id);
	}

	@Override
	public Integer getAvailableSeats(String tripId) throws Exception {
		List<Seat> seats = seatDao.findAllByTripId(tripId);
		if (seats != null) {
			return seats.size();
		}
		return null;
	}

}
