package com.unchained.springmvc.dao;

import java.util.List;

import com.unchained.springmvc.model.Seat;

public interface SeatDao extends AbstractHibernateDao<Long, Seat> {

	@Override
	Seat findById(Long id);

	@Override
	List<Seat> findAll();

	@Override
	void save(Seat seat);

	@Override
	void deleteById(Long id);

}
