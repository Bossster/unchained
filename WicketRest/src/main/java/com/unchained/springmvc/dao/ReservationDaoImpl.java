package com.unchained.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.unchained.springmvc.model.Reservation;

@Repository
public class ReservationDaoImpl extends AbstractDao<Long, Reservation> implements ReservationDao {

	@Override
	public Reservation findById(Long id) {
		Reservation reservation = getByKey(id);
		return reservation;
	}

	@Override
	public Reservation findByReservationId(String reservationId) {
		Criteria criteria = createCriteria().add(Restrictions.eq("reservationId", reservationId));
		Reservation reservation = (Reservation) criteria.uniqueResult();
		return reservation;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Reservation> findAll() {
		Criteria criteria = createCriteria().addOrder(Order.asc("reservationId"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Reservation> reservations = (List<Reservation>) criteria.list();
		return reservations;
	}

	@Override
	public void save(Reservation reservation) {
		persist(reservation);
	}

	@Override
	public void deleteById(Long id) {
		Reservation reservation = getByKey(id);
		delete(reservation);
	}

}
