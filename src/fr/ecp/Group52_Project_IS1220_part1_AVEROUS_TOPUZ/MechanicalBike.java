package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

public class MechanicalBike extends Bike implements VisitableBikes {
	static double mechanicalBikeCounter;

	public MechanicalBike() {
		super();
		this.type=BikesType.Mechanical;
		MechanicalBike.mechanicalBikeCounter+=1;
	}

}
