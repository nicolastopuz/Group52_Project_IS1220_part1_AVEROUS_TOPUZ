package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

/**
 * The MechanicalBike class represents every electrical bike in the myVelib
 * system. 
 * As a bike, it is characterized by :
 * <ul>
 * <li> a numerical id
 * <li> a type
 * </ul>
 * <p>
 * 
 * @see Bike
 * @see BikesType
 * @see VisitablesBikes
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */

public class ElectricalBike extends Bike implements VisitableBikes {
	
	/**
	 * A static double counting the total number of electrical bikes in the system
	 */
	static double electricalBikeCounter;
	
	/**
	 * A constructor creating an electrical bike
	 */
	public ElectricalBike() {
		super();
		this.type=BikesType.Electrical;
		ElectricalBike.electricalBikeCounter+=1;
	}

	/**
	 * A getter for the electricalBikeCounter
	 * @return the number of electrical bike in the myVelib system
	 */
	public static double getElectricalBikeCounter() {
		return electricalBikeCounter;
	}

}
