package com.unchained.springmvc.dao;

import java.util.List;

import com.unchained.springmvc.model.Reservation;
import com.unchained.springmvc.model.ReservationFilter;

public interface ReservationDao extends AbstractHibernateDao<Long, Reservation> {

	@Override
	Reservation findById(Long id);

	Reservation findByReservationId(String reservationId);
	
	@Override
	List<Reservation> findAll();
	
	List<ReservationFilter> findReservations();

	@Override
	void save(Reservation reservation);

	@Override
	void deleteById(Long id);

}
