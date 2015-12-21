package com.unchained.springmvc.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReservationFilter extends Reservation {

	private String reservationFrom;

	private String reservationTo;

	private Date reservationDateFrom;

	private Date reservationDateTo;

	private Integer reservationSeatCount;

	private Integer reservationPrice;

	public ReservationFilter() {
		super();
	}

	public ReservationFilter(Reservation reservation, Trip trip) {
		setId(reservation.getId());
		setReservationDate(reservation.getReservationDate());
		setReservationId(reservation.getReservationId());
		setTripId(reservation.getTripId());
		setTemporary(reservation.getTemporary());
		setBus(reservation.getBus());

		setReservationFrom(trip.getTripFrom());
		setReservationTo(trip.getTripTo());
		setReservationDateFrom(trip.getTripDateFrom());
		setReservationDateTo(trip.getTripDateTo());
		setReservationSeatCount(reservation.getSeats().size());
		setReservationPrice(getReservationSeatCount() * trip.getTripPrice());
	}

	@XmlElement
	public Integer getReservationSeatCount() {
		return reservationSeatCount;
	}

	public void setReservationSeatCount(Integer reservationSeatCount) {
		this.reservationSeatCount = reservationSeatCount;
	}

	@XmlElement
	public String getReservationFrom() {
		return reservationFrom;
	}

	public void setReservationFrom(String reservationFrom) {
		this.reservationFrom = reservationFrom;
	}

	@XmlElement
	public String getReservationTo() {
		return reservationTo;
	}

	public void setReservationTo(String reservationTo) {
		this.reservationTo = reservationTo;
	}

	@XmlElement
	public Date getReservationDateFrom() {
		return reservationDateFrom;
	}

	public void setReservationDateFrom(Date reservationDateFrom) {
		this.reservationDateFrom = reservationDateFrom;
	}

	@XmlElement
	public Date getReservationDateTo() {
		return reservationDateTo;
	}

	public void setReservationDateTo(Date reservationDateTo) {
		this.reservationDateTo = reservationDateTo;
	}

	@XmlElement
	public Integer getReservationPrice() {
		return reservationPrice;
	}

	public void setReservationPrice(Integer reservationPrice) {
		this.reservationPrice = reservationPrice;
	}

}
