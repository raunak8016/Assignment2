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
    
    private PollList choice = Factory.getInstance().createEmptyPolls();


    @FXML
    void clearButtonClicked(ActionEvent event) {
    	seatNum.setText("");
    	votePercentage.setText("");
    	pollName.setText("");
    }
    
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

    @FXML
    void updatePartyClicked(ActionEvent event) {
    	int percentage = Integer.parseInt(votePercentage.getText());
    	int numOfSeats = Integer.parseInt(seatNum.getText());
    	int index0 = pollEditSelect.getSelectionModel().getSelectedIndex();
    	int index1 = partyUpdateSelect.getSelectionModel().getSelectedIndex();
    	if (index0>=0 && index1 >= 0 && votePercentage.getText()!=null && seatNum.getText() != null) {
    		choice.getPolls()[index0].getParties()[index1].setProjectedNumberOfSeats(numOfSeats);
    		choice.getPolls()[index0].getParties()[index1].setProjectedPercentageOfVotes((float)percentage/100);
    		setPartyNames(choice.getPolls()[index0]);
    		partyUpdateSelect.getSelectionModel().select(index1);
    	}
    }
    
    public void setPartyNames(Poll userPoll) {
    	String[] partyNames = new String[userPoll.getNumberOfParties()];
    	for(int i = 0; i<partyNames.length; i++) {
    		partyNames[i] = userPoll.getParties()[i].toString();
    	}
    	partyUpdateSelect.setItems(FXCollections.observableArrayList(partyNames));
    }
    
    @FXML 
    void initialize() { 
    	setPollChoices();
    	setPartyChoices();
    }
    
    public void setPollChoices() {
    	String[] pollNames = new String[choice.getPolls().length];
    	for(int i = 0; i<pollNames.length; i++) {
    		pollNames[i] = choice.getPolls()[i].getPollName();
    	}
    	pollEditSelect.setItems(FXCollections.observableArrayList(pollNames));
    }
    public void setPartyChoices() {
    	pollEditSelect.setOnAction((event) -> {
    		int index = pollEditSelect.getSelectionModel().getSelectedIndex();
        	if (index>=0) setPartyNames(choice.getPolls()[index]); 
    	});
    }

}
