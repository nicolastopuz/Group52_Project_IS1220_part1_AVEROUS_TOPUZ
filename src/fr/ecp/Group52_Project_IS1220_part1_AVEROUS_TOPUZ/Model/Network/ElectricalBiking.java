package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network;

/**
 * The ElectricalBiking class implements the MovingBehaviors interface in the case where the user is using a electrical bike.
 * 
 * @see MovingBehavior
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */

public class ElectricalBiking implements MovingBehavior {

	@Override
	public double getSpeed() {
		return (double) 20;
	}

}
