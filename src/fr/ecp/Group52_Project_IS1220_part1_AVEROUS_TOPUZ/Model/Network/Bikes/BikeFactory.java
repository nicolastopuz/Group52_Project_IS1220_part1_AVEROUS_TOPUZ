package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Bikes;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Network;

/**
 * The BikeFactory class implements a simple Factory pattern in order to allow 
 * the creation of bikes in an easy and OPEN-CLOSE way.
 * 
 * @see Bike
 * @see BikeTypes
 * @see Network
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */

public class BikeFactory {

	public static Bike create(BikesType type) {
		if (type==BikesType.Electrical) {
			return new ElectricalBike();
		}
		else if (type==BikesType.Mechanical) {
			return new MechanicalBike();
		}
		else {
			return null;
		}
	} 
}

