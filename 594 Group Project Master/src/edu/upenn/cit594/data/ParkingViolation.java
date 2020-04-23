package edu.upenn.cit594.data;

public class ParkingViolation {

	String zipCode;
	double fine;
	String state;

	public ParkingViolation(String zipCode, double fine, String state) {
		this.zipCode = zipCode;
		this.fine = fine;
		this.state = state;
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
