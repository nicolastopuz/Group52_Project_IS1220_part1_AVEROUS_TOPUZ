package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

/**
 * The StatisticCompiler Object implements the StatisticVisitor interface.
 * It implements a visit method for each visitable items in order to compute statistics for each type of item. 
 * 
 * @see StatisticVisitor
 * @see Network
 * @see Station
 * @see User
 * @see ParkingSlot
 * @see NetworkStatisticsSortingMethods
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */

public class StatisticCompiler implements StatisticVisitor {

	/**
	 * A NetworkStatisticsSortingMethods object defining which method will be used to sort the network stations
	 */
	protected NetworkStatisticsSortingMethods sortingMethod;
	
	/**
	 * A constructor fixing which method will be used to sort the network's stations
	 * @param method A NetworkStatisticsSortingMethods object to indictate which method will be used to sort the stations
	 */
	public StatisticCompiler(NetworkStatisticsSortingMethods method) {
		this.sortingMethod=method;
	}

	/**
	 * A getter to know which sorting method is used
	 * @return A NetworkStatisticsSortingMethods object indicating what sorting method is used
	 */
	public NetworkStatisticsSortingMethods getSortingMethod() {
		return sortingMethod;
	}
	
	/**
	 * A setter to choose the sorting method for the sorting of the network's station
	 * @param sortingMethod A NetworkStatisticsSortingMethods object indicating what sorting method is used
	 */
	public void setSortingMethod(NetworkStatisticsSortingMethods sortingMethod) {
		this.sortingMethod = sortingMethod;
	}



	@Override
	public String visit(Network n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(Station s) {
		// TODO Auto-generated method stub
		
		///Allow to retrieve the total number of rent operations
		/// Allow to retrieve the total number of return operations
		/// Allow to retrieve Average of occupation of the station
		return null;
	}

	@Override
	public String visit(User u) {
		// TODO Auto-generated method stub
		
		///Allow to retrieve the number of rides
		///Allow to retrieve total time spent on a bike
		///Allow to retrieve total amount of all rides performed
		///Allow to retrieve of all the time-creditearned
		return null;
	}

	@Override
	public String visit(ParkingSlot ps) {
		// TODO Auto-generated method stub
		
		///Allow to retrieve the occupation time of the parking slot
		return null;
	}

}
