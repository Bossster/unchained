package com.unchained.springmvc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unchained.springmvc.dao.BusDao;
import com.unchained.springmvc.model.Bus;

@Service("busService")
@Transactional
public class BusServiceImpl implements BusService {
	
	@Autowired
	BusDao busDao;

	@Override
	public Bus findBusById(Long id) {
		if (id == null) {
			return null;
		}
		return busDao.findById(id);
	}

	@Override
	public Bus findBusByBusType(String busType) {
		if (StringUtils.isEmpty(busType)) {
			return null;
		}
		return busDao.findByBusType(busType);
	}

	@Override
	public List<Bus> findAllBuses() {
		return busDao.findAll();
	}

	@Override
	public void saveBus(Bus bus) {
		busDao.save(bus);
	}

	@Override
	public void update(Bus bus) {
		Bus found = busDao.findById(bus.getId());
		if (found != null) {
			found.setBusType(bus.getBusType());
			found.setMaxSeats(bus.getMaxSeats());
		}
	}

	@Override
	public void deleteBusById(Long id) {
		busDao.deleteById(id);
	}

}
