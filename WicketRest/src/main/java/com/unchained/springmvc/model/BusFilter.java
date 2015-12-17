package com.unchained.springmvc.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BusFilter extends Bus {

	public BusFilter(Bus bus) {
		setId(bus.getId());
		setBusType(bus.getBusType());
		setMaxSeats(bus.getMaxSeats());
	}

}
