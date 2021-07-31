package application;
import model.*;
import java.util.Scanner;

/**
 * 
 * @author raunak
 *
 */

public class TextApplication {
	public static final int MAX_NUM_OF_STARS = 25;
	private PollList polls;
	
	/**
	 * Blank constructor uses constant value
	 * for MAX_NUM_OF_STARS, and assigns
	 * a value of 'null' to PollList instance
	 * polls.
	 */
	public TextApplication() {}
	
	/**
	 * Constructor for the TextApplication
	 * interface which takes the instance
	 * variable polls as the PollList
	 * object for the entire application.
	 * 
	 * @param polls the PollList object
	 * in which the TextApplication stores
	 * data.
	 */
	public TextApplication(PollList polls) {
		polls = this.polls;
	}
	
	/**
	 * Displays all of the poll data for
	 * Poll objects in PollList object polls
	 * by seat count.
	 * 
	 * @param partyNames a String[] array
	 * which takes in all of the Strings and
	 * sets them as the partyNames for
	 * the Factory instance.
	 */
	public void displayPollsBySeat(String[] partyNames) {
		/*
		 *  Creates factory to generate random polls in the
		 *  private PollList variable polls.
		 */
		Factory gener = Factory.getInstance();
		gener.setPartyNames(partyNames);
		// Iterates through the length of polls to create random polls.
		for (int i = 0; i < polls.getPolls().length; i++) {
			polls.getPolls()[i] = gener.createRandomPoll("Poll"+i);
			this.displayPollDataBySeat(polls.getPolls()[i]);
		}
		/*
		 *  Displays poll data and for the poll data
		 *  will getAggregatePoll() for polls
		 */
		this.displayPollDataBySeat(polls.getAggregatePoll(partyNames));
	}
	
	/**
	 * @return polls
	 */
	public PollList getPolls() {
		return polls;
	}
	
	/**
	 * Displays a textVisualizationBySeats
	 * for the poll specified as the
	 * argument for this method.
	 * 
	 * @param aPoll to get the data of
	 */
	public void displayPollDataBySeat(Poll aPoll) {
		// Prints the poll name.
		System.out.println(aPoll.getPollName());
		/*
		 *  Iterates through the parties in aPoll and prints
		 *  the textVisualizationBySeats for the poll data.
		 */
		for (int i = 0; i < aPoll.getNumberOfParties(); i++) {
			System.out.println(aPoll.getPartiesSortedBySeats()[i].textVisualizationBySeats(MAX_NUM_OF_STARS, 13, polls.getNumOfSeats()/MAX_NUM_OF_STARS));
		}
	}
	
	/**
	 *  Runs the TextApplication, and provides the needed
	 *  interface between the user input and class methods.
	 *  User input is collected through creating a new
	 *  Scanner object, which is used for method arguments.
	 */
	public void run() {
		//collect input using scanner class
		System.out.println("Welcome to the poll tracker");
		Scanner myObj = new Scanner(System.in);
		
		System.out.print("How many seats are available in the election? ");
		int seatNumber = myObj.nextInt();
		myObj.nextLine();
		
		System.out.print("Which parties are in the election (provide name, comma seperated)? ");
		String parties1 = myObj.nextLine();
		
		System.out.print("How many polls do you want to track with this application? ");
		int pollNum = myObj.nextInt();
		myObj.nextLine();
		
		System.out.print("Would you like me to create a random set of polls? ");
		String randomOrnot = myObj.nextLine();
		
		System.out.println("Options: all (show result of all polls), aggregate (show aggregate result), quit (end application)");
		System.out.print("Choose an option: ");
		String option = myObj.nextLine();
		
		// Creates polls to avoid a NullPointerException.
		polls = new PollList(pollNum, seatNumber);
		
		// Splits parties1 into an array of String type.
		String[] partiesList = parties1.split(",");
		
		// Checks if user wants to generate polls.
		if (randomOrnot.equalsIgnoreCase("yes")) {
			// Stays in a while loop to keep allowing input until user specifies otherwise
			while (option.equalsIgnoreCase("all") || option.equalsIgnoreCase("aggregate")) {
				if (option.equalsIgnoreCase("all"))
					this.displayPollsBySeat(partiesList);
				else if (option.equalsIgnoreCase("aggregate"))
					this.displayPollDataBySeat(polls.getAggregatePoll(partiesList));
				// Calls for a new input after it gets aggregate poll data.
				System.out.println("Options: all (show result of all polls), aggregate (show aggregate result), quit (end application)");
				System.out.print("Choose an option: ");
				option = myObj.nextLine();
			}
		}
		System.exit(0);		
	}

	public static void main(String[] args) {
		TextApplication app = new TextApplication();
		app.run();
	}

}