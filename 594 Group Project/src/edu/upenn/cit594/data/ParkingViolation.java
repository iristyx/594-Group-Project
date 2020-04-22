package edu.upenn.cit594.data;

import java.util.Date;

public class ParkingViolation {

	Date timeStamp;
	String zipCode;
	Integer fine;
	String state;
	// String description;
	// String anonymousIdentifier;
	// String uniqueIdentifier;

	public ParkingViolation(Date timeStamp, String zipCode, Integer fine, String state) {
		super();
		this.timeStamp = timeStamp;
		this.zipCode = zipCode;
		this.fine = fine;
		this.state = state;
		// this.description = description;
		// this.anonymousIdentifier = anonymousIdentifier;
		// this.uniqueIdentifier = uniqueIdentifier;

	}

}
