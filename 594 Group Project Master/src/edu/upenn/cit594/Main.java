package edu.upenn.cit594;

import edu.upenn.cit594.datamanagement.ParkingCSVReader;
import edu.upenn.cit594.datamanagement.ParkingJSONReader;
import edu.upenn.cit594.datamanagement.ParkingReader;
import edu.upenn.cit594.datamanagement.ParkingReaderCreator;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertyReader;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.ParkingProcessor;
import edu.upenn.cit594.processor.PopulationProcessor;
import edu.upenn.cit594.processor.PropertyProcessor;
import edu.upenn.cit594.ui.UserInterface;

public class Main {
	
	public static String logFile;

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		logFile = args[4];
		
		Logger logger = Logger.getInstance();
		
		// Log the start time and command line args
		logger.log(startTime + " " + args[0] + " " + args[1] + " " + args[2] + " " + args[3] + " " + args[4]);
		
		if (args.length != 5) {
			throw new IllegalArgumentException("Invalid number of inputs provided.");
		}

		if (!args[0].equalsIgnoreCase("csv") && !args[0].equalsIgnoreCase("json")) {
			throw new IllegalArgumentException("Invalid format for input parking file.");
		}

		// Create reader objects
		ParkingReader parkingReader = new ParkingReaderCreator(args[0],args[1]).createReader();
		PropertyReader propertyReader = new PropertyReader(args[2]);
		PopulationReader populationReader = new PopulationReader(args[3]);
				
		// Create processor objects
		ParkingProcessor parkingProcessor = new ParkingProcessor(parkingReader, populationReader);
		PopulationProcessor populationProcessor = new PopulationProcessor(populationReader);
		PropertyProcessor propertyProcessor = new PropertyProcessor(propertyReader, populationReader);
		
		// Create user interface
		UserInterface ui = new UserInterface(parkingProcessor,populationProcessor,propertyProcessor); 
		
		// REMOVE LATER
		long endTime = System.currentTimeMillis();
		long timeElapsed = endTime - startTime;
		long minElapsed = timeElapsed / (60*1000);
		long secElapsed = (timeElapsed % (60*1000)) / 1000;
		System.out.println("Took " + minElapsed + "m" +  secElapsed + "s to execute.");
		
		ui.start();
		
		
	}

}
