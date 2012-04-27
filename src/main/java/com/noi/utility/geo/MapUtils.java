package com.noi.utility.geo;

import java.util.List;


public class MapUtils {
	
	public static GeoPoint doubleFractionalToGeoPoint(double latE6, double lonE6)
	{
		GeoPoint p = new GeoPoint((int) (latE6 * 1000000), (int)
                (lonE6 * 1000000));
		return p;
	}
	
	public static GeoPoint computeCenter(GeoPointBounds bounds)
	{	
		int latSpanE6 = 
			bounds.getMax().getLatitudeE6() -
			bounds.getMin().getLatitudeE6();
		
		int lonSpanE6 = 
			bounds.getMax().getLongitudeE6() -
			bounds.getMin().getLongitudeE6();
		
		GeoPoint pCenter = new GeoPoint(
				(int) (bounds.getMin().getLatitudeE6() + (latSpanE6/2)), 
				(int) (bounds.getMin().getLongitudeE6() + (lonSpanE6/2)));
		
		return pCenter;
	}
	
	public static Integer computeZoom(GeoPointBounds bounds)
	{
		return 13;
	}
	
	
	public static GeoPointBounds computeBounds(List<GeoPoint> points)
	{
		int minLat = 10000* 1000000;
		int minLong = 10000* 1000000;
		int maxLat = -10000* 1000000;
		int maxLong = -10000* 1000000;
	
		for (GeoPoint geoPoint : points) {
			if(geoPoint.getLatitudeE6()<minLat)
				minLat = geoPoint.getLatitudeE6();
			if(geoPoint.getLatitudeE6()>maxLat)
				maxLat = geoPoint.getLatitudeE6();	
			if(geoPoint.getLongitudeE6()<minLong)
				minLong = geoPoint.getLongitudeE6();
			if(geoPoint.getLongitudeE6()>maxLong)
				maxLong = geoPoint.getLongitudeE6();
		}
		
		
		GeoPoint pMin = new GeoPoint((int) (minLat), (int)
                (minLong));
		GeoPoint pMax = new GeoPoint((int) (maxLat), (int)
                (maxLong));
		
		GeoPointBounds bounds = 
			new GeoPointBounds(pMin, pMax);
		
		return bounds;
	}	
	
}

