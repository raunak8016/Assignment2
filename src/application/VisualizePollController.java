package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import model.Factory;
import model.Party;
import model.Poll;
import model.PollList;

/**
 * 
 * @author Sidd
 *
 */
public class VisualizePollController {
	PollList polls;
	
	/**
	 * @param polls the polls to set
	 */
	public void setPolls(PollList polls) {
		this.polls = polls;
	}

    @FXML
    private BorderPane pollVisualizer;

    @FXML
    private ChoiceBox<Poll> pollChoices;

    @FXML
    private PieChart projectedSeatsPieChart;

    @FXML
    private PieChart projectedVotesPieChart;
    
    /**
     * Displays the information for the seat data
     * of the poll specified in the pollChoices
     * Choicebox.
     * 
     * @param event of the projectedSeatsPieChart being
     * clicked for when the user wants to see a detailed
     * breakdown of the information of projected seats.
     */
    @FXML
    void onSeatChartClick(ActionEvent event) {
    	
    }

    /**
     * Displays the information for the vote data
     * of the poll specified in the pollChoices
     * Choicebox.
     * 
     * @param event of the projectedSeatsPieChart being
     * clicked for when the user wants to see a detailed
     * breakdown of the information of projected votes.
     */
    @FXML
    void onVoteChartClick(ActionEvent event) {}
    
    /**
     * Sets the data for the pie charts, which will later be displayed
     * in the GUI for the user to see when they want to select a Poll
     * within the polls instance variable.
     * 
     * @param aPoll to set the pie chart data for.
     */
    private void setChartsData(Poll aPoll) {
    	int numberOfParties = aPoll.getNumberOfParties();
    	
		// Creates and initializes both sets of data for the pie charts.
		PieChart.Data[] projectedSeatsData = new PieChart.Data[numberOfParties];
		PieChart.Data[] projectedVotesData = new PieChart.Data[numberOfParties];
		
		int index = 0;
		
		/*
		 * Iterates through the parties in aPoll instance, sorted by seats, and then
		 * adds them to both data sets for the pie charts. The variable 'i' is incremented
		 * to help loop through the indices of the Data[] array created for assigning data
		 * to the pie chart.
		 */
		for (Party party : aPoll.getPartiesSortedBySeats()) {
			projectedSeatsData[index] = new PieChart.Data(party.getName(), party.getProjectedNumberOfSeats());
			projectedVotesData[index] = new PieChart.Data(party.getName(), party.getProjectedPercentageOfVotes());
			index++;
		}
		// Sets data to the proper format through an observableArrayList data type.
		projectedSeatsPieChart.setData(FXCollections.observableArrayList(projectedSeatsData));
		projectedVotesPieChart.setData(FXCollections.observableArrayList(projectedVotesData));
    }
    
    /**
     * Initializes data and functionality for ChoiceBox pollChoices
     * because ChoiceBox as a class is not event based, but must
     * be initialized on its own. A ChangeListener is added to the
     * ChoiceBox to record any change in the choices selected by the
     * user by the pollsChoice controller
     */
	@FXML
	void initialize() {
		this.polls = Factory.getInstance().createRandomPollList();
		String[] partyNames = Factory.getInstance().getPartyNames();
		Poll aggregate = this.polls.getAggregatePoll(partyNames);
		
		// Sets the items of the pollsChoice to be the singleton Factory PollList
		pollChoices.setItems(FXCollections.observableArrayList(this.polls.getPolls()));
		pollChoices.getItems().add(aggregate);
		
		// Sets the initial choice as the aggregate poll, and displays both sets of data
		pollChoices.setValue(aggregate);
		setChartsData(aggregate);
		
		/*
		 * Adds a ChangeListener object to the ChoiceBox pollsChoice
		 * to listen to any changes in the poll selected by the user
		 * so data in the GUI can change immediately to display new
		 * data.
		 */
		pollChoices.getSelectionModel().selectedIndexProperty().addListener(
			new ChangeListener<Number>() {
				/*
				 * Looks at the previous choice and compares it to the current one,
				 * which informs the program if the user has selected a new option,
				 * allowing for the additional functionality of new pie charts to
				 * replace the old ones.
				 */
				@Override
				public void changed(ObservableValue observable, Number oldChoice, Number newChoice) {
					int index = newChoice.intValue();
					setChartsData(polls.getPolls()[index]);
				}
			}
		);
	}
}

