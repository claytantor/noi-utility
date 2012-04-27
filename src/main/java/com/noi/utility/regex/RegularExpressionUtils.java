package com.noi.utility.regex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionUtils {
	
	
	
    public static String makeFilteredString(String regexp, String eval)
    {
        int counter = 0;
        StringBuffer buf = new StringBuffer();
        Pattern p = Pattern.compile(regexp);
        Matcher pmatcher = p.matcher(eval);
        while(pmatcher.find())
        {
            buf.append(eval.substring(pmatcher.start(), pmatcher.end()));
        }
        return buf.toString();
    }
	
	   /**
     * This method will count the number times a specific regular expression exists in
     * a string.
     *
     * @param regexp The pattern that will be counted. A JAVA regular expression.
     * @param eval The string to count regular expressions in
     * @return integer. The number of times the pattern was found.
     */ 
    
    public static int countExpressionInString(String regexp, String eval)
    {
        int counter = 0;
        
        Pattern p = Pattern.compile(regexp);
        Matcher pmatcher = p.matcher(eval);
        while(pmatcher.find())
        {
            counter++;
        }
        return counter;
    }
    
    /**
     * Simple test to check if quotes are matched in a specific string. This method
     * counts the number of times quotes are found. If odd it return false, else it
     * returns true.
     * @param eval The string to evaluate
     * @return TRUE if matched.
     */    
    public static boolean isQuoteMatched(String eval)
    {
        int countopen = countExpressionInString("\\x22",eval);
        if (countopen%2 == 0) { 
            // n is even 
            return true;
        }
        else { 
            // n is odd 
            return false;
        }        
    }
    
    /**
     * Simple test to check if parens are matched in a specific string. This method
     * counts the number of open and closed quotes. If not equal it returns false, else it
     * returns true.
     *
     * @param eval The string to test for matching quotes
     * @return boolean. True if parens are matched.
     */    
    public static boolean isParensNestingMatched(String eval)
    {   
        int countopen = countExpressionInString("\\x28",eval);
        int countclosed = countExpressionInString("\\x29",eval);
        if (countopen == countclosed) { 
            // n is even 
            return true;
        }
        else { 
            // n is odd 
            return false;
        }        
                
    }
  
    /**
     * Simple test to check if parens exist in the string.
     *
     * @param eval The string to check for parens
     * @return boolean. True if the string has parens.
     */    
    public static boolean hasParens(String eval)
    {
        Pattern p = Pattern.compile("\\x28|\\x29");
        Matcher pmatcher = p.matcher(eval);
        return pmatcher.find();
    }

    /**
     * This method checks for the existence of an operator in a string.
     * @param eval The string to check for operators
     * @return TRUE if operator is in string.
     */    
    public static boolean hasOperator(String eval)
    {
        Pattern patternspace = Pattern.compile("\\s");
        Matcher smatcher = patternspace.matcher(eval);
        smatcher.find();
        int spos = smatcher.start();
        String firstword = cleanWhitespace(eval.substring(0, spos)).toUpperCase();

        //match on operator
        Pattern patternoper = Pattern.compile("AND |OR |NOT ");
        Matcher omatcher = patternoper.matcher(firstword);
        return omatcher.find();
    }


    /**
     * This method will return a list of integer values for the search pattern of a
     * regular expression in a string to be evaluated. This is very helpful for lexical
     * analysis.
     * @param regexp The JAVA regular expression to search for.
     * @param eval The string to be evaluated
     * @return An array of integer positions. If there is no match a single value array
     * of int[0] will be returned that will equal -1.
     */    
    public static int[] getPositions(String regexp, String eval)
    {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(eval);

        //find period index
        //int buffer is max of 1024
        //I hate limits, but the alternative
        //is way slow
        int[] tpos = new int[1024];
        int tpindex = 0;
        boolean found = false;
        while(found = matcher.find()) {
            tpos[tpindex] = matcher.start();
            tpindex++;
        }
        //now copy into simple array
        int[] pos = new int[tpindex];
        for(int i=0; i<tpindex;i++)
        {
            pos[i] = tpos[i];
        }
        return pos;

    }

	/**
	 * This method will remove parens from a string and return the new value.
	 * @param eval The string to remove parens from
	 * @return The new value with removed parens.
	 * @TODO JUNIT Test to validate the bevaiour of this method.
	 */    
	public static  String cleanParens(String eval)
	{
	    //nest on parens
	    Pattern p = Pattern.compile("[^\\x28\\x29]?");
	    Matcher pmatcher = p.matcher(eval);
	    StringBuffer buf = new StringBuffer();
	    while(pmatcher.find())
	    {
	        buf.append(eval.substring(pmatcher.start(), pmatcher.end()));
	    }
	
	    return buf.toString();
	
	}

	/**
	 * This method removes root level operators from a search phrase root. It is a
	 * utility method used by the parser to evaluate the query.
	 * @param eval The search phrase root that will have its operators removed.
	 * @return The cleaned search phrase root.
	 * @TODO This method should use the PPISearchPhraseInfo.getRegExp method to determine
	 * what the operators are. This would let the phrase drive the regexpr instaead of
	 * this implementation.
	 */    
	public static  String cleanRootOperators(String eval)
	{
	    
	    Pattern patternspace = Pattern.compile("AND |OR |NOT |EQUALS ");
	    Matcher omatcher = patternspace.matcher(eval);
	    int newindex = 0;
	    if(omatcher.find() && (omatcher.start()==0))
	    {
	        newindex = omatcher.end(); 
	    }
	    return eval.substring(newindex);
	}

	/**
	 * This method removes general operators from a string. It is a
	 * utility method used by the parser to evaluate the query.
	 * @param eval The search phrase root that will have its operators removed.
	 * @return The cleaned search phrase root.
	 * @TODO The use of cleanRootOperators should be reviewed. It seems redundant to have
	 * both.
	 */    
	public static  String cleanOperators(String eval)
	{
	    
	  String newphrase = eval;
	  String[] originals = {"OR ", "AND ", "NOT ", "EQUALS "};
	  for(int i=0; i<originals.length; i++)
	  {
	     newphrase = Pattern.compile(originals[i], Pattern.DOTALL).matcher(newphrase).replaceAll(""); 
	  }     
	  return newphrase;
	    
	}

	/**
	 * This is a general cleaner method to process string for leading and trailing
	 * white space. This method will remove any leading or trailing spaces from a
	 * string.
	 *
	 * ie "     BLOOD  IN THE STOOL     "
	 * becomes: "BLOOD  IN THE STOOL"
	 *
	 * A JUNIT test exists in PPIQueryTest to validate this behaviour.
	 * @param eval String. The string to remove whitespace from.
	 * @return String. The new cleaned string.
	 */    
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

	/**
	 * Simple parser utility method allows for the removal of extra spaces from string.
	 * The new string will have any extra spaces removed.
	 *
	 * ie. "      BLOOD     OR TREATMENT"
	 * will become:  " BLOOD OR TREATMENT"
	 * @param eval String. The value to have extra spaces removed.
	 * @return String. The new value with the extra spaces removed.
	 */    
	public static  String cleanExtraSpaces(String eval)
	{
	    StringBuffer cleaned = new StringBuffer(0);
	    byte[] bbuf = eval.getBytes();
	    char last = 0;
	    for(int i=0; i<bbuf.length;i++)
	    {
	        char c = (char)bbuf[i];
	        if((c == 32) && (last != 32))
	        {
	            cleaned.append(c);
	        }
	        else if (c != 32)
	        {
	            cleaned.append(c);
	        }
	        
	        last = c;          
	    }
	
	    return cleaned.toString();
	
	}
	
    /* remove leading whitespace */
    public static String ltrim(String source) {
        return source.replaceAll("^\\s+", "");
    }

    /* remove trailing whitespace */
    public static String rtrim(String source) {
        return source.replaceAll("\\s+$", "");
    }

    /* replace multiple whitespaces between words with single blank */
    public static String itrim(String source) {
        return source.replaceAll("\\b\\s{2,}\\b", " ");
    }

    /* remove all superfluous whitespaces in source string */
    public static String trim(String source) {
        return itrim(ltrim(rtrim(source)));
    }

    public static String lrtrim(String source){
        return ltrim(rtrim(source));
    }
	
	public static String replace(String inputStr, String patternStr, String replacementStr)
	{
        // Compile regular expression
        Pattern pattern = Pattern.compile(patternStr);

        // Replace all occurrences of pattern in input
        Matcher matcher = pattern.matcher(inputStr);
        String output = matcher.replaceAll(replacementStr);
        
		return output;
	} 	
}
