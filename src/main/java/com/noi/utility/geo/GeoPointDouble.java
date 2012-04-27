package com.noi.utility.geo;

public class GeoPointDouble {
	/**
		         * Latitude/Longitude coordinates.
		         */
				private double latitudeE6 = Double.NaN;
				private double longitudeE6 = Double.NaN;
	
		        /**
		         * East/North coordinates;
		         */
		        public double x = Double.NaN, y = Double.NaN;
		
		        /**
		         * Construct the point with latitude / longitude values.
		         * The x/y values are left uninitialized.
		         *
		         * @param lat Latitude of the point.
		         * @param lon Longitude of the point.
		         */
		        public GeoPointDouble(double lat, double lon) {
		                this.latitudeE6 = lat;
		                this.longitudeE6 = lon;
		        }
		
		        /**
		         * Construct the point with all values unset (set to NaN)
		         */
		        public GeoPointDouble() {
		        }
		
		        @Override
		        public GeoPointDouble clone() {
		                try {return (GeoPointDouble)super.clone();} catch (CloneNotSupportedException e) {}
		                return null;
		        }

				public double getLatitudeE6() {
					return latitudeE6;
				}

				public void setLatitudeE6(double latitudeE6) {
					this.latitudeE6 = latitudeE6;
				}

				public double getLongitudeE6() {
					return longitudeE6;
				}

				public void setLongitudeE6(double longitudeE6) {
					this.longitudeE6 = longitudeE6;
				}
		        
		        
		        
}
