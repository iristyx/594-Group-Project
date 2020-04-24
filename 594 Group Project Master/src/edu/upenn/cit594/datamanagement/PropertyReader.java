package edu.upenn.cit594.datamanagement;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.logging.Logger;

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
		
		Logger l = Logger.getInstance();
		
		if (!properties.isEmpty()) {
			return properties;
		} else {
			try {
				BufferedReader br = new BufferedReader(new FileReader(filename));
				String line = null;
				
				String headers;
				try {
					headers = br.readLine();
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
					Pattern pattern = Pattern.compile("^(\\d{5})(.*)$");
					
					while ((line = br.readLine()) != null) {
									
						// Split into an array to ignore , within double " " 
						// Explanation: https://stackoverflow.com/questions/18893390/splitting-on-comma-outside-quotes
						// Test regex: https://regexr.com/3cddl
						String[] propertyDetails = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
						
						// Read data
						String marketValue = propertyDetails[marketValueIndex];
						String livableArea = propertyDetails[livableAreaIndex];
						String zipCode = propertyDetails[zipCodeIndex];	

						Matcher m = pattern.matcher(zipCode);
						if (m.matches() && !marketValue.isEmpty() && !livableArea.isEmpty()) {											
							properties.add(new Property(Double.parseDouble(marketValue), Double.parseDouble(livableArea), m.group(1)));
					
						}
					}
					br.close();
					return properties;
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			
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
	
}
