/*
 * TrigHelper.java
 *
 * Created on November 29, 2001, 10:54 AM
 */

package com.noi.utility.math;

/**
 *
 * @author  clay
 * @version 
 */
public class TrigHelper {

    /** Creates new TrigHelper */
    public TrigHelper() {
    }
    
    //in degrees
    public static float getAngle(Vec2f _p1, Vec2f _p2)
    {
        TrigVector v = new TrigVector( _p1,  _p2);
        return v.getAngle();
        
    }
    
    public static float getDistance(Vec2f _p1, Vec2f _p2)
    {
        TrigVector v = new TrigVector( _p1, _p2);
        return v.getMagnitude();
        
    }
    
    

}
