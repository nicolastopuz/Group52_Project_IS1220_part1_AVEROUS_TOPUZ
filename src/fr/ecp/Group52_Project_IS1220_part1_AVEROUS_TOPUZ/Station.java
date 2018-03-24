package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;
import java.util.ArrayList;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.InvalidProportionsException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.MoreBikesThanSlotsException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoAvailableBikeException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoFreeSlotException;

/**
 * Stations are among the most important elements of the myVelib
 * system. A station is where bicycles can be rented and dropped. 
 * It consists of a number of parking slots where bikes are stored 
 * and of a terminal which users can interact with in order to 
 * rent bicycles. Parking slots can be be occupied by a bicycle, 
 * free or out-of-order. A station is located in a specific place 
 * specified by GPS coordinates. A station can be on service or 
 * offline: if it is offline all of its parking bays as well as the 
 * terminal cannot be used. There exist two types of stations, a 
 * <i>standard</i> type, and a <i>plus</i> type. <b>This class represents 
 * the <i>standard</i> type.</b> When a user who holds a Velib 
 * card drops a bicycle at a <i>plus</i> station, they earns 5 minutes credits 
 * in their time balance. Each station has a unique numerical ID and so 
 * each parking slot (within a station) has a unique numerical ID.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */

public class Station implements VisitableItems{
	
	/**
	 * An ArrayList to store the parking slots built in the station.
	 */
	protected ArrayList<ParkingSlot> parkingSlots;
	
	/**
	 * An Integer to indicate the number of parking slots built in the station.
	 */
	protected int numberOfSlots;
	
	/**
	 * A double  which is unique, to identify a Station.
	 */
	protected double stationID;
	
	/**
	 * 
	 */
	protected static double stationCounter;
	protected GPScoordinates location; 
	protected boolean isOnline;
	protected int parkingCounter;
	
	/**
	 * A read-only double to know the total number of rented bikes in this station 
	 */
	protected double numberOfRent;
	
	/**
	 * A read-only double to know the total number of returned bikes in this station 
	 */
	protected double numberOfReturn; 
	
	/**
	 * A long storing the average time of occupation after it's last computation by the averageTimeOfOccupation (it will be used to sort the least occupied stations)
	 */
	protected long averageTimeOfOccupation;
	
	/**
	 * This method is a constructor of the Station class. 
	 * 
	 * @param numberofslots the number of parkingslots to create in the station, as an int.
	 * @param location	the location of the station, as GPScoordinates.
	 */
	public Station(int numberofslots, GPScoordinates location) {
		this.stationID = Station.stationCounter;
		Station.stationCounter++;
		this.numberOfSlots = numberofslots;
		this.location = location;
		this.parkingSlots = new ArrayList<ParkingSlot>();
		for (int i = 0; i < numberofslots; i++) {
			this.parkingSlots.add(new ParkingSlot(this));			
		}
		this.isOnline = true;
	}
	
	/**
	 * This method is a constructor of the Station class. 
	 * 
	 * @param numberofslots	the number of parkingslots to create in the station, as an int.
	 * @param location		the location of the station, as GPScoordinates.
	 * @param numberOfBikes	the number of bikes to put on the slots of the station, as an int
	 * @param mechanicalBikeProportion	the proportion of mechanical bikes to create, as a float between 0 and 1
	 */
	public Station(int numberofslots, GPScoordinates location, int numberOfBikes, float mechanicalBikeProportion) throws MoreBikesThanSlotsException, InvalidProportionsException{
		if(numberOfBikes>numberofslots || numberOfBikes<0) {throw new MoreBikesThanSlotsException();}
		else if(mechanicalBikeProportion>1 || mechanicalBikeProportion<0) {throw new InvalidProportionsException();}
		else {
			this.stationID = Station.stationCounter;
			Station.stationCounter++;
			this.numberOfSlots = numberofslots;
			this.location = location;
			this.parkingSlots = new ArrayList<ParkingSlot>();
			for (int i = 0; i < numberofslots; i++) {
				this.parkingSlots.add(new ParkingSlot(this));
				if(i < numberOfBikes*mechanicalBikeProportion) {
					this.parkingSlots.get(i).setBike( BikeFactory.create(BikesType.Mechanical) );
				}
				else if (i < numberOfBikes) {
					this.parkingSlots.get(i).setBike( BikeFactory.create(BikesType.Electrical) );	
				}
			}
			this.isOnline = true;
		}
	}
	
