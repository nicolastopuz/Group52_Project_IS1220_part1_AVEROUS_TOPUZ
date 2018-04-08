package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Stats;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Network;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Station.ParkingSlot;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Station.Station;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.User.User;

/**
 * The StatisticVisitor interface create a visit method for each visitable object.
 * It is implemented by the statistic compiler. 
 * 
 * @see StatisticCompiler
 * @see Network
 * @see Station
 * @see User
 * @see ParkingSlot
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */

public interface StatisticVisitor {
	
	/**
	 * The visit method for a network
	 * @param n A Network Object
	 * @return A String containing all the relevant statistics
	 */
	public String visit(Network n);
	
	/**
	 * The visit method for a station
	 * @param s A Station Object
	 * @return A String containing all the relevant statistics
	 */
	public String visit(Station s);
	
	/**
	 * The visit method for a user
	 * @param u A User Object
	 * @return A String containing all the relevant statistics
	 */
	public String visit(User u);
	
	/**
	 * The visit method for a parking slot
	 * @param ps A ParkingSlot Object
	 * @return A String containing all the relevant statistics
	 */
	public String visit(ParkingSlot ps);

}
