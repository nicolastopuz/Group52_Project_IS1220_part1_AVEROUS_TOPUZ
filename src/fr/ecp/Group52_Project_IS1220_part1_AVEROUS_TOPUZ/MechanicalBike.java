package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

/**
 * The MechanicalBike class represents every mechanical bike in the myVelib
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

public class MechanicalBike extends Bike implements VisitableBikes {
	
	/**
	 * A static double counting the total number of mechanical bikes in the system
	 */
	static double mechanicalBikeCounter;
	
	/**
	 * A constructor creating a mechanical bike
	 */
	public MechanicalBike() {
		super();
		this.type=BikesType.Mechanical;
		MechanicalBike.mechanicalBikeCounter+=1;
	}

	/**
	 * A getter for the mechanicalBikeCounter
	 * @return the number of mechanical bike in the myVelib system
	 */
	public static double getMechanicalBikeCounter() {
		return mechanicalBikeCounter;
	}

}
