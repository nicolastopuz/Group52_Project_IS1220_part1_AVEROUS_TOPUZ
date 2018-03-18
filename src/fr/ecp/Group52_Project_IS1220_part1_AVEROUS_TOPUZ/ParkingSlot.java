package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;


/**
 * ParkingSlots is the class for all parking slots in the
 * myVelib system. Every parking slot has a unique ID within
 * the station it is built in. Its state defines if the 
 * parking slot is available or not. The state variable
 * can take three values :
 * <ul>
 * <li> "free" 
 * <li> "taken"
 * <li> "out of order"
 * </ul> 
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 * 
 */

public class ParkingSlot {
	private double parkingSlotID;
	private ParkingSlotState state; 
	private Station station;
	private Bike bike;
	
	public ParkingSlot(Station s) {
		this.parkingSlotID = s.getParkingCounter();
		s.countUp();
		this.station = s;
		this.state = ParkingSlotState.free;
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
	 * 
	 * @return The bike that is on the slot.
	 */
	public Bike getBike() {
		return this.bike;
	}
	
	
	//Mise en place des setters
	/**
	 * Setter method for the state of the parkingSlot
	 * 
	 * @param 	the new state of the parking slot from ParkingSlotState enum
	 * @see ParkingSlotState
	 */
	public void setState(ParkingSlotState state) {
		this.state = state;
	}
	
	/**
	 * Setter method for the bike that is on the parking slot.
	 * 
	 * @param The bike that is to be set on the slot.
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
			return (this.parkingSlotID==that.getParkingSlotID() && this.station==that.getStation() && this.bike==that.getBike());
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (int) (41*(41+this.station.hashCode())+this.parkingSlotID);
	}

	
}
