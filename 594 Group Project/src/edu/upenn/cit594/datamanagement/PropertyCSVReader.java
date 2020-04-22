package edu.upenn.cit594.datamanagement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import edu.upenn.cit594.data.Parking;

/**
 * 
 * 
 */

public class PropertyCSVReader {

	protected List<Parking> properties = new ArrayList<Parking>();	

	public PropertyCSVReader(String filename) { 	
	
	}

	public List<Parking> getProperty() {
		return properties;
	}

}


