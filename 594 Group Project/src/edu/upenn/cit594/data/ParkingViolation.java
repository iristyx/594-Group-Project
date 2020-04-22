package edu.upenn.cit594.data;

public class ParkingViolation {

	//	Date timeStamp;
	String zipCode;
	double fine;
	String state;
	// String description;
	// String anonymousIdentifier;
	// String uniqueIdentifier;

	public ParkingViolation(String zipCode, double fine, String state) {
		super();
		// this.timeStamp = timeStamp;
		this.zipCode = zipCode;
		this.fine = fine;
		this.state = state;
		// this.description = description;
		// this.anonymousIdentifier = anonymousIdentifier;
		// this.uniqueIdentifier = uniqueIdentifier;

	}

	public String getZipCode() {
		return zipCode;
	}

	public double getFine() {
		return fine;
	}

	public String getState() {
		return state;
	}

}
