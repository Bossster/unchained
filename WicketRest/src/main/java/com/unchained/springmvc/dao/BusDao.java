package com.unchained.springmvc.dao;

import java.util.List;

import com.unchained.springmvc.model.Bus;

public interface BusDao {

	Bus findById(Long id);

	Bus findByBusType(String busType);

	List<Bus> findAll();

	void save(Bus bus);

	void deleteById(Long id);

}
