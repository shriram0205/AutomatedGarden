package edu.su.rootview;
import application.Garden;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import edu.su.gardensystem.systems.Sprinkler;
import javafx.scene.control.Button;

public class rootviewController {

	private Garden garden;
	public Button AppleSprinkler;
	public Button OrangeSprinkler;
	public Button StrawberrySprinkler;
	public Button AppleHeater;
	public Button OrangeHeater;
	public Button StrawberryHeater;

	public rootviewController(Garden garden) {
		this.garden=garden;
	}

	
	public void AppleSprinkler()
	{
		
		garden.getSprinkler(0).manualSprinkle(2,true);
		
			
	}
public void OrangeSprinkler(){
	
	garden.getSprinkler(1).manualSprinkle(2,true);
		
	}
public void StrawberrySprinkler(){
	
	garden.getSprinkler(2).manualSprinkle(2,true);
	
}
public void AppleHeater()
{
	
	garden.getHeater(0).manualHeat(2,10,true);
}
public void OrangeHeater()
{
	
	garden.getHeater(1).manualHeat(2,10,true);
}
public void StrawberryHeater()
{
	
	garden.getHeater(2).manualHeat(2,10,true);
}
}
