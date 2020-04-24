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

	protected Map<String, Result> Results = new HashMap<>();

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
				// Memoization 
				if (Results.containsKey("1")) {
					System.out.println(Results.get("1").getSum());
				}
				else {
					doPopulationForAllZipCodes();
				}
			} 
			else if (choice == 2) {
				// Memoization 
				if (Results.containsKey("2")) {
					System.out.println(Results.get("2"));
					
					for (String zipCode : Results.get("2").getSortedTotalParkingFinesPerCapita().keySet()) {
						double fines = Results.get("2").getSortedTotalParkingFinesPerCapita().get(zipCode);
						System.out.println(zipCode + " " + truncateDecimal(fines,4));
					}
				}
				else {
					doTotalParkingFinesPerCapita();
				}
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
				doAverageParkingFinesAndHighestMarketValuePerZipCode(); 
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
		
		// Put into memoization hashmap 
		Result result = new Result (sum, null, null, null, null, null, null); 
		Results.put("1", result);
		
		System.out.println(sum);
	}

	protected void doTotalParkingFinesPerCapita() {
		HashMap<String,Double> totalParkingFinesPerCapita = parkingProcessor.getTotalFinesPerCapitaForAllPAZipCodes();
		Map<String,Double> sortedTotalParkingFinesPerCapita = new TreeMap<String,Double>(totalParkingFinesPerCapita);
		
		// Put into memoization hashmap 
		Result result = new Result (0, sortedTotalParkingFinesPerCapita, null, null, null, null, null); 
		Results.put("2", result);
		
		for (String zipCode : sortedTotalParkingFinesPerCapita.keySet()) {
			double fines = sortedTotalParkingFinesPerCapita.get(zipCode);
			System.out.println(zipCode + " " + truncateDecimal(fines,4));
		}
		
	}

	protected void doAverageMarketValueForResidences() {
		String zipCode = promptUserForZipCode();
		
		// Memoization 
		if (Results.get("3").getAverageMarketValue().containsKey(zipCode)) {
			double result = Results.get("3").getAverageMarketValue().get(zipCode); 
			System.out.println(truncateDecimal(result,0));
		}
		
		if (!zipCode.equals("0")) {
			double result = propertyProcessor.getAverageMarketValue(zipCode);
		
		// Put into memoization hashmap 
			Map<String,Double> averageMarketValue = null; 
			averageMarketValue.put(zipCode, result); 
			Result resultObject = new Result (0, null, averageMarketValue, null, null, null, null); 
			Results.put("3", resultObject);
			
			System.out.println(truncateDecimal(result,0));
		} else {
			System.out.println("0");
		}
	}

	protected void doAverageLivableAreaForResidences() {
		String zipCode = promptUserForZipCode();
		
		// Memoization 
		if (Results.get("4").getAverageLivableArea().containsKey(zipCode)) {
			double result = Results.get("4").getAverageLivableArea().get(zipCode); 
			System.out.println(truncateDecimal(result,0));
		}
		
		if (!zipCode.equals("0")) {
			double result = propertyProcessor.getAverageLivableArea(zipCode);
			
		// Put into memoization hashmap 
			Map<String,Double> averageLivableArea = null; 
			averageLivableArea.put(zipCode, result); 
			Result resultObject = new Result (0, null, null, averageLivableArea, null, null, null); 
			Results.put("4", resultObject);
			
			System.out.println(truncateDecimal(result,0));
			
		} else {
			System.out.println(zipCode);
		}
	}

	protected void doTotalResidentialMarketValuePerCapita() {
		String zipCode = promptUserForZipCode();
		
		// Memoization 
		if (Results.get("5").getTotalMarketValuePerCapita().containsKey(zipCode)) {
			double result = Results.get("5").getTotalMarketValuePerCapita().get(zipCode); 
			System.out.println(truncateDecimal(result,0));
		}
		
		if (!zipCode.equals("0")) {
			double result = propertyProcessor.getTotalMarketValuePerCapita(zipCode);
			
		// Put into memoization hashmap 
			Map<String,Double> totalMarketValue = null; 
			totalMarketValue.put(zipCode, result); 
			Result resultObject = new Result (0, null, null, null, totalMarketValue, null, null); 
			Results.put("5", resultObject);
			
			System.out.println(truncateDecimal(result,0));
		} else {
			System.out.println("0");
		}
	}


	protected void doAverageParkingFinesAndHighestMarketValuePerZipCode() {
		String zipCode = promptUserForZipCode();
		
		// Memoization 
		if (Results.get("6").getHighestMarketValue().containsKey(zipCode)) {
			double highestMarketValue  = Results.get("6").getHighestMarketValue().get(zipCode);
			double averageParkingFine = Results.get("6").getAverageParkingFines().get(zipCode);
			System.out.println(truncateDecimal(highestMarketValue, 0));
			System.out.println(truncateDecimal(averageParkingFine, 0));
		}
		
		if (!zipCode.equals("0")) {

			double highestMarketValue = propertyProcessor.getHighestMarketValue(zipCode);
			double averageParkingFine = parkingProcessor.getAverageParkingFinePerZipCode(zipCode);

			// Put into memoization hashmap 
			Map<String,Double> highestMarketValueMap = null; 
			highestMarketValueMap.put(zipCode, highestMarketValue); 
			Map<String,Double> averageParkingFineMap = null; 
			averageParkingFineMap.put(zipCode, averageParkingFine); 	
			Result resultObject = new Result (0, null, null, null, null, highestMarketValueMap, averageParkingFineMap); 
			Results.put("6", resultObject);
			
			System.out.println(truncateDecimal(highestMarketValue, 0));
			System.out.println(truncateDecimal(averageParkingFine, 0));


		} else {
			System.out.println("0");
		}
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
