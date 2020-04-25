package edu.upenn.cit594.processor;
import java.util.Map;

import edu.upenn.cit594.datamanagement.PopulationReader;

/*
 * Processor methods for population data
 */
public class PopulationProcessor {

	protected PopulationReader populationReader;
	protected Map<String,Integer> populations;
	
	public PopulationProcessor(PopulationReader populationReader) {
		this.populationReader = populationReader;
		this.populations = populationReader.getPopulations();
	}

	/*
	 * Returns the total sum of population for all ZIP Codes
	 */
	public int getTotalPopulation() {

		int sum = 0;
		for (String zipCode : populations.keySet()) {
			sum += populations.get(zipCode);
		}
		return sum;
	}
	
}
