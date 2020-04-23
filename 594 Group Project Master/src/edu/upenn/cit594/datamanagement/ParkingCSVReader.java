package edu.upenn.cit594.datamanagement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.logging.Logger;

/**
 * Reads a csv file containing information on parking violations
 * 
 */

public class ParkingCSVReader implements ParkingReader {

	protected String filename;
	protected List<ParkingViolation> parkingViolations = new LinkedList<ParkingViolation>();

	public ParkingCSVReader(String name) {

		filename = name;
		parkingViolations = getParkingViolations();
	}

	@Override
	public List<ParkingViolation> getParkingViolations() {
		
		Logger l = Logger.getInstance();
		
		if (!parkingViolations.isEmpty()) {
			return parkingViolations;
		} else {
			Scanner in;
			try {
				in = new Scanner(new FileReader(filename));
				
				// Log current time and name of file that is opened
				l.log(System.currentTimeMillis());
				l.log(filename);
				
				while (in.hasNext()) {
					String parkingLine = in.nextLine();
					String[] parkingDetails = parkingLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
	
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
}
