package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.ParkingReader;
import edu.upenn.cit594.datamanagement.PopulationReader;

public class ParkingProcessor {

	protected PopulationReader populationReader;
	protected ParkingReader parkingReader;
	protected HashMap<String, Integer> populations;
	protected List<ParkingViolation> parkingViolations;
	protected Set<String> PAZipCodes = new HashSet<String>();

	public ParkingProcessor(ParkingReader parkingReader, PopulationReader populationReader) {
		this.populationReader = populationReader;
		this.parkingReader = parkingReader;
		this.populations = populationReader.getPopulations();
		this.parkingViolations = parkingReader.getParkingViolations();

	}

	/*
	 * Return value of total fines divided by population given a ZIP Code Returns 0
	 * if population is zero
	 */
	public HashMap<String, Double> getTotalFinesPerCapitaForAllPAZipCodes() {
		HashMap<String, Double> finesZipCodeMap = new HashMap<String, Double>();
		Set<String> zipCodes = getPAZipCodes();
		for (String zipCode : zipCodes) {
			int population = populations.get(zipCode);
			double totalFines = getTotalFinesByZipCode(zipCode);

			// Ignore zipCode if total aggregates fines is 0 or if population is 0
			if (population != 0 && totalFines != 0) {
				double totalFinesPerCapita = totalFines / population;
				finesZipCodeMap.put(zipCode, totalFinesPerCapita);
			}
		}
		return finesZipCodeMap;

	}

	/*
	 * Return sum of number of fines in a given ZIP Code
	 * Return 0 if input ZIP code is invalid
	 */
	public double getNumberOfFinesByZipCode(String zipCode) {
		double fineCount = 0;
		for (ParkingViolation parking : parkingViolations) {
			if (parking.getZipCode().equals(zipCode)) {
				fineCount ++;
			}
		}
		return fineCount;
	}
	
	
	/*
	 * Return a list of unique PA ZIP codes, with available information on
	 * population size
	 */
	private Set<String> getPAZipCodes() {

		// If PAZipCodes is not yet generated
		if (PAZipCodes.isEmpty()) {
			for (ParkingViolation parking : parkingViolations) {
				if (parking.getState().toLowerCase().equals("pa")) {
					String zipCode = parking.getZipCode();
					// Check if information on population is available
					if (populations.containsKey(zipCode)) {
						PAZipCodes.add(zipCode);
					}
				}
			}
		}
		return PAZipCodes;
	}

	/*
	 * Private helper method: return sum of total fines in a given ZIP Code
	 * Return 0 of input ZIP Code is not invalid 
	 */
	private double getTotalFinesByZipCode(String zipCode) {
		double totalFines = 0;
		for (ParkingViolation parking : parkingViolations) {
			if (parking.getZipCode().equals(zipCode)) {
				totalFines += parking.getFine();
			}
		}
		return totalFines;
	}

	
	
}
