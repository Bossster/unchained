package com.unchained.springmvc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unchained.springmvc.dao.ReservationDao;
import com.unchained.springmvc.model.Reservation;
import com.unchained.springmvc.model.ReservationFilter;

@Service("reservationService")
@Transactional
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationDao reservationDao;

	@Override
	public Reservation findReservationById(Long id) throws Exception {
		if (id == null) {
			return null;
		}
		return reservationDao.findById(id);
	}

	@Override
	public Reservation findReservationByReservationId(String reservationId) throws Exception {
		if (StringUtils.isEmpty(reservationId)) {
			return null;
		}
		return reservationDao.findByReservationId(reservationId);
	}

	@Override
	public List<Reservation> findAllReservations() throws Exception {
		return reservationDao.findAll();
	}

	@Override
	public List<ReservationFilter> findReservations() throws Exception {
		return reservationDao.findReservations();
	}

	@Override
	public void saveReservation(Reservation reservation) throws Exception {
		reservationDao.save(reservation);
	}

	@Override
	public void update(Reservation reservation) throws Exception {
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
	public void deleteReservationById(Long id) throws Exception {
		reservationDao.deleteById(id);
	}

}
