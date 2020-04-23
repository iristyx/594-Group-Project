package edu.upenn.cit594.datamanagement;

import java.util.List;

import edu.upenn.cit594.data.ParkingViolation;

public interface ParkingReader {
	
	public List<ParkingViolation> getParkingViolations();
	
}
