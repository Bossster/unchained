package com.unchained.springmvc.model;

import java.io.Serializable;
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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "HR.TRIP", uniqueConstraints = { @UniqueConstraint(columnNames = "TRIP_ID") })
@XmlRootElement
public class Trip implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TRIP_DATE_FROM", length = 60, nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date tripDateFrom;

	@Column(name = "TRIP_DATE_TO", length = 60, nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date tripDateTo;

	@Column(name = "TRIP_FROM", length = 60, nullable = false)
	private String tripFrom;

	@Column(name = "TRIP_TO", length = 60, nullable = false)
	private String tripTo;

	@Column(name = "TRIP_ID", length = 20, unique = true, nullable = false)
	private String tripId;

	@Column(name = "TRIP_PRICE", length = 20, nullable = false)
	private Integer tripPrice;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BUS_ID")
	private Bus bus;

	@XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	@XmlElement
	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	@XmlElement
	public Date getTripDateFrom() {
		return tripDateFrom;
	}

	public void setTripDateFrom(Date tripDateFrom) {
		this.tripDateFrom = tripDateFrom;
	}

	@XmlElement
	public Date getTripDateTo() {
		return tripDateTo;
	}

	public void setTripDateTo(Date tripDateTo) {
		this.tripDateTo = tripDateTo;
	}

	@XmlElement
	public String getTripFrom() {
		return tripFrom;
	}

	public void setTripFrom(String tripFrom) {
		this.tripFrom = tripFrom;
	}

	@XmlElement
	public String getTripTo() {
		return tripTo;
	}

	public void setTripTo(String tripTo) {
		this.tripTo = tripTo;
	}

	@XmlElement
	public Integer getTripPrice() {
		return tripPrice;
	}

	public void setTripPrice(Integer tripPrice) {
		this.tripPrice = tripPrice;
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
		return "Trip:[" + id + ", " + tripDateFrom + ", " + tripDateTo + ", " + tripFrom + ", " + tripTo + ", " + tripId
				+ ", " + tripPrice + "]";
	}

}
