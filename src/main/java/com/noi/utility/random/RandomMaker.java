/*
 * RandomMaker.java
 *
 * Created on November 20, 2001, 9:01 PM
 */

package com.noi.utility.random;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author  clay
 * @version 
 */
public class RandomMaker {
	
	private static Random _rnd;
	
	public static int nextInt(int lower, int higher) {
		if(_rnd == null)
			_rnd = new Random(new Date().getTime());
		
		int ran = _rnd.nextInt(higher - lower);
		
		return ran+lower;

	}
	
	public static long nextLong(long lower, long higher) {
		
		if(_rnd == null)
			_rnd = new Random(new Date().getTime());
		
		//get the range, casting to long to avoid overflow problems
	    long range = higher - lower + 1;
	    // compute a fraction of the range, 0 <= frac < range
	    long fraction = (long)(range * _rnd.nextDouble());
	    long randomNumber =  fraction + lower; 
		
		return randomNumber;

	}
    
    public static float getFloat(float val)
    {
		if(_rnd == null)
			_rnd = new Random(new Date().getTime());
		
        float rval = _rnd.nextFloat();
        
        if(rval != val)
            return  rval;
        else
        {
            float nv = getFloat(rval);
            return nv;
        }
            
    }
    
    public static float getFloat()
    {
		if(_rnd == null)
			_rnd = new Random(new Date().getTime());
		
        float rval = _rnd.nextFloat();
        
        return rval;
            
    }
    
    public static boolean getBoolean()
    {
    	switch(nextInt(0, 1000)%2)
    	{
    	case 0:
    		return false;
    	case 1:
    		return true;
    	default:
    		return true;
    	}

    }
    
    public static String makeString(int len)
    {
		if(_rnd == null)
			_rnd = new Random(new Date().getTime());
        String rs = "";
        for(int i =0; i<=len; i++)
        {
            int val = 65+_rnd.nextInt(23);
            char c = (char)val;
            Character ch = new Character(c);
            rs = rs+ch.toString();
        }
        return rs;
    }

    public static String makeSentance(int len)
    {
		StringBuffer buf = new StringBuffer();
		while (buf.length()<len) {
			String word = makeString(nextInt(3, 12));
			buf.append(word+" ");			
		}
		return buf.toString().substring(0,buf.length()-1);
    }

}
