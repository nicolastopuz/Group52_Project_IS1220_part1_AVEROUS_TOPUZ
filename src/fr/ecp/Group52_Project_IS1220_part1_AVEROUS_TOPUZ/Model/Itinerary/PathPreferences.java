package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Itinerary;

public enum PathPreferences {
	Fastest(new FastestPath(), "fastest"),
	Shortest(new ShortestPath(), "shortest");
	
	private PathPreference preference;
	private String commandSelector;
	
	PathPreferences(PathPreference preference, String cmdSelector) {
		this.preference = preference;
		this.commandSelector = cmdSelector;
	}
	
	public String getCommand() {
		return this.commandSelector;
	}
	
	public PathPreference getPathPreference() {
		return this.preference;
	}
}
