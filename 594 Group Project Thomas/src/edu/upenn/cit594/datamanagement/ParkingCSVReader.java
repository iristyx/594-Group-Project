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

	ArrayList<Parking> parkingViolations = new ArrayList<>();
	
	public ParkingCSVReader(String filename) { 	


		try {
			Scanner s = new Scanner(new FileReader(filename));			
			while (s.hasNextLine()) {

					// Add single Parking to the parking ArrayList 
					parkingViolations.add(parseParking(s.nextLine()));
				
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		
	}


	public Parking parseParking(String s) {

		// Split into an array
		String[] newRow = s.split(",");

		// Fill in the blanks of the CSV file 
		// Set values in the CSV file as characteristics of Flight
		String timeStamp = newRow[0].length() == 0 ? "-11111" : newRow[0];
		String fine = newRow[1].length() == 0 ? "-11111" : newRow[1];
		String description = newRow[2].length() == 0 ? "-11111" : newRow[2];
		String anonymousIdentifier = newRow[3].length() == 0 ? "-11111" : newRow[3];
		String state = newRow[4].length() == 0 ? "-11111" : newRow[4];
		String uniqueIdentifier = newRow[5].length() == 0 ? "-11111" : newRow[5];
		
		// If last column [6] is blank specifically, program crashes 
		String zipCode = null; 
		if(6 >= newRow.length){
			//index not exists
			zipCode = "-11111";
		}else{
			// index exists
			zipCode = newRow[6].length() == 0 ? "-11111" : newRow[6];
		}
		
		
		// Use to test ParkingCSVReader 
//		System.out.println(timeStamp);
//		System.out.println(fine);
//		System.out.println(description);
//		System.out.println(anonymousIdentifier);
//		System.out.println(state);
//		System.out.println(uniqueIdentifier);
//		System.out.println(zipCode);
//		System.out.println(" ");
		
		
		// Form a new Parking
		Parking p = new Parking(
				timeStamp,
				fine,
				description, 
				anonymousIdentifier,
				state, 
				uniqueIdentifier, 
				zipCode
			);
		
		// Return single parking violation 
		return p;

	}


	public ArrayList<Parking> getParkingViolations() {
		return parkingViolations;
	}

}


