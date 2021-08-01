package model;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * @author Raunak
 *
 */

public class Poll {
	private String name = "Unnamed Poll";
	private Party[] parties;
	private int numberOfParties = 0;

	/**
	 * Constructor that creates a new Poll instance or object
	 * when a name and the maxNumberofParties values are specified
	 * for the instance variables.
	 * 
	 * @param name
	 * @param maxNumberOfParties
	 */
	public Poll(String name, int maxNumberOfParties) {
		this.name = name;
		if (maxNumberOfParties>=1) parties = new Party[maxNumberOfParties];
		else parties = new Party[10];
	}
	
	/**
	 * @return the name of the Poll instance.
	 */
	public String getPollName() {
		return name;
	}
	
	/**
	 * @return the Poll instance's parties.
	 */
	public Party[] getParties() {
		return parties;
	}
	
	/**
	 * @param name the name to set for the Poll instance.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the numberOfParties within the Poll instance.
	 */
	public int getNumberOfParties() {
		return numberOfParties;
	}
	
	/**
	 * @param aParty to add to the Poll instance.
	 */
	public void addParty(Party aParty) {
		if (aParty==null) System.out.println("Error: party entered is null");
		boolean addEnd = true;
		for (int i = 0; i < numberOfParties; i++) {
			if (aParty.getName().equalsIgnoreCase(parties[i].getName())) {
				addEnd = false;
				parties[i] = aParty;
			}
		}
		if (addEnd && (numberOfParties < parties.length)) {
			parties[numberOfParties++] = aParty;
		}
	}

	/**
	 * Gets the party specified within the Poll instance.
	 * 
	 * @param name String name of the party.
	 * @return the party if it exists within the poll.
	 */
	public Party getParty(String name) {
		boolean found = false;
		int place = -5;
		for (int i = 0; i < numberOfParties; i++) {
			if (name.equalsIgnoreCase(parties[i].getName())) {
				found = true;
				place = i;
			}
		}
		if (found) {
			return parties[place];
		}
		else {
			return null;
		}
	}
	
	/**
	 * Takes in a Party[] array as a parameter and/or
	 * argument, and returns the reversed version of
	 * the result.
	 * 
	 * @param ordered array of Party objects.
	 * @return The ordered array, but reversed.
	 */
	private Party[] reversePartyArray(Party[] ordered) {
		//Reverse code found on https://stackoverflow.com/questions/2137755/how-do-i-reverse-an-int-array-in-java
		for(int i = 0; i < ordered.length / 2; i++){
		    Party temp = ordered[i];
		    ordered[i] = ordered[ordered.length - i - 1];
		    ordered[ordered.length - i - 1] = temp;
		}
		return ordered;
	}
	
	/**
	 * Gets the parties within the poll sorted by the
	 * number of seats in descending order.
	 * 
	 * @return a Party[] array where results are sorted
	 * by the number of seats.
	 */
	public Party[] getPartiesSortedBySeats() {
		/*
		 * sort documentation found on https://stackoverflow.com/
		 * questions/18895915/how-to-sort-an-array-of-objects-in-java
		 */
		Arrays.sort(parties, Comparator.comparing(Party::getProjectedNumberOfSeats));
		this.reversePartyArray(parties);
		return parties;
	}

	/**
	 * Gets the parties within the poll sorted by the
	 * number of votes in descending order.
	 * 
	 * @return a Party[] array where results are sorted
	 * by the number of votes.
	 */
	public Party[] getPartiesSortedByVotes() {
		Arrays.sort(parties, Comparator.comparing(Party::getProjectedPercentageOfVotes));
		this.reversePartyArray(parties);
		return parties;
	}
	
	/**
	 * Returns the name of all of the parties within
	 * the poll as the String representation.
	 */
	@Override
	public String toString() {
		String ret = name + "\n";
		for (int i = 0; i < this.getNumberOfParties(); i++) {
			 ret=ret + parties[i].getName() + "\n";
		}
		return ret;
	}
}
