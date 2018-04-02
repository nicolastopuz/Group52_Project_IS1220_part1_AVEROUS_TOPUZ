package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

public enum AvailableCommands {
	setup("", ""),
	addUser("", ""),
	offline("", ""),
	online("", ""),
	rentBike("", ""),
	returnBike("", ""),
	displayStation("", ""),
	displayUser("", ""),
	sortStation("", ""),
	display("", "");
	
	protected String syntax;
	protected String doc;
	
	AvailableCommands(String syntax, String doc) {
		this.syntax = syntax;
		this.doc = doc;
	}
}
