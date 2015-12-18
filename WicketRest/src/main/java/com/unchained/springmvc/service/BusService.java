package com.unchained.springmvc.service;

import java.util.List;

import com.unchained.springmvc.model.Bus;

public interface BusService {

	Bus findBusById(Long id) throws Exception;
	
	Bus findBusByBusType(String busType) throws Exception;
	
	List<Bus> findAllBuses() throws Exception;
	
	void saveBus(Bus bus) throws Exception;
	
	void update(Bus bus) throws Exception;
	
	void deleteBusById(Long id) throws Exception;
	
}
