package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

public enum PathPreferences {
	Fastest(new FastestPath()),
	Shortest(new ShortestPath());
	
	private PathPreferenceVisitor preference;
	
	PathPreferences(PathPreferenceVisitor preference) {
		this.preference = preference;
	}
	
	public PathPreferenceVisitor getPathPreference() {
		return this.preference;
	}
}
