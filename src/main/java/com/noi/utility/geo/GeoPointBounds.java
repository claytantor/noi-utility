package com.noi.utility.geo;


public class GeoPointBounds {
	private GeoPoint min;
	private GeoPoint max;
	
	
	
	public GeoPointBounds(GeoPoint min, GeoPoint max) {
		super();
		this.min = min;
		this.max = max;
	}
	
	public GeoPoint getMin() {
		return min;
	}
	public void setMin(GeoPoint min) {
		this.min = min;
	}
	public GeoPoint getMax() {
		return max;
	}
	public void setMax(GeoPoint max) {
		this.max = max;
	}
	

}
