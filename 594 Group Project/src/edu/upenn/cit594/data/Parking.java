package edu.upenn.cit594.data;

public class Parking {

	String timeStamp;
	String fine;
	String description;
	String anonymouseIdentifier;
	String state;
	String uniqueIdentifier;
	String zipCode; 

	
	public Parking(String timeStamp, String fine, String description, String anonymouseIdentifier, String state,
			String uniqueIdentifier, String zipCode) {
		super();
		this.timeStamp = timeStamp;
		this.fine = fine;
		this.description = description;
		this.anonymouseIdentifier = anonymouseIdentifier;
		this.state = state;
		this.uniqueIdentifier = uniqueIdentifier;
		this.zipCode = zipCode;
	}


}
