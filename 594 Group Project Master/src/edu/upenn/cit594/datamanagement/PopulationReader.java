package edu.upenn.cit594.datamanagement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Reads a csv file containing information on population by zip code
 * 
 */

public class PopulationReader {
	
	protected String filename;
	protected HashMap<String,Integer> populations = new HashMap<String,Integer>();
	
	
	public PopulationReader(String name) {
		filename = name;
		populations = getPopulations();

	}
	
	public HashMap<String, Integer> getPopulations() {
		HashMap<String, Integer> populations = new HashMap<String,Integer>();
		Scanner in = null;
		try {
			in = new Scanner(new FileReader(filename));
			while (in.hasNext()) {
				String population = in.nextLine();
				String[] populationDetails = population.split(" ");
				String zipCode = populationDetails[0];
				String populationNum = populationDetails[1];

				// Check for any missing data in the required fields - only create new Population if all info are valid
				if(!zipCode.isEmpty() && !populationNum.isEmpty()) {
					populations.put(zipCode, Integer.parseInt(populationNum));
				}
			}
			in.close();
			return populations;
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException: Population input file not found.");
		} catch (SecurityException e1) {
			System.out.println("SecurityException: Population input file cannot be opened.");
		}
		return null;
	}
	
}
