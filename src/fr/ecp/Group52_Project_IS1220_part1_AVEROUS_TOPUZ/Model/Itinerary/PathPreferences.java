package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Itinerary;

public enum PathPreferences {
	Fastest(new FastestPath(), "fastest"),
	Shortest(new ShortestPath(), "shortest");
	
	private PathPreferenceVisitor preference;
	private String commandSelector;
	
	PathPreferences(PathPreferenceVisitor preference, String cmdSelector) {
		this.preference = preference;
		this.commandSelector = cmdSelector;
	}
	
	public String getCommand() {
		return this.commandSelector;
	}
	
	public PathPreferenceVisitor getPathPreference() {
		return this.preference;
	}
}
