package edu.upenn.cit594;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.ParkingCSVReader;
import edu.upenn.cit594.datamanagement.ParkingJSONReader;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertyReader;
import edu.upenn.cit594.datamanagement.PropertyReader;
import edu.upenn.cit594.ui.UserInterface;


public class Main {

	public static void main(String[] args) {


		// Step 1a. Parking CSV Reader
		String textfile = "parking2.csv"; 
		ParkingCSVReader parkingCSVReader = new ParkingCSVReader(textfile);
		List<ParkingViolation> parkingViolations1 = parkingCSVReader.getParkingViolations(); 

		
		// Step 1b. Parking JSON Reader 
		String jsonfile = "parking2.json"; 
		ParkingJSONReader parkingJSONReader = new ParkingJSONReader(jsonfile);
		List<ParkingViolation> parkingViolations2 = parkingJSONReader.getParkingViolations();


		// Step 2. Create Property CSV Reader
		// String csvfile = args[2];
		String csvfile = "propertiesv2.csv";
		PropertyReader propertyReader = new PropertyReader(csvfile);
		List<Property> properties = propertyReader.getProperties();


		// Step 3. Create Population TXT Reader
		// String txtfile = args[3];
		String txtfile = "population2.txt";
		PopulationReader populationReader = new PopulationReader (txtfile);
		HashMap<String, Integer> populations = populationReader.getPopulations();


		UserInterface ui = new UserInterface(); 



	}

}
