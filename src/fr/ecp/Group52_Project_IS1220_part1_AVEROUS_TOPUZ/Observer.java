package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoRideException;

/**
 * An interface to be implemented by classes that observe other ones.
 * In this case, the interface has been built for users to observe
 * the state of stations on the network during a ride.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public interface Observer {
	
	
	public void update() throws NoRideException;

}
