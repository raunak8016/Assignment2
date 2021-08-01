package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Factory;


/**
 * 
 * @author raunak
 *
 */
public class SetupController {

    @FXML
    private Button submitButton;

    @FXML
    private TextField enterPollNumber;

    @FXML
    private TextField enterPartyNumber;

    @FXML
    private TextField enterSeatNumber;

    @FXML
    private Button resetButton;
    
    @FXML
    private Button partyNamesButton;
    
    @FXML
    private ArrayList<TextField> partyNamesField = new ArrayList<TextField>();
    
    PollTrackerApp copy;

    /**
     * Method to reset data within the SetupController.
     * Uses the reset button in the GUI being pressed
     * to know whether the user wants to reset.
     * 
     * @param event of the 'reset' button being clicked
     * as an input.
     */
    @FXML
    void resetButtonClicked(ActionEvent event) {
    	enterPollNumber.setText("");
    	enterPartyNumber.setText("");
    	enterSeatNumber.setText("");
    }

    /**
     * Method to submit data with the GUI application as
     * a form. Once a user has finished adding their
     * needed data, they can submit to ensure their
     * changes go through.
     * 
     * @param event of the 'submit' button within the
     * fxml form being clicked.
     */
    @FXML
    void submitButtonClicked(ActionEvent event) {
    	Factory.getInstance().setNumOfSeats(Integer.valueOf(enterSeatNumber.getText()));
    	Factory.getInstance().setNumOfPolls(Integer.valueOf(enterPollNumber.getText()));

		Stage stage = (Stage) submitButton.getScene().getWindow();
		partyNamesButton = new Button("Submit Party Names");

        VBox r = new VBox();
        r.getChildren().add(partyNamesButton);
        for (int i = 0; i < Integer.valueOf(enterPartyNumber.getText()); i++) {
        	Label name = new Label("Name of Party");
            r.getChildren().add(name);
            partyNamesField.add(new TextField());
            r.getChildren().add(partyNamesField.get(i));
        }
        partyNamesButton.setOnAction(this::partyNamesButtonClicked);
        
        // creates a Scene Instance
        Scene sc = new Scene(r, 400, 400);
		stage.setScene(sc);
		stage.show();
    }
    
    /**
     * Gets the pollView of the application, and retrieves
     * the partyNames within the pollView specified, and
     * then adds them to PollList.
     * 
     * @param event of the submit button being clicked.
     */
    @FXML
    void partyNamesButtonClicked(ActionEvent event) {
       	String[] partyNames = new String[Integer.valueOf(enterPartyNumber.getText())];
    	for(int i = 0; i < Integer.valueOf(enterPartyNumber.getText()); i++) {
    		partyNames[i] = partyNamesField.get(i).getText();
    	}
    	// Retrieves the singleton instance of the Factory.
    	Factory.getInstance().setPartyNames(partyNames);
    	copy.pollView();
    }

    /**
     * Links the SetupController with the main application so
     * functionality can be linked to the GUI.
     * 
     * @param pollTrackerApp is the main GUI application class.
     */
	public void linkWithApplication(PollTrackerApp pollTrackerApp) {
		// TODO Auto-generated method stub
		 copy = pollTrackerApp;
	}
}

