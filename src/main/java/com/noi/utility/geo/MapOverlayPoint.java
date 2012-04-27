package com.noi.utility.geo;


/**
 * @author cgraham
 *
 */
public class MapOverlayPoint extends GeoPoint {
	
	public static final int TYPE_MOMENT = 0;
	public static final int TYPE_USER = 1;
	public static final int TYPE_MESSAGE = 2;
	
	private int mType;
	private int mIndex;
	
	public MapOverlayPoint(
			int latitudeE6, 
			int longitudeE6, 
			int type, 
			int index) 
	{
		super(latitudeE6, longitudeE6);
		mType = type;
		mIndex = index;
	}

	public int getType() {
		return mType;
	}

	public int getIndex() {
		return mIndex;
	}





}
