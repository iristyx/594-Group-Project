package edu.upenn.cit594;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Property;

import edu.upenn.cit594.datamanagement.ParkingCSVReader;
import edu.upenn.cit594.datamanagement.ParkingJSONReader;
import edu.upenn.cit594.datamanagement.PopulationTXTReader;
import edu.upenn.cit594.datamanagement.PropertyCSVReader;


public class Main {

	public static void main(String[] args) {

		boolean properArguments = false; 

//		if (args.length != 4) {
//			System.out.println("Error");
//			System.exit(0); 
//		}

		// Step 1a. Parking CSV Reader
//		Pattern p1 = Pattern.compile("csv", Pattern.CASE_INSENSITIVE);
//		Matcher m1 = p1.matcher(args[0]);
//
//		if (m1.matches()) {
			// String textfile = args[1]; 
			String textfile = "parking2.csv"; 
			ParkingCSVReader parkingCSVReader = new ParkingCSVReader(textfile);
			properArguments = true; 
			ArrayList<Parking> parkingViolations1 = parkingCSVReader.getParkingViolations(); 
//		}

		// Step 1b. Parking JSON Reader 
//		Pattern p2 = Pattern.compile("json", Pattern.CASE_INSENSITIVE);
//		Matcher m2 = p2.matcher(args[0]);
//		if (m2.matches()) {
			// String jsonfile = args[1]; 
			String jsonfile = "parking2.json"; 
			ParkingJSONReader parkingJSONReader = new ParkingJSONReader(jsonfile);
			properArguments = true; 
			ArrayList<Parking> parkingViolations2 = parkingJSONReader.getParkingViolations();
//		}
			
			
//		if (properArguments == false) {
//			System.out.println("Error");
//			System.exit(0); 
//		}

		
		// Step 2. Create Property CSV Reader
		// String csvfile = args[2];
		String csvfile = "propertiesv2.csv";
		PropertyCSVReader propertyCSVReader = new PropertyCSVReader(csvfile);
		ArrayList<Property> properties = propertyCSVReader.getProperty();
		
		
		// Step 3. Create Population TXT Reader
		// String txtfile = args[3];
		String txtfile = "population2.txt";
		PopulationTXTReader populationTXTReader = new PopulationTXTReader (txtfile);
		ArrayList<Population> populations = populationTXTReader.getPopulation(); 
		
		

		

	}

}
