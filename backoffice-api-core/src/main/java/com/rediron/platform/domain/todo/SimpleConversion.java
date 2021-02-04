package com.rediron.platform.domain.todo;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.tomax.commons.core.StringUtils;


// For testing only
public final class SimpleConversion {
	private static List<String> TRUE_VALUES = Arrays.asList(new String[] { "true", "y",
			"yes", "t", "1", "on" });

	private static List<String> FALSE_VALUES = Arrays.asList(new String[] { "false",
			"n", "no", "f", "0", "off" });

	/**
	 * Method to convert an object of one type into another. Only generic
	 * conversions are possible.
	 * 
	 * @param obj
	 *            Object to convert
	 * @param convertInto
	 *            class to convert into
	 * @return resulting object of type convertInto
	 * @throws IllegalArgumentException
	 *             thrown if conversion cannot be done
	 */
	public static Object convert(Object obj, Class<?> convertInto) {
		// Deal with primitives
		if (boolean.class.equals(convertInto)) {
			Object b = convert(obj, Boolean.class);
			return b == null ? Boolean.FALSE : b;
		}
		if (int.class.equals(convertInto)) {
			Object b = convert(obj, Integer.class);
			return b == null ? Integer.valueOf(0) : b;
		}
		if (long.class.equals(convertInto)) {
			Object b = convert(obj, Long.class);
			return b == null ? Long.valueOf(0) : b;
		}
		if (double.class.equals(convertInto)) {
			Object b = convert(obj, Double.class);
			return b == null ? Double.valueOf(0) : b;
		}

		// Null and empty strings should return null
		if (obj == null) {
			return null;
		}
		if (obj instanceof String && ((String) obj).trim().length() == 0) {
			return null;
		}

		// If it's already an assignable type, return it
		if (convertInto.isAssignableFrom(obj.getClass())) {
			return obj;
		}

		// Various conversions
		if (String.class.isAssignableFrom(convertInto)) {
			return obj.toString(); 
		} else if (Boolean.class.isAssignableFrom(convertInto)) {
			return convertBoolean(obj);
		} else if (Integer.class.isAssignableFrom(convertInto)) {
			return convertInteger(obj);
		} else if (Double.class.isAssignableFrom(convertInto)) {
			return convertDouble(obj);
		} else if (Long.class.isAssignableFrom(convertInto)) {
			return convertLong(obj);
		} else if (java.sql.Date.class.isAssignableFrom(convertInto)) {
		    return convertToSqlDate(obj);
		} else if (java.sql.Time.class.isAssignableFrom(convertInto)) {
		    return convertToSqlTime(obj);
		} else if (Timestamp.class.isAssignableFrom(convertInto)) {
		    return convertToTimestamp(obj);
		} else if (Date.class.isAssignableFrom(convertInto)) {
		    return convertToDate(obj);
		}
		throw new IllegalArgumentException("Unable to convert object of type "
				+ obj.getClass() + " to type " + convertInto.getName());
	}

	private static Boolean convertBoolean(Object obj) {
	    if (obj == null || obj instanceof Boolean) {
	        return (Boolean) obj;
	    }
		String lcase = obj.toString().toLowerCase();
		if (TRUE_VALUES.contains(lcase)) {
			return Boolean.TRUE;
		} else if (FALSE_VALUES.contains(lcase)) {
			return Boolean.FALSE;
		}
		throw new IllegalArgumentException("Unable to convert '" + obj.toString()
				+ "' to a boolean");
	}

