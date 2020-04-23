package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Property;

public class PropertyProcessor {

	public PropertyProcessor() {

	}

	
	public double AverageMarketValuePerZipCode(String zipCode, List<Property> properties) {
		
		double marketValue = 0; 
		double propertyCount = 0; 
		double averageMarketValuePerZipCode = 0; 
		
		for (Property p : properties) {
			if (p.getZipCode().contentEquals(zipCode)) {
				propertyCount = propertyCount + 1; 
				double doubleValue = p.getMarketValue();
				marketValue = marketValue + doubleValue; 
			}
		}
		
		averageMarketValuePerZipCode = marketValue / propertyCount; 
		
		System.out.println((int) averageMarketValuePerZipCode);
		
		return averageMarketValuePerZipCode;
		
	}
	
	
	public double averageResidentialTotalLivableArea(String zipCode, List<Property> properties) {
		
		double livableArea = 0; 
		double propertyCount = 0; 
		double averageLivableAreaPerZipCode = 0; 
		
		for (Property p : properties) {
			if (p.getZipCode().contentEquals(zipCode)) {
				propertyCount = propertyCount + 1; 
				double doubleValue = p.getLivableArea();
				livableArea = livableArea + doubleValue; 
			}
		}
		
		averageLivableAreaPerZipCode = livableArea / propertyCount; 
		
		System.out.println((int) averageLivableAreaPerZipCode);
		
		return averageLivableAreaPerZipCode;
		
	}
	
	
	public double totalMarketValuePerCapita(String zipCode, List<Property> properties, HashMap<String, Integer> populations) {
		
		double marketValue = 0; 
		int population = 0; 
		double totalMarketValuePerCapita = 0; 
		
		for (Property p : properties) {
			if (p.getZipCode().contentEquals(zipCode)) {
				double doubleValue = p.getMarketValue();
				marketValue = marketValue + doubleValue; 
			}
		}
		
		for (String s : populations.keySet()) {
		
			if (s.contentEquals(zipCode)) {
				population = populations.get(s);	
			}
		}
		
		totalMarketValuePerCapita = marketValue / population; 
		
		System.out.println((int) totalMarketValuePerCapita);
		
		return totalMarketValuePerCapita;
		
	}
	
}
