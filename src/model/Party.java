package model;

/**
 * 
 * @author Raunak
 *
 */
public class Party {
	private String name;
	private float projectedNumberOfSeats;
	private float projectedPercentageOfVotes;
	
	/**
	 * Constructor creates Party instance with partyName.
	 * 
	 * @param partyName the name of the Party instance.
	 */
	public Party(String partyName) {
		name = partyName; //set name
	}
	
	/**
	 * Constructor creates Party with partyName, projectedNumberOfSeats,
	 * and projectedPercentageOfVotes.
	 * 
	 * @param partyName the name of the Party instance.
	 * @param projectedNumberOfSeats for the Party instance to win.
	 * @param projectedPercentageOfVotes for the Party instance to win.
	 */
	public Party(String partyName, float projectedNumberOfSeats, float projectedPercentageOfVotes) {
		name = partyName;
		if (projectedNumberOfSeats>=0f) this.projectedNumberOfSeats = projectedNumberOfSeats;
		if (projectedPercentageOfVotes>=0f&&projectedPercentageOfVotes<=1f) this.projectedPercentageOfVotes = projectedPercentageOfVotes;
	}
	
	/**
	 * @return the projectedPercentageOfVotes.
	 */
	public float getProjectedPercentageOfVotes() {
		return projectedPercentageOfVotes;
	}
	
	/**
	 * @return name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param projectedPercentageOfVotes the projectedPercentageOfVotes to set.
	 */
	public void setProjectedPercentageOfVotes(float projectedPercentageOfVotes) {
		if (projectedPercentageOfVotes>=0f && projectedPercentageOfVotes<=1f)this.projectedPercentageOfVotes = projectedPercentageOfVotes;
		else System.out.println("Error for value entered");
	}

	/**
	 * @return projectedNumberOfSeats.
	 */
	public float getProjectedNumberOfSeats() {
		return projectedNumberOfSeats;
	}

	/**
	 * @param projectedNumberOfSeats to set.
	 */
	public void setProjectedNumberOfSeats(float projectedNumberOfSeats) {
		if (projectedNumberOfSeats>=0f) this.projectedNumberOfSeats = projectedNumberOfSeats;
		else System.out.println("Error for value entered");
	}

	/**
	 * toString() method returns string representation.
	 */
	@Override
	public String toString() {
		int percentage= (int)(projectedPercentageOfVotes*100);
		String ret = name + " (" + percentage + "% of votes, " + projectedNumberOfSeats + " seats)";
		return ret;
	}

	/**
	 * Returns the projectedPercentageOfSeats based on
	 * the totalNumberOfSeats.
	 * 
	 * @param totalNumberOfSeats
	 * @return
	 */
	public double projectedPercentOfSeats(int totalNumberOfSeats) {
		if (totalNumberOfSeats<=0) return 0;
		double projperc=projectedNumberOfSeats/totalNumberOfSeats;
		return projperc;
	}
	
	/**
	 * Returns a text Visual for party statistics with stars or asterisks
	 * used in the visualization.
	 * 
	 * @param numofStars the number of stars needed for the visualization.
	 * @param starsNeededForMajority the number of stars needed for the majority.
	 * @param maxStars the maximum number of stars.
	 * @return the visualization with the number of stars.
	 */
	private String visualWithNumofStars(int numofStars, int starsNeededForMajority, int maxStars) {
		int count = 1;
		String ret="";
		for(int i=1; i <= numofStars; i++) {
			ret=ret + "*";
			count++;
			if(count-1 == starsNeededForMajority) {
				ret=ret + "|";
			}
		}
		while (count < maxStars+1) {
			ret=ret + " ";
			count++;
			if(count-1 == starsNeededForMajority) {
				ret=ret+"|";
			}
		}
		return ret;
	}
	
	/**
	 * Returns a text visualization by the number of 
	 * seats for the party.
	 * 
	 * @param maxStars maximum number of stars in the visualization.
	 * @param starsNeededForMajority the number of stars needed for a majority.
	 * @param numOfSeatsPerStar the number of seats every star represents.
	 * @return a String of the text visualization by the number of seats.
	 */
	public String textVisualizationBySeats(int maxStars, int starsNeededForMajority, double numOfSeatsPerStar) {
		int numberofstars=(int)(projectedNumberOfSeats/numOfSeatsPerStar);		
		String visual = this.visualWithNumofStars(numberofstars, starsNeededForMajority, maxStars);
		return visual + " " + name + " (" + (int)(projectedPercentageOfVotes*100) + "% of votes, " + projectedNumberOfSeats + " seats)";
	}

	/**
	 * Returns a text visualization by the number of votes that the
	 * Party instance has.
	 * 
	 * @param maxStars maximum number of stars in the visualization
	 * @param starsNeededForMajority the number of stars needed for a majority.
	 * @param percentOfVotesPerStar the number of seats every star represents.
	 * @return a String of the text visualization by the number of votes.
	 */
	public String textVisualizationByVotes(int maxStars, int starsNeededForMajority, double percentOfVotesPerStar) {
		int numberofstars=(int)(projectedPercentageOfVotes*100/percentOfVotesPerStar);
		String visual = this.visualWithNumofStars(numberofstars, starsNeededForMajority, maxStars);
		return visual + " " + name + " (" + (int)(projectedPercentageOfVotes*100) + "% of votes, " + projectedNumberOfSeats + " seats)";
	}
	
}
