package edu.upenn.cit594;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.datamanagement.ParkingReader;
import edu.upenn.cit594.datamanagement.ParkingReaderCreator;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertyReader;
import edu.upenn.cit594.processor.ParkingProcessor;
import edu.upenn.cit594.processor.PopulationProcessor;
import edu.upenn.cit594.processor.PropertyProcessor;
import edu.upenn.cit594.ui.UserInterface;

public class Main {

	public static void main(String[] args) {

		String[] testArgs = new String[5];
		testArgs[0] = "json";
		testArgs[1] = "parking.json";
		testArgs[2] = "properties.csv";
		testArgs[3] = "population.txt";
		testArgs[4] = "logfile.txt";

		if (testArgs.length != 5) {
			throw new IllegalArgumentException("Invalid number of inputs provided.");
		}

		if (!testArgs[0].equalsIgnoreCase("csv") && !testArgs[0].equalsIgnoreCase("json")) {
			throw new IllegalArgumentException("Invalid format for input parking file.");
		}

		// Create reader objects

		// try to implement this in Singleton later
		ParkingReaderCreator parkingReaderCreator = new ParkingReaderCreator(testArgs[0], testArgs[1]); 
		ParkingReader parkingReader = parkingReaderCreator.createParkingReader();
		PropertyReader propertyReader = new PropertyReader(testArgs[2]);
		PopulationReader populationReader = new PopulationReader(testArgs[3]);
		
		// Create processor objects
		ParkingProcessor parkingProcessor = new ParkingProcessor(parkingReader, populationReader);
		PopulationProcessor populationProcessor = new PopulationProcessor(populationReader);
		PropertyProcessor propertyProcessor = new PropertyProcessor(propertyReader, populationReader);
		
		// Create user interface
		UserInterface ui = new UserInterface(parkingProcessor,populationProcessor,propertyProcessor); 
		ui.start();
	}

}
