package com.unchained.springmvc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unchained.springmvc.dao.ReservationDao;
import com.unchained.springmvc.dao.SeatDao;
import com.unchained.springmvc.dao.TripDao;
import com.unchained.springmvc.model.Reservation;
import com.unchained.springmvc.model.ReservationFilter;
import com.unchained.springmvc.model.Seat;
import com.unchained.springmvc.model.Trip;

@Service("reservationService")
@Transactional
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationDao reservationDao;

	@Autowired
	SeatDao seatDao;

	@Autowired
	TripDao tripDao;

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
	public void saveReservation(ReservationFilter reservation) throws Exception {
		Reservation entity = new Reservation();
		entity.setReservationDate(new Date());
		entity.setReservationId(UUID.randomUUID().toString());
		entity.setTripId(reservation.getTripId());
		entity.setTemporary(Boolean.TRUE);

		Trip trip = tripDao.findByTripId(reservation.getTripId());
		entity.setBus(trip.getBus());
		entity.setSeats(bookSeats(entity, reservation.getReservationSeatCount()));
		reservationDao.save(entity);
	}

	private List<Seat> bookSeats(Reservation reservation, int reservationSeatCount) {
		List<Seat> result = new ArrayList<Seat>();
		List<Seat> seats = seatDao.findAllByTripId(reservation.getTripId());
		Set<Integer> seatNumbers = new HashSet<Integer>();

		if (seats != null && !seats.isEmpty()) {
			for (Seat seat : seats) {
				seatNumbers.add(seat.getSeatNumber());
			}
		}

		int seatCount = 1;
		for (int i = 1; i <= reservation.getBus().getMaxSeats(); i++) {
			if (!seatNumbers.contains(i)) {
				Seat seat = new Seat();
				seat.setBus(reservation.getBus());
				seat.setReservation(reservation);
				seat.setSeatNumber(i);
				result.add(seat);

				if (++seatCount > reservationSeatCount) {
					break;
				}
			}
		}
		return result;
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
