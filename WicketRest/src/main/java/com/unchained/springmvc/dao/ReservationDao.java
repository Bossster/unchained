package com.unchained.springmvc.dao;

import java.util.List;

import com.unchained.springmvc.model.Reservation;

public interface ReservationDao extends AbstractHibernateDao<Long, Reservation> {

	@Override
	Reservation findById(Long id);

	@Override
	List<Reservation> findAll();

	@Override
	void save(Reservation reservation);

	@Override
	void deleteById(Long id);

}
