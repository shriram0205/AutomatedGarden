package edu.su.gardensystem.plants;

public abstract class Plant {

	PlantStage stage;
	public void grow() throws PlantFullyGrownException{
		switch (this.stage) {
		case Seed:
			stage=PlantStage.Sapling;
			break;
		case Sapling:
			stage=PlantStage.Plant;
			break;
		case Plant:
			stage=PlantStage.Flowered;
			break;
		case Flowered:
			throw new PlantFullyGrownException();			
		}
	};
	
	public void harvest(){
		this.stage=PlantStage.Flowered;
	}
	
	public void plant(){
		this.stage=PlantStage.Seed;
		}
	public void sprinkle(int amoutOfWater) throws PlantFullyGrownException{
	//for subclass implementation 	
	}
	public String getName(){
		return null;}
	public PlantStage getPlantStage(){
		return this.stage;
	}

	public void heat(int heaterDuration, int heaterTemp) throws PlantOverHeatedException {
	
	}
	public void setDayDefaults(){}

	public int getMaxHeatTemp() {
		return 0;
	}
}
