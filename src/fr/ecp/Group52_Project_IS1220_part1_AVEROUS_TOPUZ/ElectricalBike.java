package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

public class ElectricalBike extends Bike implements VisitableBikes {
	static double electricalBikeCounter;

	public ElectricalBike() {
		super();
		this.type=BikesType.Electrical;
		ElectricalBike.electricalBikeCounter+=1;
	}

}
