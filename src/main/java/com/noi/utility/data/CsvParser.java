package com.noi.utility.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class CsvParser {
	
	static Logger logger = Logger.getLogger(CsvParser.class);	

	
	public static final char DEFAULT_SEP = ',';

	/** Construct a CSV parser, with the default separator (`,'). */
	public CsvParser() {
		this(DEFAULT_SEP);
	}

	/** Construct a CSV parser with a given separator. Must be
	 * exactly the string that is the separator, not a list of
	 * separator characters!
	 */
	public CsvParser(char sep) {
		fieldSep = sep;
	}

	/** The fields in the current String */
	protected ArrayList<String> list = new ArrayList<String>();

	/** the separator char for this parser */
	protected char fieldSep;

	/** parse: break the input String into fields
	 * @return java.util.Iterator containing each field 
	 * from the original as a String, in order.
	 */
	public List<String> parse(String line)
	{
		StringBuffer sb = new StringBuffer();
		list.clear();			// discard previous, if any
		int i = 0;

		if (line.length() == 0) {
			list.add(line);
			return list;
		}

		do {
            sb.setLength(0);
            if (i < line.length() && line.charAt(i) == '"')
                i = advQuoted(line, sb, ++i);	// skip quote
            else
                i = advPlain(line, sb, i);
            list.add(sb.toString());
            logger.debug("parse: " +sb.toString());
			i++;
		} while (i < line.length());

		return list;
	}

	/** advQuoted: quoted field; return index of next separator */
	protected int advQuoted(String s, StringBuffer sb, int i)
	{
		int j;
		int len= s.length();
        for (j=i; j<len; j++) {
            if (s.charAt(j) == '"' && j+1 < len) {
                if (s.charAt(j+1) == '"') {
                    j++; // skip escape char
                } else if (s.charAt(j+1) == fieldSep) { //next delimeter
                    j++; // skip end quotes
                    break;
                }
            } else if (s.charAt(j) == '"' && j+1 == len) { // end quotes at end of line
                break; //done
			}
			sb.append(s.charAt(j));	// regular character.
		}
		return j; 
	}

	/** advPlain: unquoted field; return index of next separator */
	protected int advPlain(String s, StringBuffer sb, int i)
	{
		int j;

		j = s.indexOf(fieldSep, i); // look for separator
		logger.debug("csv: " + "i = " + i + " j = " + j);
        if (j == -1) {               	// none found
            sb.append(s.substring(i));
            return s.length();
        } else {
            sb.append(s.substring(i, j));
            return j;
        }
    }

}
