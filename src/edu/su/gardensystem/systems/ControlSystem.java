package edu.su.gardensystem.systems;

import edu.su.gardensystem.controller.NaturalEvents;
import edu.su.gardensystem.plants.Plant;

public interface ControlSystem {
	void setDayDefaults();
	String getInfo();
	void startLife();
	void kill();
	Plant getPlant();
	void setPlant(Plant p);
	void notifyEvent(NaturalEvents event, Object amount);
	

}
