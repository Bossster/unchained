package com.unchained.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "HR.BUS", uniqueConstraints = { @UniqueConstraint(columnNames = "BUS_TYPE") })
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "BUS_TYPE", length = 20, unique = true, nullable = false)
	private String busType = "DEFAULT";

	@Column(name = "MAX_SEATS", length = 20, nullable = false)
	private Integer maxSeats = 1;

	@Column(name = "MAX_BIKES", length = 20, nullable = false)
	private Integer maxBikes = 1;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public Integer getMaxSeats() {
		return maxSeats;
	}

	public void setMaxSeats(Integer maxSeats) {
		this.maxSeats = maxSeats;
	}

	public Integer getMaxBikes() {
		return maxBikes;
	}

	public void setMaxBikes(Integer maxBikes) {
		this.maxBikes = maxBikes;
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
		return "Bus:[" + id + ", " + busType + ", " + maxSeats + ", " + maxBikes + "]";
	}

}