	//Autres M�thodes
	/**
	 * Increments the parking slot counter. This method is called upon
	 * when a parking slot is added to the station.
	 * 		  
	 * @see ParkingSlots
	 */
	public void countUp() {
		this.parkingCounter++;
	}
	
	/**
	 * This method's goal is to add a newly created parking slot to the
	 * list of parking slots inside the station. The parking slots 
	 * within a station will be easier to manipulate if there is an 
	 * <code>ArrayList</code> of them.
	 * 
	 * @see ParkingSlots
	 * @param ps	the parking slot to add to the list
	 */
	public void addParkingSlot(ParkingSlot ps) {
		this.parkingSlots.add(ps);
	}
	
	/**
	 * This method tells if there is a free parking slot in the station.
	 * It checks all parking slots, and returns <code>true</code> if one of them is 
	 * free, <code>false</code> if there is no spot left to drop a bike. 
	 * 
	 * @return <code>true</code> if there is a free spot, <code>false</code> if not
	 */
	public boolean isFreeSlot() {
		int l = this.parkingSlots.size();
		int i = 0;
		boolean isFree = false;
		while(i<l && !isFree) {
			isFree = parkingSlots.get(i).isFree();
			i++;
		}
		return isFree;
	}
	
	/**
	 * This method must be used after checking that there is indeed a free
	 * parking slot in the station. It then returns the usable parking slot.
	 * 
	 * @return	a free parking slot in the station
	 */
	public ParkingSlot getFreeSlot() throws NoFreeSlotException {
		if(!this.isFreeSlot()) {
			throw new NoFreeSlotException();
		}
		else {
			int l = this.parkingSlots.size();
			int i = 0;
			boolean isFree = false;
			while(i<l && !isFree) {
				isFree = parkingSlots.get(i).isFree();
				i++;
			}	
			return this.parkingSlots.get(i);
		}
	}
	
	/**
	 * This method tells if there is an available bicycle in the station.
	 * It checks all parking slots, and returns <code>true</code> if one of them has 
	 * an available bicycle, <code>false</code> if there is none. 
	 * 
	 * @return <code>true</code> if there is an available bicycle, <code>false</code> if not
	 */
	public boolean isAvailableBicycle() {
		int l = this.parkingSlots.size();
		int i = 0;
		boolean isBike = false;
		while(i<l && !isBike) {
			isBike = parkingSlots.get(i).isBike();
			i++;
		}
		return isBike;
	}
	
	/**
	 * This method must be used after checking that there is indeed an available
	 * bicycle in the station. It then returns the parking slot on which the
	 * available bicycle is stationed.
	 * 
	 * @return	a parking slot with an available bike in the station
	 */
	public ParkingSlot getAvailableBicycle() throws NoAvailableBikeException {
		if(!this.isAvailableBicycle()) {
			throw new NoAvailableBikeException();
		}
		else {
			int l = this.parkingSlots.size();
			int i = 0;
			boolean isBike = false;
			while(i<l && !isBike) {
				isBike = parkingSlots.get(i).isBike();
				i++;
			}
			return parkingSlots.get(i);
		}
	}
	
