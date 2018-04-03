package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

public enum AvailableCommands {
	setup("setup <networkName> \nsetup <networkName> <nstations> <nslots> <sidearea> <fillingPercentage>", "	 * Command to create a myVelib network named <networkName> and consisting \r\n" + 
			"	 * of <nstations> (default: 10) stations each of which has <nslots> (default :10) parking slots and such that stations \r\n" + 
			"	 * are initially populated with <fillingPercentage> (default: 70%) bikes randomly equally distrubuted\r\n" + 
			"	 * in each of the 10 stations. Stations are randomly placed in a square of side <sidearea> in km (default: 4km)"),
	
	addUser("addUser <userName> <networkName> \naddUser <userName> <cardType> <networkName>", "	 * Command to add a user named <userName> to the network\r\n" + 
			"	 * within the network <networkName>, with a card of type <cardType> (default: no card) "),
	
	offline("offline <networkName> <stationID>", "	 * Command to turn station of id <stationID> in the network <networkName> off\r\n" ),
	
	online("online <networkName> <stationID>", "	 * Command to turn station of id <stationID> in the network <networkName> on\r\n" ),
	
	rentBike("rentBike <userID> <stationID>", "	 * Command to let the user <userID> rent a bike from station <stationID> \r\n" ),
	
	returnBike("returnBike <userID> <stationID>", "	 * Command to let the user <userID> return a bike to station <stationID> \r\n" ),
	
	displayStation("displayStation <networkName> <stationID>", "	 * Command to display the statistics of station <stationID> of a myVelib network <networkName>.\r\n" ),
	
	displayUser("displayUser <networkName> <userID>", "	 * Command to display the statistics of user whose ID is <userID>\r\n" + 
			"	 * in the myVelib network <networkName>."),
	
	sortStation("sortStation <networkName> <sortpolicy>", "	 * Command to display the stations of network <networkName> in increasing order w.r.t. to the \r\n" + 
			"	 * sorting policy of the client <sortpolicy>.\r\n"),
	
	display("display <velibnetworkName>", "	 * Command to display the entire status (stations, parking bays, users) \r\n" + 
			"	 * of a myVelib network <networkName>.");
	
	
	protected String syntax;
	protected String doc;
	
	AvailableCommands(String syntax, String doc) {
		this.syntax = syntax;
		this.doc = doc;
	}
	
	public String getSyntax() {
		return this.syntax;
	}
	
	public String getDoc() {
		return this.doc;
	}
}
