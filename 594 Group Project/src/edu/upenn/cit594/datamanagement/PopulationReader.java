package edu.upenn.cit594.datamanagement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import edu.upenn.cit594.data.Coordinates;
import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Tweet;

/**
 * Reads a csv file containing information on population by zip code
 * 
 */

public class PopulationReader {
	
	protected String filename;
	protected List<Population> populations = new ArrayList<Population>();
	
	
	public PopulationReader(String name) {
		filename = name;
		populations = getPopulations();

	}
	
	public List<Population> getPopulations() {
		List<Population> populations = new ArrayList<Population>();
		Scanner in = null;
		try {
			in = new Scanner(new FileReader(filename));
			while (in.hasNext()) {
				String population = in.nextLine();
				String[] populationDetails = population.split("\t");
				String zipCode = populationDetails[0];
				String populationNum = populationDetails[1];

				// Check for any missing data in the required fields - only create new Population if all info are valid
				if(!zipCode.isEmpty() && !populationNum.isEmpty()) {
					populations.add(new Population(zipCode, Integer.parseInt(populationNum)));
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
