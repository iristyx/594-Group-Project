package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.datamanagement.ParkingReader;
import edu.upenn.cit594.datamanagement.PopulationReader;

public class ParkingProcessor {
	
	protected PopulationReader populationReader;
	protected ParkingReader parkingReader;
	protected HashMap<String,Integer> populations;
	protected List<ParkingViolation> parkingViolations;
	
	public ParkingProcessor(ParkingReader parkingReader, PopulationReader populationReader) {
		this.populationReader = populationReader;
		this.parkingReader = parkingReader;
		this.populations = populationReader.getPopulations();
		this.parkingViolations = parkingReader.getParkingViolations();
		
	}

	/*
	 * Return value of total fines divided by population given a ZIP Code
	 * Returns 0 if population is zero
	 */
	public Double getFinesPerCapitaByZipCode(String zipCode) {
		int population = populations.get(zipCode);
		if (population == 0) {
			return 0.0;
		}
		double fines = getTotalFinesByZipCode(zipCode);
		double finesPerCapita = fines / population;
		return finesPerCapita;
		
	}
	
	/*
	 * Return a list of unique PA ZIP codes, with available information on population size
	 */
	public Set<String> getPAZipCodes(){
		
		Set<String> PAZipCodes = new HashSet<String>();
		for (ParkingViolation parking : parkingViolations) {
			if (parking.getState().toLowerCase().equals("pa")) {
				String zipCode = parking.getZipCode();
				
				// Check if information on population is available
				if (populations.containsKey(zipCode)){
					PAZipCodes.add(zipCode);
				}
			}
			
		}
		return PAZipCodes;
	}
	
	/*
	 * Private helper method: return sum of total fines in a given ZIP Code
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
