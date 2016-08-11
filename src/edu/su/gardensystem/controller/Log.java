package edu.su.gardensystem.controller;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import edu.su.gardensystem.plants.Plant;
import edu.su.gardensystem.plants.PlantFullyGrownException;
import edu.su.gardensystem.systems.ControlSystem;

public class Log {
	private static Logger logger,plantLogger,systemLogger,godLogger;
	private static FileHandler plantFile,systemFile,godFile;
	
	
	public static void init() throws SecurityException, IOException{
		if(logger==null){
			logger = Logger.getLogger("Gardening System");
			
			plantLogger= Logger.getLogger("Plant Log");
			plantFile= new FileHandler(Constants.PlantFileName);
			plantLogger.addHandler(plantFile);
			plantFile.setFormatter(new SimpleFormatter());
			
			systemLogger= Logger.getLogger("System Log");
			systemFile= new FileHandler(Constants.SystemFileName);
			systemLogger.addHandler(systemFile);
			systemFile.setFormatter(new SimpleFormatter());
			
			godLogger= Logger.getLogger("God Log");
			godFile= new FileHandler(Constants.GodFileName);
			godLogger.addHandler(godFile);
			godFile.setFormatter(new SimpleFormatter());
			
			
			
			
			
		}
	}
	
	public static void writeLog(int lvl,String msg,Object caller){
		Level level;
		switch(lvl){
		case 0:
			level= Level.INFO;
			break;
		case 1:
			level = Level.WARNING;
			break;
		case 2:
			level = Level.SEVERE;
			break;
		default:
			level = Level.ALL;
			break;
		}
		if(caller instanceof Plant){
			plantLogger.log(level, msg);
		}
		if(caller instanceof ControlSystem){
			systemLogger.log(level, msg);
		}
		if(caller instanceof DayScheduler){
			godLogger.log(level, msg);
		}
		
		//logger.log(level, msg);
	}

}
