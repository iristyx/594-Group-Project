package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.ParkingReader;
import edu.upenn.cit594.datamanagement.PopulationReader;

public class ParkingProcessor {

	protected ParkingReader parkingReader;
	protected PopulationReader populationReader;
	protected List<ParkingViolation> parkingViolations;
	protected Map<String, Integer> populations;
	protected Set<String> populationZipCodes;
	protected Map<String, Set<String>> zipCodesByState = new HashMap<>();

	public ParkingProcessor(ParkingReader parkingReader, PopulationReader populationReader) {
		this.populationReader = populationReader;
		this.parkingReader = parkingReader;
		this.populations = populationReader.getPopulations();
		this.parkingViolations = parkingReader.getParkingViolations();
		this.populationZipCodes = populationReader.getZipCodes();


	}

	/*
	 * Return value of total fines divided by population given a ZIP Code Returns 0
	 * if population is zero
	 */
	public Map<String, Double> getTotalFinesPerCapitaForZipCodesInState(String stateAbbreviation) {
		Map<String, Double> finesZipCodeMap = new HashMap<String, Double>();
		Set<String> zipCodes = getStateZipCodes(stateAbbreviation);
	
		for (String zipCode : zipCodes) {
			
			// Check if there is population data for this ZIP Code
			if (populationZipCodes.contains(zipCode)) {
				int population = populations.get(zipCode);
				double totalFines = getTotalFinesByZipCode(zipCode);

				// Ignore zipCode if total aggregates fines is 0 or if population is 0
				if (population != 0 && totalFines != 0) {
					double totalFinesPerCapita = totalFines / population;
					finesZipCodeMap.put(zipCode, totalFinesPerCapita);
				}
			}
		}
		return finesZipCodeMap;

	}

	/*
	 * Return sum of number of fines in a given ZIP Code (ignore non PA values)
	 * Return 0 if input ZIP code is invalid
	 */
	public int getNumberOfPAFinesByZipCode(String zipCode) {
		int fineCount = 0;
		for (ParkingViolation parking : parkingViolations) {
			if (parking.getZipCode().equals(zipCode) && parking.getState().equalsIgnoreCase("pa")) {
				fineCount ++;
			}
		}

		return fineCount;
	}
	
	
	/*
	 * Return a list of unique ZIP codes based on input stateAbbreviation, with available information on
	 * population size
	 */
	private Set<String> getStateZipCodes(String stateAbbreviation) {

		// If ZIP Codes had not been generated for the input stateAbbreviation
		if (!zipCodesByState.containsKey(stateAbbreviation)) {
			Set<String> zipCodesInState = new HashSet<String>();
			for (ParkingViolation parking : parkingViolations) {
				if (parking.getState().equalsIgnoreCase(stateAbbreviation)) {
					zipCodesInState.add(parking.getZipCode());
				}	
			}
			zipCodesByState.put(stateAbbreviation,zipCodesInState);
		} 
		
		return zipCodesByState.get(stateAbbreviation);
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
