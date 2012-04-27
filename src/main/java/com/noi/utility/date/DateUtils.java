package com.noi.utility.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtils {
	
	private static DateUtils _instance;
	
	public static final String PATTERN_COMMON = "MM/dd/yyyy";
	public static final String PATTERN_BASIC = "mmm dd, yyyy";
	public static final String SIMPLE_FORMAT = "dd-MMM-yyyy";
	public static final String DESC_SIMPLE_FORMAT = "yyyy-MM-dd";	
	public static final String PATTERN_DATE_STRING = "EEE MMM dd HH:mm:ss zzz yyyy";	
	public static final String FULL_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";	
	public static final String POSTGRES_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";	
	public static final String SQL_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";	
	public static final String NOSPACE_TIMESTAMP_FORMAT_TZ = "yyyy-MM-dd_HH:mm:ssZ";
	public static final String NOSPACE_TIMESTAMP_FORMAT = "yyyy-MM-dd_HH:mm:ss";
	
	public static final long WEEK_IN_MILLS = 604800000 ; 
	public static final long DAY_IN_MILLS = 86400000 ; 

	private static final Log logger = LogFactory.getLog(DateUtils.class);
	
	private DateUtils()
	{
		
	}
	
	public static DateUtils getInstance() 
	{
		if(_instance == null)
			_instance = new DateUtils();
		return _instance;
	}
	
	public static String 
		formatDate(String strUnFormatted, String strPatternRendered, String strSystemFormat)
	{
		String strFormatted = "";
		if (strUnFormatted!=null){
			if (!strUnFormatted.equals("")){
			   try {
			    	SimpleDateFormat dfDate = 
			            new SimpleDateFormat(strSystemFormat);
					Date parsed = dfDate.parse(strUnFormatted);
					((SimpleDateFormat) dfDate).applyPattern(strPatternRendered);
			     	strFormatted =  dfDate.format(parsed);
		        } catch (Exception e) {
		        	logger.debug("invalid date",e);
		        }
			}	
		}
		return strFormatted;
	}
		
	public static Date stringToDate(String dateValue, String dformat) {
		Date parsed=null;
		if (dateValue!=null) {
			SimpleDateFormat format = 
	            new SimpleDateFormat(dformat);
			try {
				//logger.debug("This is the date value:" + dateValue);
	            parsed = format.parse(dateValue);
	        }
			catch(Exception e)
			{
	        	logger.debug("exception in coverting string to date.", e);
	        	return null;
			}
		}
		return parsed;
	}
	
	public static Date stringToDate(String dateValue, String dformat, TimeZone tz) {
		Date parsed=null;
		if (dateValue!=null) {
			SimpleDateFormat format = 
	            new SimpleDateFormat(dformat);
			format.setTimeZone(tz);
			try {
				//logger.debug("This is the date value:" + dateValue);
	            parsed = format.parse(dateValue);
	        }
			catch(Exception e)
			{
	        	logger.debug("exception in coverting string to date.", e);
	        	return null;
			}
		}
		return parsed;
	}
	
	public static String dateToString(Date dateValue, String dformat) {
		String display=null;
		if (dateValue!=null) {
			SimpleDateFormat format = 
	            new SimpleDateFormat(dformat);
			try {
				display = format.format(dateValue);
	        }catch(Exception e)
			{
	        	logger.debug("exception in coverting string to date.", e);
	        	return null;
			}
		}
		return display;
	}
	
	public static String dateToString(Date dateValue, String dformat, TimeZone tz) {
		String display=null;
		if (dateValue!=null) {
			SimpleDateFormat format = 
	            new SimpleDateFormat(dformat);
			format.setTimeZone(tz);
			try {
				display = format.format(dateValue);
	        }catch(Exception e)
			{
	        	logger.debug("exception in coverting string to date.", e);
	        	return null;
			}
		}
		return display;
	}
	
	public static Date getGMTNow()
	{
		//all server times are GMT
		Date now = 
			Calendar.getInstance(TimeZone.getDefault()).getTime();
		
		String nowString = DateUtils.dateToString(
				now, 
				DateUtils.FULL_TIMESTAMP_FORMAT, 
				TimeZone.getTimeZone("GMT"));
		
		Date gmtNow = DateUtils.stringToDate(
				nowString, DateUtils.FULL_TIMESTAMP_FORMAT);
		
		return gmtNow;
	}
	
	public static String formatSimpleDate(Date dateValue)
	{
		return dateToString(dateValue, SIMPLE_FORMAT);
	}
	
	public static Integer calculateAge(Date dob)
	{
		Calendar cal = new GregorianCalendar();
		cal.setTime(dob);
	    Calendar now = new GregorianCalendar();
	    int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
	     if((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
	       || (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH)
	       && cal.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH)))
	     {
	        res--;
	     }
	     return res;
	}
	
	public static Date calculateDob(Integer age)
	{
		Calendar now = new GregorianCalendar();
		int yearOfBirth = now.get(Calendar.YEAR) - age;
		Calendar cal = new GregorianCalendar(
				yearOfBirth, 
				now.get(Calendar.MONTH),
				now.get(Calendar.DAY_OF_MONTH));
		 return cal.getTime();
	}
	
	public static String formatDuration(
			Date from, Date to, boolean showMs, boolean showS) {
		
		long lMs = to.getTime() - from.getTime();
		
		//always positive
		if(lMs<0)
			lMs = lMs*-1;
		
		// Validate
		if (lMs > 0L) {
			// -- Declare variables
			String strDays = "";
			String strHours = "";
			String strMinutes = "";
			String strSeconds = "";
			String strMillisecs = "";
			String strReturn = "";
			long lRest;

			// -- Find values
			// -- -- Days
			strDays = String.valueOf(lMs / 86400000L);
			lRest = lMs % 86400000L;
			// -- -- Hours
			strHours = String.valueOf(lRest / 3600000L);
			lRest %= 3600000L;
			// -- -- Minutes
			strMinutes = String.valueOf(lRest / 60000L);
			lRest %= 60000L;
			// -- -- Seconds
			strSeconds = String.valueOf(lRest / 1000L);
			lRest %= 1000L;
			// -- -- Milliseconds
			strMillisecs = String.valueOf(lRest);

			// -- Format return
			// -- -- Days
			if (new Integer(strDays).intValue() == 1) {
				strReturn += strDays + "day ";
			} else if (new Integer(strDays).intValue() > 1) {
				strReturn += strDays + "days ";
			}
			
			// -- -- Hours
			if (new Integer(strHours).intValue() != 0) {
				strReturn += strHours + "hr ";
			}
			// -- -- Minutes
			if (new Integer(strMinutes).intValue() != 0) {
				strReturn += strMinutes + "min ";
			}
			// -- -- Seconds
			if (new Integer(strSeconds).intValue() != 0 && showS) {
				strReturn += strSeconds + "sec ";
			}
			// -- -- Milliseconds
			if (new Integer(strMillisecs).intValue() != 0 && showMs) {
				strReturn += strMillisecs + "ms";
			}

			return strReturn;
		} else if (lMs == 0L) {
			return "0ms";
		} else {
			return "-1";
		}
	}	
	
	/**
	 * This utility method returns a future date after number of days.
	 * 
	 * @param days
	 * @return
	 */
	public static Date getDateAfterDays(Date d, int days) {
		long backDateMS = d.getTime() + ((long) days) * 24 * 60
				* 60 * 1000;
		Date backDate = new Date();
		backDate.setTime(backDateMS);
		return backDate;
	}
	
	
	/**
	 * Calculates midnight of the day in which date lies with respect
	 * to a time zone.
	 **/
	public static Date midnight(Date date, TimeZone tz) {
	  Calendar cal = new GregorianCalendar(tz);
	  cal.setTime(date);
	  cal.set(Calendar.HOUR_OF_DAY, 0);
	  cal.set(Calendar.MINUTE, 0);
	  cal.set(Calendar.SECOND, 0);
	  cal.set(Calendar.MILLISECOND, 0);
	  return cal.getTime();
	}

	/**
	 * Adds a number of days to a date. DST change dates are handeled
	 * according to the time zone. That's necessary as these days don't
	 * have 24 hours.
	 */
	public static Date addDays(Date date, int days, TimeZone tz) {
	  Calendar cal = new GregorianCalendar(tz);
	  cal.setTime(date);
	  cal.add(Calendar.DATE, days);
	  return cal.getTime();
	}

	
	
	
}
