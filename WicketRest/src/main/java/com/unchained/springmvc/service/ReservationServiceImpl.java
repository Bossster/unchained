package com.unchained.springmvc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unchained.springmvc.dao.ReservationDao;
import com.unchained.springmvc.dao.SeatDao;
import com.unchained.springmvc.model.Reservation;

@Service("reservationService")
@Transactional
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationDao reservationDao;

	@Autowired
	SeatDao seatDao;

	@Override
	public Reservation findReservationById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> findAllReservations() {
		return reservationDao.findAll();
	}

	@Override
	public void saveReservation(Reservation reservation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Reservation reservation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteReservationById(Long id) {
		// TODO Auto-generated method stub

	}

}
