package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.InvalidProportionsException;

/**
 * A class to test the functionning of the network for a unique user.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 *
 */

public class UseCaseUniqueUser {

	public static void main(String[] args) {
		Network n;
		try {
			n = new Network("network",10,10,0.7);
		} catch (InvalidProportionsException e) {
			e.printStackTrace();
		} finally {
			n = new Network("network");
		}
		n.createUser("Pierre");
		System.out.println(n.getUserList().get(0));
		
	}

}
