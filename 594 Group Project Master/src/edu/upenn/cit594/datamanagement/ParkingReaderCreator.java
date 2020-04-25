package edu.upenn.cit594.datamanagement;

public class ParkingReaderCreator {

	protected String filename;
	protected String format;

	public ParkingReaderCreator(String format, String name) {
		this.format = format;
		filename = name;
	}

	public ParkingReader createReader() {
		if (format.equalsIgnoreCase("csv")) {
			return new ParkingCSVReader(filename);
		} else if (format.equalsIgnoreCase("json")) {
			return new ParkingJSONReader(filename);
		} else {
			return null;
		}
	}
}