	private static Integer convertInteger(Object obj) {
	    if (obj == null || obj instanceof Integer) {
	        return (Integer) obj;
	    }
		try {
			// If it's a Number, then use it
			if (obj instanceof Number) {
				return Integer.valueOf(((Number) obj).intValue());
			}
			// We convert dates into integers in YYYYMMDD format
			if (obj instanceof Date) {
				return Integer.valueOf(DateUtils.dateToInt((Date) obj));
			}

			// Otherwise convert to a String and try that
			return Integer.valueOf(makeIntegerString(obj));
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to convert '" + obj.toString()
					+ "' to an integer", e);
		}
	}

	private static Double convertDouble(Object obj) {
	    if (obj == null || obj instanceof Double) {
	        return (Double) obj;
	    }
		try {
			if (obj instanceof Number) {
				return Double.valueOf(((Number) obj).doubleValue());
			}
			// Otherwise convert to a String and try that
			return Double.valueOf(StringUtils.stripNonNumericChars(obj.toString()));
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to convert '" + obj.toString()
					+ "' to a double", e);
		}
	}

	/**
	 * Calls makeDecimalString then takes that result and if it contains a
	 * decimal point, returns a substring up to that decimal point, otherwise
	 * returns the result as is.
	 * 
	 * @param obj
	 * @return
	 */
	private static String makeIntegerString(Object obj) {
	    if (obj == null) {
	        return null;
	    }
		String decString = StringUtils.stripNonNumericChars(obj.toString());
		// Now only take it to the decimal point, if there is one
		int i = decString.indexOf('.');
		if (i < 0) {
		    return decString;
		}
		return decString.substring(0, i);
	}

	private static Long convertLong(Object obj) {
	    if (obj == null || obj instanceof Long) {
	        return (Long) obj;
	    }
		try {
			if (obj instanceof Number) {
				return Long.valueOf(((Number) obj).longValue());
			}
			// We convert dates into longs in YYYYMMDDHHMMSS format
			if (obj instanceof Date) {
				return Long.valueOf(DateUtils.dateTimeToLong((Date) obj));
			}
			// Otherwise convert to a String and try that
			return Long.valueOf(makeIntegerString(obj));
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to convert '" + obj.toString()
					+ "' to a long", e);
		}
	}
	
	public static java.sql.Date convertToSqlDate(Object obj) throws IllegalArgumentException {
	    if (obj == null || obj instanceof java.sql.Date) {
	        return (java.sql.Date)obj; 
	    } else if (obj instanceof Date) {
	        return new java.sql.Date(((Date) obj).getTime());
	    } else if (obj instanceof Integer) {
	        Date date = DateUtils.intToDate((Integer)obj);
	        return new java.sql.Date(date.getTime());
	    } else if (obj instanceof Long) {
	        Date date = DateUtils.longToDateTime((Long) obj);
            return new java.sql.Date(date.getTime());
	    } else if (obj instanceof String) {
	        Date date = DateUtils.stringToDate((String) obj);
            return new java.sql.Date(date.getTime());
	    }
        throw new IllegalArgumentException("Unable to convert '" + obj.toString()
                + "' to a Date");
	}
    
    public static Timestamp convertToTimestamp(Object obj) throws IllegalArgumentException {
        if (obj == null || obj instanceof Timestamp) {
            return (Timestamp)obj;
        } else {
            Date date = convertToDate(obj);
            return (date == null) ? null : new Timestamp(date.getTime());
        }
    }
    
    public static java.sql.Time convertToSqlTime(Object obj) throws IllegalArgumentException {
        if (obj == null || obj instanceof java.sql.Time) {
            return (java.sql.Time)obj; 
        } else {
            Date date = convertToDate(obj);
            return (date == null) ? null : new java.sql.Time(date.getTime());
        }
    }

    public static Date convertToDate(Object obj) throws IllegalArgumentException {
        if (obj == null) {
            return null;
        } else if (obj instanceof java.sql.Date || obj instanceof java.sql.Timestamp) {
            return new Date(((Date)obj).getTime());
        } else if (obj instanceof Date) {
            return (Date)obj; 
        } else if (obj instanceof Integer) {
            return DateUtils.intToDate((Integer)obj);
        } else if (obj instanceof Long) {
            return DateUtils.longToDateTime((Long) obj);
        } else if (obj instanceof String) {
            return DateUtils.stringToDateTime((String) obj);
        }
        throw new IllegalArgumentException("Unable to convert '" + obj.toString()
                + "' to a Date");
    }

    /**
	 * Convenience method to return a primitive boolean from conversion
	 * 
	 * @param obj
	 *            Object to convert
	 * @return boolean representation of the object. If conversion resulted in
	 *         null, then <code>false</code> is returned
	 * @throws IllegalArgumentException
	 *             thrown if conversion cannot be done
	 */
	public static boolean convertToBoolean(Object obj) {
		return ((Boolean) convert(obj, boolean.class)).booleanValue();
	}

	/**
	 * Convenience method to return a primitive double from conversion
	 * 
	 * @param obj
	 *            Object to convert
	 * @return double representation of the object. If conversion resulted in
	 *         null, then <code>0.0</code> is returned
	 * @throws IllegalArgumentException
	 *             thrown if conversion cannot be done
	 */
	public static double convertToDouble(Object obj) {
		return ((Double) convert(obj, double.class)).doubleValue();
	}

	/**
	 * Convenience method to return a primitive int from conversion
	 * 
	 * @param obj
	 *            Object to convert
	 * @return int representation of the object. If conversion resulted in null,
	 *         then <code>0</code> is returned
	 * @throws IllegalArgumentException
	 *             thrown if conversion cannot be done
	 */
	public static int convertToInt(Object obj) {
		return ((Integer) convert(obj, int.class)).intValue();
	}

	/**
	 * Convenience method to return a primitive long from conversion
	 * 
	 * @param obj
	 *            Object to convert
	 * @return long representation of the object. If conversion resulted in
	 *         null, then <code>0</code> is returned
	 * @throws IllegalArgumentException
	 *             thrown if conversion cannot be done
	 */
	public static long convertToLong(Object obj) {
		return ((Long) convert(obj, long.class)).longValue();
	}

	/**
	 * Convenience method to convert to a String. Seems silly, but it is handy
	 * as any object can be sent in and null is taken care of.
	 * 
	 * @param obj
	 *            Object to convert
	 * @return String representation of the object. Null is returned if null was
	 *         given or if the resulting string was empty
	 * @throws IllegalArgumentException
	 *             thrown if conversion cannot be done
	 */
	public static String convertToString(Object obj) {
		return (String) convert(obj, String.class);
	}
}
