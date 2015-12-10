package com.unchained.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.unchained.springmvc.model.Bus;

@Repository
public class BusDaoImpl extends AbstractDao<Long, Bus> implements BusDao {

	@Override
	public Bus findById(Long id) {
		Bus bus = getByKey(id);
		return bus;
	}

	@Override
	public Bus findByBusType(String busType) {
		Criteria criteria = createCriteria().add(Restrictions.eq("busType", busType));
		Bus bus = (Bus) criteria.uniqueResult();
		return bus;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Bus> findAll() {
		Criteria criteria = createCriteria().addOrder(Order.asc("busType"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Bus> buses = (List<Bus>) criteria.list();
		return buses;
	}

	@Override
	public void save(Bus bus) {
		persist(bus);
	}

	@Override
	public void deleteById(Long id) {
		Bus bus = getByKey(id);
		delete(bus);
	}

}
