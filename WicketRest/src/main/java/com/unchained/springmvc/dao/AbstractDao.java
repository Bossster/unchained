package com.unchained.springmvc.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<PK extends Serializable, E> {

	@Autowired
	SessionFactory sessionFactory;

	protected final Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected Criteria createCriteria() {
		return getSession().createCriteria(entityClass);
	}

	@SuppressWarnings("unchecked")
	public E getByKey(PK key) {
		return (E) getSession().get(entityClass, key);
	}

	public void persist(E entity) {
		getSession().persist(entity);
	}

	public void delete(E entity) {
		getSession().delete(entity);
	}

}
