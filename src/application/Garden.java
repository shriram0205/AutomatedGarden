package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import edu.su.gardensystem.controller.DayScheduler;
import edu.su.gardensystem.controller.Log;
import edu.su.gardensystem.plants.Apple;
import edu.su.gardensystem.plants.Orange;
import edu.su.gardensystem.plants.Plant;
import edu.su.gardensystem.plants.Strawberry;
import edu.su.gardensystem.systems.ControlSystem;
import edu.su.gardensystem.systems.Heater;
import edu.su.gardensystem.systems.Sprinkler;

public class Garden {
	ArrayList<Plant> plants;
	ArrayList<ControlSystem> systems;
	DayScheduler dayScheduler;
	ArrayList<Sprinkler> Sprinklers;
	ArrayList<Heater> Heaters;
	
	public Garden() {
	
	 plants= new ArrayList<Plant>();
	 systems = new ArrayList<ControlSystem>();
	 Sprinklers= new ArrayList<Sprinkler>();
	 Heaters = new ArrayList<Heater>();
	 
	}

	public void addPlant(Plant P){
		this.plants.add(P);
	}
	public void addControlSystem(ControlSystem system){
		this.systems.add(system);
		
	}
	private void startSystems() {
		dayScheduler= new DayScheduler(systems);
		for(ControlSystem system:systems){
			system.startLife();
		}
		
	}
	public void startGame(){
		startSystems();
		dayScheduler.startUniverse();
		 
	}

	public static void main(String[] args) throws SecurityException, IOException {	
		Garden garden=new Garden();
		 Log.init();
		 
		 
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

	public void addSprinkler(Sprinkler spr) {
		this.Sprinklers.add(spr);
		
	}

	public Sprinkler getSprinkler(int i) {
		return this.Sprinklers.get(i);

	}
	public void addHeater(Heater spr1) {
		this.Heaters.add(spr1);
		
		
	}
	public Heater getHeater(int i) {
		return this.Heaters.get(i);
		
	}
		
	}


