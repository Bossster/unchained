package com.unchained.springmvc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "HR.BUS", uniqueConstraints = { @UniqueConstraint(columnNames = "BUS_TYPE") })
@XmlRootElement
public class Bus implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "BUS_TYPE", length = 20, unique = true, nullable = false)
	private String busType = "DEFAULT";

	@Column(name = "MAX_SEATS", length = 20, nullable = false)
	private Integer maxSeats = 1;

	@XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	@XmlElement
	public Integer getMaxSeats() {
		return maxSeats;
	}

	public void setMaxSeats(Integer maxSeats) {
		this.maxSeats = maxSeats;
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
		return "Bus:[" + id + ", " + busType + ", " + maxSeats + "]";
	}

}
