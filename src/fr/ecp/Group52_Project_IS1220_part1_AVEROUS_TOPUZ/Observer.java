package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoRideException;

public interface Observer {
	
	public void update() throws NoRideException;

}
