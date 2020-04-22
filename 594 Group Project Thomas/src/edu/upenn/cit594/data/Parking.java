package edu.upenn.cit594.data;

public class Parking {

	String timeStamp;
	String fine;
	String description;
	String anonymousIdentifier;
	String state;
	String uniqueIdentifier;
	String zipCode; 

	
	public Parking(String timeStamp, String fine, String description, String anonymousIdentifier, String state,
			String uniqueIdentifier, String zipCode) {
		super();
		this.timeStamp = timeStamp;
		this.fine = fine;
		this.description = description;
		this.anonymousIdentifier = anonymousIdentifier;
		this.state = state;
		this.uniqueIdentifier = uniqueIdentifier;
		this.zipCode = zipCode;
	}


	public Parking(double parseDouble, double parseDouble2, String string) {
		// TODO Auto-generated constructor stub
	}


}
