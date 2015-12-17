package com.unchained.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.unchained.springmvc.model.Trip;

@Repository
public class TripDaoImpl extends AbstractDao<Long, Trip> implements TripDao {

	@Override
	public Trip findById(Long id) {
		Trip trip = getByKey(id);
		return trip;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Trip> findAll() {
		Criteria criteria = createCriteria().addOrder(Order.asc("tripId"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Trip> trips = (List<Trip>) criteria.list();
		return trips;
	}

	@Override
	public void save(Trip trip) {
		persist(trip);
	}

	@Override
	public void deleteById(Long id) {
		Trip trip = getByKey(id);
		delete(trip);
	}

}
