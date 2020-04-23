package edu.upenn.cit594.datamanagement;

public class ParkingReaderCreator {
	
	protected ParkingReader parkingReader;
	protected String format;
	protected String filename;
	
	public ParkingReaderCreator(String format, String name) {
		this.format = format.toLowerCase();
		this.filename = name;
		this.parkingReader = createParkingReader();
	}
	
	/**
	 * Create a Reader object based on the specified input file format. Only .txt and .json files are accepted
	 * @return a Reader object (TextReader or JSONReader). Else, an exception will be thrown
	 */
	public ParkingReader createParkingReader() {
		if (format.equals("csv")) {
			return new ParkingCSVReader(filename);
		} else if (format.equals("json")) {
			return new ParkingJSONReader(filename);
		} else {
		throw new IllegalArgumentException("IllegalArgumentException: Input file format is invalid. Format of input file must be 'csv' or 'json'.");
		}
	}
}