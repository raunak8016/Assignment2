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
    	String name = pollName.getText();
    	int index = pollEditSelect.getSelectionModel().getSelectedIndex();
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
    	int percentage = Integer.parseInt(votePercentage.getText());
    	int numOfSeats = Integer.parseInt(seatNum.getText());
    	int index0 = pollEditSelect.getSelectionModel().getSelectedIndex();
    	int index1 = partyUpdateSelect.getSelectionModel().getSelectedIndex();
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
    	String[] partyNames = new String[userPoll.getNumberOfParties()];
    	for(int i = 0; i < partyNames.length; i++) {
    		partyNames[i] = userPoll.getParties()[i].toString();
    	}
    	partyUpdateSelect.setItems(FXCollections.observableArrayList(partyNames));
    }
    
    /**
     * Initializes and sets poll and party
     * choices within the GUI.
     */
    @FXML 
    void initialize() { 
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
    	String[] pollNames = new String[choice.getPolls().length];
    	for(int i = 0; i<pollNames.length; i++) {
    		pollNames[i] = choice.getPolls()[i].getPollName();
    	}
    	pollEditSelect.setItems(FXCollections.observableArrayList(pollNames));
    }
    
    /**
     * Sets party choices based on user input.
     */
    public void setPartyChoices() {
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
