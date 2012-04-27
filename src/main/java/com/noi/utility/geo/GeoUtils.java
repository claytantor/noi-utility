package com.noi.utility.geo;

import java.util.List;


public class GeoUtils {
	public static GeoPoint doubleE6ToGeoPoint(double latE6, double lonE6)
	{
		GeoPoint p = new GeoPoint((int) (latE6 * 1000000), (int)
                (lonE6 * 1000000));
		return p;
	}
	
	public static double[] geoPointToDoubles(GeoPoint p)
	{
		double[] latlon = new double[2];
		latlon[0] = p.getLatitudeE6()/1000000.0;
		latlon[1] = p.getLongitudeE6()/1000000.0;
		
		return latlon;
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
	

	public static GeoPointBounds computeBounds(
			int latitudeCenter,
			int longitudeCenter,
			int latitudeSpan, 
			int logitudeSpan)
	{
		//min
		int minLat = latitudeCenter - (latitudeSpan/2);
		int minLon = longitudeCenter - (logitudeSpan/2);
		GeoPoint pMin = new GeoPoint(minLat, minLon);
		
		//max
		int maxLat = latitudeCenter + (latitudeSpan/2);
		int maxLon = longitudeCenter + (logitudeSpan/2);
		GeoPoint pMax = new GeoPoint(maxLat, maxLon);
		
			
		GeoPointBounds bounds = 
			new GeoPointBounds(pMin, pMax);
		
		return bounds;
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
