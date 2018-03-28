package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

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
		Network n = new Network(10,10,0.7f);
		n.createUser("Pierre");
		
		
	}

}
