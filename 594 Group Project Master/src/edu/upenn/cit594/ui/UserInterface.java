package edu.upenn.cit594.ui;

import java.math.BigDecimal;
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

	protected Map<String, Result> Results = new HashMap<String, Result>();

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
				"'6' to display average parking fines and highest market value for a specified ZIP Code\n" +
				"'0' to exit.");
		System.out.println("\nYour selection: ");

		while (true) {

			String choice = in.next();

			// Log current time and user selection
			l.log(System.currentTimeMillis());
			l.log("User Selection:" + choice);

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
				doAverageParkingFinesAndHighestMarketValuePerZipCode(); 
			}
			
			else {
				throw new IllegalArgumentException("Invalid input! Input must be an integer 1-6, or 0 to exit program.");
			}

			System.out.println("\nPlease select another option from the list above [1-6], or 0 to exit: ");
		}
		in.close();
	}

	protected void doPopulationForAllZipCodes() {
		
		// If computation was not performed before
		if (!Results.containsKey("1")) {
			int sum = populationProcessor.getTotalPopulation();
			Results.put("1", new Result<Integer>(sum));
		}
		
		// Get computed answer from Results
		int result = (int) Results.get("1").getResult();
		System.out.println(result);
	}

	protected void doTotalParkingFinesPerCapita() {
		
		// If computation was not performed before
		if (!Results.containsKey("2")) {
			HashMap<String,Double> totalParkingFinesPerCapita = parkingProcessor.getTotalFinesPerCapitaForAllPAZipCodes();
			Map<String,Double> sortedTotalParkingFinesPerCapita = new TreeMap<String,Double>(totalParkingFinesPerCapita);
			Results.put("2", new Result<Map<String,Double>>(sortedTotalParkingFinesPerCapita));
		}
		
		// Get computed answer from Results
		Map<String,Double> resultsMap = (Map<String, Double>) Results.get("2").getResult();
		for (String zipCode : resultsMap.keySet()) {
			double fines = resultsMap.get(zipCode);
			System.out.println(zipCode + " " + truncateDecimal(fines,4));
		}
	}

	protected void doAverageMarketValueForResidences() {
		
		String zipCode = promptUserForZipCode();
		HashMap<String,Double> resultsMap = new HashMap<>();
		
		// If computation was not performed before
		if (!Results.containsKey("3")) {
			double averageMarketValue = propertyProcessor.getAverageMarketValue(zipCode);
			resultsMap.put(zipCode,averageMarketValue);
			Results.put("3", new Result<Map<String,Double>>(resultsMap));
		} 
		
		// If computation was performed before, check if it was done for the current input ZIP Code
		else {
			resultsMap = (HashMap<String, Double>) Results.get("3").getResult();
		
			// If computation was not performed before for the current input ZIP Code
			if (!resultsMap.containsKey(zipCode)) {
				double averageMarketValue = propertyProcessor.getAverageMarketValue(zipCode);
				resultsMap.put(zipCode,averageMarketValue);
				Results.get("3").setResult(resultsMap);
			}
		}
		
		// Get computed answer from Results
		double result = resultsMap.get(zipCode);
		System.out.println(truncateDecimal(result,0));
	}

	protected void doAverageLivableAreaForResidences() {
		String zipCode = promptUserForZipCode();
		HashMap<String,Double> resultsMap = new HashMap<>();
		
		// If computation was not performed before
		if (!Results.containsKey("4")) {
			double averageLivableArea= propertyProcessor.getAverageLivableArea(zipCode);
			resultsMap.put(zipCode,averageLivableArea);
			Results.put("4", new Result<Map<String,Double>>(resultsMap));
		} 
		
		// If computation was performed before, check if it was done for the current input ZIP Code
		else {
			resultsMap = (HashMap<String, Double>) Results.get("4").getResult();
		
			// If computation was not performed before for the current input ZIP Code
			if (!resultsMap.containsKey(zipCode)) {
				double averageLivableArea = propertyProcessor.getAverageLivableArea(zipCode);
				resultsMap.put(zipCode,averageLivableArea);
				Results.get("4").setResult(resultsMap);
			}
		}
		
		// Get computed answer from Results
		double result = resultsMap.get(zipCode);
		System.out.println(truncateDecimal(result,0));
	}

	protected void doTotalResidentialMarketValuePerCapita() {
		String zipCode = promptUserForZipCode();
		HashMap<String,Double> resultsMap = new HashMap<>();
		
		// If computation was not performed before
		if (!Results.containsKey("5")) {
			double totalResidentialMarketValuePerCapita = propertyProcessor.getTotalMarketValuePerCapita(zipCode);
			resultsMap.put(zipCode,totalResidentialMarketValuePerCapita);
			Results.put("5", new Result<Map<String,Double>>(resultsMap));
		} 
		
		// If computation was performed before, check if it was done for the current input ZIP Code
		else {
			resultsMap = (HashMap<String, Double>) Results.get("5").getResult();
		
			// If computation was not performed before for the current input ZIP Code
			if (!resultsMap.containsKey(zipCode)) {
				double totalResidentialMarketValuePerCapita = propertyProcessor.getAverageLivableArea(zipCode);
				resultsMap.put(zipCode,totalResidentialMarketValuePerCapita);
				Results.get("5").setResult(resultsMap);
			}
		}
		
		// Get computed answer from Results
		double result = resultsMap.get(zipCode);
		System.out.println(truncateDecimal(result,0));
	}


	protected void doAverageParkingFinesAndHighestMarketValuePerZipCode() {
		String zipCode = promptUserForZipCode();
		HashMap<String,Double[]> resultsMap = new HashMap<>();
		
		// If computation was not performed before
		if (!Results.containsKey("6")) {
			Double[] values = new Double[2];
			values[0] = parkingProcessor.getAverageParkingFinePerZipCode(zipCode);
			values[1] = propertyProcessor.getHighestMarketValue(zipCode);
			resultsMap.put(zipCode,values);
			Results.put("6", new Result<Map<String,Double[]>>(resultsMap));
		} 
		// If computation was performed before, check if it was done for the current input ZIP Code
		else {
			resultsMap = (HashMap<String, Double[]>) Results.get("6").getResult();
		
			// If computation was not performed before for the current input ZIP Code
			if (!resultsMap.containsKey(zipCode)) {
				Double[] values = new Double[2];
				values[0] = parkingProcessor.getAverageParkingFinePerZipCode(zipCode);
				values[1] = propertyProcessor.getHighestMarketValue(zipCode);
				resultsMap.put(zipCode,values);
				Results.get("6").setResult(resultsMap);
			}
		}
					
		// Get computed answer from Results
		Double[] results = resultsMap.get(zipCode);
		System.out.println(truncateDecimal(results[0],4) + "\t" + truncateDecimal(results[1],0));
	}
	

	protected String promptUserForZipCode() {
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

	protected BigDecimal truncateDecimal(double inputValue ,int decimalPlaces)	{
		if (inputValue > 0) {
			return new BigDecimal(String.valueOf(inputValue)).setScale(decimalPlaces, BigDecimal.ROUND_FLOOR);
		} else {
			return new BigDecimal(String.valueOf(inputValue)).setScale(decimalPlaces, BigDecimal.ROUND_CEILING);
		}
	}

}
