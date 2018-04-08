package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.User.MovingBehavior;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.User.User;

/**
 * The Moving Behavior interface defines the getspeed method allowing 
 * to get the speed of the user according to his mean of transport
 * 
 * @see User
 * @see Walking
 * @see ElectricalBiking
 * @see MechanicalBiking
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */

public interface MovingBehavior {
	
	/**
	 * A method allowing us to know the speed of the user according to his mean of transport
	 * @return the speed of the user
	 */
	public double getSpeed();
}
