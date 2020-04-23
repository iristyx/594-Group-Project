package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.Property;

public class AveragePropertyMarketValueCalculator implements AveragePropertyCalculator {

	/*
	 * Return total market value of all residences over the number of residences in a given ZIP code
	 */
	@Override
	public double getAverage(String zipCode, List<Property> properties) {
		double marketValue = 0; 
		double propertyCount = 0; 
		
		for (Property property : properties) {
			if (property.getZipCode().equals(zipCode)) {
				propertyCount += 1; 
				marketValue += property.getMarketValue(); 
			}
		}
		
		double averageMarketValue = marketValue / propertyCount; 
			
		return averageMarketValue;
	}

}
