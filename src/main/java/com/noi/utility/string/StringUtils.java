package com.noi.utility.string;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;

import org.apache.commons.io.IOUtils;

public class StringUtils extends org.apache.commons.lang.StringUtils {
	
	public static long calculateChecksum(String target)
	throws IOException
	{
		byte buffer[] = target.getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
		CheckedInputStream cis = new CheckedInputStream(bais, new Adler32());
		byte readBuffer[] = new byte[5];
		long value = -1;
		while (cis.read(readBuffer) >= 0){
		    value = cis.getChecksum().getValue();		    
		}
		return value;
	}
	
	public static boolean isEmpty(String testString)
	{
		if(testString == null)
			return true;
		if(cleanWhitespace(testString).length()==0)
			return true;
		
		return false;
			
	}
	
	public static  String cleanWhitespace(String eval)
	{
	    Pattern patternspace = Pattern.compile("\\S");
	    Matcher smatcher = patternspace.matcher(eval);
	    String result = eval;
	    int start = 0;
	    int end = eval.length();
	    boolean set = false;
	    while(smatcher.find())
	    {
	        if((smatcher.start() > 0) && (set == false))
	        {
	            start = smatcher.start();
	        }
	        set = true;
	        end = smatcher.end();
	    }
	
	
	    if(start > 0)
	        result = eval.substring(start, end);
	    else
	        result = eval.substring(0, end);
	
	    return result;
	
	}

	public static String truncate(String eval, String suffix, int targetLength)
	{
		if(eval.length()>targetLength)
			return eval.substring(0, targetLength-1)+suffix;
		else
			return eval;
	}
	
	public static CharSequence makeSequence(String source)
	{
		return source.subSequence(0, source.length());
	}
	
	public static String readResource(String classpath, Class resourceClass, String encoding)
	throws IOException
	{
		StringBuffer buf = new StringBuffer();
		InputStream ios = resourceClass.getResourceAsStream(classpath);
		return IOUtils.toString(ios, encoding);
	}
	
	/** @return an array of adjacent letter pairs contained in the input string */

	   public static String[] letterPairs(String str) {

	       int numPairs = str.length()-1;

	       String[] pairs = new String[numPairs];

	       for (int i=0; i<numPairs; i++) {

	           pairs[i] = str.substring(i,i+2);

	       }

	       return pairs;

	   }
	
	/** @return an ArrayList of 2-character Strings. */
	public static ArrayList<String> wordLetterPairs(String str) {

	       ArrayList allPairs = new ArrayList();

	       // Tokenize the string and put the tokens/words into an array
	       String[] words = str.split("\\s");

	       // For each word

	       for (int w=0; w < words.length; w++) {

	           // Find the pairs of characters

	           String[] pairsInWord = letterPairs(words[w]);

	           for (int p=0; p < pairsInWord.length; p++) {

	               allPairs.add(pairsInWord[p]);

	           }

	       }

	       return allPairs;
	   }
	
	/** @return lexical similarity value in the range [0,1] */
	   public static double compareStrings(String str1, String str2) {

	       ArrayList pairs1 = wordLetterPairs(str1.toUpperCase());

	       ArrayList pairs2 = wordLetterPairs(str2.toUpperCase());

	       int intersection = 0;

	       int union = pairs1.size() + pairs2.size();

	       for (int i=0; i<pairs1.size(); i++) {

	           Object pair1=pairs1.get(i);

	           for(int j=0; j<pairs2.size(); j++) {

	               Object pair2=pairs2.get(j);

	               if (pair1.equals(pair2)) {

	                   intersection++;

	                   pairs2.remove(j);

	                   break;

	               }

	           }

	       }

	       return (2.0*intersection)/union;

	   }

}
