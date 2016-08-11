package edu.su.gardensystem.plants;

public class PlantFullyGrownException extends Exception {
	String Message="";
	public PlantFullyGrownException() {
		Message="The plant has grown fully.. System recommends to harvest the plant.. ";
	}
 public String getMessage(){
	return Message;
	 
 }
}
