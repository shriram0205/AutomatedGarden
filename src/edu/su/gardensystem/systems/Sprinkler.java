package edu.su.gardensystem.systems;

import java.util.Timer;
import java.util.TimerTask;

import edu.su.gardensystem.controller.Constants;
import edu.su.gardensystem.controller.Log;
import edu.su.gardensystem.controller.NaturalEvents;
import edu.su.gardensystem.plants.Plant;
import edu.su.gardensystem.plants.PlantFullyGrownException;

public class Sprinkler implements ControlSystem{
	private Plant plant;
	private boolean isSprinkledToday;
	private boolean isRainedToday; //TODO check for static modifier
	private TimerTask SprinklerTask;
	private int SprinklerWaterAmount;
	private Timer Life;
	
	
	public Sprinkler(int level) {
		this.SprinklerWaterAmount=level;
		
	}
	@Override
	public void setPlant(Plant p){
		this.plant=p;
		
		SprinklerTask = new TimerTask() {
			
			@Override
			public void run() {
				if(isRainedToday){
					Log.writeLog(1, " The Sprinkler for the plant "+plant.getName()+" is Turned Still OFF because of the previous rain ",Sprinkler.this);
					return;
				}
				if(isSprinkledToday){
					Log.writeLog(1, " The Sprinkler for the plant "+plant.getName()+" is Turned Still OFF because it is Already sprinkled",Sprinkler.this);
					return;
				}
				Log.writeLog(0, " The Sprinkler for the plant "+plant.getName()+" is Turned ON",Sprinkler.this);
				try {
					isSprinkledToday=true; 
					plant.sprinkle(SprinklerWaterAmount);
					Log.writeLog(0, " The plant "+plant.getName()+" is sprinkled with " +SprinklerWaterAmount,Sprinkler.this);
					
				} catch (PlantFullyGrownException e) {
					Log.writeLog(2, " The plant "+plant.getName()+" is fully Grown ." + e.getMessage(),Sprinkler.this.getPlant());					
				}
				Log.writeLog(0, " The Sprinkler for the plant "+plant.getName()+" is Turned OFF",Sprinkler.this);
				Log.writeLog(0, " The the plant "+plant.getName()+" is now " + plant.getPlantStage(),Sprinkler.this.getPlant());
				
			}

			
		};
		
		
	}

	public void setDayDefaults() {
		isRainedToday=false;
		isSprinkledToday=false;
		
	}
	@Override
	public void startLife(){
		if(Life !=null){
			
			Life.schedule(SprinklerTask, Constants.Delay_Control_Systems, Constants.Day_Duration_In_Millis);
		}
		else{
			Life= new Timer(this.plant.getName());
			
			Life.schedule(SprinklerTask, Constants.Delay_Control_Systems, Constants.Day_Duration_In_Millis);
		}
		Log.writeLog(0, " The Sprinkler for the plant "+plant.getName()+" is Inititated ",Sprinkler.this);
	}
	
	public void kill(){
		if(Life!=null){
			Life.cancel();
			Log.writeLog(0, " The Sprinkler for the plant "+plant.getName()+" is killed forever",Sprinkler.this);
			Life=null;
		}
	}
	@Override
	public void notifyEvent(NaturalEvents event,Object amount){
		if(event== NaturalEvents.Rain){
			this.isRainedToday=true;
			Log.writeLog(0, " The Sprinkler for the plant "+plant.getName()+" is notified of rain",Sprinkler.this);
			if(isSprinkledToday){
				Log.writeLog(2, " The plant "+plant.getName()+" is Already sprinkled and it is raining",Sprinkler.this.getPlant());
			}
			try {
				plant.sprinkle((Integer)amount);
				Log.writeLog(1, " The plant "+plant.getName()+" is sprinkled with " +amount +" (By rain)",Sprinkler.this.getPlant());
			} catch (PlantFullyGrownException e) {
				Log.writeLog(2, " The plant "+plant.getName()+" is fully Grown ." + e.getMessage(),Sprinkler.this.getPlant());					
			
		}
	}
		
		
	}
	public void manualSprinkle(int amount,boolean isForced){
		if(isForced){
			Log.writeLog(1, " The Sprinkler for the plant "+plant.getName()+" is Turned ON by force ",Sprinkler.this.getPlant());
			try {
				this.isSprinkledToday=true;
				plant.sprinkle(amount);
				
				Log.writeLog(0, " The plant "+plant.getName()+" is sprinkled with " +amount,Sprinkler.this);
				
			} catch (PlantFullyGrownException e) {
				Log.writeLog(2, " The plant "+plant.getName()+" is critical." + e.getMessage(),Sprinkler.this.getPlant());					
			}
		}
		else{
			if(isRainedToday){
				Log.writeLog(1, " The Sprinkler for the plant "+plant.getName()+" is Turned Still OFF because of the previous rain ",Sprinkler.this);
				return;
			}
			if(isSprinkledToday){
				Log.writeLog(1, " The Sprinkler for the plant "+plant.getName()+" is Turned Still OFF because it is Already sprinkled",Sprinkler.this);
				return;
			}
			Log.writeLog(0, " The Sprinkler for the plant "+plant.getName()+" is Turned ON",Sprinkler.this);
			try {
				this.isSprinkledToday=true;
				plant.sprinkle(SprinklerWaterAmount);
				Log.writeLog(0, " The plant "+plant.getName()+" is sprinkled with " +SprinklerWaterAmount,Sprinkler.this);
			} catch (PlantFullyGrownException e) {
				Log.writeLog(2, " The plant "+plant.getName()+" is critical." + e.getMessage(),Sprinkler.this.getPlant());					
			}
			Log.writeLog(0, " The Sprinkler for the plant "+plant.getName()+" is Turned OFF",Sprinkler.this);
			Log.writeLog(0, " The the plant "+plant.getName()+" is now " + plant.getPlantStage(),Sprinkler.this.getPlant());
		}
		
	}

	@Override
	public String getInfo() {		
		return "Sprinkler System for the plant "+this.plant.getName();
	}

	@Override
	public Plant getPlant() {
		return this.plant;
	}


	

	

}
