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
	
	public Party(String partyName) {
		name = partyName;
	}
	
	public Party(String partyName, float projectedNumberOfSeats, float projectedPercentageOfVotes) {
		name = partyName;
		if (projectedNumberOfSeats>=0f) this.projectedNumberOfSeats = projectedNumberOfSeats;
		if (projectedPercentageOfVotes>=0f&&projectedPercentageOfVotes<=1f) this.projectedPercentageOfVotes = projectedPercentageOfVotes;
	}
	
	public float getProjectedPercentageOfVotes() {
		return projectedPercentageOfVotes;
	}
	
	public String getName() {
		return name;
	}
	
	public void setProjectedPercentageOfVotes(float projectedPercentageOfVotes) {
		if (projectedPercentageOfVotes>=0f && projectedPercentageOfVotes<=1f)this.projectedPercentageOfVotes = projectedPercentageOfVotes;
	}

	public float getProjectedNumberOfSeats() {
		return projectedNumberOfSeats;
	}

	public void setProjectedNumberOfSeats(float projectedNumberOfSeats) {
		if (projectedNumberOfSeats>=0f) this.projectedNumberOfSeats = projectedNumberOfSeats;
	}

	@Override
	public String toString() {
		int percentage= (int)(projectedPercentageOfVotes*100);
		String ret = name+" ("+percentage+"% of votes, "+ projectedNumberOfSeats+" seats)";
		return ret;
	}

	public double projectedPercentOfSeats(int totalNumberOfSeats) {
		if (totalNumberOfSeats<=0) return 0;
		double projperc=projectedNumberOfSeats/totalNumberOfSeats;
		return projperc;
	}
	private String visualWithNumofStars(int numofStars, int starsNeededForMajority, int maxStars) {
		int count = 1;
		String ret="";
		for(int i=1; i<=numofStars; i++) {
			ret=ret+"*";
			count++;
			if(count-1==starsNeededForMajority) {
				ret=ret+"|";
			}
		}
		while (count<maxStars+1) {
			ret=ret+" ";
			count++;
			if(count-1==starsNeededForMajority) {
				ret=ret+"|";
			}
		}
		return ret;
	}
	
	public String textVisualizationBySeats(int maxStars, int starsNeededForMajority, double numOfSeatsPerStar) {
		int numberofstars=(int)(projectedNumberOfSeats/numOfSeatsPerStar);		
		String visual = this.visualWithNumofStars(numberofstars, starsNeededForMajority, maxStars);
		return visual+" "+name + " (" + (int)(projectedPercentageOfVotes*100) + "% of votes, "+ projectedNumberOfSeats + " seats)";
	}

	public String textVisualizationByVotes(int maxStars, int starsNeededForMajority, double percentOfVotesPerStar) {
		int numberofstars=(int)(projectedPercentageOfVotes*100/percentOfVotesPerStar);
		String visual = this.visualWithNumofStars(numberofstars, starsNeededForMajority, maxStars);
		return visual+" " + name + " (" + (int)(projectedPercentageOfVotes*100) + "% of votes, "+ projectedNumberOfSeats + " seats)";
	}
	
}
