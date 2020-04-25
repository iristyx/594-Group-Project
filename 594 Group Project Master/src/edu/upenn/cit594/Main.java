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
		String[] testArgs = new String[5];
		testArgs[0] = "json";
		testArgs[1] = "parking2.json";
		testArgs[2] = "properties3.csv";
		testArgs[3] = "population3.txt";
		testArgs[4] = "logfile.txt";

		logFile = testArgs[4];
		
		Logger logger = Logger.getInstance();
		
		// Log the start time and command line args
		logger.log(startTime + " " + testArgs[0] + " " + testArgs[1] + " " + testArgs[2] + " " + testArgs[3] + " " + testArgs[4]);
		
		if (testArgs.length != 5) {
			throw new IllegalArgumentException("Invalid number of inputs provided.");
		}

		if (!testArgs[0].equalsIgnoreCase("csv") && !testArgs[0].equalsIgnoreCase("json")) {
			throw new IllegalArgumentException("Invalid format for input parking file.");
		}

		// Create reader objects
		ParkingReaderCreator parkingReaderCreator = new ParkingReaderCreator(testArgs[0], testArgs[1]); 
		ParkingReader parkingReader = parkingReaderCreator.createParkingReader();
		PropertyReader propertyReader = new PropertyReader(testArgs[2]);
		PopulationReader populationReader = new PopulationReader(testArgs[3]);
		
		long endTime = System.currentTimeMillis();
		long runTime = endTime - startTime;
		System.out.println("Took " + runTime + "ms to execute.");
		
		// Create processor objects
		ParkingProcessor parkingProcessor = new ParkingProcessor(parkingReader, populationReader);
		PopulationProcessor populationProcessor = new PopulationProcessor(populationReader);
		PropertyProcessor propertyProcessor = new PropertyProcessor(propertyReader, populationReader);
		
		// Create user interface
		UserInterface ui = new UserInterface(parkingProcessor,populationProcessor,propertyProcessor); 
		ui.start();
		
		
	}

}
