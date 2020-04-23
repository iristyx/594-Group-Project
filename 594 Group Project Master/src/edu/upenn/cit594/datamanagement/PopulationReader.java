package edu.upenn.cit594.datamanagement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import edu.upenn.cit594.logging.Logger;

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
		
		Logger l = Logger.getInstance();
		
		if (!populations.isEmpty()) {
			return populations;
		} else {
			
			Scanner in;
			try {
				in = new Scanner(new FileReader(filename));
				
				// Log current time and name of file that is opened
				l.log(System.currentTimeMillis());
				l.log(filename);
				
				while (in.hasNext()) {
					String population = in.nextLine();
					String[] populationDetails = population.split(" ");
					if(populationDetails.length == 2) {
						String zipCode = populationDetails[0];
						String populationNum = populationDetails[1];
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
	
	public Set<String> getZipCodes(){
		return populations.keySet();
	}
	
}
