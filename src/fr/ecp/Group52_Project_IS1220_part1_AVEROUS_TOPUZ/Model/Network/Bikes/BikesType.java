package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Bikes;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.User.MovingBehavior.ElectricalBiking;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.User.MovingBehavior.MecanicalBiking;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.User.MovingBehavior.MovingBehavior;

/**
 * The BikesType enumeration  defines all the possible types of bike
 * 
 * @see Bike
 * @see MechanicalBike
 * @see ElectricalBike
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */

public enum BikesType {
	Mechanical(new MecanicalBiking()),
	Electrical(new ElectricalBiking());
	
	private MovingBehavior behavior;
	
	BikesType(MovingBehavior behavior) {
		this.behavior = behavior;
	}
	
	public MovingBehavior getBehavior() {
		return behavior;
	}
}
