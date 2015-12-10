package com.unchained.springmvc.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "HR.RESERVATION")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "RESERVATION_ID", length = 20, unique = true, nullable = false)
	private String reservationId;

	@Column(name = "TRIP_ID", length = 20, nullable = false)
	private String tripId;

	@Column(name = "DATE_TIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;

	@Column(name = "TEMPORARY", nullable = false)
	private Boolean temporary;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BUS_ID")
	private Bus bus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Boolean isTemporary() {
		return temporary;
	}

	public void setTemporary(Boolean temporary) {
		this.temporary = temporary;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	@Override
	public String toString() {
		return "Reservation:[" + id + "," + reservationId + "," + tripId + "," + dateTime + "," + temporary + "]";
	}

}
