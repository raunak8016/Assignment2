package application;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author raunak
 *
 */
public class SetupTester extends Application {
	/**
	 * Sets the scene and displays the information for
	 * the SetupView.fxml and corresponding controller
	 * class file.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			//loader code to initiate the scene
			FXMLLoader loader = new FXMLLoader();
			BorderPane root = loader.load(new FileInputStream("src/views/SetupView.fxml"));
			Scene scene = new Scene(root,700,700);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * launches the SetupView scene
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
