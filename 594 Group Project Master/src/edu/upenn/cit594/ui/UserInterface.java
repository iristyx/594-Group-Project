package edu.upenn.cit594.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


import edu.upenn.cit594.data.Result;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.ParkingProcessor;
import edu.upenn.cit594.processor.PopulationProcessor;
import edu.upenn.cit594.processor.PropertyProcessor;

public class UserInterface {

	protected PropertyProcessor propertyProcessor;
	protected PopulationProcessor populationProcessor;
	protected ParkingProcessor parkingProcessor;
	protected Scanner in;
	protected Map<String, Result> results = new HashMap<String, Result>();

	public UserInterface(ParkingProcessor parkingProcessor, PopulationProcessor populationProcessor, PropertyProcessor propertyProcessor) {
		this.propertyProcessor = propertyProcessor;
		this.populationProcessor = populationProcessor;
		this.parkingProcessor = parkingProcessor;
		in = new Scanner(System.in);
	}

	public void start() {

		Logger logger = Logger.getInstance();

		// Prompt user for input
		System.out.println("Please key in one of the numbers from the list below:\n\n" + 
				"'1' to display total population for all ZIP Codes\n" +
				"'2' to display total parking fines per capita for each ZIP Code\n" + 
				"'3' to display average market value for residences in a specified ZIP Code\n" +
				"'4' to display average total livable area for residences in a specified ZIP Code\n" +
				"'5' to display total residential market value per capita for a specified ZIP Code\n" +
				"'6' to display total market value over number of parking fines for a specified ZIP Code\n" +
				"'0' to exit.");
		System.out.println("Your selection: ");

		while (true) {

			String choice = in.next();

			// Log current time and user selection
			logger.log(System.currentTimeMillis());
			logger.log("User Selection:" + choice);

			if (choice.equals("0")) {
				break;
			}
			
			else if (choice.equals("1")) {
				doPopulationForAllZipCodes();
			} 
			
			else if (choice.equals("2")) {
				doTotalParkingFinesPerCapita();
			}
			
			else if (choice.equals("3")) {
				doAverageMarketValueForResidences();
			}
			
			else if (choice.equals("4")) {
				doAverageLivableAreaForResidences();
			}
			
			else if (choice.equals("5")) {
				doTotalResidentialMarketValuePerCapita();
			}
			
			else if (choice.equals("6")) {
				doTotalMarketValuePerFineByZipCode(); 
			}
			else {
				throw new IllegalArgumentException("Invalid input! Input must be an integer 1-6, or 0 to exit program.");
			}

			System.out.println("Please select another option from the list above [1-6], or 0 to exit: ");
		}
		in.close();
	}

	
	

	/**
	 * Task 1: Display the total population of all of the ZIP Codes in the population input file
	 * Should return 1526206 based on provided input file 'population.txt'
	 */
	protected void doPopulationForAllZipCodes() {
		
		// If computation was not performed before
		if (!results.containsKey("1")) {
			int sum = populationProcessor.getTotalPopulation();
			results.put("1", new Result<Integer>(sum));
		}
		
		// Get computed answer from Results
		int result = (int) results.get("1").getResult();
		System.out.println(result);
	}

	
	/**
	 * Task 2: Display the total fines per capita for each ZIP Code
	 * i.e. total aggregates fines divided by population of the ZIP Code
	 * ZIP Codes must be written to screen in ascending numerical order
	 * Total fines per capita must be displayed with precision of four decimal places (truncated, not rounded)
	 */
	protected void doTotalParkingFinesPerCapita() {
		
		// If computation was not performed before
		if (!results.containsKey("2")) {
			Map<String,Double> totalParkingFinesPerCapita = parkingProcessor.getTotalFinesPerCapitaForZipCodesInState("pa");
			Map<String,Double> sortedTotalParkingFinesPerCapita = new TreeMap<String,Double>(totalParkingFinesPerCapita);
			results.put("2", new Result<Map<String,Double>>(sortedTotalParkingFinesPerCapita));
		}
		
		// Get computed answer from Results
		Map<String,Double> resultsMap = (Map<String, Double>) results.get("2").getResult();
		for (String zipCode : resultsMap.keySet()) {
			double fines = resultsMap.get(zipCode);
			System.out.println(zipCode + " " + truncateDecimal(fines,4));
		}
	}

	
	/**
	 * Task 3: Display the average residential market value for user-specified ZIP Code
	 * i.e. total market value for all residences in the ZIP Code divided by the number of residences
	 * Result must be truncated to an integer, not rounded
	 * Display '0' if total residential market value for ZIP Code is 0 or if user inputs invalid ZIP Code
	 */
	protected void doAverageMarketValueForResidences() {
		
		String zipCode = promptUserForZipCode();
		HashMap<String,Double> resultsMap = new HashMap<>();
		
		// If computation was not performed before
		if (!results.containsKey("3")) {
			double averageMarketValue = propertyProcessor.getAverageMarketValue(zipCode);
			resultsMap.put(zipCode,averageMarketValue);
			results.put("3", new Result<Map<String,Double>>(resultsMap));
		} 
		
		// If computation was performed before, check if it was done for the current input ZIP Code
		else {
			resultsMap = (HashMap<String, Double>) results.get("3").getResult();
		
			// If computation was not performed before for the current input ZIP Code
			if (!resultsMap.containsKey(zipCode)) {
				double averageMarketValue = propertyProcessor.getAverageMarketValue(zipCode);
				resultsMap.put(zipCode,averageMarketValue);
				results.get("3").setResult(resultsMap);
			}
		}
		
		// Get computed answer from Results
		double result = resultsMap.get(zipCode);
		System.out.println(truncateDecimal(result,0));
	}

	
	
