package com.unchained.springmvc.service;

import java.util.List;

import com.unchained.springmvc.model.Reservation;
import com.unchained.springmvc.model.ReservationFilter;

public interface ReservationService {

	Reservation findReservationById(Long id) throws Exception;

	Reservation findReservationByReservationId(String reservationId) throws Exception;

	List<Reservation> findAllReservations() throws Exception;

	List<ReservationFilter> findReservations() throws Exception;

	void saveReservation(Reservation reservation) throws Exception;

	void update(Reservation reservation) throws Exception;

	void deleteReservationById(Long id) throws Exception;

}
