package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network;

/**
 * The BikesType enumeration  defines all the possible types of bike
 * 
 * @see Bike
 * @see MechanicalBike
 * @see EletricalBike
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
