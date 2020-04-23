package edu.upenn.cit594.datamanagement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.data.Property;


/**
 * 
 * 
 */

public class PropertyCSVReader {

	ArrayList<Property> properties = new ArrayList<Property>();	
	ArrayList<String> columns = new ArrayList<>();
	ArrayList<Parking> parking = new ArrayList<>();
	
	int count = 0; 
	int market_value_index = 0;
	int total_livable_area_index = 0;
	int zip_code_index = 0; 
	
	
	public PropertyCSVReader(String filename) { 	



		try {
			Scanner s = new Scanner(new FileReader(filename));			
			while (s.hasNextLine()) {

				// Read column in the first row of CSV file
				if (columns.size() == 0) {
					
				
					
					String nextLine = s.nextLine();
					for (String a : nextLine.split(",")) {
						
//						System.out.println(a);
						
						if (a.contentEquals("market_value")) {
							market_value_index = count; 
						}
						
						if (a.contentEquals("total_livable_area")) {
							total_livable_area_index = count;
						}
						
						if (a.contentEquals("zip_code")) {
							zip_code_index = count; 
						}
						
						count++; 
						columns.add(a);
						
					}
					
//					System.out.println(" ");
//					System.out.println("market: " + market_value_index);
//					System.out.println("livable: " + total_livable_area_index);
//					System.out.println("zip: " + zip_code_index);
//					System.out.println(" ");
					
				} else { 
					// Add single Flight to the flight ArrayList 
					properties.add(parseProperty(s.nextLine()));
				}

			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}



	}



	public Property parseProperty(String s) {

		// Split into an array to ignore , within double " " 
		// Explanation: https://stackoverflow.com/questions/18893390/splitting-on-comma-outside-quotes
		// Test regex: https://regexr.com/3cddl
		String[] newRow = s.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

		// Fill in the blanks of the CSV file 
		// Set values in the CSV file as characteristics of Property 
		String marketValue = newRow[market_value_index ].length() == 0 ? "-11111" : newRow[market_value_index];
		String livableArea = newRow[total_livable_area_index].length() == 0 ? "-11111" : newRow[total_livable_area_index];
		String zipCode = newRow[zip_code_index].length() == 0 ? "-11111" : newRow[zip_code_index];

		
		// Use to test class
//		System.out.println(marketValue);
//		System.out.println(livableArea);
//		System.out.println(zipCode);
//		System.out.println(" ");
		
		// Form a new property 
		Property p = new Property(
				marketValue,
				livableArea,
				zipCode
				);

		// Return single parking violation 
		return p;

	}



	public ArrayList<Property> getProperty() {
		return properties;
	}

}


