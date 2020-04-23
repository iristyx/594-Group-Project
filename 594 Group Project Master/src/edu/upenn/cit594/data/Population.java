package edu.upenn.cit594.data;

public class Population {

	String zipCode;
	String population;
	
	public Population(String zipCode, String population) {
		super();
		this.zipCode = zipCode;
		this.population = population;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	} 
	


}
