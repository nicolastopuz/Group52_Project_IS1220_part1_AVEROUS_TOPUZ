package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoRideException;

public interface Observable {
	
	public void addDepartureObserver(Observer obs);
	public void addArrivalObserver(Observer obs);
	public void removeDepartureObserver(Observer obs);
	public void removeArrivalObserver(Observer obs);
	public void notifyAllDepartureObservers() throws NoRideException;
	public void notifyAllArrivalObservers() throws NoRideException;
	public void notifyObserver(Observer obs) throws NoRideException;

}
