/*
 * FileToString.java
 *
 * Created on November 27, 2002, 9:53 AM
 */

package com.noi.utility.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * @author clay
 */
public class FileToString {

	static Logger logger = Logger.getLogger(FileToString.class);

	public static String readFileToSingleString(String f) {
		String s = new String("");
		BufferedReader fis = null;
		try {
			// URL u = new URL( codebase, f ) ;
			fis = new BufferedReader(new FileReader(f));
		} catch (Exception e) {
			s = "readFile : " + f + "\r\n --> Exception : " + e;
			return s;
		}
		String sS = new String("");
		while (true) {
			try {
				sS = fis.readLine();
				if (sS != null)
					s = s + "\r\n" + sS;
				else
					break;
			} catch (Exception e) {
				logger.debug("end");
				break;
			}
		}
		return s;
	}

	public static List<String> readFileToStringList(String f) {

		List<String> lines = new ArrayList<String>();
		BufferedReader fis = null;
		try {
			// URL u = new URL( codebase, f ) ;
			fis = new BufferedReader(new FileReader(f));
		} catch (Exception e) {
			logger.error("readFile : " + f + "\r\n --> Exception : ", e);
			return lines;
		}
		String sS = new String("");
		while (true) {
			try {
				sS = fis.readLine();
				if (sS != null)
					lines.add(sS);
				else
					break;
			} catch (Exception e) {
				logger.debug("end");
				break;
			}
		}
		return lines;
	}

}
