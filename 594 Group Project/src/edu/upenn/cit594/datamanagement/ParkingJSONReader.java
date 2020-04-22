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

	protected List<Parking> parkingViolations = new ArrayList<Parking>();	
	
	public ParkingJSONReader(String filename) {
		
	}
	
	public List<Parking> getParkingViolations() {
		return parkingViolations;
	}
	
}
