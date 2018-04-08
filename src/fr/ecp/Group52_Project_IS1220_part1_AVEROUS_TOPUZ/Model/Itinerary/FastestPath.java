package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Itinerary;

import java.util.ArrayList;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.ElectricalBiking;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.GPScoordinates;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.MecanicalBiking;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.MovingBehavior;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Station;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Walking;

/**
 * This class corresponds to a preference of the user within a ride to go from
 * point A to point B using not the shortest, but the fastest path available
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class FastestPath implements PathPreferenceVisitor {
	
	/**
	 * The different moving behaviors, used here to calculate the different paths.
	 */
	protected MovingBehavior walk, elec, meca;
	/**
	 * A simple array, which will contain the departure and arrival station for the given ride.
	 */
	protected Station[] departureAndArrival;
	/**
	 * The ride in which the path is to be calculated.
	 */
	protected Ride ride;
	/**
	 * The average duration of the ride with a mechanical bike.
	 */
	protected double mechanicalRideDuration;
	/**
	 * The average duration of the ride with an electrical bike.
	 */
	protected double electricalRideDuration;
	
	/**
	 * Constructor for an instance of the FastestPath strategy.
	 * @param ride	The ride in question, that should be calculated
	 */
	public FastestPath() {
		this.walk = new Walking();
		this.elec = new ElectricalBiking();
		this.meca = new MecanicalBiking();
	}
	
	//Autres m�thodes

	@Override
	public Station[] departureAndArrival() {
		Station[] departureAndArrival = new Station[2];
				
		ArrayList<Station> departureStations = ride.getDepartureStations();
		ArrayList<Station> arrivalStations = ride.getArrivalStations();
		
		double[] distanceToDeparture = ride.distanceToDeparture(departureStations);
		double[] distanceToArrival = ride.distanceToArrival(arrivalStations);
		double[][] distancesUsedStations = ride.distancesUsedStation(departureStations, arrivalStations);
				
		double[][] timeBetweenStationsMechanical = new double[departureStations.size()][arrivalStations.size()];
		double[][] timeBetweenStationsElectrical = new double[departureStations.size()][arrivalStations.size()];

		
		for (int i = 0; i < departureStations.size(); i++) {
			for (int j = 0; j < arrivalStations.size(); j++) {
				timeBetweenStationsMechanical[i][j] = distanceToDeparture[i]/walk.getSpeed()
						+ distancesUsedStations[i][j]/meca.getSpeed()
						+ distanceToArrival[j]/walk.getSpeed();
				timeBetweenStationsElectrical[i][j] = distanceToDeparture[i]/walk.getSpeed()
						+ distancesUsedStations[i][j]/elec.getSpeed()
						+ distanceToArrival[j]/walk.getSpeed();
				/*
				 * This calculates the cost of every trip possible with these departure and
				 * arrival stations. It is the sum of the duration from the starting point
				 * to a Departure station, the duration from the Departure station to an Arrival 
				 * station, and the duration from the Arrival station to the Arrival point.
				 */
			}
		}
		
		
		int departureIndex = 0, arrivalIndex = 0;
		double minTimeMeca = timeBetweenStationsMechanical[0][0];
		double minTimeElec = timeBetweenStationsElectrical[0][0];
		for (int i = 0; i < distanceToDeparture.length; i++) {
			for (int j = 0; j < distanceToArrival.length; j++) {
				if (minTimeMeca>=timeBetweenStationsMechanical[i][j] && !departureStations.get(i).equals(arrivalStations.get(j))) {
					minTimeMeca = timeBetweenStationsMechanical[i][j];
					minTimeElec = timeBetweenStationsElectrical[i][j];
					
					departureIndex = i;
					arrivalIndex = j;
					/*
					 * Having the cost of every possible trip in the tripCostMatrix, we now
					 * only need to find the cheapest trip, and through which stations this trip goes.
					 * We now have the minimal distance, which is the shortest path.
					 */
				}
			}
		}
		
		departureAndArrival[0] = departureStations.get(departureIndex);
		departureAndArrival[1] = arrivalStations.get(arrivalIndex);
		
		this.electricalRideDuration = minTimeElec;
		this.mechanicalRideDuration = minTimeMeca;
		
		return departureAndArrival;
	}
	
	@Override
	public Station getUpdateOnArrivalStation(GPScoordinates departure) {
		ArrayList<Station> arrivalStations = this.ride.getArrivalStations();
		
		double[] distanceNextStationToArrival = ride.distanceToArrival(arrivalStations);
		double[] distanceToNextStation = new double[arrivalStations.size()];
		double[] totalMecaDurations = new double[arrivalStations.size()];
		double[] totalElecDurations = new double[arrivalStations.size()];
		
		for (int i = 0; i < arrivalStations.size(); i++) {
			distanceToNextStation[i] = departure.distanceTo(arrivalStations.get(i).getLocation()) ;
			totalMecaDurations[i] = distanceToNextStation[i]/meca.getSpeed() + distanceNextStationToArrival[i]/walk.getSpeed();
			totalElecDurations[i] = distanceToNextStation[i]/elec.getSpeed() + distanceNextStationToArrival[i]/walk.getSpeed();
		}
		
		int arrivalIndex = 0;
		double minMecaTime = totalMecaDurations[0];
		double minElecTime = totalElecDurations[0];
		for (int i = 0; i < totalMecaDurations.length; i++) {
			if(minMecaTime > totalMecaDurations[i]) {
				arrivalIndex = i;
				minMecaTime = totalMecaDurations[i];
				minElecTime = totalElecDurations[i];
			}
		}
		
		this.mechanicalRideDuration = minMecaTime;
		this.electricalRideDuration = minElecTime;
				
		return arrivalStations.get(arrivalIndex);
	}
	
	//Getters
	
	/**
	 * Getter for minimal ride duration with a mechanical bike.
	 * @return	the minimal ride duration as a double.
	 */
	public double getMechanicalRideDuration() {
		return this.mechanicalRideDuration;
	}
	
	/**
	 * Getter for minimal ride duration with an electrical bike.
	 * @return	the minimal ride duration as a double.
	 */
	public double getElectricalRideDuration() {
		return this.electricalRideDuration;
	}
	/**
	 * Getter for Departure and Arrival stations of the optimized ride, 
	 * in a size 2 array. in position [0] is departure Station, position
	 * [1] is arrival Station.
	 * @return	the departure and arrival stations as a size 2 array.
	 */
	public Station[] getDepartureAndArrival() {
		return this.departureAndArrival;
	}
	
	/**
	 * Getter for the ride in which the path has to be calculated.
	 * @return	the Ride object in which the path is calculated.
	 */
	public Ride getRide() {
		return this.ride;
	}
	
	/**
	 * Getter for Departure station of the path.
	 * @return	The departure Station of the path.
	 */
	public Station getDeparture() {
		return this.departureAndArrival[0];
	}	
	
	/**
	 * Getter for Arrival station of the path.
	 * @return	The arrival Station of the path.
	 */
	public Station getArrival() {
		return this.departureAndArrival[1];
	}

	//Setters
	
	@Override
	public void setRide(Ride ride) {
		this.ride = ride;
		this.departureAndArrival = this.departureAndArrival();
	}
	
	/**
	 * Setter for the departureAndArrival array, containing departure
	 * and arrival Station objects of the ride.
	 * @param departureAndArrival	a Station array of size 2 containing 
	 * departure and arrival station.
	 */
	public void setDepartureAndArrival(Station[] departureAndArrival) {
		this.departureAndArrival = departureAndArrival;
	}
}
