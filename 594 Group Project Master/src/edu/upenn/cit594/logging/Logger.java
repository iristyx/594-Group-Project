package edu.upenn.cit594.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import edu.upenn.cit594.Main;

public class Logger {

	static String logFileName = Main.logFile;
	private PrintWriter out;

	private Logger(String filename) {
		File logFile = new File(filename);
		
		// Check if log file already exists. If not, create the file
		if (!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				System.out.println("IOException: Log file cannot be created.");
			}
		}
		try {
			out = new PrintWriter(logFile);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException: Log file cannot be found.");
		}
	}

	private static Logger instance = new Logger(logFileName);

	public static Logger getInstance() {
		return instance;
	}

	public void log(String msg) {
		out.println(msg);
		out.flush();
	}

	public void log(long currentTimeMillis) {
		out.println(currentTimeMillis);
		out.flush();
		
	}

}
