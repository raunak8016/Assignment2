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

    @FXML
    void resetButtonClicked(ActionEvent event) {
    	enterPollNumber.setText("");
    	enterPartyNumber.setText("");
    	enterSeatNumber.setText("");
    }

    @FXML
    void submitButtonClicked(ActionEvent event) {
    	Factory.getInstance().setNumOfSeats(Integer.valueOf(enterSeatNumber.getText()));
    	Factory.getInstance().setNumOfPolls(Integer.valueOf(enterPollNumber.getText()));

		Stage stage = (Stage) submitButton.getScene().getWindow();
		partyNamesButton = new Button("Submit Party Names");

        VBox r = new VBox();
        r.getChildren().add(partyNamesButton);
        for (int i = 0; i<Integer.valueOf(enterPartyNumber.getText()); i++) {
        	Label name = new Label("Name of Party");
            r.getChildren().add(name);
            partyNamesField.add(new TextField());
            r.getChildren().add(partyNamesField.get(i));
        }
        partyNamesButton.setOnAction(this::partyNamesButtonClicked);
        
        // create a scene
        Scene sc = new Scene(r, 400, 400);
		stage.setScene(sc);
		stage.show();
    }
    
    @FXML
    void partyNamesButtonClicked(ActionEvent event) {
       	String[] partyNames = new String[Integer.valueOf(enterPartyNumber.getText())];
    	for(int i = 0; i<Integer.valueOf(enterPartyNumber.getText()); i++) {
    		partyNames[i] = partyNamesField.get(i).getText();
    	}
    	Factory.getInstance().setPartyNames(partyNames);
    }
}

