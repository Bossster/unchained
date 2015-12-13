package com.unchained.springmvc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "HR.RESERVATION", uniqueConstraints = { @UniqueConstraint(columnNames = "RESERVATION_ID") })
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "RESERVATION_DATE", length = 60, nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date reservationDate;

	@Column(name = "RESERVATION_ID", length = 20, unique = true, nullable = false)
	private String reservationId;

	@Column(name = "TRIP_ID", length = 20, nullable = false)
	private String tripId;

	@Column(name = "TEMPORARY", nullable = false)
	private Boolean temporary = Boolean.TRUE;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BUS_ID")
	private Bus bus;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation", orphanRemoval = true)
	private List<Seat> seats = new ArrayList<Seat>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
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

	public Boolean getTemporary() {
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
	
	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "Reservation:[" + id + ", " + reservationDate + ", " + reservationId + ", " + tripId + ", " + temporary + "]";
	}

}
