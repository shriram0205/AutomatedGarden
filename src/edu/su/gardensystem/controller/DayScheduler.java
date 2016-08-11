package edu.su.gardensystem.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

import edu.su.gardensystem.systems.ControlSystem;

public class DayScheduler {
	private TimerTask dayTask;
	private Timer universalTimer;
	private ArrayList<ControlSystem> systems;
	private int DaysPassed=0;
	
	public DayScheduler(ArrayList<ControlSystem> values) {
		this.systems=values;
		dayTask= new TimerTask() {				

			@Override
			public void run() {
			   		for(ControlSystem system:systems){
			   			
			   			if((DaysPassed+1)%5==0){
			   				Log.writeLog(0, "The New day with rain has started for "+ system.getInfo() ,DayScheduler.this);
			   				system.notifyEvent(NaturalEvents.Rain, (ThreadLocalRandom.current().nextInt(1,4)));
			   			}
			   			else if((DaysPassed+1)%4==0){
			   				Log.writeLog(0, "The New day with snow has started for "+ system.getInfo() ,DayScheduler.this);
			   				system.notifyEvent(NaturalEvents.Snow, (ThreadLocalRandom.current().nextInt(1,4)));
			   			
			   			}
			   			else{
			   				Log.writeLog(0, "The New Regular day has started for "+ system.getInfo() ,DayScheduler.this);
			   			}
			   			system.setDayDefaults();
			   			DaysPassed++;
			   			
			   		}
			}
		};
	}
	public void startUniverse(){
		if(universalTimer!=null){
			universalTimer.schedule(dayTask,0,Constants.Day_Duration_In_Millis);
		}
		else{
			
			universalTimer= new Timer("Universe");
			universalTimer.schedule(dayTask,0,Constants.Day_Duration_In_Millis);
		}
		Log.writeLog(0, "God comes to play !!!",DayScheduler.this);
		
	}
	
	

}
