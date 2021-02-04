package com.rediron.platform.domain.rnet;


/**
 * Marker interface for an object that is a persistent entity. It is used to make code analysis easier but also
 * as the benefit of implementing Serializable which all entities should do anyway.
 * 
 * @author lovanya.chaudhary
 *
 * @param <E>
 */
public interface PersistableObject<E> extends CommonBusinessObject<E> {}

