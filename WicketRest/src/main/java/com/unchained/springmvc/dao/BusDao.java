package com.unchained.springmvc.dao;

import java.util.List;

import com.unchained.springmvc.model.Bus;

public interface BusDao extends AbstractHibernateDao<Long, Bus> {

	@Override
	Bus findById(Long id);

	Bus findByBusType(String busType);

	@Override
	List<Bus> findAll();

	@Override
	void save(Bus bus);

	@Override
	void deleteById(Long id);

}
