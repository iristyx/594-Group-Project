package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertyReader;

public class PropertyProcessor {

	protected PropertyReader propertyReader;
	protected PopulationReader populationReader;
	protected List<Property> properties;
	protected HashMap<String, Integer> populations;
	protected Set<String> propertyZipCodes;
	protected Set<String> populationZipCodes;
	protected Set<String> validZipCodes = new HashSet<String>();

	public PropertyProcessor(PropertyReader propertyReader, PopulationReader populationReader) {
		this.propertyReader = propertyReader;
		this.populationReader = populationReader;
		properties = propertyReader.getProperties();
		populations = populationReader.getPopulations();
		propertyZipCodes = propertyReader.getZipCodes();
		populationZipCodes = populationReader.getZipCodes();
		validZipCodes = getValidZipCodes();
	}

	/*
	 * Strategy design to return average market value of residences by ZIP Code
	 */
	public double getAverageMarketValue(String zipCode) {
		return getAverage(zipCode, new AveragePropertyMarketValueCalculator());
	}

	/*
	 * Strategy design to return average livable area of residences by ZIP Code
	 */
	public double getAverageLivableArea(String zipCode) {

		return getAverage(zipCode, new AveragePropertyLivableAreaCalculator());
	}

	/*
	 * Strategy design to return average value of residences by ZIP Code
	 */
	private double getAverage(String zipCode, AveragePropertyCalculator calculator) {
		// If ZIP Code is invalid
		if (!propertyZipCodes.contains(zipCode)) {
			return 0.0;
		} else {
			double average = calculator.getAverage(zipCode, properties);
			return average;
		}
	}

	public double getTotalMarketValuePerCapita(String zipCode) {
		
		// If ZIP Code is invalid
		if (!validZipCodes.contains(zipCode)) {
			return 0.0;
		} else {
			double marketValue = 0;
			int population = populations.get(zipCode);
			if (population == 0) {
				return 0.0;
			} else {
				for (Property property : properties) {
					if (property.getZipCode().equals(zipCode)) {
						marketValue += property.getMarketValue();
					}
				}
				double totalMarketValuePerCapita = marketValue / population;
				return totalMarketValuePerCapita;
			}
		}
	}

	private Set<String> getValidZipCodes() {

		if (validZipCodes.isEmpty()) {
			for (String zipCode : propertyZipCodes) {
				if (populationZipCodes.contains(zipCode)) {
					validZipCodes.add(zipCode);
				}
			}
		}
		return validZipCodes;
	}

}
