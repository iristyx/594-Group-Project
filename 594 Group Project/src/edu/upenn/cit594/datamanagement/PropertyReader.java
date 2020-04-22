package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Property;

/**
 * Reads a csv file containing information on properties
 * 
 */

public class PropertyReader {

	protected String filename;
	protected List<Property> properties = new ArrayList<Property>();

	public PropertyReader(String name) {
		filename = name;

	}

	public List<Property> getProperties() {
		List<Property> properties = new ArrayList<Property>();
		Scanner in = null;
		try {
			in = new Scanner(new FileReader(filename));

			// Read in the list of headers
			String headers = in.nextLine();
			String[] headerList = headers.split(",");

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
			while (in.hasNext()) {
				String propertyLine = in.nextLine();
				String[] propertyDetails = propertyLine.split(",");

				// Read data
				String marketValue = propertyDetails[marketValueIndex];
				String livableArea = propertyDetails[livableAreaIndex];
				String zipCode = propertyDetails[zipCodeIndex];

				// Check for any missing data in the required fields - only create new Property
				// if all info are valid
				if (!marketValue.isEmpty() && !livableArea.isEmpty() && !zipCode.isEmpty()) {
					properties.add(new Property(Integer.parseInt(marketValue), Integer.parseInt(livableArea), zipCode));
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
