package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Stats;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Itinerary.Ride;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Network;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Station.ParkingSlot;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Station.Station;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.User.User;

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


	/**
	 * This method returns as a string the relevant statistics for a network which is to say : <p>
	 * - the number of stations <p>
	 * - the number of bikes <p>
	 * - the number of users <p>
	 * - a list of the stations sorted according to the most used station or the least occupied station<p>
	 */
	@Override
	public String visit(Network n) {
		if (this.sortingMethod==NetworkStatisticsSortingMethods.MostUsed) {
			ArrayList<ArrayList<Double>> stations = new ArrayList<ArrayList<Double>>();
			ArrayList<ArrayList<Double>> sortedStations = new ArrayList<ArrayList<Double>>();
			for (Station s : n.getStationList()) {
				ArrayList<Double> paire= new ArrayList<Double>();
				paire.add(s.getStationID());
				paire.add(s.getNumberOfRent()+s.getNumberOfReturn());
				stations.add(paire);
			}
			while (!stations.isEmpty()) {
				int indice_max=-1;
				double max=-1;
				for (int i=0; i<stations.size();i++) {
					if (stations.get(i).get(1)>max) {
						indice_max=i;
						max = stations.get(i).get(1);
					}
				}
				sortedStations.add(stations.get(indice_max));
				stations.remove(indice_max);
			}
			String toReturn = "This Network has "+n.getStationList().size()+" stations, "+n.getBikeList().size()+" bikes and "+n.getUserList().size()+" users.\nThe list of it's stations sorted from the least occupied to the most occupied is the following :\n";
			for (int i = 0; i < sortedStations.size(); i++) {
				toReturn += "\tN�"+i+" : Station number "+sortedStations.get(i).get(0)+", used "+sortedStations.get(i).get(1)+" times.\n";
			}
			return (toReturn);
		}
		else if (this.sortingMethod==NetworkStatisticsSortingMethods.LeastOccupied) {
			ArrayList<ArrayList<Double>> stations = new ArrayList<ArrayList<Double>>();
			ArrayList<ArrayList<Double>>  sortedStations = new ArrayList<ArrayList<Double>>();
			for (Station s : n.getStationList()) {
				ArrayList<Double> paire= new ArrayList<Double>();
				paire.add(s.getStationID());
				paire.add((double) s.getAverageTimeOfOccupation());
				stations.add(paire);
			}
			while (!stations.isEmpty()) {
				int indice_min=-1;
				double min=200000000;
				for (int i=0; i<stations.size();i++) {
					if (stations.get(i).get(1)<min) {
						indice_min=i;
						min = stations.get(i).get(1);
					}
				}
				sortedStations.add(stations.get(indice_min));
				stations.remove(indice_min);
			}
			String toReturn = "This Network has "+n.getStationList().size()+" stations, "+n.getBikeList().size()+" bikes and "+n.getUserList().size()+" users.\nThe list of it's stations sorted from the least occupied to the most occupied is the following :\n";
			for (int i = 0; i < sortedStations.size(); i++) {
				toReturn += "\tN�"+i+" : Station number "+sortedStations.get(i).get(0)+", used "+sortedStations.get(i).get(1)+" times.\n";
			}
			return (toReturn);
		}
		return null;
	}

	/**
	 * This method returns as a string the relevant statistics for a station which is to say : <p>
	 * - the total number of rent operations <p>
	 * - the total number of return operation <p>
	 * - the average occupation of the station <p>
	 */
	@Override
	public String visit(Station s) {
		return ("This station, ID number "+s.getStationID()+" has witnessed "+s.getNumberOfRent()+" rent operations as well as "+s.getNumberOfReturn()+" return operations.\nIt's average occupation rate is "+Math.round(s.getAverageTimeOfOccupation()/36000)/100+" hours, "+ Math.round(s.getAverageTimeOfOccupation()/600)/100 +" minutes and "+ Math.round(s.getAverageTimeOfOccupation()/10)/100 +" seconds ("+s.getAverageTimeOfOccupation()+ "milliseconds).\n");
	}

	/**
	 * This method returns as a string the relevant statistics for a user which is to say : <p>
	 * - the number of rides <p>
	 * - the total time spent on a bike <p>
	 * - the total amount of all rides performed <p>
	 * - all the time-credit earned <p>
	 */
	@Override
	public String visit(User u) {
		double numberOfRides = u.getRides().size();
		
		double timeSpentOnABike = 0;
		double totalPrice = 0;
		double totalCreditTimeEarned = 0;
		
		for (Ride r : u.getRides()) {
			timeSpentOnABike+=r.getTimeOnBike();
			totalPrice+=r.getPriceOfRide();
			totalCreditTimeEarned+=r.getCreditEarned();
		}
		
		
		totalPrice = totalPrice*100;
		totalPrice = Math.round(totalPrice);
		totalPrice = totalPrice/100; //Simple trick to have only 2 digits after comma.
		
		int minOnBike= (int)timeSpentOnABike;
		double secOnBike= Math.round((timeSpentOnABike-minOnBike)*60);
		
		return ("The User "+ u.getName()+", ID number "+u.getNumericalId()+", has done "+numberOfRides+" rides. He has spent "+minOnBike+" minutes "+secOnBike+" secondes on a bike, paid a total amount of charges of "+totalPrice+" euros� and earned a total amount of time credit of "+totalCreditTimeEarned+" minutes.\n");
	}

	/**
	 * This method returns as a string the relevant statistics for a user which is to say : <p>
	 * - the occupation time of the parking slot 
	 */
	@Override
	public String visit(ParkingSlot ps) {		
		return ("The Parking Slot number "+ps.getParkingSlotID()+" of station number "+ps.getStation().getStationID()+" has a total time  of occupation of "+ps.getTimeOfOccupation()+" hours.\n");
	}

}
