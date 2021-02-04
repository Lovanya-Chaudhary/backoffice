package com.rediron.platform.domain.rnet;

import java.io.Serializable;

/**
 * This interface denotes an object which contains fields and collections that share a common business meaning. Often
 * this is a one to one mapping with a database table but can sometimes be a custom object that combines various
 * source of data.
 * 
 * @author lovanya.chaudhary
 *
 */
public interface CommonBusinessObject<E> extends Serializable, Comparable<E> {
	/**
	 * Value of -1 used for implementing Comparable
	 */
	public static final int BEFORE = -1;
	/**
	 * Value of 1 used for implementing Comparable
	 */
	public static final int AFTER = 1;
	/**
	 * Value of 0 used for implementing Comparable
	 */
	public static final int EQUAL = 0;
}