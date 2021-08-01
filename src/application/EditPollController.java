package application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Factory;
import model.Poll;
import model.Party;
import model.PollList;

public class EditPollController {

    @FXML
    private TextField votePercentage;

    @FXML
    private Button updatePartyButton;

    @FXML
    private Button clearButton;

    @FXML
    private TextField seatNum;

    @FXML
    private ChoiceBox<String> partyUpdateSelect;

    @FXML
    private Button updatePollButton;

    @FXML
    private ChoiceBox<String> pollEditSelect;
    
    @FXML
    private TextField pollName;
    
    PollList choice = Factory.getInstance().createEmptyPolls();

    /**
     * Clears and resets the text within the Edit
     * fields so all text fields go back to being
     * blank.
     * 
     * @param event where the clear button is clicked
     * in the GUI.
     */
    @FXML
    void clearButtonClicked(ActionEvent event) {
    	//set each Textfield to empty
    	seatNum.setText("");
    	votePercentage.setText("");
    	pollName.setText("");
    }
    
    /**
     * Updates the name of the Poll specified
     * when default names are "Poll" and the
     * index of the Poll instance within the polls
     * concatenated as a String.
     * 
     * @param event where the update button is
     * clicked by the user in the GUI.
     */
    @FXML
    void updatePollName(ActionEvent event) {
    	//get text from new poll name
    	String name = pollName.getText(); 
    	
    	//check which poll is being Selected
    	int index = pollEditSelect.getSelectionModel().getSelectedIndex();
    	
    	//change the name of the poll if a Poll is currently selected
    	if (index >= 0) {
    		choice.getPolls()[index].setName(name);
        	setPollChoices();
        	pollEditSelect.getSelectionModel().select(index);
    	}
    }
    
    /**
     * Updates the Party instance specified within the
     * interface.
     * 
     * @param event where user clicks the update button.
     */
    @FXML
    void updatePartyClicked(ActionEvent event) {
    	//get values in the text fields to update the party object
    	int percentage = Integer.parseInt(votePercentage.getText());
    	int numOfSeats = Integer.parseInt(seatNum.getText());
    	
    	//get the index of the party and poll currently selected
    	int index0 = pollEditSelect.getSelectionModel().getSelectedIndex();
    	int index1 = partyUpdateSelect.getSelectionModel().getSelectedIndex();
    	
    	//update the party based on user input for the specific poll selected
    	if (index0 >= 0 && index1 >= 0 && votePercentage.getText() != null && seatNum.getText() != null) {
    		choice.getPolls()[index0].getParties()[index1].setProjectedNumberOfSeats(numOfSeats);
    		choice.getPolls()[index0].getParties()[index1].setProjectedPercentageOfVotes((float) percentage / 100);
    		setPartyNames(choice.getPolls()[index0]);
    		partyUpdateSelect.getSelectionModel().select(index1);
    	}
    }
    
    /**
     * Sets the names of the parties based on the user input.
     * 
     * @param userPoll the Poll instance
     */
    public void setPartyNames(Poll userPoll) {
    	//get the array of names for the Poll selected
    	String[] partyNames = new String[userPoll.getNumberOfParties()];
    	for(int i = 0; i < partyNames.length; i++) {
    		partyNames[i] = userPoll.getParties()[i].toString();
    	}
    	//set the choices for the Parties available in the poll
    	partyUpdateSelect.setItems(FXCollections.observableArrayList(partyNames));
    }
    
    /**
     * Initializes and sets poll and party
     * choices within the GUI.
     */
    @FXML 
    void initialize() { 
    	//run methods to set the choices for Poll and Party choiceboxes
    	setPollChoices();
    	setPartyChoices();
    }
    
    /**
     * Sets the choices of polls by creating a String
     * array and adding the names of the polls, and setting
     * those as the options within the ChoiceBox instance
     * pollEditSelect.
     */
    public void setPollChoices() {
    	//set Poll Choices based on created Factory list
    	String[] pollNames = new String[choice.getPolls().length];
    	for(int i = 0; i<pollNames.length; i++) {
    		pollNames[i] = choice.getPolls()[i].getPollName();
    	}
    	
    	//set Poll choices in choice box using the string array of Poll names
    	pollEditSelect.setItems(FXCollections.observableArrayList(pollNames));
    }
    
    /**
     * Sets party choices based on user input.
     */
    public void setPartyChoices() {
    	//set Party choices once a Poll has been selected
    	pollEditSelect.setOnAction((event) -> {
    		int index = pollEditSelect.getSelectionModel().getSelectedIndex();
        	if (index>=0) setPartyNames(choice.getPolls()[index]); 
    	});
    }

    /**
     * @param polls the PollList instance to set polls to.
     */
	public void setPolls(PollList polls) {
		polls = choice;
	}

	public void refresh() {}

}
