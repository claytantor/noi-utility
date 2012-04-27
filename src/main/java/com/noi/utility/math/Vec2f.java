/*
 * Vec2f.java
 *
 * Created on December 5, 2001, 8:52 AM
 */

package com.noi.utility.math;

/**
 *
 * @author  clay
 * @version 
 */
public class Vec2f {

    /** Holds value of property x. */
    private float x;
    
    /** Holds value of property y. */
    private float y;
    
    /** Creates new Vec2f */
    public Vec2f(float _x, float _y) {
        x = _x;
        y = _y;
    }

    /** Getter for property x.
     * @return Value of property x.
     */
    public float getX() {
        return x;
    }
    
    /** Setter for property x.
     * @param x New value of property x.
     */
    public void setX(float x) {
        this.x = x;
    }
    
    /** Getter for property y.
     * @return Value of property y.
     */
    public float getY() {
        return y;
    }
    
    /** Setter for property y.
     * @param y New value of property y.
     */
    public void setY(float y) {
        this.y = y;
    }
    
}
