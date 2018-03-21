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
	public GPScoordinates(String latitudeDMS, String longitudeDMS) throws BadCoordinatesSyntaxException, OutOfBoundsException, InvalidCoordinatesException{
		if(!latitudeDMS.contains("°") || !latitudeDMS.contains("'") || !latitudeDMS.contains("\"")) {
			throw new BadCoordinatesSyntaxException();
		}
		else if(!longitudeDMS.contains("°") || !longitudeDMS.contains("'") || !longitudeDMS.contains("\"")) {
			throw new BadCoordinatesSyntaxException();
		}
		else {
			double[] modLat;
			double[] modLon;
			String[] latDMS = latitudeDMS.split("°|'|\"");
			String[] lonDMS = longitudeDMS.split("°|'|\"");
			modLat = moduloLatitude(latDMS);
			modLon = moduloLongitude(lonDMS);
			this.latitude=modLat[0] + (modLat[1]/60) + (modLat[2]/3600); 
			this.longitude=modLon[0] + (modLon[1]/60) + (modLon[2]/3600); 
		}
	}	
	
	/**
	 * Constructor for GPS coordinates, given the decimal
	 * latitude and longitude.
	 * 
	 * @param latitude
	 * @param longitude
	 */
	public GPScoordinates(double latitude, double longitude) throws OutOfBoundsException {
		double d = longitude;
		d = d%360;
		longitude = longitude%180;
		
		if(latitude>90 || latitude<-90) {throw new OutOfBoundsException();}
		else {
			if(d>180 && d<360) {longitude-=180;}
			else if(d<-180 && d>-360) {longitude+=180;}
			
			this.latitude=latitude; 
			this.longitude=longitude; 
		}
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
	 * 
	 * @param	pointA	the first point.
	 * @param 	pointB	the point to which the distance should be
	 * 					calculated to.
	 * @return	the distance between the 2 points, in km.
	 */
	public static double distanceAB(GPScoordinates pointA, GPScoordinates pointB) {
		double distance = pointA.distanceTo(pointB);
		return distance;
	}
	
	/**
	 * A simple mathematical function to guarantee that the longitude's 
	 * values are in order : degrees between 180 and -180, minutes and seconds between 0 and 60.
	 * 
	 * @param latDMS	a String array, containing degrees, minutes and seconds for longitude
	 * @return 	a double array, containing longitude's degrees, minutes and seconds.	
	 */
	protected static double[] moduloLongitude(String[] lonDMS) throws InvalidCoordinatesException {
		
		double lonD, lonM, lonS;
		lonD = Double.parseDouble(lonDMS[0]);
		lonM = Double.parseDouble(lonDMS[1]);
		lonS = Double.parseDouble(lonDMS[2]);
		
		if(lonM<0 || lonS<0) {
			throw new InvalidCoordinatesException();
		}
		else {
			while(lonS > 60) { //Modulo seconds, and increment minutes accordingly
				lonS-=60;
				lonM++;
			}
			while(lonM > 60) { //Modulo minutes, and increment degrees accordingly
				lonM-=60;
				lonD++;
			}
			double c=lonD;
			c = c%360;
			lonD = lonD%180;
			if(c>180 && c<360) {lonD-=180;}
			else if(c<-180 && c>-360) {lonD+=180;}	
			double[] result = {lonD, lonM, lonS};
			return result;
		}
	}
	
	/**
	 * A simple mathematical function to guarantee that the latitude's 
	 * values are in order : degrees between 90 and -90, minutes and seconds between 0 and 60.
	 * 
	 * @param latDMS	a String array, containing degrees, minutes and seconds for latitude
	 * @return 	a double array, containing latitude's degrees, minutes and seconds.	
	 */
	protected static double[] moduloLatitude(String[] latDMS) throws InvalidCoordinatesException, OutOfBoundsException {
		
		double latD, latM, latS;
		latD = Double.parseDouble(latDMS[0]); 
		latM = Double.parseDouble(latDMS[1]);
		latS = Double.parseDouble(latDMS[2]);

		if(latM<0 || latS<0) {
			throw new InvalidCoordinatesException();
		}
		else {
			while(latS > 60) { //Modulo seconds, and increment minutes accordingly
				latS-=60;
				latM++;
			}
			while(latM > 60) { //Modulo minutes, and increment degrees accordingly
				latM-=60;
				latD++;
			}
			if(latD>90 || latD<-90) {throw new OutOfBoundsException();}
			else {				
				double[] result = {latD, latM, latS};
				return result;
			}	
		}
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
