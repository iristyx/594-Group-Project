package edu.upenn.cit594.datamanagement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.upenn.cit594.data.Population;

/**
 * 
 * 
 */

public class PopulationTXTReader implements Reader {

	ArrayList<Population> populations = new ArrayList<Population>();

	public PopulationTXTReader(String filename) {

		try {	
			Scanner s = new Scanner(new FileReader(filename));	
			
			while (s.hasNextLine()) {	
				int count = 0;
				String nextLine = s.nextLine();
				String[] string = nextLine.split(" ");
				
				String zipCode = string[0]; 
				String population = string[1]; 
	
				// Test class
//				System.out.println(zipCode + " " + population);
				
				populations.add(new Population(zipCode, population));	
			}		
			
		} catch (FileNotFoundException e) {
			System.out.println("Error");
			System.exit(0);
		}

	}



	public ArrayList<Population> getPopulation() {
		return populations;
	}

}
