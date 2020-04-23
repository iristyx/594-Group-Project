package edu.upenn.cit594.data;

public class Property {

	double marketValue; 
	double livableArea;
	String zipCode;
	
	
	public Property(double marketValue, double livableArea, String zipCode) {
		super();
		this.marketValue = marketValue;
		this.livableArea = livableArea;
		this.zipCode = zipCode;
	}


	public double getMarketValue() {
		return marketValue;
	}


	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}


	public double getLivableArea() {
		return livableArea;
	}


	public void setLivableArea(double livableArea) {
		this.livableArea = livableArea;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	} 
	

	
	
}
