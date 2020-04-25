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

		Logger logger = Logger.getInstance();

		if (!properties.isEmpty()) {
			return properties;

		} else {

			try {
				BufferedReader br = new BufferedReader(new FileReader(filename));
				String line = null;
				
				// Log current time and name of file that is opened
				logger.log(System.currentTimeMillis());
				logger.log(filename);

				String headers;
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
				while ((line = br.readLine()) != null) {

					String[] propertyDetails = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

					// Read data
					String marketValue = propertyDetails[marketValueIndex];
					String livableArea = propertyDetails[livableAreaIndex];
					String zipCode = propertyDetails[zipCodeIndex];	
					String fiveDigitsZipCode = getValidFiveDigitsZipCode(zipCode);

					
					// Check if marketValue and livableArea contain parsable values, and that zipCode has valid five digits ZIP Code
					if ((!marketValue.isEmpty()) && (!livableArea.isEmpty()) && (fiveDigitsZipCode != null)) {
						try {
							properties.add(new Property(Double.parseDouble(marketValue), Double.parseDouble(livableArea), fiveDigitsZipCode));
						} catch (Exception e) {
						}
					}
				}

				br.close();
				return properties;

			} catch (IOException e) {
				System.out.println("IOException: Properties input file cannot be opened.");
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

	private String getValidFiveDigitsZipCode(String zipCode) {
		if (hasValidFiveDigitsZipCode(zipCode)) {
			return zipCode.substring(0, 5);
		} 
		return null;
	}

	private boolean hasValidFiveDigitsZipCode(String zipCode) {

		try {
			int d = Integer.parseInt(zipCode.substring(0, 5));
			if (d > 9999 && d < 100000) {
				return true;
			}
		}
		catch (Exception e) {}
		return false;
	}
	

}
