package edu.upenn.cit594.datamanagement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.processor.AveragePropertyCalculator;

/**
 * Reads a csv file containing information on properties
 * 
 */

public class PropertyReader {

	protected String filename;
	protected List<Property> properties = new LinkedList<Property>();
	protected Set<String> zipCodes = new HashSet<String>();

	public PropertyReader(String name) {
		filename = name;
		properties = getProperties();

	}

	public List<Property> getProperties() {
		if (!properties.isEmpty()) {
			return properties;
		} else {
			Scanner in;
			try {
				in = new Scanner(new FileReader(filename));
	
				// Read in the list of headers
				String headers = in.nextLine();
				String[] headerList = headers.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
	
				// Determine column positions of the required fields (market_value,
				// total_livable_area, zip_code)
				Integer marketValueIndex = 0;
				Integer livableAreaIndex = 0;
				Integer zipCodeIndex = 0;
	
				for (int i = 0; i < headerList.length; i++) {
					if (headerList[i].equals("market_value")) {
						marketValueIndex = i;
					} else if (headerList[i].equals("total_livable_area")) {
						livableAreaIndex = i;
					} else if (headerList[i].equals("zip_code")) {
						zipCodeIndex = i;
					}
				}
	
				// Read the rest of the data
				int count = 0;
				Pattern pattern = Pattern.compile("^(\\d{5})(.*)$");
				
				while (in.hasNext()) {
					String propertyLine = in.nextLine();
					
					// Split into an array to ignore , within double " " 
					// Explanation: https://stackoverflow.com/questions/18893390/splitting-on-comma-outside-quotes
					// Test regex: https://regexr.com/3cddl
					String[] propertyDetails = propertyLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
					
					// Read data
					String marketValue = propertyDetails[marketValueIndex];
					String livableArea = propertyDetails[livableAreaIndex];
					String zipCode = propertyDetails[zipCodeIndex];	
	
					// Check for any missing data in the required fields - only create new Property
					// if all info are valid
					
					if (!marketValue.isEmpty() && !livableArea.isEmpty()) {
						
						Matcher m = pattern.matcher(zipCode);
						if (m.matches()) {
							
							// Remove between comments, when submitting
							count += 1;
							if (count % 10000 == 0) {
								System.out.println("Import row "+ count+"... ");
							}
							// Remove up to here
							
							properties.add(new Property(Double.parseDouble(marketValue), Double.parseDouble(livableArea), m.group(1)));
						}
					}					
				}
	
				in.close();
				return properties;
				
			} catch (FileNotFoundException e) {
				System.out.println("FileNotFoundException: Properties input file not found.");
			} catch (SecurityException e1) {
				System.out.println("SecurityException: Properties input file cannot be opened.");
			}
			return null;
		}
	}
	
	public Set<String> getZipCodes() {
		
		// If set of ZIP Code was not yet generated
		if (zipCodes.isEmpty()) {	
			for (Property property : properties) {
				zipCodes.add(property.getZipCode());
			}
		}
		return zipCodes;
	}
	
//	public List<Property> getPropertyList(){
//		return properties;
//	}
	

}
