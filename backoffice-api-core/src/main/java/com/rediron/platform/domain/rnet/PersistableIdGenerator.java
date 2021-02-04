package com.rediron.platform.domain.rnet;


import java.io.Serializable;

import javax.persistence.EntityManager;

/**
 * Interface used to implement a custom id generator
 * 
 * @author james
 * 
 */
public interface PersistableIdGenerator<T> {
	public Serializable generate(EntityManager em, T entity);
}