	/**
	 * Task 4: Display the average residential livable area for user-specified ZIP Code
	 * i.e. sum of total livable areas for all residences in the ZIP Code divided by the number of residences
	 * Result must be truncated to an integer, not rounded
	 * Display '0' if total residential livable area for ZIP Code is 0 or if user inputs invalid ZIP Code
	 */
	protected void doAverageLivableAreaForResidences() {
		String zipCode = promptUserForZipCode();
		HashMap<String,Double> resultsMap = new HashMap<>();
		
		// If computation was not performed before
		if (!results.containsKey("4")) {
			double averageLivableArea= propertyProcessor.getAverageLivableArea(zipCode);
			resultsMap.put(zipCode,averageLivableArea);
			results.put("4", new Result<Map<String,Double>>(resultsMap));
		} 
		
		// If computation was performed before, check if it was done for the current input ZIP Code
		else {
			resultsMap = (HashMap<String, Double>) results.get("4").getResult();
		
			// If computation was not performed before for the current input ZIP Code
			if (!resultsMap.containsKey(zipCode)) {
				double averageLivableArea = propertyProcessor.getAverageLivableArea(zipCode);
				resultsMap.put(zipCode,averageLivableArea);
				results.get("4").setResult(resultsMap);
			}
		}
		
		// Get computed answer from Results
		double result = resultsMap.get(zipCode);
		System.out.println(truncateDecimal(result,0));
	}

	
	
	/**
	 * Task 5: Display the total residential market value per capita for user-specified ZIP Code
	 * Result must be truncated to an integer, not rounded
	 * Display '0' if total residential market value for ZIP Code is 0 or if user inputs invalid ZIP Code
	 */
	protected void doTotalResidentialMarketValuePerCapita() {
		String zipCode = promptUserForZipCode();
		HashMap<String,Double> resultsMap = new HashMap<>();
		
		// If computation was not performed before
		if (!results.containsKey("5")) {
			double totalResidentialMarketValue = propertyProcessor.getTotalMarketValuePerCapita(zipCode);
			resultsMap.put(zipCode,totalResidentialMarketValue);
			results.put("5", new Result<Map<String,Double>>(resultsMap));
		}

		// If computation was performed before, check if it was done for the current input ZIP Code
		else {
			resultsMap = (HashMap<String, Double>) results.get("4").getResult();
					
			// If computation was not performed before for the current input ZIP Code
			if (!resultsMap.containsKey(zipCode)) {
				double totalResidentialMarketValue = propertyProcessor.getTotalMarketValuePerCapita(zipCode);
				resultsMap.put(zipCode,totalResidentialMarketValue);
				results.put("5", new Result<Map<String,Double>>(resultsMap));
			}
			
		}
		
		// Get computed answer from Results
		double result = resultsMap.get(zipCode);
		System.out.println(truncateDecimal(result,0));
	}


	/**
	 * Task 6: Display the residential market value per capita over number of fines for a user-specified ZIP Code
	 * Result truncated to an integer, not rounded
	 * Display '0' if total residential market value for ZIP Code is 0, if number of fines is 0, or if user inputs invalid ZIP Code
	 */
	protected void doTotalMarketValuePerFineByZipCode() {

		String zipCode = promptUserForZipCode();
		HashMap<String,Double> resultsMap = new HashMap<>();
		
		// If computation was not performed before
		if (!results.containsKey("6")) {
			double perCapitaResidentialMarketValue = propertyProcessor.getTotalMarketValuePerCapita(zipCode);
			double fineCount = parkingProcessor.getNumberOfFinesByZipCode(zipCode);
			double perCapitaMarketValueOverNumberOfFines;
			
			
			// If there are no fines for the ZIP Code
			if (fineCount == 0) {
				perCapitaMarketValueOverNumberOfFines = 0;
			}
			
			perCapitaMarketValueOverNumberOfFines = perCapitaResidentialMarketValue / fineCount; 
			
			resultsMap.put(zipCode,perCapitaMarketValueOverNumberOfFines);
			results.put("6", new Result<Map<String,Double>>(resultsMap));
		}

		// If computation was performed before, check if it was done for the current input ZIP Code
		else {
			resultsMap = (HashMap<String, Double>) results.get("6").getResult();
					
			// If computation was not performed before for the current input ZIP Code
			double perCapitaResidentialMarketValue = propertyProcessor.getTotalMarketValuePerCapita(zipCode);
			double fineCount = parkingProcessor.getNumberOfFinesByZipCode(zipCode);
			double perCapitaMarketValueOverNumberOfFines = perCapitaResidentialMarketValue / fineCount; 
			
			resultsMap.put(zipCode,perCapitaMarketValueOverNumberOfFines);
			results.put("6", new Result<Map<String,Double>>(resultsMap));
			
		}
		
		// Get computed answer from Results
		double result = resultsMap.get(zipCode);
		System.out.println(truncateDecimal(result,0));
		
		
	}

	private String promptUserForZipCode() {
		Logger l = Logger.getInstance();
		System.out.print("Please input a 5-digit ZIP Code: ");
		String zipCode = in.next();
		if (zipCode.length() != 5) {
			return "0";
		}

		// Log current time and user's ZIP Code input
		l.log(System.currentTimeMillis());
		l.log(zipCode);

		return zipCode;
	}

	private BigDecimal truncateDecimal(double inputValue ,int decimalPlaces)	{
		if (inputValue > 0) {
			return new BigDecimal(String.valueOf(inputValue)).setScale(decimalPlaces, BigDecimal.ROUND_FLOOR);
		} else {
			return new BigDecimal(String.valueOf(inputValue)).setScale(decimalPlaces, BigDecimal.ROUND_CEILING);
		}
	}

}
