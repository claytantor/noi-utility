/*
 * CharOffset.java
 *
 * Created on February 5, 2002, 9:36 PM
 */

package com.noi.utility.data;

/**
 *
 * @author  clay
 * @version 
 */
public class CharOffset {

    //very slow conversion this should be an ascii offset
    public static int letterToNumber(String as_letter)
    {
        String letter = as_letter.toLowerCase();
        if(letter.compareTo("a")==0)
            return 1;
        else if (letter.compareTo("b")==0)
            return 2;
        else if  (letter.compareTo("c")==0)
            return 3;
        else  if (letter.compareTo("d")==0)
            return 4;
        else  if (letter.compareTo("e")==0)
            return 5;
        else if  (letter.compareTo("f")==0)
            return 6;
        else if  (letter.compareTo("g")==0)
            return 7;
        else if  (letter.compareTo("h")==0)
            return 8;
        else
            return 0;
    }
    
    //very slow conversion this should be an ascii offset
    public static String numberToLetter(int _num)
    {

        if (_num==1)
            return"a";
        else if  (_num==2)
            return "b";
        else if  (_num==3)
            return "c";
        else  if(_num==4)
            return "d";
        else  if (_num==5)
            return "e";
        else if  (_num==6)
            return "f";
        else if (_num==7)
            return "g";
        else if  (_num==8)
            return "h";
        else
            return "z";
    }

}
