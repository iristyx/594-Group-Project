package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

	/*
	 * Return list of parking violations by parsing data from input file
	 */
	@Override
	public List<ParkingViolation> getParkingViolations() {

		Logger logger = Logger.getInstance();

		if (!parkingViolations.isEmpty()) {
			return parkingViolations;
		} else {
			try {
				BufferedReader br = new BufferedReader(new FileReader(filename));
				String line = null;

				// Log current time and name of file that is opened
				logger.log(System.currentTimeMillis());
				logger.log(filename);

				while ((line = br.readLine()) != null) {
					String[] parkingDetails = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

					if (parkingDetails.length == 7) {
						// Read data
						String fine = parkingDetails[1];
						String state = parkingDetails[4];
						String zipCode = parkingDetails[6];

						// Check for any missing data in the required fields - only create new
						// ParkingViolation if all info are valid
						if (!zipCode.isEmpty() && !state.isEmpty()) {
							parkingViolations.add(new ParkingViolation(zipCode, Double.parseDouble(fine), state));
						}
					}
				}
				br.close();
				return parkingViolations;
			} catch (FileNotFoundException e) {
				System.out.println("FileNotFoundException: Parking input file not found.");
			}
			catch (IOException e) {
				System.out.println("IOException: Parking input file not found.");
			} catch (SecurityException e1) {
				System.out.println("SecurityException: Parking input file cannot be opened.");
			}
			return null;
		}
	}
}
