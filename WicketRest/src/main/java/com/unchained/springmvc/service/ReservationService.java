package com.unchained.springmvc.service;

import java.util.List;

import com.unchained.springmvc.model.Reservation;

public interface ReservationService {

	Reservation findReservationById(Long id);

	List<Reservation> findAllReservations();

	void saveReservation(Reservation reservation);

	void update(Reservation reservation);

	void deleteReservationById(Long id);

}
