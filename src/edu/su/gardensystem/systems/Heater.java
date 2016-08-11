package edu.su.gardensystem.systems;

import java.util.Timer;
import java.util.TimerTask;

import edu.su.gardensystem.controller.Constants;
import edu.su.gardensystem.controller.Log;
import edu.su.gardensystem.controller.NaturalEvents;
import edu.su.gardensystem.plants.Plant;
import edu.su.gardensystem.plants.PlantFullyGrownException;
import edu.su.gardensystem.plants.PlantOverHeatedException;

public class Heater implements ControlSystem {
	private Plant plant;
	private boolean isHeatedToday;
	private boolean isSnowedToday; 
	private TimerTask HeaterTask;
	private int HeaterTemp;
	private int HeaterDuration;
	private Timer Life;
	

	public Heater(int temp,int duration) {
	this.HeaterTemp=temp;
	this.HeaterDuration=duration;
	}
	@Override
	public void setDayDefaults() {
		isHeatedToday=false;
		isSnowedToday=false;

	}

	@Override
	public String getInfo() {
		return "Heater System for the plant "+this.plant.getName();
	}

	@Override
	public void startLife() {
		{
			if(Life !=null){
				
				Life.schedule(HeaterTask, Constants.Delay_Control_Systems, Constants.Day_Duration_In_Millis);
			}
			else{
				Life= new Timer(this.plant.getName());
				
				Life.schedule(HeaterTask, Constants.Delay_Control_Systems, Constants.Day_Duration_In_Millis);
			}
			Log.writeLog(0, " The Heater for the plant "+plant.getName()+" is Inititated ",Heater.this);
		}

	}

	@Override
	public void kill() {
		if(Life!=null){
			Life.cancel();
			Log.writeLog(0, " The Heater for the plant "+plant.getName()+" is destroyed forever",Heater.this);
			Life=null;
		}

	}

	@Override
	public Plant getPlant() {
		return this.plant;
	}

	@Override
	public void setPlant(Plant p) {
		if(HeaterTemp>p.getMaxHeatTemp()){
			Log.writeLog(2, " The plant "+plant.getName()+" cannot handle this Temperature",Heater.this.getPlant() );					
		}
		this.plant=p;
		HeaterTask= new TimerTask() {
			
			@Override
			public void run() {
				if(isSnowedToday){
					Log.writeLog(1, " The Heater for the plant "+plant.getName()+" is Turned ON for "+HeaterDuration+" because of the previous snow ",Heater.this);
					try {
						plant.heat(HeaterDuration,HeaterTemp);
					} catch (PlantOverHeatedException e) {
						Log.writeLog(2, " The plant "+plant.getName()+" is overheated." + e.getMessage(),Heater.this.getPlant());					
					}
				}
				if(isHeatedToday){
					Log.writeLog(1, " The Heater for the plant "+plant.getName()+" is Turned Still OFF because it is Already heated today",Heater.this);
					return;
				}
				Log.writeLog(0, " The Heater for the plant "+plant.getName()+" is Turned ON",Heater.this);
				try {
					isHeatedToday=true; 
					plant.heat(HeaterDuration,HeaterTemp);
					Log.writeLog(0, " The plant "+plant.getName()+" is heated for " +HeaterDuration,Heater.this.getPlant());
					
				} catch (PlantOverHeatedException e) {
					Log.writeLog(2, " The plant "+plant.getName()+" is overheated." + e.getMessage(),Heater.this);					
				}
				Log.writeLog(0, " The Heater for the plant "+plant.getName()+" is Turned OFF",Heater.this);
				
			}
		};

	}

	@Override
	public void notifyEvent(NaturalEvents event, Object amount) {
		if(event== NaturalEvents.Snow){
			this.isSnowedToday=true;
			Log.writeLog(0, " The Heater for the plant "+plant.getName()+" is notified of snow",Heater.this);
			if(isHeatedToday){
				Log.writeLog(2, " The plant "+plant.getName()+" is heated for the day and it is snowing. Have to re-heat",Heater.this.getPlant());
				isHeatedToday=false;
			}
		
		
		}
	}
	
	public void manualHeat(int duration,int temp,boolean isForced){
		if(temp>plant.getMaxHeatTemp()&& !isForced){
			Log.writeLog(2, "The plant "+plant.getName()+" cannot handle this temperature",Heater.this.getPlant());
			return;
		}
		if(isHeatedToday && isForced){
			Log.writeLog(1, "The plant "+plant.getName()+" is already heated",Heater.this);
			try {
				this.plant.heat(duration, temp);
			} catch (PlantOverHeatedException e) {
				Log.writeLog(2, " The plant "+plant.getName()+" is overheated." + e.getMessage(),Heater.this.getPlant());					
			}
		}
		else{
			try {
				this.plant.heat(duration, temp);
			} catch (PlantOverHeatedException e) {
				Log.writeLog(2, " The plant "+plant.getName()+" is overheated." + e.getMessage(),Heater.this.getPlant());					
			}
		}	
		
	}
}
