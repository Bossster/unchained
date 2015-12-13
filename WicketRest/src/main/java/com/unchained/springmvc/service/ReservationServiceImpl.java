package com.unchained.springmvc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unchained.springmvc.dao.BusDao;
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

	@Autowired
	BusDao busDao;

	@Override
	public Reservation findReservationById(Long id) {
		if (id == null) {
			return null;
		}
		return reservationDao.findById(id);
	}

	@Override
	public List<Reservation> findAllReservations() {
		return reservationDao.findAll();
	}

	@Override
	public void saveReservation(Reservation reservation) {
		reservationDao.save(reservation);
	}

	@Override
	public void update(Reservation reservation) {
		Reservation found = reservationDao.findById(reservation.getId());
		if (found != null) {
			found.setReservationDate(reservation.getReservationDate());
			found.setReservationId(reservation.getReservationId());
			found.setTripId(reservation.getTripId());
			found.setTemporary(reservation.getTemporary());
			found.setBus(reservation.getBus());
			found.setSeats(reservation.getSeats());
		}
	}

	@Override
	public void deleteReservationById(Long id) {
		reservationDao.deleteById(id);
	}

}
