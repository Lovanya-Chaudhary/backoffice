package com.rediron.platform.domain.todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.text.*;

import com.tomax.commons.core.StringUtils;

// For testing only
public final class DateUtils {
	/**
	 * The preferred date format is yyyyMMdd
	 */
	public static final String PREFERRED_DATE_FORMAT = "yyyyMMdd";

	/**
	 * A list of all known date formats, in descending order of precedence. The
	 * current list contains:
	 * <ul>
	 * <li>{@link #PREFERRED_DATE_FORMAT}</li>
	 * <li>yyyy-MM-dd</li>
     * <li>MM/dd/yyyy</li>
	 * <li>MM/dd/yy</li>
	 * <li>yyyy/MM/dd</li>
	 * <li>yyMMdd</li>
	 * </ul>
	 */
	public static final String[] CONVERTIBLE_DATE_FORMATS = new String[] {
			PREFERRED_DATE_FORMAT, "yyyy-MM-dd", "MM/dd/yyyy", "MM/dd/yy", "yyyy/MM/dd", "yyMMdd" };

	/**
	 * The preferred date/time format is yyyy-MM-dd HH:mm:ss
	 */
	public static final String PREFERRED_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * A list of all known date formats, in descending order of precedence. The
	 * current list contains:
	 * <ul>
	 * <li>{@link #PREFERRED_DATETIME_FORMAT}</li>
	 * <li>yyyyMMddHHmmss</li>
	 * <li>yyyyMMdd HHmmss</li>
	 * </ul>
	 */
	public static final String[] CONVERTIBLE_DATETIME_FORMATS = new String[] {
			PREFERRED_DATETIME_FORMAT, "yyyyMMddHHmmss", "yyyyMMdd HHmmss" };

	private static final String TIME_PATTERN = "MM/dd/yy HH:mm:ss";

	private static final DateFormatter TIME_FORMATTER = new DateFormatter(
			new SimpleDateFormat(TIME_PATTERN));

	/**
	 * Constant for number of milliseconds in a day
	 */
	public static final long MILLIS_IN_DAY = 86400000L;

	/**
	 * @param date
	 *            date to convert
	 * @return integer for the date in YYYYMMDD format
	 */
	public static int dateToInt(Date date) {
		if (date == null) {
			throw new NullPointerException("date is null");
		}
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		return Integer.parseInt(fmt.format(date));
	}

	/**
	 * @param date
	 *            date to convert
	 * @return long for the date and time in YYYYMMDDHHMMSS format
	 */
	public static long dateTimeToLong(Date date) {
		if (date == null) {
			throw new NullPointerException("date is null");
		}
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		return Long.parseLong(fmt.format(date));
	}

	/**
	 * Converts an integer in YYYYMMDD format into a Date - the time on the date
	 * will be 12:00:00 AM
	 * 
	 * @param date
	 *            integer in YYYYMMDD format to convert to a Date
	 * @return Resulting Date object
	 */
	public static Date intToDate(int date) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		try {
			return fmt.parse("" + date);
		} catch (ParseException e) {
			throw new RuntimeException("Unable to convert int " + date
					+ " into a valid date");
		}
	}

	/**
	 * Given an elapsed number of milliseconds, this method will return a String
	 * representation of that in HH:MM:SS.MMM format
	 * 
	 * @param millis
	 *            Number of elapsed milliseconds
	 * @return the string representation of the elapsed time in HH:MM:SS.MMM
	 *         format
	 * @throws IllegalArgumentException
	 *             thrown if millis is a negative value
	 */
	public static String elapsedTimeToString(long millis) {
		if (millis < 0) {
			throw new IllegalArgumentException("millis must be a positve value");
		}
		String milliStr = Integer.toString((int) (millis % 1000));
		long time = millis / 1000;
		String seconds = Integer.toString((int) (time % 60));
		String minutes = Integer.toString((int) ((time % 3600) / 60));
		String hours = Integer.toString((int) (time / 3600));
		StringBuffer buf = new StringBuffer();
		buf.append(StringUtils.padLeft(hours, 2, '0'));
		buf.append(':');
		buf.append(StringUtils.padLeft(minutes, 2, '0'));
		buf.append(':');
		buf.append(StringUtils.padLeft(seconds, 2, '0'));
		buf.append('.');
		buf.append(StringUtils.padLeft(milliStr, 3, '0'));
		return buf.toString();
	}

