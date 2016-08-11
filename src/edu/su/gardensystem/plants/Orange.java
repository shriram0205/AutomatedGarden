package edu.su.gardensystem.plants;
import edu.su.gardensystem.controller.Log;
public class Orange extends Plant {
	private int Water_Quantity;	
	private int increment_water_level=8;
	private int mx_heat_level_for_the_day= 100;
	private int heated_for_today=0;
	private int mx_temperature=25; //todo try constant class
	public Orange() {
		super();
		this.plant();
		this.Water_Quantity=0;
		heated_for_today=0;
	}
	
	@Override
	public String getName(){		
		
		return "Orange";
	}
	
	
	@Override
	public void setDayDefaults() {
		heated_for_today=0;
	};
	@Override
	public void sprinkle(int amoutOfWater) throws PlantFullyGrownException{
		if(this.getPlantStage()==PlantStage.Flowered){
			Log.writeLog(0, " The Sprinkler for plant "+getName()+" is overridden and set to OFF ",Orange.this);
			return;
		}
		Water_Quantity+=amoutOfWater;
		validateWaterQuantity(amoutOfWater);
		
	}

	private void validateWaterQuantity(int amount) throws PlantFullyGrownException {
		
		if(Water_Quantity>=increment_water_level){
			try {
				this.grow();
				Water_Quantity/=increment_water_level;
			} catch (PlantFullyGrownException e) {
				e.printStackTrace();
				Water_Quantity+=amount;
				throw e;
			}
			
		}
		
	}
	@Override
	public void heat(int heaterDuration, int heaterTemp) throws PlantOverHeatedException {
		if(heaterDuration*heaterTemp>=mx_heat_level_for_the_day){
			heated_for_today+=(heaterDuration*heaterTemp);
			throw new PlantOverHeatedException();
		}
		else{
			heated_for_today+=(heaterDuration*heaterTemp);
		}
	}

	@Override
	public int getMaxHeatTemp() {
		// TODO Auto-generated method stub
		return this.mx_temperature;
	}

}
