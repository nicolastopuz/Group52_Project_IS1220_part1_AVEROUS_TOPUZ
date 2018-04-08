package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

public enum ArrivalPreferences {
	AvoidPlus(new AvoidPlus(), "avoidplus"),
	PreferPlus(new PreferPlus(), "preferplus"),
	NoPreference(new NoPreference(), "nopreference");
	
	private ArrivalStationPreferenceVisitable preference;
	private String commandSelector;
	
	ArrivalPreferences(ArrivalStationPreferenceVisitable preference, String cmdSelector) {
		this.preference = preference;
		this.commandSelector = cmdSelector;
	}
	
	public String getCommand() {
		return this.commandSelector;
	}
	
	public ArrivalStationPreferenceVisitable getArrivalPreference() {
		return this.preference;
	}
}
