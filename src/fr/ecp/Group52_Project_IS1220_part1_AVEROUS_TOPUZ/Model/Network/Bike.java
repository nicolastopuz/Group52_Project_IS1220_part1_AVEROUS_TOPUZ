package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network;

/**
 * The Bike abstract class basically is the core of every bike in the myVelib
 * system. 
 * he is characterized by :
 * <ul>
 * <li> a numerical id
 * <li> a type
 * </ul>
 * <p>
 * 
 * @see BikesType
 * @see MechanicalBike
 * @see EletricalBike
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */

public abstract class Bike {
	
	/**
	 * A read-only static double counting the total number of bikes
	 */
	static double bikeCounter;
	
	/**
	 * A read-only double defining the unique numericalId of the bike
	 */
	protected double numericalId; 
	
	/**
	 * A read-only BikesType object defining which kind of bike this is 
	 */
	protected BikesType type;
	
	/**
	 * The MovingBehavior associated to the type of bike
	 */
	protected MovingBehavior behavior;
	
	/**
	 * The Network the Bike belongs to
	 */
	protected Network network;

	/**
	 * The general constructor of the Bike abstract class
	 */
	public Bike() {
		super();
		Bike.bikeCounter+=1;
		this.numericalId=Bike.bikeCounter;
	}

	/**
	 * A getter for the BikeCounter
	 * @return the number of bike in the myVelib system as double
	 */
	public static double getBikeCounter() {
		return bikeCounter;
	}

	/**
	 * A getter for the NumericalId
	 * @return the unique numerical id of the bike as a double
	 */
	public double getNumericalId() {
		return numericalId;
	}
	
	/**
	 * A getter for the bike's type
	 * @return the bike's type as a BikesType object
	 */
	public BikesType getType() {
		return type;
	}
	
	/**
	 * A getter for the MovingBehavior associated with this type of bike
	 * @return the MovingBehavior associated with this type of bike
	 */
	public MovingBehavior getBehavior() {
		return this.behavior;
	}
	
	/**
	 * A getter got the Network the Bike belongs to
	 * @return the Network the Bike belongs to
	 */
	public Network getNetwork() {
		return network;
	}
	
	/**
	 * A setter for the Network the Bike belongs to
	 * @param network	the Network the Bike belongs to
	 */
	public void setNetwork(Network network) {
		this.network = network;
	}
	
	@Override
	public abstract String toString();
	
	@Override
	public abstract boolean equals(Object ob);
	
	@Override
	public abstract int hashCode();
}
