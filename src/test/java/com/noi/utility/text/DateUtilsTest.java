package com.noi.utility.text;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.noi.utility.date.DateUtils;

public class DateUtilsTest extends TestCase {

	static Logger logger = Logger.getLogger(DateUtilsTest.class);
	
	public void testDatesParsable()
	{
		logger.debug("testDatesParsable");
		String[][] dates = 
			{
				{"2008-11-16 11:10:24.0",DateUtils.FULL_TIMESTAMP_FORMAT},
				{"Thu May 07 05:32:16 UTC 2009",DateUtils.PATTERN_DATE_STRING},
				{"Thu May 07 05:32:16 UTC 2009","EEE MMM dd HH:mm:ss zzz yyyy"},
				{"2009-05-09_20:19:05-0700",DateUtils.NOSPACE_TIMESTAMP_FORMAT_TZ},
				
				
			};
		
		
		try {
			for (int i = 0; i < dates.length; i++) {
				String[] date = dates[i];
				Date d = 
					DateUtils.stringToDate(date[0], date[1]);
				logger.debug(d);
			}
		} catch (RuntimeException e) {
			fail("cannot parse date");
		}
		
	}
	
	public void testDatesFormatted()
	{
		logger.debug("testDatesFormatted");
		Date d = Calendar.getInstance().getTime();
		String[] formats = 
			{
				DateUtils.FULL_TIMESTAMP_FORMAT,
				DateUtils.PATTERN_DATE_STRING,
				DateUtils.SQL_TIMESTAMP_FORMAT,
				DateUtils.NOSPACE_TIMESTAMP_FORMAT,				
				DateUtils.NOSPACE_TIMESTAMP_FORMAT_TZ,				
			};
		
		
		try {
			for (int i = 0; i < formats.length; i++) {
				String format =	DateUtils.dateToString(d, formats[i]);
				logger.debug(format);
			}
		} catch (RuntimeException e) {
			fail("cannot parse date");
		}
		
	}
}
