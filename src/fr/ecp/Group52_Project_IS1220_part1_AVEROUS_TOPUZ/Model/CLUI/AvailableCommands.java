package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.CLUI;

public enum AvailableCommands {
	setup("setup <networkName> \nsetup <networkName> <nstations> <nslots> <sidearea> <fillingPercentage>", "	 * Command to create a myVelib network named <networkName> and consisting \r\n" + 
			"	 * of <nstations> (default: 10) stations each of which has <nslots> (default :10) parking slots and such that stations \r\n" + 
			"	 * are initially populated with <fillingPercentage> (default: 70%) bikes randomly equally distrubuted\r\n" + 
			"	 * in each of the 10 stations. Stations are randomly placed in a square of side <sidearea> in km (default: 4km)"),
	
	addUser("addUser <userName> <networkName> \naddUser <userName> <cardType> <networkName>", "	 * Command to add a user named <userName> to the network\r\n" + 
			"	 * within the network <networkName>, with a card of type <cardType> (default: no card) "),
	
	offline("offline <networkName> <stationID>", "	 * Command to turn station of id <stationID> in the network <networkName> off\r\n" ),
	
	online("online <networkName> <stationID>", "	 * Command to turn station of id <stationID> in the network <networkName> on\r\n" ),
	
	rentBike("rentBike <userID> <stationID>\nrentBike <userName> <stationID>", "	 * Command to let the user <userID>/<userName> rent a bike from station <stationID> \r\n" ),
	
	returnBike("returnBike <userID> <stationID> \nreturnBike <userID> <stationID> <time>", "	 * Command to let the user <userID> return a bike to station <stationID> \r\n"+
			"	 * after spending <time> minutes on a bike \r\n"),
	
	displayStation("displayStation <networkName> <stationID>", "	 * Command to display the statistics of station <stationID> of a myVelib network <networkName>.\r\n" ),
	
	displayUser("displayUser <networkName> <userID> \ndisplayUser <networkName> <userName>", "	 * Command to display the statistics of user whose ID is <userID>,\r\n" + 
			"	 * or name is <userName>, in the myVelib network <networkName>."),
	
	sortStation("sortStation <networkName> <sortpolicy>", "	 * Command to display the stations of network <networkName> in increasing order w.r.t. to the \r\n" + 
			"	 * sorting policy of the client <sortpolicy>.\r\n"),
	
	display("display <velibnetworkName>", "	 * Command to display the entire status (stations, parking bays, users) \r\n" + 
			"	 * of a myVelib network <networkName>."),
	
	endTest("endTest","	 * Command to end a test from file and give control back to CLUI."),
	
	goTo("goTo <userID> <arrivalLatitude> <arrivalLongitude>\ngoTo <userID> <arrivalStationID>\n"  
			+ "goTo <userName> <arrivalLatitude> <arrivalLongitude>\ngoTo <userName> <arrivalStationID>","	 * Command to compute the ride for user <userID>/<userName> going either to \r\n" + 
					"	 * GPS coordinates <arrivalLatitude> and <arrivalLongitude>, or to station <arrivalStationID>."),
	
	joinedRideSimulation("joinedRideSimulation <userID> <arrivalLatitude> <arrivalLongitude>\njoinedRideSimulation <userID> <arrivalStationID>"
			+ "\njoinedRideSimulation <userName> <arrivalLatitude> <arrivalLongitude>\njoinedRideSimulation <userName> <arrivalStationID>","	 * Command to launch a simulated ride for user <userID>/<userName> going eigher to \r\n" + 
					"	 * GPS coordinates <arrivalLatitude> and <arrivalLongitude>, or to station <arrivalStationID>. The ride thread will start and the system \r\n"
					+ "	 * will wait for the ride to join before doing anything else."),
	
	rideSimulation("rideSimulation <userID> <arrivalLatitude> <arrivalLongitude>\nrideSimulation <userID> <arrivalStationID>"
			+ "\nrideSimulation <userName> <arrivalLatitude> <arrivalLongitude>\nrideSimulation <userName> <arrivalStationID>", "	 * Command to launch a simulated ride for user <userID>/<userName> going eigher to \r\n" + 
			"	 * GPS coordinates <arrivalLatitude> and <arrivalLongitude>, or to station <arrivalStationID>. The ride thread will start."),
	
	choosePath("choosePath <userID> <pathPreference>\nchoosePath <userName> <pathPreference>","	 * Command to choose the path preference <pathPreference> for user <userID>/<userName>"),
	
	chooseArrival("chooseArrival <userID> <arrivalPreference>\nchooseArrival <userName> <arrivalPreference>","	 * Command to choose the arrival station preference <arrivalPreference> for user <userID>/<userName>"),
	
	addStation("addStation <networkName> <stationType> <numberOfSlots>\naddStation <networkName> <stationType> <numberOfSlots> <numberOfBikes> <mechanicalBikeProportion>","	 * Command to add a station to the network <networkName>. Station will contain \r\n"
			+ "	 * <numberOfSlots> slots, will be of type <stationType>, and will contain <numberOfBikes> bikes with a \r\n"
			+ "	 * proportion of <mechanicalBikeProportion> of mechanical bikes. (default being an empty station)"),
	
	setUserLocation("setUserLocation <userID> <latitude> <longitude>\nsetUserLocation <userName> <latitude> <longitude>","	 * Command to set user <userID>/<userName>'s location to GPS coordinates <latitude> and <longitude>"),
	
	setStationLocation("setStationLocation <networkName> <stationID> <latitude> <longitude>","	 * Command to set the location of station <stationID> in network <networkName> to GPS coordinates <latitude> and <longitude>");
	
	
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
