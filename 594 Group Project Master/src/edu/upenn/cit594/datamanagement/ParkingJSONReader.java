package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.logging.Logger;

/**
 * Reads a json file containing information on parking violations
 * 
 */

public class ParkingJSONReader implements ParkingReader {

	protected String filename;
	protected List<ParkingViolation> parkingViolations = new LinkedList<ParkingViolation>();

	public ParkingJSONReader(String name) {
		filename = name;
	}

	@Override
	public List<ParkingViolation> getParkingViolations() {

		Logger logger = Logger.getInstance();

		if (!parkingViolations.isEmpty()) {
			return parkingViolations;
		} else {
			JSONParser parser = new JSONParser();
			try {
				JSONArray parkingArray = (JSONArray) parser.parse(new FileReader(filename));

				// Log current time and name of file that is opened
				logger.log(System.currentTimeMillis());
				logger.log(filename);

				Iterator<?> iter = parkingArray.iterator();
				while (iter.hasNext()) {
					JSONObject parkingLine = (JSONObject) iter.next();

					// Extract relevant useful information
					String zipCode = (String) parkingLine.get("zip_code");
					long fine = (long) parkingLine.get("fine");
					String state = (String) parkingLine.get("state");

					// Check for any missing data in the required fields - only create new
					// ParkingViolation if all info are valid
					if (!zipCode.isEmpty() && !state.isEmpty()) {
						parkingViolations.add(new ParkingViolation(zipCode, fine, state));
					}

				}
				return parkingViolations;

			} catch (FileNotFoundException e) {
				System.out.println("FileNotFoundException: Parking input file not found.");
			} catch (IOException e1) {
				System.out.println("IOException: Parking input file cannot be opened.");
			} catch (ParseException e2) {
				System.out.println("ParseException: Parking input file cannot be read.");
			} catch (SecurityException e3) {
				System.out.println("SecurityException: Parking input file cannot be opened.");
			}

			return null;
		}
	}
}
