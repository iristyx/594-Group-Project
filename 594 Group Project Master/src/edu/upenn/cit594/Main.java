package edu.upenn.cit594;

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
		
		// Test only - should read in command lines args[] for actual run
//		String[] testArgs = new String[5];
//		testArgs[0] = "json";
//		testArgs[1] = "parking.json";
//		testArgs[2] = "properties.csv";
//		testArgs[3] = "population.txt";
//		testArgs[4] = "logfile.txt";

		if (args.length != 5) {
			throw new IllegalArgumentException("Invalid number of inputs provided.");
		}
		
		logFile = args[4];		
		Logger logger = Logger.getInstance();
		
		// Log the start time and command line args
		logger.log(startTime + " " + args[0] + " " + args[1] + " " + args[2] + " " + args[3] + " " + args[4]);
		
		if (!args[0].equalsIgnoreCase("csv") && !args[0].equalsIgnoreCase("json")) {
			throw new IllegalArgumentException("Invalid format for input parking file.");
		}

		// Create reader objects
		ParkingReaderCreator parkingReaderCreator = new ParkingReaderCreator(args[0], args[1]); 
		ParkingReader parkingReader = parkingReaderCreator.createParkingReader();
		PropertyReader propertyReader = new PropertyReader(args[2]);
		PopulationReader populationReader = new PopulationReader(args[3]);
		
		// REMOVE BETWEEN COMMENTS BEFORE SUBMISSION
		long endTime = System.currentTimeMillis();
		long runTime = endTime - startTime;
		System.out.println("Took " + runTime + "ms to execute.");
		// REMOVE BETWEEN COMMENTS BEFORE SUBMISSION
		
		// Create processor objects
		ParkingProcessor parkingProcessor = new ParkingProcessor(parkingReader, populationReader);
		PopulationProcessor populationProcessor = new PopulationProcessor(populationReader);
		PropertyProcessor propertyProcessor = new PropertyProcessor(propertyReader, populationReader);
		
		// Create user interface
		UserInterface ui = new UserInterface(parkingProcessor,populationProcessor,propertyProcessor); 
		ui.start();
	}

}