	/**
	 * A method to compute the average time of occupation and set its value in averageTimeOfOccupation
	 */
	public void computingTheAverageTimeOccupation() {
		long averageOccupationTime = 0;
		for (ParkingSlot ps : this.getParkingSlots()) {
			averageOccupationTime+=ps.getTimeOfOccupation();
		}
		this.averageTimeOfOccupation=averageOccupationTime/this.getParkingSlots().size();
	}
	
	
	//Mise en place des getters
	/**
	 * Getter for the counter of parking slots. This counter is used to 
	 * guarantee that every parking slot as a unique numerical ID 
	 * within a station.
	 * 
	 * @return	The value of the counter, an <code>int</code>.
	 */
	public double getParkingCounter() {
		return this.parkingCounter;
	}
	
	/**
	 * Getter for the number of slots that exist in the station.
	 * 
	 * @return	the number of slots in the station as an int.
	 */
	public int getNumberOfSlots() {
		return this.numberOfSlots;
	}
	
	/**
	 * Getter for the coordinates of the station.
	 * 
	 * @return	The coordinates of the station.
	 */
	public GPScoordinates getLocation() {
		return this.location;
	}
	
	/**
	 * Getter for the isOnline boolean. User to know if the station is 
	 * online or not. 
	 * 
	 * @return	the isOnline boolean is <code>true</code> if the station 
	 * is online, <code>false</code> if not.
	 */
	public boolean isOnline() {
		return this.isOnline;
	}
	
	/**
	 * Getter method for the unique ID of the station.
	 * 
	 * @return	the ID of the station.
	 */
	public double getStationID() {
		return this.stationID;
	}
	
	/**
	 * Getter method for the parkingSlots array.
	 * 
	 * @return	an arraylist, composed of the parkingslots in the station.
	 */
	public ArrayList<ParkingSlot> getParkingSlots(){
		return this.parkingSlots;
	}
	
	/**
	 * A getter to retrieve the number of of rented bikes
	 * @return the number of rented bikes as a double
	 */
	public double getNumberOfRent() {
		return numberOfRent;
	}

	/**
	 * A getter to retrieve the number of of returned bikes
	 * @return the number of returned bikes as a double
	 */
	public double getNumberOfReturn() {
		return numberOfReturn;
	}
	
	/**
	 * A getter to retrieve the average time of occupation of the station 
	 * @return the average time of occupation of the station as a long 
	 */
	public long getAverageTimeOfOccupation() {
		return this.averageTimeOfOccupation;
	}

	//Mise en place des setters
	/**
	 * This method allows to set the location of the station.
	 * 
	 * @see Coordinates
	 * @param location the location of the station, as Coordinates.
	 */
	public void setLocation(GPScoordinates location) {
		this.location = location;
	}
	
	/**
	 * This method allows to define if a station is online or
	 * offline. If it is online, the state should be set to 
	 * <code>true</code>. Otherwise, if the station is offline
	 * for a reason or another, the state should change to 
	 * <code>false</code>.
	 * 
	 * @param isOn	<code>true</code> if the station should be 
	 * set to online, <code>false</code> if it should be set to 
	 * offline.
	 */
	public void setState(boolean isOn) {
		this.isOnline = isOn;
	}	
	
	/**
	 * A setter to change the number of rented bikes in this stations
	 * @param numberOfRent A double representing the new number of rented bikes
	 */
	public void setNumberOfRent(double numberOfRent) {
		this.numberOfRent = numberOfRent;
	}

	/**
	 * A setter to change the number of returned bikes in this stations
	 * @param numberOfRent A double representing the new number of returned bikes
	 */
	public void setNumberOfReturn(double numberOfReturn) {
		this.numberOfReturn = numberOfReturn;
	}

	@Override
	public String toString() {
		String str = ("This is station number " + this.stationID + ", containing " + this.numberOfSlots +" parking slots.");
		str += "\n";
		str += ("This station is located at : "+this.location.toString() +".");
		return str;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Station) {
			Station that = (Station) obj;
			return (this.stationID==that.getStationID() && this.location==that.getLocation() && this.parkingSlots==that.getParkingSlots());
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (int) (41*(41+this.stationID));
	}

	@Override
	public String accept(StatisticCompiler v) {
		return v.visit(this);
	}

	
}
