package edu.upenn.cit594.datamanagement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import edu.upenn.cit594.data.ParkingViolation;

/**
 * Reads a csv file containing information on parking violations
 * 
 */

public class ParkingCSVReader implements ParkingReader {

	protected String filename;

	public ParkingCSVReader(String name) {

		filename = name;
	}

	@Override
	public List<ParkingViolation> getParkingViolations() {
		List<ParkingViolation> parkingViolations = new ArrayList<ParkingViolation>();
		Scanner in = null;
		try {
			in = new Scanner(new FileReader(filename));
			while (in.hasNext()) {
				String parkingLine = in.nextLine();
				String[] parkingDetails = parkingLine.split(",");

				if (parkingDetails.length == 7) {
					// Read data
					String zipCode = parkingDetails[6];
					String fine = parkingDetails[1];
					String state = parkingDetails[4];

					// Check for any missing data in the required fields - only create new ParkingViolation if all info are valid
					if(!zipCode.isEmpty() && !state.isEmpty()) {
						parkingViolations.add(new ParkingViolation(zipCode, Double.parseDouble(fine), state));
					}
				}
			}
			in.close();
			return parkingViolations;
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException: Parking input file not found.");
		} catch (SecurityException e1) {
			System.out.println("SecurityException: Parking input file cannot be opened.");
		}
		return null;
	}
}
