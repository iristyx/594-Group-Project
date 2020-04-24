package edu.upenn.cit594.junittest;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.ParkingReader;
import edu.upenn.cit594.datamanagement.ParkingReaderCreator;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertyReader;
import edu.upenn.cit594.processor.PopulationProcessor;

public class MethodTest {

	@Test
	/*
	 * Check that ParkingReaders are reading all the data properly, total 20413 valid parking violations
	 */
	public void ParkingReaderTest() {
		String format = "csv";
		String filename = "parking.csv";
		ParkingReaderCreator parkingReaderCreator = new ParkingReaderCreator(format, filename); // try to implement singleton late
		ParkingReader parkingReader = parkingReaderCreator.createParkingReader();
		List<ParkingViolation> parkingViolations = parkingReader.getParkingViolations();
		assertEquals(parkingViolations.size(),20413);
	}
	
	@Test
	/*
	 * Check that PropertyReader is reading all the data properly, total 581221 properties with required information and valid ZIP Codes (52 unique counts)
	 */
	public void PropertyReaderTest() {
		String filename = "properties.csv";
		PropertyReader propertyReader = new PropertyReader(filename);
		List<Property> validProperties = propertyReader.getProperties();
		Set<String> validZipCodes = propertyReader.getZipCodes();
		assertEquals(validProperties.size(),581221);
		assertEquals(validZipCodes.size(),52);
	}

	@Test
	/*
	 * Check that PopulationReader is reading all the data properly, 48 rows in total
	 */
	public void PopulationReaderTest() {
		String filename = "population.txt";
		PopulationReader populationReader = new PopulationReader(filename);
		Set<String> validZipCodes = populationReader.getZipCodes();
		assertEquals(validZipCodes.size(),48);
	}
	
	@Test
	/*
	 * 1. Total Population for all ZIP Codes
	 */
	public void Task1() {
		PopulationReader popRead = new PopulationReader("population.txt");
		PopulationProcessor popProc = new PopulationProcessor(popRead);
		int total = popProc.getTotalPopulation();
		assertEquals(total,1526206);
	}
}
	
