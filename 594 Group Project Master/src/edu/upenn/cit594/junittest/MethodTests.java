package edu.upenn.cit594.junittest;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import edu.upenn.cit594.datamanagement.ParkingCSVReader;
import edu.upenn.cit594.datamanagement.ParkingJSONReader;
import edu.upenn.cit594.datamanagement.ParkingReader;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.processor.ParkingProcessor;
import edu.upenn.cit594.processor.PopulationProcessor;

public class MethodTests {

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
	
	@Test
	/*
	 * 2. Total Fines Per Capita, json reader
	 */
	public void Task2JSON() {
		PopulationReader popRead = new PopulationReader("population.txt");
		ParkingReader parkRead = new ParkingJSONReader("parking.json");
		ParkingProcessor parkProc = new ParkingProcessor(parkRead,popRead);
		
		Set<String> zipCodes = parkProc.getPAZipCodes();
		for (String zipCode : zipCodes) {
			double finesPerCapita = parkProc.getFinesPerCapitaByZipCode(zipCode);
			if (finesPerCapita != 0) {
				// Print in 4 d.p.
				String strFinesPerCapita = String.format("%.4f", finesPerCapita);
				System.out.println(zipCode + " " + strFinesPerCapita);
			}
		}
//		fail("Not yet implemented");
	}
	
	@Test
	/*
	 * 2. Total Fines Per Capita, csv reader
	 */
	public void Task2CSV() {
		PopulationReader popRead = new PopulationReader("population.txt");
		ParkingReader parkRead = new ParkingCSVReader("parking.csv");
		ParkingProcessor parkProc = new ParkingProcessor(parkRead,popRead);
		
		Set<String> zipCodes = parkProc.getPAZipCodes();
		for (String zipCode : zipCodes) {
			double finesPerCapita = parkProc.getFinesPerCapitaByZipCode(zipCode);
			if (finesPerCapita != 0) {
				// Print in 4 d.p.
				String strFinesPerCapita = String.format("%.4f", finesPerCapita);
				System.out.println(zipCode + " " + strFinesPerCapita);
			}
//		fail("Not yet implemented");
		}
	}

}
