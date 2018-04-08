package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Station;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.EmptySlotException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoRideException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.OccupiedSlotException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Bikes.Bike;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Bikes.BikesType;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.User.User;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Stats.StatisticCompiler;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Stats.VisitableItems;

/**
 * ParkingSlots is the class for all parking slots in the
 * myVelib system. Every parking slot has a unique ID within
 * the station it is built in. Its state defines if the 
 * parking slot is available or not. The state variable
 * can take three values :
 * <ul>
 * <li> "free" 
 * <li> "taken"
 * <li> "outOfOrder"
 * </ul> 
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 * 
 */

public class ParkingSlot implements VisitableItems{
	/**
	 * A double to store the unique ID of the parking slot within a Station.
	 */
	protected double parkingSlotID;
	
	/**
	 * A ParkingSlotState to store the state the parking slot is in.
	 */
	protected ParkingSlotState state;
	
	/**
	 * A Station object to indicate in which Station the parking slot is located.
	 */
	protected Station station;
	
	/**
	 * A Bike object to store the bike that is available at the parking slot.
	 */
	protected Bike bike;
	
	/**
	 * A hidden LocalDateTime object storing the last change of state in order to compute the total time of occupation of the slot 
	 */
	protected LocalDateTime lastChange;
	
	/**
	 * A long storing the total time of occupation of the Parking Slot 
	 */
	protected double timeOfOccupation; 
	
	/**
	 * A constructor for creating a ParkingSlot instance with a unique ID within a station
	 * @param station	the station in which the parking slot is built.
	 */
	public ParkingSlot(Station station) {
		this.parkingSlotID = station.getParkingCounter();
		station.countUp();
		this.station = station;
		this.state = ParkingSlotState.free;
		this.lastChange = LocalDateTime.now();
		this.timeOfOccupation =0;
	}
	
	/**
	 * A constructor for creating a ParkingSlot instance with a unique ID within a station
	 * @param station	the station in which the parking slot is built.
	 * @param bike		the bike stationed at the parking slot
	 */
	public ParkingSlot(Station station, Bike bike) {
		this.parkingSlotID = station.getParkingCounter();
		station.countUp();
		this.station = station;
		this.state = ParkingSlotState.taken;
		this.bike = bike;
		this.lastChange = LocalDateTime.now();
		this.timeOfOccupation =0;
	}
	
	
	//Autres M�thodes
	
	/**
	 * This method tells if a parking slot is available or not. 
	 * It returns <code>false</code> if it is "taken" or "out of order", <code>true</code> if it is "free".
	 * 
	 * @return a boolean, tells if the spot is free (<code>true</code>) or not (<code>false</code>)
	 */
	public boolean isFree() {
		if (this.state == ParkingSlotState.free) { return true; }
		else { return false; }
	}
	
	/**
	 * This method tells if there is a bike on the parking slot or not.
	 * It returns <code>false</code> if there is no bike, or <code>true</code> if there is one.
	 * 
	 * @return a boolean, tells if there's a bike (<code>true</code>) or not (<code>false</code>)
	 */
	public boolean isBike() {
		if (this.state == ParkingSlotState.taken) { return true; }
		else { return false; }
	}
	
	/**
	 * This method tells if there is a bike of type <code>type</code> on the parking slot or not.
	 * It returns <code>false</code> if there is no bike, or <code>true</code> if there is one.
	 * 
	 * @param	type	The type of bike to check if there is
	 * @return a boolean, tells if there's a bike (<code>true</code>) or not (<code>false</code>)
	 */
	public boolean isBike(BikesType type) {
		if (this.state == ParkingSlotState.taken && this.bike.getType()==type) { return true; }
		else { return false; }
	}
	
