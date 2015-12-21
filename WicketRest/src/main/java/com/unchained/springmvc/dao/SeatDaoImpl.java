package com.unchained.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.unchained.springmvc.model.Seat;

@Repository
public class SeatDaoImpl extends AbstractDao<Long, Seat> implements SeatDao {

	@Override
	public Seat findById(Long id) {
		Seat seat = getByKey(id);
		return seat;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Seat> findAll() {
		Criteria criteria = createCriteria().addOrder(Order.asc("seatNumber"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Seat> seats = (List<Seat>) criteria.list();
		return seats;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Seat> findAllByTripId(String tripId) {
		Criteria criteria = createCriteria().addOrder(Order.asc("seatNumber"));
		criteria.createAlias("reservation", "reservation").add(Restrictions.eq("reservation.tripId", tripId));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Seat> seats = (List<Seat>) criteria.list();
		return seats;
	}

	@Override
	public void save(Seat seat) {
		persist(seat);
	}

	@Override
	public void deleteById(Long id) {
		Seat seat = getByKey(id);
		delete(seat);
	}

}
