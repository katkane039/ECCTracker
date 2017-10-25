package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login extends Application {
	
public static Stage parentWindow;
	
	@Override
	public void start(Stage primaryStage1) {
		parentWindow = primaryStage1;
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("Login1.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		Scene scene1 = new Scene(root);
		primaryStage1.setScene(scene1); 
		primaryStage1.sizeToScene();
		primaryStage1.show();
		primaryStage1.setMaximized(true);
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}