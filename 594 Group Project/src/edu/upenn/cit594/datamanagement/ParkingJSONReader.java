package edu.upenn.cit594.datamanagement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.data.Population;


/**
 * Reads a json file containing information on parking violations
 * 
 */

public class ParkingJSONReader implements ParkingReader {

	protected String filename;

	public ParkingJSONReader(String name) {
		filename = name;
	}

	@Override
	public List<ParkingViolation> getParkingViolations() {
		List<ParkingViolation> parkingViolations = new ArrayList<ParkingViolation>();
		JSONParser parser = new JSONParser();
		try {
			JSONArray parkingArray = (JSONArray) parser.parse(new FileReader(filename));
			Iterator<?> iter = parkingArray.iterator();
			while (iter.hasNext()) {
				JSONObject parkingLine = (JSONObject) iter.next();

				// Extract relevant useful information
				String timestampString = (String) parkingLine.get("date");
				Date timestamp;
				try {
					timestamp = new SimpleDateFormat("YYYY-MM-DDThh:mm:ssZ").parse(timestampString);
					String zipCode = (String) parkingLine.get("zip_code");
					String fine = (String) parkingLine.get("fine");
					String state = (String) parkingLine.get("state");
					
					// Check for any missing data in the required fields - only create new ParkingViolation if all info are valid
					if(!zipCode.isEmpty() && !fine.isEmpty() && !state.isEmpty()) {
						parkingViolations.add(new ParkingViolation(timestamp, zipCode, Integer.parseInt(fine), state));
					}

				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
