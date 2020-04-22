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
 
	protected List<Population> population = new ArrayList<Population>();
	
	public PopulationTXTReader(String filename) {

	
	}
	
	public List<Population> getPopulation() {
		return population;
	}
	
}
