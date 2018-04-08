package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Stats;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Network;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Station.ParkingSlot;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Station.Station;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.User.User;

/**
 * The VisitableItems interface create an accept method for our statistic compiler.
 * It is implemented by objects network, station, user and ParkingSlot 
 * 
 * @see Network
 * @see Station
 * @see User
 * @see ParkingSlot
 * @see StatisticCompiler
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public interface VisitableItems {
	
	/**
	 * The accept method for the statistic compiler
	 * @param v A StaticticCompiler object
	 * @return A String containing the relevant statistic.
	 */
	public String accept(StatisticCompiler v);

}
