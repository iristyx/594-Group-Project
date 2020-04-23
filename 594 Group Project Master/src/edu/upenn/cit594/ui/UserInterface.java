package edu.upenn.cit594.ui;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.ParkingProcessor;
import edu.upenn.cit594.processor.PopulationProcessor;
import edu.upenn.cit594.processor.PropertyProcessor;

public class UserInterface {

	protected PropertyProcessor propertyProcessor;
	protected PopulationProcessor populationProcessor;
	protected ParkingProcessor parkingProcessor;
	protected Scanner in;

	public UserInterface(ParkingProcessor parkingProcessor, PopulationProcessor populationProcessor, PropertyProcessor propertyProcessor) {
		this.propertyProcessor = propertyProcessor;
		this.populationProcessor = populationProcessor;
		this.parkingProcessor = parkingProcessor;
		in = new Scanner(System.in);
	}

	public void start() {
		
		Logger l = Logger.getInstance();
		
		// Prompt user for input
		System.out.println("Please key in one of the numbers from the list below:\n\n" + 
							"'1' to display total population for all ZIP Codes\n" +
							"'2' to display total parking fines per capita for each ZIP Code\n" + 
							"'3' to display average market value for residences in a specified ZIP Code\n" +
							"'4' to display average total livable area for residences in a specified ZIP Code\n" +
							"'5' to display total residential market value per capita for a specified ZIP Code\n" +
							"'6' to display CUSTOM FUNCTION WHICH DOES COMPUTATION USING ALL 3 INPUT FILES\n" +
							"'0' to exit.");
		System.out.println("Your selection: ");
		
		while (true) {
			
			int choice = in.nextInt();
			
			// Log current time and user selection
			l.log(System.currentTimeMillis());
			l.log("User Selection:" + choice);
			
			if (choice == 0) {
				break;
			}
			else if (choice == 1) {
				doPopulationForAllZipCodes();
			} 
			else if (choice == 2) {
				doTotalParkingFinesPerCapita();
			}
			else if (choice == 3) {
				doAverageMarketValueForResidences();
			}
			else if (choice == 4) {
				doAverageLivableAreaForResidences();
			}
			else if (choice == 5) {
				doTotalResidentialMarketValuePerCapita();
			}
			else if (choice == 6) {
				System.out.println("It's not ready, but stay tuned for our custom function!");
				//do some custom function
			}
			else {
				throw new IllegalArgumentException("Invalid input! Input must be an integer 1-6, or 0 to exit program.");
			}
			
			System.out.println("Please select another option from the list above [1-6], or 0 to exit: ");
		}
		in.close();
	}

	protected void doPopulationForAllZipCodes() {
		int sum = populationProcessor.getTotalPopulation();
		System.out.println(sum);
	}
	
	protected void doTotalParkingFinesPerCapita() {
		HashMap<String,Double> totalParkingFinesPerCapita = parkingProcessor.getTotalFinesPerCapitaForAllPAZipCodes();
		Map<String,Double> sortedTotalParkingFinesPerCapita = new TreeMap<String,Double>(totalParkingFinesPerCapita);
		for (String zipCode : sortedTotalParkingFinesPerCapita.keySet()) {
			System.out.println(zipCode + " " + sortedTotalParkingFinesPerCapita.get(zipCode));
		}
	}
	
	protected void doAverageMarketValueForResidences() {
		String zipCode = promptUserForZipCode();
		double result = propertyProcessor.getAverageMarketValue(zipCode);
		System.out.println(truncateDecimal(result,0));
	}
	
	protected void doAverageLivableAreaForResidences() {
		String zipCode = promptUserForZipCode();
		double result = propertyProcessor.getAverageLivableArea(zipCode);
		System.out.println(truncateDecimal(result,0));
	}
	
	protected void doTotalResidentialMarketValuePerCapita() {
		String zipCode = promptUserForZipCode();
		double result = propertyProcessor.getTotalMarketValuePerCapita(zipCode);
		System.out.println(truncateDecimal(result,0));
	}
	
	protected String promptUserForZipCode() {
		Logger l = Logger.getInstance();
		System.out.print("Please input a 5-digit ZIP Code: ");
		String zipCode = in.next();
		if (zipCode.length() != 5) {
			throw new IllegalArgumentException("Invalid ZIP Code. Input must be 5-digit.");
		}
		
		// Log current time and user's ZIP Code input
		l.log(System.currentTimeMillis());
		l.log(zipCode);
		
		return zipCode;
	}
	
	protected BigDecimal truncateDecimal(double inputValue ,int decimalPlaces)	{
	    if (inputValue > 0) {
	        return new BigDecimal(String.valueOf(inputValue)).setScale(decimalPlaces, BigDecimal.ROUND_FLOOR);
	    } else {
	        return new BigDecimal(String.valueOf(inputValue)).setScale(decimalPlaces, BigDecimal.ROUND_CEILING);
	    }
	}

}
