package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

public abstract class Bike {
	static double bikeCounter;
	protected double numericalId; 
	protected BikesType type;

	public Bike() {
		super();
		Bike.bikeCounter+=1;
		this.numericalId=Bike.bikeCounter;
	}

}
