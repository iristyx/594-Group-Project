package edu.upenn.cit594.data;

public class Property {

	double marketValue;
	double livableArea;
	String zipCode;
	
	public Property(double marketValue, double livableArea, String zipCode) {
		this.marketValue = marketValue;
		this.livableArea = livableArea;
		this.zipCode = zipCode;
	}

	public double getMarketValue() {
		return marketValue;
	}

	public double getLivableArea() {
		return livableArea;
	}

	public String getZipCode() {
		return zipCode;
	}
	

}