	/**
	 * This method allows a User to drop a bike in a parking slot.
	 * It is called upon by the User class.
	 * 
	 * @param	u	User who drops bike
	 * @see		User 
	  */
	public void acceptBike(User u) throws OccupiedSlotException, NoRideException {
		if (!this.isFree()) {throw new OccupiedSlotException();}
		if (!u.isOnARide()) {throw new NoRideException();}
		else {
			this.bike = u.getBike();
			u.setBike(null);
			this.setState(ParkingSlotState.taken);
			
			if(this.station.getFreeSlotNumber() == 0) {
				try {
					this.station.notifyAllArrivalObservers();
				} catch (NoRideException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	
	/**
	 * This method allows a User to take a bike in a parking slot.
	 * It is called upon by the User class.
	 * 
	 * @param	u	User who takes bike
	 * @see		User 
	  */
	public void giveBike(User u) throws EmptySlotException, NoRideException {
		if (!this.isBike()) {throw new EmptySlotException();}
		if (!u.isOnARide()) {throw new NoRideException();}
		else {
			u.setBike(this.bike);
			
			this.bike = null;
			this.setState(ParkingSlotState.free);
			
			if(this.station.getAvailableBikeNumber() == 0) {
				try {
					this.station.notifyAllDepartureObservers();
				} catch (NoRideException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void updateOccupationTime() {
		if(this.state == ParkingSlotState.taken) {
			Duration duration = Duration.between(this.lastChange, LocalDateTime.now());
			this.timeOfOccupation += duration.toMillis();
			this.lastChange = LocalDateTime.now();
		}
	}
	//Mise en place des getters
	/**
	 * Getter method for the state of the parkingSlot
	 * 
	 * @return 	the state of the parking slot from ParkingSlotState enum
	 * @see ParkingSlotState
	 */
	public ParkingSlotState getState() {
		return this.state;
	}
	
	/**
	 * Getter method for the parking slot's unique ID
	 * 
	 * @return The parking slot's ID.
	 */
	public double getParkingSlotID() {
		return this.parkingSlotID;
	}
	
	/**
	 * Getter method for the station the parking slot belongs to.
	 * 
	 * @return The parking slot's station.
	 */
	public Station getStation() {
		return this.station;
	}
	
	/**
	 * Getter method for the bike that is on the parking slot.
	 * @return The bike that is on the slot.
	 */
	public Bike getBike() throws EmptySlotException {
		if(this.bike == null) {throw new EmptySlotException();}
		else { return this.bike;}
	}
	
	/**
	 * Getter method for the time of occupation of the parkingSlot
	 * @return the time of occupation of the parkingSlot as a long 
	 */
	public double getTimeOfOccupation() {
		return this.timeOfOccupation;
	}
	
	
	
	//Mise en place des setters
	/**
	 * Setter method for the state of the parkingSlot
	 * 
	 * @param state	the new state of the parking slot from ParkingSlotState enum
	 * @see ParkingSlotState
	 */
	public void setState(ParkingSlotState state) {
		if (this.state.equals(ParkingSlotState.taken)) {
			this.updateOccupationTime();
			this.station.computingTheAverageTimeOccupation();
		}
		else if (this.state.equals(ParkingSlotState.free)) {
			this.lastChange = LocalDateTime.now();
		}
		this.state = state;
	}
	
	/**
	 * Setter method for the bike that is on the parking slot.
	 * 
	 * @param bike The bike that is to be set on the slot.
	 */
	public void setBike(Bike bike) {
		this.bike = bike;
	}
	
	
	@Override
	public String toString() {
		String str = ("This is parking slot number " + this.parkingSlotID + ", situated within station number " + this.station.getStationID() +".");
		str += "\n";
		str += ("This parking slot is located at : "+this.station.getLocation().toString()+".");
		return str; 
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ParkingSlot) {
			ParkingSlot that = (ParkingSlot) obj;
			return (this.parkingSlotID==that.getParkingSlotID() && this.station==that.getStation());
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (int) (41*(41+this.station.hashCode())+this.parkingSlotID);
	}

	@Override
	public String accept(StatisticCompiler v) {
		return v.visit(this);
	}

	
}
