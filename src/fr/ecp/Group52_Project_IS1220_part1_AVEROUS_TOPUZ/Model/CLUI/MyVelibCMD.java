package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.CLUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.InvalidProportionsException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.InvalidSyntaxException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.MoreBikesThanSlotsException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoRideException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoSuchNetworkException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoSuchStationException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoSuchUserException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.OutOfBoundsException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Itinerary.ArrivalPreferences;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Itinerary.PathPreferences;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.GPScoordinates;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Network;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Station.Station;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.User.User;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.User.Card.CardTypes;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Stats.NetworkStatisticsSortingMethods;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Stats.StatisticCompiler;


/**
 * The command interface for the myVelib system.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class MyVelibCMD {
	
	static ArrayList<Network> allNetworks;
	static ArrayList<User> allUsers;
	static StatisticCompiler statisticCompiler;
	static boolean running;
	static Scanner input;
	
	
	public static void main(String[] args) {
		
		input = new Scanner(System.in);
		running = true;
		String arg = new String();
		String[] allCommands;
		
		allNetworks = new ArrayList<Network>();
		allUsers = new ArrayList<User>();
		statisticCompiler = new StatisticCompiler(NetworkStatisticsSortingMethods.MostUsed);
		
		System.out.println("------------ Running system ------------");
		System.out.println("Thank you for using the myVelib system.");
		System.out.println("Type /h for general help on the system.");
		System.out.println("Type <command> /h for specific help for \na command.");
		System.out.println("Use exit command to stop the simulation.");

		
		while(running) {
			arg = input.nextLine();
			allCommands = readcmd(arg);
			
			allCommands[0] = allCommands[0].toLowerCase(); //change to Lower Case to make it easier to compare

			try {
				executeCommand(allCommands, arg);
			} catch( InvalidSyntaxException e ) {
				e.printStackTrace();
			}
		}		
	}

	public static void executeCommand(String[] allCommands, String arg) throws InvalidSyntaxException {
		if(allCommands.length == 2 && allCommands[1].equals("/h") && whatCommand(allCommands[0])!=null) {
			help(whatCommand(allCommands[0]));
		}
		else {			
			switch (allCommands[0]) {
			case "echo":
				String str = String.join(" ", allCommands).substring(5);
				System.out.println(str);
				break;
				
			case "setup":
				if(allCommands.length == 2) {
					setup(allCommands[1]);
				}
				else if(allCommands.length == 6) {
					try {
						setup(allCommands[1], Integer.parseInt(allCommands[2]), Integer.parseInt(allCommands[3]), Double.parseDouble(allCommands[4]), Double.parseDouble(allCommands[5]));
					} catch (NumberFormatException e) {
						throw new InvalidSyntaxException();
					}
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;
				
			case "runtest":
				if(allCommands.length == 2) {
					String path = allCommands[1];
					File file = new File(path);
					try {
						input = new Scanner(file);
					} catch (FileNotFoundException e) {
						System.out.println("No such file in directory. Please try again.");
					}
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;
				
			case "endtest":
				if(allCommands.length == 1) {
					input = new Scanner(System.in);
					System.out.println("Test case is over.");
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;
				
			case "adduser":
				if(allCommands.length == 3) {
					addUser(allCommands[1], allCommands[2]);
				}
				else if(allCommands.length == 4) {
					CardTypes type;
					allCommands[2] = allCommands[2].toLowerCase();
					switch (allCommands[2]) {
						case "nocard":
							type=CardTypes.NoCard;
							break;
						case "vlibre":
							type=CardTypes.Vlibre;
							break;
						case "vmax":
							type=CardTypes.Vmax;
							break;
						default: 
							throw new InvalidSyntaxException();
					}
					addUser(allCommands[1], type, allCommands[3]);
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;
				
			case "offline":
				if (allCommands.length == 3) {
					try {
						offline(allCommands[1], Integer.parseInt(allCommands[2]));
					} catch (NumberFormatException e) {
						throw new InvalidSyntaxException();
					}
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;

			case "online":
				if (allCommands.length == 3) {
					try {
						online(allCommands[1], Integer.parseInt(allCommands[2]));
					} catch (NumberFormatException e) {
						throw new InvalidSyntaxException();
					}
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;
				
			case "rentbike":
				if (allCommands.length == 3) {
					try {
						int stationID = Integer.parseInt(allCommands[2]);
						try {
							rentBike(Integer.parseInt(allCommands[1]), stationID);
						} catch (NumberFormatException e) {
							rentBike(allCommands[1], stationID);
						}
					} catch (NumberFormatException e) {
						throw new InvalidSyntaxException();
					}
					
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;
				
			case "returnbike":
				if (allCommands.length == 3) {
					try {
						returnBike(Integer.parseInt(allCommands[1]), Integer.parseInt(allCommands[2]));
					} catch (NumberFormatException e) {
						throw new InvalidSyntaxException();
					}
				}
				else if (allCommands.length == 4) {
					try {
						returnBike(Integer.parseInt(allCommands[1]), Integer.parseInt(allCommands[2]), Double.parseDouble(allCommands[3]));
					} catch (NumberFormatException e) {
						throw new InvalidSyntaxException();
					}
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;
				
			case "displaystation":
				if (allCommands.length == 3) {
					try {
						displayStation(allCommands[1], Integer.parseInt(allCommands[2]));
					} catch (NumberFormatException e) {
						throw new InvalidSyntaxException();
					}
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;
				
			case "displayuser":
				if (allCommands.length == 3) {
					try {
						displayUser(allCommands[1], Integer.parseInt(allCommands[2]));
					} catch (NumberFormatException e) {
						displayUser(allCommands[1], allCommands[2]);
					}
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;
				
			case "sortstation":
				if(allCommands.length == 3) {
					NetworkStatisticsSortingMethods sortMethod;
					allCommands[2] = allCommands[2].toLowerCase();
					switch (allCommands[2]) {
						case "leastoccupied":
							sortMethod = NetworkStatisticsSortingMethods.LeastOccupied;
							break;
						case "mostused":
							sortMethod = NetworkStatisticsSortingMethods.MostUsed;
							break;
						default: 
							throw new InvalidSyntaxException();
					}
					sortStation(allCommands[1], sortMethod);
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;
				
			case "display":
				if( allCommands.length == 2) {
					display(allCommands[1]);
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;
				
			case "goto":
				if (allCommands.length == 3) {
					try {
						User u = findUser(Integer.parseInt(allCommands[1]));
						goTo(u, Integer.parseInt(allCommands[2]));
					} catch (NumberFormatException e) {
						try {
							User u = findUser(allCommands[1]);
							goTo(u, Integer.parseInt(allCommands[2]));
						} catch (NoSuchUserException e1) {
							e1.printStackTrace();
						} catch (NumberFormatException e1) {
							throw new InvalidSyntaxException();
						} 
					} catch (NoSuchUserException e) {
						e.printStackTrace();
					}
				}
				else if (allCommands.length == 4) {
					try {
						User u = findUser(Integer.parseInt(allCommands[1]));
						GPScoordinates arrival = new GPScoordinates( Double.parseDouble(allCommands[2]), Double.parseDouble(allCommands[3]) );
						goTo(u, arrival);
					} catch (NumberFormatException e) {
						try {
							User u = findUser(allCommands[1]);
							GPScoordinates arrival = new GPScoordinates( Double.parseDouble(allCommands[2]), Double.parseDouble(allCommands[3]) );
							goTo(u, arrival);
						} catch (NoSuchUserException e1) {
							e1.printStackTrace();
						} catch (NumberFormatException | OutOfBoundsException e1) {
							throw new InvalidSyntaxException();
						} 
					} catch (NoSuchUserException | OutOfBoundsException e) {
						e.printStackTrace();
					}
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;
			
			case "joinedridesimulation":
				if (allCommands.length == 3) {
					try {
						User u = findUser(Integer.parseInt(allCommands[1]));
						goTo(u, Integer.parseInt(allCommands[2]));
						u.getRide().start();
						u.getRide().join();
					} catch (NumberFormatException e) {
						try {
							User u = findUser(allCommands[1]);
							goTo(u, Integer.parseInt(allCommands[2]));
							u.getRide().start();
							u.getRide().join();
						} catch (NoSuchUserException | InterruptedException | NoRideException e1) {
							e1.printStackTrace();
						} catch (NumberFormatException e1) {
							throw new InvalidSyntaxException();
						} 
					} catch (NoSuchUserException | InterruptedException | NoRideException e) {
						e.printStackTrace();
					}
				}
				else if (allCommands.length == 4) {
					try {
						User u = findUser(Integer.parseInt(allCommands[1]));
						GPScoordinates arrival = new GPScoordinates( Double.parseDouble(allCommands[2]), Double.parseDouble(allCommands[3]) );
						goTo(u, arrival);
						u.getRide().start();
						u.getRide().join();
					} catch (NumberFormatException e) {
						try {
							User u = findUser(allCommands[1]);
							GPScoordinates arrival = new GPScoordinates( Double.parseDouble(allCommands[2]), Double.parseDouble(allCommands[3]) );
							goTo(u, arrival);
							u.getRide().start();
							u.getRide().join();
						} catch (NoSuchUserException | InterruptedException | NoRideException e1) {
							e1.printStackTrace();
						} catch (NumberFormatException | OutOfBoundsException e1) {
							throw new InvalidSyntaxException();
						} 
					} catch (NoSuchUserException | NoRideException | InterruptedException | OutOfBoundsException e) {
						e.printStackTrace();
					}
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;
				
			case "ridesimulation":
				if (allCommands.length == 3) {
					try {
						User u = findUser(Integer.parseInt(allCommands[1]));
						goTo(u, Integer.parseInt(allCommands[2]));
						u.getRide().start();
					} catch (NumberFormatException e) {
						try {
							User u = findUser(allCommands[1]);
							goTo(u, Integer.parseInt(allCommands[2]));
							u.getRide().start();
						} catch (NoSuchUserException | NoRideException e1) {
							e1.printStackTrace();
						} catch (NumberFormatException e1) {
							throw new InvalidSyntaxException();
						} 
					} catch (NoSuchUserException | NoRideException e) {
						e.printStackTrace();
					}
				}
				else if (allCommands.length == 4) {
					try {
						User u = findUser(Integer.parseInt(allCommands[1]));
						GPScoordinates arrival = new GPScoordinates( Double.parseDouble(allCommands[2]), Double.parseDouble(allCommands[3]) );
						goTo(u, arrival);
						u.getRide().start();
					} catch (NumberFormatException e) {
						try {
							User u = findUser(allCommands[1]);
							GPScoordinates arrival = new GPScoordinates( Double.parseDouble(allCommands[2]), Double.parseDouble(allCommands[3]) );
							goTo(u, arrival);
							u.getRide().start();
						} catch (NoSuchUserException | NoRideException e1) {
							e1.printStackTrace();
						} catch (NumberFormatException | OutOfBoundsException e1) {
							throw new InvalidSyntaxException();
						} 
					} catch (NoSuchUserException | NoRideException | OutOfBoundsException e) {
						e.printStackTrace();
					}
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;
			
			case "choosepath":
				if (allCommands.length == 3) {
					try {
						choosePath(Integer.parseInt(allCommands[1]), allCommands[2]);
					} catch (NumberFormatException e) {
						choosePath(allCommands[1], allCommands[2]);
					}
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;
				
			case "choosearrival":
				if (allCommands.length == 3) {
					try {
						chooseArrival(Integer.parseInt(allCommands[1]), allCommands[2]);
					} catch (NumberFormatException e) {
						chooseArrival(allCommands[1], allCommands[2]);
					}
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;
				
			case "addstation":
				if (allCommands.length == 4) {
					try {
						addStation(allCommands[1], allCommands[2], Integer.parseInt(allCommands[3]));
					} catch (NumberFormatException e) {
						throw new InvalidSyntaxException();
					}
				}
				else if (allCommands.length == 6) {
					try {
						addStation(allCommands[1], allCommands[2], Integer.parseInt(allCommands[3]), Integer.parseInt(allCommands[4]), Float.parseFloat(allCommands[5]));
					} catch (NumberFormatException e) {
						throw new InvalidSyntaxException();
					}
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;
				
			case "setstationlocation":
				if (allCommands.length == 5) {
					try {
						setStationLocation(allCommands[1], Integer.parseInt(allCommands[2]), Double.parseDouble(allCommands[3]), Double.parseDouble(allCommands[4]));
					} catch( NumberFormatException e) {
						throw new InvalidSyntaxException();
					}
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;
				
			case "setuserlocation":
				if (allCommands.length == 4) {
					try {
						setUserLocation(Integer.parseInt(allCommands[1]), Double.parseDouble(allCommands[2]), Double.parseDouble(allCommands[3]));
					} catch( NumberFormatException e) {
						setUserLocation(allCommands[1], Double.parseDouble(allCommands[2]), Double.parseDouble(allCommands[3]));
					}
				}
				else {
					throw new InvalidSyntaxException();
				}
				break;
				
			case "exit":
				exit();
				break;
			
			case "/h":
				help();
				break;
				
			default:
				System.out.println("Unknown command.");
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Simple function used to read the command entered by the client,
	 * in which each parameter should be seperated by a blank space. 
	 * It allows the user to enter Names or prompts containing spaces
	 * using quotes.
	 * The command <code>name The Chief</code> will be read as three commands, 
	 * "name", "The" and "Chief". But entering <code>name "The Chief"</code> will be 
	 * read as "name" and "The Chief".
	 * 
	 * @param arg the full command to be read and split into a String array
	 * @return	the command read as a String array.
	 */
	public static String[] readcmd(String arg) {
		boolean isQuote = false;
		String[] allcmd;
		
		if(!arg.contains("\"")) {
			allcmd = arg.split(" ");
		}
		else {
			ArrayList<String> tampon = new ArrayList<String>();
			int lastIndex = 0;
			arg += " ";
			for(int i = 0; i < arg.length(); i++) {
		
				if(arg.charAt(i)==' ' && arg.charAt(lastIndex) == ' ' && !isQuote) {
						lastIndex = i+1;
				}
				else if(arg.charAt(i) == ' ' && !isQuote) {
					tampon.add(arg.substring(lastIndex, i));
					lastIndex = i+1;
				}
				else if(arg.charAt(i) == '"') {
					if(isQuote) {
						tampon.add(arg.substring(lastIndex, i));
					}
					isQuote = !isQuote;
					lastIndex = i+1;
				}
			}
			
			allcmd = new String[tampon.size()];
			for (int i = 0; i < allcmd.length; i++) {
				allcmd[i] = tampon.get(i);
			}
		}
		return allcmd;
	}
	
	public static AvailableCommands whatCommand(String cmd) {
		AvailableCommands[] commands = AvailableCommands.values();
		for (int i = 0; i < commands.length; i++) {
			if(cmd.equals(commands[i].toString().toLowerCase()))
				return commands[i];
		}
		return null;
	}
	
	/**
	 * Simple method to find a User knowing only his UserID
	 * @param userID	the ID of the user you wish to find
	 * @return	the User whose ID is userID (NoSuchUserException if no such user exists)
	 */
	public static User findUser(int userID) throws NoSuchUserException {
		for(User u : allUsers) {
			if (u.getNumericalId()==userID) {
				return u;
			}
		}
		throw new NoSuchUserException();
	}
	
	/**
	 * Simple method to find a User knowing only his UserID
	 * @param userName	the name of the user you wish to find
	 * @return	the User whose ID is userID (NoSuchUserException if no such user exists)
	 */
	public static User findUser(String userName) throws NoSuchUserException {
		for(User u : allUsers) {
			if (u.getName().equals(userName)) {
				return u;
			}
		}
		throw new NoSuchUserException();
	}
	
	/**
	 * Simple method to find a Network knowing only its name
	 * @param networkName	the name of the network you wish to find
	 * @return	the Network whose name is networkName (null if none has this name)
	 */
	public static Network findNetwork(String networkName) throws NoSuchNetworkException {
		for(Network n : allNetworks) {
			if (n.getName().equals(networkName)) {
				return n;
			}
		}
		throw new NoSuchNetworkException();
	}
	
	// Commands for the command prompt
	
	/**
	 * command to create a myVelib network with given name and consisting 
	 * of 10 stations each of which has 10 parking slots and such that stations 
	 * are initially populated with 70% bikes randomly equally distrubuted
	 * in each of the 10 stations 	 
	 * 
	 * @param name the name to be given to the network
	 * @param allNetworks		List of all networks, to add the new network to
	 */
	public static void setup(String name) {
		setup(name, 10, 10, 4, 0.7);
	}
	
	/**
	 * command to create a myVelib network with given name and consisting 
	 * of nomberOfStations stations each of which has slotsPerStations parking slots
	 * and initially willed to fillingPercentage percent of its total space
	 * 
	 * @param name	Name of the Network
	 * @param numberOfStations	Number of Stations to build in
	 * @param slotsPerStation	number of slots per station
	 * @param fillingPercentage	Percentage of slots to fill with bikes
	 * @param allNetworks		List of all networks, to add the new network to
	 */
	public static void setup(String name, int numberOfStations, int slotsPerStation, double sideArea, double fillingPercentage) {
		try {
			allNetworks.add(new Network(name, numberOfStations, slotsPerStation, sideArea, fillingPercentage));
			System.out.println("Network "+name+" has been setup.");
		}
		catch( InvalidProportionsException e ) {e.printStackTrace();}
	}
	
	/**
	 * command to add a station to the network
	 * 
	 * @param network Name the network to which the user should be added
	 * @param stationType type of station to create
	 * @param numberOfSlots the number of parking slots to build into the station
	 */
	public static void addStation(String networkName, String stationType, int numberOfSlots) throws InvalidSyntaxException {
		try {
			if(stationType.equals("standard")) {
				Network n = findNetwork(networkName);
				n.createStation(numberOfSlots);
			}
			else if(stationType.equals("plus")) {
				Network n = findNetwork(networkName);
				n.createStationPlus(numberOfSlots);
			}
			else {
				throw new InvalidSyntaxException();
			}
		} catch (NoSuchNetworkException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * command to add a station to the network
	 * 
	 * @param network Name the network to which the user should be added
	 * @param stationType type of station to create
	 * @param numberOfSlots the number of parking slots to build into the station
	 * @param numberOfBikes the number of bikes to place into the station
	 * @param mechanicalBikeProportion the proportion of mechanical bikes to build into the station
	 */
	public static void addStation(String networkName, String stationType, int numberOfSlots,int numberOfBikes, float mechanicalBikeProportion) throws InvalidSyntaxException {
		try {
			if(stationType.equals("standard")) {
				Network n = findNetwork(networkName);
				n.createStation(numberOfSlots, numberOfBikes, mechanicalBikeProportion);
			}
			else if(stationType.equals("plus")) {
				Network n = findNetwork(networkName);
				n.createStationPlus(numberOfSlots, numberOfBikes, mechanicalBikeProportion);
			}
			else {
				throw new InvalidSyntaxException();
			}
		} catch (NoSuchNetworkException | MoreBikesThanSlotsException | InvalidProportionsException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * command to add a user to the network
	 * 
	 * @param userName name of the user as a String
	 * @param network  the network to which the user should be added
	 */
	public static void addUser(String userName, String networkName) {
		try {
			allUsers.add( findNetwork(networkName).createUser(userName) );	
			System.out.println("User "+userName+" has been added to network "+networkName+".");
		} catch (NoSuchNetworkException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * command to add a user to the network
	 * 
	 * @param userName name of the user as a String
	 * @param cardType type of card to assign to the user
	 * @param network  the network to which the user should be added
	 */
	public static void addUser(String userName, CardTypes cardType, String networkName) {
		try {
			allUsers.add( findNetwork(networkName).createUser(userName, cardType) );	
			System.out.println("User "+userName+" has been added to network "+networkName+".");
		} catch (NoSuchNetworkException e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * command to set the location of a station
	 * 
	 * @param networkName	the name of the network the station is in
	 * @param stationID		the ID of the station	
	 * @param latitude		the latitude of where the station should be set
	 * @param longitude		the longitude of where the station should be set
	 */
	public static void setStationLocation(String networkName, int stationID, double latitude, double longitude) throws InvalidSyntaxException {
		try {
			Network n = findNetwork(networkName);
			Station s = n.findStation(stationID);
			s.setLocation(new GPScoordinates(latitude, longitude));
		} catch (OutOfBoundsException | NoSuchNetworkException | NoSuchStationException e) {
			throw new InvalidSyntaxException();
		}
	}
	
	/**
	 * command to set the location of a user
	 * 
	 * @param userID		the ID of the user	
	 * @param latitude		the latitude of where the user should be set
	 * @param longitude		the longitude of where the user should be set
	 */
	public static void setUserLocation(int userID, double latitude, double longitude) throws InvalidSyntaxException {
		try {
			User u = findUser(userID);
			u.setPosition(new GPScoordinates(latitude, longitude));
		} catch (OutOfBoundsException e) {
			throw new InvalidSyntaxException();
		} catch (NoSuchUserException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * command to set the location of a user
	 * 
	 * @param userName		the name of the user	
	 * @param latitude		the latitude of where the user should be set
	 * @param longitude		the longitude of where the user should be set
	 */
	public static void setUserLocation(String userName, double latitude, double longitude) throws InvalidSyntaxException {
		try {
			User u = findUser(userName);
			u.setPosition(new GPScoordinates(latitude, longitude));
		} catch (OutOfBoundsException e) {
			throw new InvalidSyntaxException();
		} catch (NoSuchUserException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Command to turn a specific station in the network off
	 * @param network	the network in which the station has been built
	 * @param stationID	the ID of the station
	 */
	public static void offline(String networkName, int stationID) {
		try {
			findNetwork(networkName).findStation(stationID).setState(false);
			System.out.println("Station "+stationID+" from network "+networkName+" has been turned offline.");
		} catch (NoSuchNetworkException e) {
			e.printStackTrace();
		} catch(NoSuchStationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Command to turn a specific station in the network on
	 * @param network	the network in which the station has been built
	 * @param stationID	the ID of the station
	 */
	public static void online(String networkName, int stationID) {
		try {
			findNetwork(networkName).findStation(stationID).setState(true);
			System.out.println("Station "+stationID+" from network "+networkName+" has been turned online.");
		} catch (NoSuchNetworkException e) {
			e.printStackTrace();
		} catch(NoSuchStationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * command to let the user userID renting a bike from station stationID 
	 * (if no bikes are available should behave accordingly)
	 * 
	 * @param userID	the ID of the user who wants to rent a bike
	 * @param stationID	the ID of the station of which you wish to rent a bike
	 */
	public static void rentBike(int userID, int stationID) {
		try {
			User u = findUser(userID);
			u.takeBike(u.getNetwork().findStation(stationID));
			System.out.println("User "+u.getName()+" has successfully rented a bike from station "+stationID+".");
		} catch (NoSuchUserException e) {
			e.printStackTrace();
		} catch(NoSuchStationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * command to let the user userName renting a bike from station stationID 
	 * (if no bikes are available should behave accordingly)
	 * 
	 * @param userName	the name of the user who wants to rent a bike
	 * @param stationID	the ID of the station of which you wish to rent a bike
	 */
	public static void rentBike(String userName, int stationID) {
		try {
			User u = findUser(userName);
			u.takeBike(u.getNetwork().findStation(stationID));
			System.out.println("User "+userName+" has successfully rented a bike from station "+stationID+".");
		} catch (NoSuchUserException e) {
			e.printStackTrace();
		} catch(NoSuchStationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * command to let the user userID return a bike to station stationID 
	 * (if no slots are available should behave accordingly)
	 * 
	 * @param userID	the ID of the user who wants to rent a bike
	 * @param stationID	the ID of the station of which you wish to rent a bike
	 */
	public static void returnBike(int userID, int stationID) {
		try {
			User u = findUser(userID);
			u.dropBike(u.getNetwork().findStation(stationID));
			System.out.println("User "+userID+" has successfully returned a bike at station "+stationID+".");
		} catch (NoSuchUserException | NoSuchStationException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * command to let the user userID return a bike to station stationID 
	 * (if no slots are available should behave accordingly)
	 * 
	 * @param userID	the ID of the user who wants to rent a bike
	 * @param stationID	the ID of the station of which you wish to rent a bike
	 * @param time 		the time the user has spent on the bike before returning it
	 */
	public static void returnBike(int userID, int stationID, double time) {
		try {
			User u = findUser(userID);
			u.dropBike(u.getNetwork().findStation(stationID), time);
			System.out.println("User "+userID+" has successfully returned a bike at station "+stationID+".");
		} catch (NoSuchUserException | NoSuchStationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * command to give instructions to user on how to get to arrival
	 * GPScoordinates using the myvelib system
	 * 
	 * @param user 		the user who is on the move
	 * @param arrival	the GPScoordinates of the position the user wants to get to
	 */
	public static void goTo(User user, GPScoordinates arrival) {
		user.goTo(arrival);	
	}
	
	/**
	 * command to give instructions to user on how to get to station
	 * stationID using the myvelib system
	 * 
	 * @param user 		the user who is on the move
	 * @param stationID	the ID of the station the user wants to get to
	 */
	public static void goTo(User user, int stationID) {
		Station arrival;
		try {
			arrival = user.getNetwork().findStation(stationID);
			goTo(user,arrival.getLocation());
		} catch (NoSuchStationException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * command to display the statistics of station stationID of a myVelib network networkName.
	 * @param networkName the name of the network
	 * @param stationID	the ID of the station you wish to get intel on
	 */
	public static void displayStation(String networkName, int stationID) {
		try {
			String str = statisticCompiler.visit( findNetwork(networkName).findStation(stationID) );
			System.out.println(str);
		} catch (NoSuchNetworkException e) {
			e.printStackTrace();
		} catch(NoSuchStationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * command to display the statistics of user whose ID is userID
	 * in the myVelib network networkName.
	 * @param networkName the name of the network
	 * @param userID	the ID of the user you wish to get intel on
	 */
	public static void displayUser(String networkName, int userID) {
		try {
			String str = statisticCompiler.visit( findUser(userID) );
			System.out.println(str);
		} catch (NoSuchUserException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * command to display the statistics of user whose name is userName
	 * in the myVelib network networkName.
	 * @param networkName the name of the network
	 * @param userName	the name of the user you wish to get intel on
	 */
	public static void displayUser(String networkName, String userName) {
		try {
			String str = statisticCompiler.visit( findUser(userName) );
			System.out.println(str);
		} catch (NoSuchUserException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * command to display the stations in increasing order w.r.t. to the 
	 * sorting policy of the client sortpolicy.
	 * @param networkName the name of the network
	 * @param sortpolicy	the sorting policy for the sort
	 */
	public static void sortStation(String networkName, NetworkStatisticsSortingMethods sortpolicy) {
		try {
			statisticCompiler.setSortingMethod(sortpolicy);
			String str = statisticCompiler.visit( findNetwork(networkName) );
			System.out.println(str);	
		} catch (NoSuchNetworkException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * command to display the entire status (stations, parking bays, users) 
	 * of a myVelib network networkName.
	 * @param networkName the name of the Network
	 */
	public static void display(String networkName) {
		try {
			String str = statisticCompiler.visit( findNetwork(networkName) );
			System.out.println(str);	
		} catch (NoSuchNetworkException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Command for user to choose the his PathPreference
	 * 
	 * @param userID			The ID of the user whose path preference is to be set
	 * @param pathPreference	The path preference of the user
	 */
	public static void choosePath(int userID, String pathPreference) {
		try {
			boolean tampon = true;
			User u = findUser(userID) ;
			PathPreferences[] allPreferences = PathPreferences.values();
			for (int i = 0; i < allPreferences.length; i++) {
				if(allPreferences[i].getCommand().equals(pathPreference)) {
					u.setPathPreference(allPreferences[i]);
					tampon = false;
					break;
				}
			}
			if(tampon) {
				System.out.println("This preference does not exist, try again.");
			}System.out.println("This preference does not exist, try again.");
		} catch (NoSuchUserException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Command for user to choose the his PathPreference
	 * 
	 * @param userName			The name of the user whose path preference is to be set
	 * @param pathPreference	The path preference of the user
	 */
	public static void choosePath(String userName, String pathPreference) {
		try {
			boolean tampon=true;
			User u = findUser(userName) ;
			PathPreferences[] allPreferences = PathPreferences.values();
			for (int i = 0; i < allPreferences.length; i++) {
				if(allPreferences[i].getCommand().equals(pathPreference)) {
					u.setPathPreference(allPreferences[i]);
					tampon = false;
					break;
				}
			}
			if(tampon) {
				System.out.println("This preference does not exist, try again.");
			}
		} catch (NoSuchUserException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Command for user to choose the his ArrivalPreference
	 * 
	 * @param userID			The ID of the user whose arrival preference is to be set
	 * @param arrivalPreference	The arrival preference of the user
	 */
	public static void chooseArrival(int userID, String arrivalPreference) {
		try {
			boolean tampon = true;
			User u = findUser(userID) ;
			ArrivalPreferences[] allPreferences = ArrivalPreferences.values();
			for (int i = 0; i < allPreferences.length; i++) {
				if(allPreferences[i].getCommand().equals(arrivalPreference)) {
					u.setArrivalStationPreference(allPreferences[i]);
					tampon = false;
					break;
				}
			}
			if(tampon) {
				System.out.println("This preference does not exist, try again.");
			}		
		} catch (NoSuchUserException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Command for user to choose the his ArrivalPreference
	 * 
	 * @param userName			The name of the user whose arrival preference is to be set
	 * @param arrivalPreference	The arrival preference of the user
	 */
	public static void chooseArrival(String userName, String arrivalPreference) {
		try {
			boolean tampon = true;
			User u = findUser(userName) ;
			ArrivalPreferences[] allPreferences = ArrivalPreferences.values();
			for (int i = 0; i < allPreferences.length; i++) {
				if(allPreferences[i].getCommand().equals(arrivalPreference)) {
					u.setArrivalStationPreference(allPreferences[i]);
					tampon = false;
					break;
				}
			}
			if(tampon) {
				System.out.println("This preference does not exist, try again.");
			}
		} catch (NoSuchUserException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * command to shot the system down.
	 */
	public static void exit() {
		running = false;
		System.out.println("Thank you for using the myVelib system.");
		System.out.println("--------- Shutting system down ---------");
		input.close();
	}
	
	public static void help() {
		AvailableCommands[] totalCommands = AvailableCommands.values();
		System.out.println("----------------- Help -----------------");
		System.out.println("---- List of all available commands ----\n");
		for (int i = 0; i < totalCommands.length; i++) {
			System.out.println(totalCommands[i].getSyntax());
			System.out.println(totalCommands[i].getDoc());
			System.out.println();
		}
	}
	
	public static void help(AvailableCommands command) {
		System.out.println("Help on command "+command);
		System.out.println(command.getSyntax());
		System.out.println(command.getDoc());
	}
}	
	