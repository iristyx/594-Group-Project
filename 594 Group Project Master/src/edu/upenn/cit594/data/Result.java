package edu.upenn.cit594.data;

import java.util.Map;


public class Result {

	int sum;
	Map<String,Double> sortedTotalParkingFinesPerCapita;

	Map<String,Double> averageMarketValue;
	Map<String,Double> averageLivableArea;
	Map<String,Double> totalMarketValuePerCapita;
	
	Map<String,Double> highestMarketValue;
	Map<String,Double> averageParkingFines;
	

	public Result(int sum, Map<String, Double> sortedTotalParkingFinesPerCapita, Map<String, Double> averageMarketValue,
			Map<String, Double> averageLivableArea, Map<String, Double> totalMarketValuePerCapita,
			Map<String, Double> highestMarketValue, Map<String, Double> averageParkingFines) {
		super();
		this.sum = sum;
		this.sortedTotalParkingFinesPerCapita = sortedTotalParkingFinesPerCapita;
		this.averageMarketValue = averageMarketValue;
		this.averageLivableArea = averageLivableArea;
		this.totalMarketValuePerCapita = totalMarketValuePerCapita;
		this.highestMarketValue = highestMarketValue;
		this.averageParkingFines = averageParkingFines;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public Map<String, Double> getSortedTotalParkingFinesPerCapita() {
		return sortedTotalParkingFinesPerCapita;
	}

	public void setSortedTotalParkingFinesPerCapita(Map<String, Double> sortedTotalParkingFinesPerCapita) {
		this.sortedTotalParkingFinesPerCapita = sortedTotalParkingFinesPerCapita;
	}

	public Map<String, Double> getAverageMarketValue() {
		return averageMarketValue;
	}

	public void setAverageMarketValue(Map<String, Double> averageMarketValue) {
		this.averageMarketValue = averageMarketValue;
	}

	public Map<String, Double> getAverageLivableArea() {
		return averageLivableArea;
	}

	public void setAverageLivableArea(Map<String, Double> averageLivableArea) {
		this.averageLivableArea = averageLivableArea;
	}

	public Map<String, Double> getTotalMarketValuePerCapita() {
		return totalMarketValuePerCapita;
	}

	public void setTotalMarketValuePerCapita(Map<String, Double> totalMarketValuePerCapita) {
		this.totalMarketValuePerCapita = totalMarketValuePerCapita;
	}

	public Map<String, Double> getHighestMarketValue() {
		return highestMarketValue;
	}

	public void setHighestMarketValue(Map<String, Double> highestMarketValue) {
		this.highestMarketValue = highestMarketValue;
	}

	public Map<String, Double> getAverageParkingFines() {
		return averageParkingFines;
	}

	public void setAverageParkingFines(Map<String, Double> averageParkingFines) {
		this.averageParkingFines = averageParkingFines;
	}


	
	
}
