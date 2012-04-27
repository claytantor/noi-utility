package com.noi.utility.geo;

public class GeoPoint {
	private int longitudeE6;
	private int latitudeE6;
	
	
	public GeoPoint(int latitudeE6,int longitudeE6) {
		super();
		this.longitudeE6 = longitudeE6;
		this.latitudeE6 = latitudeE6;
	}
	
	public GeoPoint(double latitudeFractional,double longitudeFractional) {
		this((int) (latitudeFractional * 1000000), (int)
                (longitudeFractional * 1000000));
	}
	
	public int getLongitudeE6() {
		return longitudeE6;
	}

	public int getLatitudeE6() {
		return latitudeE6;
	}

	public double getLongitudeFractional()
	{
		return (double)longitudeE6 / 1000000;
	}
	public double getLatitudeFractional()
	{
		return (double)latitudeE6 / 1000000;
	}


}
