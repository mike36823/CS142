// Program created for CS 142 by Cole Wilson and Michael Herrera, 10/27/2021

import java.io.*;

/**
* The CustomLogger class is a helper class for the
* LetterGrade program. It allows a log file to be
* saved, as well as printed to the console live.
* 
* NOTE: see LetterGrade for credits
*
* @author  Cole Wilson and Michael Herrera
* @version 1.0
* @since   10/27/2021
*/

public class CustomLogger {
	public String logtext = "";

	public void print(String text) {
		// log
		logtext += text;
		System.out.print(text);
	}
	public void println(String text) {
		print(text+"\n");
	}
	public void save(String filename) {
		try {
			// take logtext and write it to filename
			PrintWriter output = new PrintWriter(filename);
			output.print(logtext);
			output.close();
		} catch (IOException theException) {
			System.err.println("[logger] unable to save log " + filename + " due to "+ theException);
		}
	}
}
