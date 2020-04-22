package edu.upenn.cit594.ui;

import java.util.Scanner;

public class UserInterface {

	public UserInterface() {

		// Prompt user for input
		System.out.println("Please key in one of the numbers from the list below:\n\n" + 
							"'1' to display total population for all ZIP Codes\n" +
							"'2' to display total parking fines per capita for each ZIP Code\n" + 
							"'3' to display average market value for residences in a specified ZIP Code\n" +
							"'4' to display average total livable area for residences in a specified ZIP Code\n" +
							"'5' to display total residential market value per capita for a specified ZIP Code\n" +
							"'6' to display CUSTOM FUNCTION TO BE CONFIRMED LATER\n\n" +
							"'0' to exit.");
		
		Scanner in = new Scanner(System.in);
		String selection = in.next();
		
		if (selection.equals("0")) {
			// program terminates
		} else if (selection.equals("1")) {
						
		} else if (selection.equals("2")) {
			
		} else if (selection.equals("3")) {
			
		} else if (selection.equals("4")) {
			
		} else if (selection.equals("5")) {
			
		} else if (selection.equals("6")) {
			
		} else {
			// Invalid input error message
		}
		
		
		in.close();
	}

}
