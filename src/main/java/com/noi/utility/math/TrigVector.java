/*
 * TrigVector.java
 *
 * Created on November 29, 2001, 11:01 AM
 */

package com.noi.utility.math;

/**
 *
 * @author  clay
 * @version 
 */
public class TrigVector {
    
    private float x1, y1, x2, y2;
    private Vec2f p1, p2;

    /** Creates new TrigVector */
    public TrigVector(Vec2f _p1, Vec2f _p2) {
        p1 = _p1;
        p2 = _p2;
    }
    
    public Vec2f getP1()
    {
        return p1;
    }
    
    public Vec2f getP2()
    {
        return p2;
    }
    
    
    public float getMagnitude()
    {
        float xr = p2.getX()-p1.getX();
        float yr = p2.getY()-p1.getY();
        float mag = (float)Math.sqrt((double)((xr*xr)+(yr*yr)));
        return mag;
    }
    
    //in degrees
    public float getAngle()
    {
        
        float _x2 = p2.getX()-p1.getX();
        float _y2 = p2.getY()-p1.getY();
 
                
        float _h = getMagnitude();        
        //sin omega = (opposite)/(hypot)       
        float omega = _y2/_h;
        float rads = (float)Math.asin(omega);
        
        //float omega = _y2/_x2;        
        //float rads = (float)Math.atan(omega);
        float degrees = (float)Math.toDegrees((double)rads);
        float beta = (float)0.0;
        
/*_x2:60.0 _y2:50.0 degrees:39.805573
vector angle:50.194427 magnitude:78.10249
_x2:60.0 _y2:-50.0 degrees:-39.805573
vector angle:129.80557 magnitude:78.10249
_x2:-60.0 _y2:-50.0 degrees:-39.805573
vector angle:230.19443 magnitude:78.10249
_x2:-60.0 _y2:50.0 degrees:39.805573
vector angle:309.80557 magnitude:78.10249
_x2:0.0 _y2:-50.0 degrees:-90.0
vector angle:0.0 magnitude:50.0
_x2:60.0 _y2:0.0 degrees:0.0
vector angle:0.0 magnitude:60.0
_x2:0.0 _y2:50.0 degrees:90.0
vector angle:0.0 magnitude:50.0
_x2:-60.0 _y2:0.0 degrees:0.0
vector angle:0.0 magnitude:60.0 */       
        
        if((_x2 >0) && (_y2 > 0))
             beta = 90-degrees;
        else if ((_x2 >0) && (_y2 < 0))
             beta = 90-degrees;
        else if ((_x2 <0) && (_y2 < 0))
             beta = 270+degrees;
        else if ((_x2 <0) && (_y2 > 0))
             beta = 270+degrees;
        else if ((_x2 ==0) && (_y2 > 0))
             beta = (float)0.0;
        else if ((_x2 >0) && (_y2 == 0))
             beta = (float)90.0;
        else if ((_x2 ==0) && (_y2 < 0))
             beta = (float)180.0;
        else if ((_x2 <0) && (_y2 == 0))
             beta = (float)270.0;
        
        
                
        return beta;
 
    }
    
    public TrigVector getUnitVector()
    {
        float _degrees = getAngle();
        float _rads = (float)Math.toRadians((double)_degrees);
        float _ry = (float)Math.sin(_rads);
        float _rx = (float)Math.cos(_rads);
        Vec2f o = new Vec2f((float)0, (float)0);
        Vec2f p = new Vec2f(_rx, _ry);
        return new TrigVector(o,p);
                              
    }

}
