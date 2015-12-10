package com.unchained.springmvc.service;

import java.util.List;

import com.unchained.springmvc.model.Bus;

public interface BusService {

	Bus findBusById(Long id);
	
	Bus findBusByBusType(String busType);
	
	List<Bus> findAllBuses();
	
	void saveBus(Bus bus);
	
	void update(Bus bus);
	
	void deleteBusById(Long id);
	
}
