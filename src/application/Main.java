package application;
	
import java.io.IOException;

import edu.su.gardensystem.controller.Log;
import edu.su.gardensystem.plants.Apple;
import edu.su.gardensystem.plants.Orange;
import edu.su.gardensystem.plants.Strawberry;
import edu.su.gardensystem.systems.Heater;
import edu.su.gardensystem.systems.Sprinkler;
import edu.su.rootview.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private static Garden garden;
	
	public Main() {
		
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			
			setup();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/edu/su/rootview/rootview.fxml"));
			loader.setControllerFactory(new javafx.util.Callback<Class<?>, Object>() {
				
				@Override
				public Object call(Class<?> param) {
					return new rootviewController(garden);
					
				}
			});
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			primaryStage.setTitle("Automated Gardening System");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	public void setup() throws SecurityException, IOException{
		 Log.init();
		 
		 garden= new Garden();
		 Strawberry strawberry= new Strawberry();
		 garden.addPlant(strawberry);
		 
		 Orange orange= new Orange();
		 garden.addPlant(orange);
		 
		 Apple apple= new Apple();
		 garden.addPlant(apple);
		 
		 Sprinkler strawberrySprinkler= new Sprinkler(2);
		 strawberrySprinkler.setPlant(strawberry);
		 garden.addControlSystem(strawberrySprinkler);
		 garden.addSprinkler(strawberrySprinkler);
		 
		 Heater strawberryHeater= new Heater(15,3);
		 strawberryHeater.setPlant(strawberry);
		 garden.addControlSystem(strawberryHeater);
		 garden.addHeater(strawberryHeater);
		 
		 Sprinkler orangeSprinkler= new Sprinkler(2);
		 orangeSprinkler.setPlant(orange);
		 garden.addControlSystem(orangeSprinkler);
		 garden.addSprinkler(orangeSprinkler);
		 
		 Heater orangeHeater= new Heater(15,3);
		 orangeHeater.setPlant(orange);
		 garden.addControlSystem(orangeHeater);
		 garden.addHeater(orangeHeater);
		 
		 Sprinkler appleSprinkler= new Sprinkler(2);
		 appleSprinkler.setPlant(apple);
		 garden.addControlSystem(appleSprinkler);
		 garden.addSprinkler(appleSprinkler);
		 
		 Heater appleHeater= new Heater(15,3);
		 appleHeater.setPlant(apple);
		 garden.addControlSystem(appleHeater);
		 garden.addHeater(appleHeater);
		 
		 
		 garden.startGame();
		 
	}
}
