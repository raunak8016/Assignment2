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

	public Poll(String name, int maxNumberOfParties) {
		this.name = name;
		if (maxNumberOfParties>=1) parties = new Party[maxNumberOfParties];
		else parties = new Party[10];
	}
	
	public String getPollName() {
		return name;
	}
	

	public Party[] getParties() {
		return parties;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getNumberOfParties() {
		return numberOfParties;
	}
	
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
	private Party[] reversePartyArray(Party[] ordered) {
		//reverse code found on https://stackoverflow.com/questions/2137755/how-do-i-reverse-an-int-array-in-java
		for(int i = 0; i < ordered.length / 2; i++){
		    Party temp = ordered[i];
		    ordered[i] = ordered[ordered.length - i - 1];
		    ordered[ordered.length - i - 1] = temp;
		}
		return ordered;
	}
	
	public Party[] getPartiesSortedBySeats() {
		/*
		 * sort documentation found on https://stackoverflow.com/
		 * questions/18895915/how-to-sort-an-array-of-objects-in-java
		 */
		Arrays.sort(parties, Comparator.comparing(Party::getProjectedNumberOfSeats));
		this.reversePartyArray(parties);
		return parties;
	}

	public Party[] getPartiesSortedByVotes() {
		Arrays.sort(parties, Comparator.comparing(Party::getProjectedPercentageOfVotes));
		this.reversePartyArray(parties);
		return parties;
	}
	
	@Override
	public String toString() {
		String ret = name+"\n";
		for (int i = 0; i<this.getNumberOfParties(); i++) {
			 ret=ret+parties[i].getName()+"\n";
		}
		return ret;
	}
}
