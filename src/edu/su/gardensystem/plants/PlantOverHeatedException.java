package edu.su.gardensystem.plants;

public class PlantOverHeatedException extends Exception {
	String Message="";
	public PlantOverHeatedException() {
		Message="The plant has overheated.. System recommends not to heat for today ";
	}
 public String getMessage(){
	return Message;
	 
 }
}
