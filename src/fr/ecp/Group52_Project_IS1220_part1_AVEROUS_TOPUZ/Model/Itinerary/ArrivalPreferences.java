package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Itinerary;

public enum ArrivalPreferences {
	AvoidPlus(new AvoidPlus(), "avoidplus"),
	PreferPlus(new PreferPlus(), "preferplus"),
	NoPreference(new NoPreference(), "nopreference");
	
	private ArrivalStationPreference preference;
	private String commandSelector;
	
	ArrivalPreferences(ArrivalStationPreference preference, String cmdSelector) {
		this.preference = preference;
		this.commandSelector = cmdSelector;
	}
	
	public String getCommand() {
		return this.commandSelector;
	}
	
	public ArrivalStationPreference getArrivalPreference() {
		return this.preference;
	}
}
