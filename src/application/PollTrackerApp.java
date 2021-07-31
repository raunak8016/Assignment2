package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import model.Factory;
import model.PollList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;

public class PollTrackerApp extends Application {
	Stage primaryStage;
	PollList polls;
	
	private void setupView() {
		FXMLLoader loader = new FXMLLoader();
		Scene scene;
		try {
			Parent setupView = (Parent) loader.load(new FileInputStream("src/views/SetupView.fxml"));
			SetupController setupController = loader.getController();
			setupController.linkWithApplication(this);
			scene = new Scene(setupView,300,300);
			primaryStage.setScene(scene);
			primaryStage.show();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void pollView() {
		FXMLLoader loader = new FXMLLoader();
		Scene scene;
		polls = Factory.getInstance().createEmptyPolls();

		try {
			loader = new FXMLLoader();
			Tab editView = new Tab("Edit", loader.load(new FileInputStream("src/views/EditPollView.fxml")));
			EditPollController editPollController = loader.getController();
			editPollController.setPolls(polls);
			editPollController.refresh();
			loader = new FXMLLoader();
			Tab visualizeView = new Tab("Visualize", loader.load(new FileInputStream("src/views/VisualizePollView.fxml")));
			VisualizePollController visualizePollController = loader.getController();
			visualizePollController.setPolls(polls);
			TabPane root = new TabPane(editView, visualizeView);
			scene = new Scene(root, 800,500);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		setupView();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
