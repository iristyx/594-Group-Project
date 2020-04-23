package edu.upenn.cit594.datamanagement;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.data.Population;

/**
 * 
 * 
 */

public class ParkingJSONReader implements Reader {

	ArrayList<Parking> parkingViolations = new ArrayList<>();

	public ParkingJSONReader(String filename) {

		// create a parser
		JSONParser parser = new JSONParser();
		// open the file and get the array of JSON objects
		JSONArray strings = null;

		try {
			strings = (JSONArray)parser.parse(new FileReader(filename));
		} catch (IOException | ParseException e) {
			System.out.println("Error");
			System.exit(0);
		}

		// use an iterator to iterate over each element of the array
		Iterator iter = strings.iterator();

		// iterate while there are more objects in array
		while (iter.hasNext()) {
			JSONObject X = (JSONObject) iter.next();

			Object string; 

			string = X.get("ticket_number");
			String uniqueIdentifier = string.toString();

			string = X.get("plate_id");
			String anonymousIdentifier = string.toString();

			string = X.get("date");
			String timeStamp = string.toString();

			string = X.get("zip_code");
			String zipCode = string.toString();

			string = X.get("violation");
			String description = string.toString();

			string = X.get("fine");
			String fine = string.toString();

			string = X.get("state");
			String state = string.toString();

			// Use to test ParkingCSVReader 
//			System.out.println(timeStamp);
//			System.out.println(fine);
//			System.out.println(description);
//			System.out.println(anonymousIdentifier);
//			System.out.println(state);
//			System.out.println(uniqueIdentifier);
//			System.out.println(zipCode);
//			System.out.println(" ");
			
			parkingViolations.add(new Parking(timeStamp, fine, description, anonymousIdentifier, state, uniqueIdentifier, zipCode));
		}


	}

	public ArrayList<Parking> getParkingViolations() {
		return parkingViolations;
	}

}
