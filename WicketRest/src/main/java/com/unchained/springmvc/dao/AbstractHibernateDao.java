package com.unchained.springmvc.dao;

import java.io.Serializable;
import java.util.List;

public interface AbstractHibernateDao<PK extends Serializable, E> {

	E findById(PK pk);

	List<E> findAll();

	void save(E e);

	void deleteById(PK pk);

}
