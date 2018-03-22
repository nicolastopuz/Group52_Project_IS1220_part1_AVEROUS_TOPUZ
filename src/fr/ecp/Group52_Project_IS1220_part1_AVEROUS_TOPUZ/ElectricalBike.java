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
	
	@Override 
	public String toString() {
		return ("This bike is an electrical bike. Its unique numerical id is "+ this.numericalId);
	}
	
	@Override 
	public boolean equals(Object ob) {
		if (ob instanceof ElectricalBike) {
			ElectricalBike that  = (ElectricalBike) ob;
			return (this.numericalId==that.getNumericalId());
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int) (31*(31+this.numericalId)+7); 
	}
}
