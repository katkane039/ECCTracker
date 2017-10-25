package application;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Title extends Application {

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
   Group group = new Group();
    
    Image  image  = new Image("Tracker.png",500,500,true,true); 
    ImageView imageView = new ImageView(image);
    imageView.relocate(400, 100);
    
    FadeTransition ft = new FadeTransition(Duration.millis(5000), imageView);
    ft.setFromValue(1.0);
    ft.setToValue(0.0);
    ft.play();
    Parent root;
    group.getChildren().add(imageView);
    
   
	try {
		root = FXMLLoader.load(getClass().getClassLoader().getResource("FrontPage.fxml"));
	} catch (IOException e) {
		e.printStackTrace();
		return;
	}
	Scene scene =new Scene(root);
    scene = new Scene(group);
    primaryStage.setScene(scene);
    primaryStage.show();
    primaryStage.setMaximized(true);
    
    

  }
}