	/**
	 * @param date
	 *            long in YYYYMMDDHHMMSS format to convert to a Date
	 * @return Resulting Date object
	 */
	public static Date longToDateTime(long date) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			return fmt.parse("" + date);
		} catch (ParseException e) {
			throw new RuntimeException("Unable to convert long " + date
					+ " into a valid date/time");
		}
	}

	/**
	 * Given a date object, this method leaves the date portion intact, but
	 * changes the time to be 12:00:00 AM
	 * 
	 * @param date
	 *            date to truncate the time
	 * @return New Date object with time set to 12:00:00 AM
	 */
	public static Date truncTime(Date date) {
		if (date == null) {
			return null;
		}
		int intDate = dateToInt(date);
		// intToDate sets time to 12:00:00 AM
		return intToDate(intDate); 
	}

	private static int getRoundedDays(long time) {
		long days = time / MILLIS_IN_DAY;
		if (time % MILLIS_IN_DAY != 0) {
			days++;
		}
		return (int) days;
	}
	/**
	 * Compares two dates by truncating each date.
	 * 
	 * @param a
	 *            first date to compare
	 * @param b
	 *            date to compare to
	 * @return the difference in days between the two dates, if a > b, a
	 *         positive value is returned.
	 */
	public static int compareDays(Date a, Date b) {
		a = truncTime(a);
		b = truncTime(b);
		return getRoundedDays(a.getTime()) - getRoundedDays(b.getTime());
	}

	/**
	 * Private - utility class for static methods only
	 */
	private DateUtils() {
	}

	/**
	 * Returns the current system date in YYYYMMDD format.
	 * 
	 * @return the current system date in YYYYMMDD format
	 */
	public static int getSysDateAsInt() {
		return dateToInt(new Date());
	}

	/**
	 * Adds the number of days to the given integer date and returns the new
	 * date.
	 * 
	 * @param date
	 *            date (in YYYYMMDD format) to add days to
	 * @param numDays
	 *            number of days to add (can be negative)
	 * @return an integer that is the new date in YYYYMMDD format.
	 */
	public static int addDays(int date, int numDays) {
		Date dt = intToDate(date);
		return dateToInt(addDays(dt, numDays));
	}

	/**
	 * Adds the number of days to the given date and returns the new date.
	 * 
	 * @param date
	 *            {@link Date} to add days to
	 * @param numDays
	 *            number of days to add (can be negative)
	 * @return a {@link Date} that is the new date
	 */
	public static Date addDays(Date date, int numDays) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, numDays);
		return cal.getTime();
	}

	/**
	 * @deprecated use {@link #MILLIS_IN_DAY} instead.
	 */
	private static long milliDay = MILLIS_IN_DAY;

	/**
	 * Compares the two integer dates (YYYYMMDD format) and returns the
	 * difference in days.
	 * 
	 * @param date1
	 *            first date to compare
	 * @param date2
	 *            second date to compare
	 * @return the difference in days
	 */
	public static int compareDates(int date1, int date2) {
		Date d1 = intToDate(date1);
		Date d2 = intToDate(date2);
		return (int) ((d1.getTime() - d2.getTime()) / MILLIS_IN_DAY);
	}

	/**
	 * Compares the two dates and returns the difference in days.
	 * 
	 * @param d1
	 *            first date to compare
	 * @param d2
	 *            second date to compare
	 * @return the difference in days
	 */
	public static int compareDates(Date d1, Date d2) {
		return (int) ((d1.getTime() - d2.getTime()) / MILLIS_IN_DAY);
	}

	/**
	 * Returns a time stamp that can be used when logging to files. The current
	 * time is formatted as:<br>
	 * <code>"MM/DD/YY HH:mm:ss "</code>
	 * 
	 * @return a string that is the current timestamp.
	 */
	public static String getTimestamp() {
		return getTimestamp(new Date()) + " ";
	}

	/**
	 * Formats the given date as:<br>
	 * <code>"MM/DD/YY HH:mm:ss"</code>
	 * 
	 * @return a string that is the formatted time
	 */
	public static String getTimestamp(Date date) {
		try {
			return TIME_FORMATTER.valueToString(date);
		} catch (ParseException e) {
			return date.toString();
		}
	}

	/**
	 * Attempts to convert the given string into a date value. If successful,
	 * the resulting {@link Date} object will have its time set to 00:00:00.<br>
	 * Attempts are made to convert string values checks for all formats in
	 * {@link #CONVERTIBLE_DATE_FORMATS}
	 * 
	 * @param str
	 *            String representing a date.
	 * @return Resulting Date object with its time set to 00:00:00
	 * @throws IllegalArgumentException
	 *             thrown if the date string cannot be converted
	 */
	public static Date stringToDate(String str) {
		str = StringUtils.trim(str);
		if (str == null) {
			throw new IllegalArgumentException("Empty value cannot be converted into a date");
		}

		// First, check for sysdate
		if (str.toLowerCase().startsWith("sysdate")) {
			if ("sysdate".equals(str.toLowerCase())) {
				return truncTime(new Date());
			}

			// Everything after the sysdate should be the increment number
			String leftover = StringUtils.stripNonNumericChars(str.substring(7));
			try {
				int diff = Integer.parseInt(leftover);
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, diff);
				return truncTime(cal.getTime());
			} catch (Exception e) {
				throw new IllegalArgumentException("Unable to convert \"" + str + "\" into a date");
			}
		}

		for (int i = 0; i < CONVERTIBLE_DATE_FORMATS.length; i++) {
			try {
				Date dt = new SimpleDateFormat(CONVERTIBLE_DATE_FORMATS[i]).parse(str);
				return truncTime(dt);
			} catch (Exception e) {
			} // Ignore, just move on
		}

		// If we got to here then the formatting was bad
		throw new IllegalArgumentException("Unable to convert \"" + str + "\" into a date");
	}

	/**
	 * Attempts to convert the given string into a date/time value.<br>
	 * Attempts are made to convert string values checks for all formats in
	 * {@link #CONVERTIBLE_DATETIME_FORMATS}
	 * 
	 * @param str
	 *            String representing a date/time.
	 * @return Resulting Date object
	 * @throws IllegalArgumentException
	 *             thrown if the date/time string cannot be converted
	 */
	public static Date stringToDateTime(String str) {
		str = StringUtils.trim(str);
		if (str == null) {
			throw new IllegalArgumentException("Empty value cannot be converted into a date/time");
		}
		for (int i = 0; i < CONVERTIBLE_DATETIME_FORMATS.length; i++) {
			try {
				Date dt = new SimpleDateFormat(CONVERTIBLE_DATETIME_FORMATS[i]).parse(str);
				return DateUtils.truncTime(dt);
			} catch (Exception e) {
			} // Ignore, just move on
		}

		// If we got to here then the formatting was bad
		throw new IllegalArgumentException("Unable to convert \"" + str + "\" into a date/time");
	}

}
