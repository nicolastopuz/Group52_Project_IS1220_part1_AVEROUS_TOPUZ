package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

/**
 * This class defines the GPS coordinates of an element. It is here used
 * to determine the location of a Station, or of a user.
 * 
 * @author 	Pierre Averous
 * @author	Nicolas Topuz
 * @since	1.0
 */

public class GPScoordinates {

	protected double latitude;
	protected double longitude;
	protected static final int R = 6371; // Radius of the Earth, in km
	
	//Mise en place des constructeurs
	
	/**
	 * Constructor for GPS coordinates, given the coordinates
	 * in DMS format (degrees, minutes, seconds), as a string
	 * (DD°MM'SS,SSS").
	 * 
	 * @param latitudeDMS	as a string in DMS format (DD°MM'SS,SSS").
	 * @param longitudeDMS	as a string in DMS format (DD°MM'SS,SSS").
	 */
	public GPScoordinates(String latitudeDMS, String longitudeDMS) {
		
		double latD, latM, latS, lonD, lonM, lonS;
		
		latD = Double.parseDouble(latitudeDMS.substring(0,1));
		latM = Double.parseDouble(latitudeDMS.substring(3,4));
		latS = Double.parseDouble(latitudeDMS.substring(6));
		
		lonD = Double.parseDouble(longitudeDMS.substring(0,1));
		lonM = Double.parseDouble(longitudeDMS.substring(3,4));
		lonS = Double.parseDouble(longitudeDMS.substring(6));
		
		this.latitude=latD + (latM/60) + (latS/3600); 
		this.longitude=lonD + (lonM/60) + (lonS/3600); 
	}	
	
	/**
	 * Constructor for GPS coordinates, given the decimal
	 * latitude and longitude.
	 * 
	 * @param latitude
	 * @param longitude
	 */
	public GPScoordinates(double latitude, double longitude) {
		this.latitude=latitude; 
		this.longitude=longitude; 
	}
	
	//Autres méthodes
	/**
	 * This method calculates the distance in kilometers between
	 * two points, given their GPS coordinates. This method uses
	 * the Haversine formula to calculate the distance.
	 * The Haversine formula is used to determine the great-circle distance
	 * between two points on a sphere of known radius, given their latitudes 
	 * and longitudes.
	 *
	 * @param	pointB	the point to which the distance should be
	 * 					calculated to.
	 * @return	the distance between the 2 points, in km.
	 */
	public double distanceTo(GPScoordinates pointB) {
		double latB = pointB.getLatitude();
		double lonB = pointB.getLongitude();
		
		double dLat = (latB-this.latitude)*(Math.PI/180); //Angle between latitudes of points A and B, converted to radians.
		double dLon = (lonB-this.longitude)*(Math.PI/180); //Angle between longitudes of points A and B, converted to radians.
		
		double a = 
				Math.sin(dLat/2) * Math.sin(dLat/2) +
				Math.cos(this.latitude*Math.PI/180) * Math.cos(latB*Math.PI/180) *
				Math.sin(dLon/2) * Math.sin(dLon/2)
				;
		
		double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double distance = R * b;
		return distance;
	}
	
	/**
	 * This is a static method, that allow to calculate the distance 
	 * in kilometers between two points, A and B, given their GPS coordinates. 
	 * This method uses the Haversine formula to calculate the distance.
	 * The Haversine formula is used to determine the great-circle distance
	 * between two points on a sphere of known radius, given their latitudes 
	 * and longitudes.
	 * 
	 * @param	pointA	the first point.
	 * @param 	pointB	the point to which the distance should be
	 * 					calculated to.
	 * @return	the distance between the 2 points, in km.
	 */
	public static double distanceAB(GPScoordinates pointA, GPScoordinates pointB) {
		double latA = pointA.getLatitude();
		double lonA = pointA.getLongitude();		
		double latB = pointB.getLatitude();
		double lonB = pointB.getLongitude();
		
		double dLat = (latB-latA)*(Math.PI/180); //Angle between latitudes of points A and B, converted to radians.
		double dLon = (lonB-lonA)*(Math.PI/180); //Angle between longitudes of points A and B, converted to radians.
		
		double a = 
				Math.sin(dLat/2) * Math.sin(dLat/2) +
				Math.cos(latA*Math.PI/180) * Math.cos(latB*Math.PI/180) *
				Math.sin(dLon/2) * Math.sin(dLon/2)
				;
		
		double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double distance = R * b;
		return distance;
	}
	
	
	//Mise en place des getters
	/**
	 * Getter method for latitude.
	 * 
	 * @return latitude of these coordinates.
	 */
	public double getLatitude() {
		return this.latitude;
	}
	
	/**
	 * Getter method for longitude.
	 * 
	 * @return longitude of these coordinates.
	 */
	public double getLongitude() {
		return this.longitude;
	}
		
	
	@Override
	public String toString() {
		return (this.latitude + ", " + this.longitude);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GPScoordinates) {
			GPScoordinates that = (GPScoordinates) obj;
			return (this.longitude==that.getLongitude() && this.latitude==that.getLatitude());
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (int) (41*(41+this.longitude+this.latitude));
	}

	
	
	
}
