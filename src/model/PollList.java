package model;

/**
 *
 * @author Sidd_Pai
 *
 */
public class PollList {
	private Poll[] polls;
	private int numOfSeats;
	
	/**
	 * Creates an instance of PollList, and
	 * sets values based on conditionals.
	 * 
	 * @param numOfPolls number of polls
	 * @param numOfSeats number of seats
	 */
	public PollList(int numOfPolls, int numOfSeats) {
		// Checks for value of numOfPolls and assigns value of '5' if numOfPolls <= 1.
		if (numOfPolls >= 1) {
			polls = new Poll[numOfPolls];
		}
		else {
			polls = new Poll[5];
		}
		// Checks for value of numOfPolls and assigns value of '10' if numOfSeats <= 1.
		if (numOfSeats >= 1) {
			this.numOfSeats = numOfSeats;
		}
		else {
			this.numOfSeats = 10;
		}
	}
	
	/**
	 * @return the numOfSeats
	 */
	public int getNumOfSeats() {
		return numOfSeats;
	}
	
	/**
	 * @return the polls
	 */
	public Poll[] getPolls() {
		return polls;
	}
	
	/**
	 * @param aPoll the Poll instance to add to PollList instance
	 */
	public void addPoll(Poll aPoll) {
		// Error message if aPoll is assigned a value of 'null'.
		if (aPoll == null) {
			System.out.println("Error: argument has value of 'null' ");
		}
		else {
			int index = 0;
			// Checks if the value of aPoll title is the same as any other poll to replace it.
			for (; index < polls.length && polls[index] != null; index++) {
				if (polls[index].getPollName().equalsIgnoreCase(aPoll.getPollName())) {
					polls[index] = aPoll;
					break;
				}
			}
			// Assigns last spot of polls list to add aPoll.
			if (index < polls.length) {
				polls[index] = aPoll;
			}
			// Error message for when the list/array is full.
			else {
				System.out.println("Error: list is full");
			}
		}
	}
	
	/**
	 * @param partyNames names of parties in aggregate poll
	 * @return aggregate of all parties as a Poll object
	 */
	public Poll getAggregatePoll(String[] partyNames) {
		Poll aggregate = new Poll("Aggregate", partyNames.length);
		// Sets votes and seats according to getAveragePartyData().
		for (String partyName : partyNames) {
			Party partyCopy = getAveragePartyData(partyName);
			if (partyCopy != null) {
				aggregate.addParty(partyCopy);
			}
		}
		return adjustPollToMaximums(aggregate);
	}
	
	/**
	 * @param partyName the name of the party to collect data of
	 * @return average of all party with partyName results
	 */
	public Party getAveragePartyData(String partyName) {
		Party partyCopy = new Party(partyName);
		float seats = 0.0f;
		float votes = 0.0f;
		int pollCount = 0;
		// Checks if party is in Poll instance and assigns data.
		for (Poll poll : this.polls) {
			Party party = poll.getParty(partyName);
			if (party != null) {
				pollCount += 1;
				seats += party.getProjectedNumberOfSeats();
				votes += party.getProjectedPercentageOfVotes();
			}
		}
		// Calculates and sets averages for partyCopy.
		seats /= pollCount;
		votes /= pollCount;
		partyCopy.setProjectedNumberOfSeats(seats);
		partyCopy.setProjectedPercentageOfVotes(votes);
		
		return partyCopy;
	}
	
	
	/**
	 * @param aPoll  with party vote counts that need to be adjusted
	 * @return aPoll with votes adjusted to the maximum amount
	 */
	public Poll adjustPollToMaximums(Poll aPoll) {
		float totalPercentageOfVotes = 0.0f;
		float totalNumberOfSeats = 0.0f;
		// Iterates through parties in poll and counts total seats and votes.
		for (Party party : aPoll.getPartiesSortedBySeats()) {
			totalPercentageOfVotes += party.getProjectedPercentageOfVotes();
			totalNumberOfSeats += party.getProjectedNumberOfSeats();
		}
		// Adjusts number of votes to 100% if totalPercentageOfVotes > 100%.
		if (totalPercentageOfVotes > 1.0f) {
			float divisor = 1.0f / totalPercentageOfVotes;
			for (Party party : aPoll.getPartiesSortedByVotes()) {
				party.setProjectedPercentageOfVotes(party.getProjectedPercentageOfVotes() * divisor);
			}
		}
		// Adjusts number of seats for each party if totalNumberOfSeats > numOfSeats.
		if (totalNumberOfSeats > numOfSeats) {
			float divisor = numOfSeats / totalNumberOfSeats;
			for (Party party : aPoll.getPartiesSortedBySeats()) {
				party.setProjectedNumberOfSeats(party.getProjectedNumberOfSeats() * divisor);
			}
		}
		return aPoll;
	}
	
	@Override
	public String toString() {
		// Returns string representation as formatted string.
		return String.format("Number of seats: %s", numOfSeats);
	}
	
}
