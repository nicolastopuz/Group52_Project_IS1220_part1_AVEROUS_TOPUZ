package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.*;


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
		
		while(running) {
			arg = input.nextLine();
			allCommands = readcmd(arg);
			
			allCommands[0] = allCommands[0].toLowerCase(); //change to Lower Case to make it easier to compare
			try {
				executeCommand(allCommands);
			} catch( InvalidSyntaxException e ) {
				e.printStackTrace();
			}
		}		
	}

	public static void executeCommand(String[] allCommands) throws InvalidSyntaxException {
		if(allCommands.length == 2) {
			if(allCommands[1].equals("/h") && whatCommand(allCommands[0])!=null) 
				help(whatCommand(allCommands[0]));
		}
		else {
			switch (allCommands[0]) {
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
						rentBike(Integer.parseInt(allCommands[1]), Integer.parseInt(allCommands[2]));
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
						throw new InvalidSyntaxException();
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
				System.out.println("on regarde : "+i+" et lastIndex est "+lastIndex);
		
				if(arg.charAt(i)==' ' && arg.charAt(lastIndex) == ' ' && !isQuote) {
						lastIndex = i+1;
				}
				else if(arg.charAt(i) == ' ' && !isQuote) {
					tampon.add(arg.substring(lastIndex, i));
					System.out.println("ajout de -> "+arg.substring(lastIndex, i));
					lastIndex = i+1;
				}
				else if(arg.charAt(i) == '"') {
					if(isQuote) {
						tampon.add(arg.substring(lastIndex, i));
						System.out.println("ajout de -> "+arg.substring(lastIndex, i));
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
			if(cmd.equals(commands[i].toString()))
				return commands[i];
		}
		return null;
	}
	
	/**
	 * Simple method to find a User knowing only his UserID
	 * @param userID	the ID of the user you wish to find
	 * @return	the User whose ID is userID (null if noone has this ID)
	 */
	public static User findUser(int userID) {
		for(User u : allUsers) {
			if (u.getNumericalId()==userID) {
				return u;
			}
		}
		return null;
	}
	
	/**
	 * Simple method to find a Network knowing only its name
	 * @param networkName	the name of the network you wish to find
	 * @return	the Network whose name is networkName (null if none has this name)
	 */
	public static Network findNetwork(String networkName) {
		for(Network n : allNetworks) {
			if (n.getName().equals(networkName)) {
				return n;
			}
		}
		return null;
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
		}
		catch( InvalidProportionsException e ) {e.printStackTrace();}
	}
	
	/**
	 * command to add a user to the network
	 * 
	 * @param userName name of the user as a String
	 * @param network  the network to which the user should be added
	 */
	public static void addUser(String userName, String networkName) {
		allUsers.add( findNetwork(networkName).createUser(userName) );	
	}
	
	/**
	 * command to add a user to the network
	 * 
	 * @param userName name of the user as a String
	 * @param cardType type of card to assign to the user
	 * @param network  the network to which the user should be added
	 */
	public static void addUser(String userName, CardTypes cardType, String networkName) {
		allUsers.add( findNetwork(networkName).createUser(userName, cardType) );	
	}	
	
	/**
	 * Command to turn a specific station in the network off
	 * @param network	the network in which the station has been built
	 * @param stationID	the ID of the station
	 */
	public static void offline(String networkName, int stationID) {
		findNetwork(networkName).findStation(stationID).setState(false);
	}
	
	/**
	 * Command to turn a specific station in the network on
	 * @param network	the network in which the station has been built
	 * @param stationID	the ID of the station
	 */
	public static void online(String networkName, int stationID) {
		findNetwork(networkName).findStation(stationID).setState(true);
	}
	
	/**
	 * command to let the user userID renting a bike from station stationID 
	 * (if no bikes are available should behave accordingly)
	 * 
	 * @param userID	the ID of the user who wants to rent a bike
	 * @param stationID	the ID of the station of which you wish to rent a bike
	 */
	public static void rentBike(int userID, int stationID) {
		User u = findUser(userID);
		u.takeBike(u.getNetwork().findStation(stationID));
	}
	
	/**
	 * command to let the user userID return a bike to station stationID 
	 * (if no slots are available should behave accordingly)
	 * 
	 * @param userID	the ID of the user who wants to rent a bike
	 * @param stationID	the ID of the station of which you wish to rent a bike
	 */
	public static void returnBike(int userID, int stationID) {
		User u = findUser(userID);
		u.dropBike(u.getNetwork().findStation(stationID));
	}
	
	/**
	 * command to display the statistics of station stationID of a myVelib network networkName.
	 * @param networkName the name of the network
	 * @param stationID	the ID of the station you wish to get intel on
	 */
	public static void displayStation(String networkName, int stationID) {
		String str = statisticCompiler.visit( findNetwork(networkName).findStation(stationID) );
		System.out.println(str);
	}
	
	/**
	 * command to display the statistics of user whose ID is userID
	 * in the myVelib network networkName.
	 * @param networkName the name of the network
	 * @param userID	the ID of the user you wish to get intel on
	 */
	public static void displayUser(String networkName, int userID) {
		String str = statisticCompiler.visit( findUser(userID) );
		System.out.println(str);
	}
	
	/**
	 * command to display the stations in increasing order w.r.t. to the 
	 * sorting policy of the client sortpolicy.
	 * @param networkName the name of the network
	 * @param sortpolicy	the sorting policy for the sort
	 */
	public static void sortStation(String networkName, NetworkStatisticsSortingMethods sortpolicy) {
		statisticCompiler.setSortingMethod(sortpolicy);
		String str = statisticCompiler.visit( findNetwork(networkName) );
		System.out.println(str);
	}
	
	/**
	 * command to display the entire status (stations, parking bays, users) 
	 * of a myVelib network networkName.
	 * @param networkName the name of the Network
	 */
	public static void display(String networkName) {
		String str = statisticCompiler.visit( findNetwork(networkName) );
		System.out.println(str);	
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
	