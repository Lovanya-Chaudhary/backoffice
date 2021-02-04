package com.rediron.platform.domain.rnet;

import java.io.Serializable;

/**
 * This interface denotes an identifier for a common business object which contains fields that make up a business key.
 * Often this is a one to one mapping with a database table's primary key columns but can sometimes be a custom object
 * that combines various sources of data. These objects often have Java Validate Framework (JSR303) annotations to
 * facilitate field validate by the client or on the server.
 * 
 * 
 * @author lovanya.chaudhary
 *
 * @param <E>
 */
public interface CommonBusinessObjectIdentifier<E> extends Serializable, Comparable<E> {
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
