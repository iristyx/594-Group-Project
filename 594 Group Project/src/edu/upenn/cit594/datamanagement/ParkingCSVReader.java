package edu.upenn.cit594.datamanagement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import edu.upenn.cit594.data.Parking;

/**
 * 
 * 
 */

public class ParkingCSVReader {

	protected List<Parking> parkingViolations = new ArrayList<Parking>();	

	public ParkingCSVReader(String filename) { 	
	
	}

	public List<Parking> getParkingViolations() {
		return parkingViolations;
	}

}


