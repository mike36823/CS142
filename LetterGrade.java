// Program created for CS 142 by Cole Wilson and Michael Herrera, 10/27/2021

import java.io.*;
import java.util.Scanner;

/**
 * The LetterGrade program prompts the user for a filename to read. Percentage
 * grades are read from that file, and calculated into letter grades. Finally,
 * the average grade is calculated and the log is saved.
 * 
 * CREDITS: This program was created with the following resources: - try/catch
 * syntax from https://www.w3schools.com/java/java_try_catch.asp - array info
 * from https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html -
 *
 * @author Cole Wilson and Michael Herrera
 * @version 1.0
 * @since 10/27/2021
 */

public class LetterGrade {
	public static int numberOfGrades = 5;
	public static String seperator = "\n";
	public static CustomLogger logger = new CustomLogger();

	public static void main(String[] args) {
		// if a command line argument is supplied, use that filename instead of keyboard
		// entry
		String filename;
		if (args.length > 1) {
			filename = args[1];
		} else {
			filename = getKeyboardInput("please enter the name of the input file: ");
		}

		// read content from filename and split into grades of type double
		String fileContent = readFile(filename);
		double[] grades = getGradesFromText(fileContent);

		// confirm the correct number of grades
		if (grades.length != numberOfGrades) {
			System.err.printf("You supplied %d grades in the input file, not %d! Please try again.", grades.length,
					numberOfGrades);
			System.exit(1); // exit entire program
		}

		// main behavior here.....
		else if (grades.length <= numberOfGrades) {

			// determineGrade Method
			calcAverage(grades);
			determineGrade(grades);
			

			// save log
			logger.save("<filename>_ltr.txt");
		}

	}

	public static double[] getGradesFromText(String text) {
		String[] lines = text.split(seperator);
		double[] grades = new double[lines.length];
		for (int index = 0; index < lines.length; index++) {
			String gradeString = lines[index].replaceFirst("%", "");
			try {
				grades[index] = Double.parseDouble(gradeString);
			} catch (Exception notAPercentage) {
				System.err.printf("Invalid grade format \"%s\". Please try again.", gradeString);
				System.exit(1); // exit whole program
			}

		}
		return grades;
	}

	public static String readFile(String filename) {
		File file = new File(filename);
		Scanner fileScanner;
		String content = "";

		try {
			fileScanner = new Scanner(file);
			while (fileScanner.hasNext())
				content += fileScanner.nextLine() + "\n";
			fileScanner.close();
		} catch (FileNotFoundException error) {
			System.err.printf("The file \"%s\" was not found on this filesystem. Please try again!\n", filename);
			System.exit(1); // exits entire program
		}

		return content;
	}

	public static String getKeyboardInput(String prompt) {
		Scanner kb = new Scanner(System.in);
		System.out.print(prompt);
		String input = kb.nextLine();
		kb.close();
		return input;
	}

	public static void determineGrade(double[] grades) {

		for (int i = 0; i < grades.length; i++) {

			if (grades[i] <= 100 & grades[i] >= 90) {
				logger.println(grades[i] + " = A ");

			} else if (grades[i] <= 89 & grades[i] >= 80) {
				logger.println(grades[i] + " = B ");

			} else if (grades[i] <= 79 & grades[i] >= 70) {
				logger.println(grades[i] + " = C ");

			} else if (grades[i] <= 69 & grades[i] >= 60) {
				logger.println(grades[i] + " = D ");

			} else if (grades[i] < 60) {
				logger.println(grades[i] + " = F ");
			}
		}
		return;
	}

	public static void calcAverage(double[] grades) {

		// Used from this cite
		// https://beginnersbook.com/2017/09/java-program-to-calculate-average-using-array/
		
		double total = 0;
		for (int i = 0; i < grades.length; i++) {
			total = total + grades[i];
		}

		double average = total / grades.length;

		logger.println("The average of all the grades are: " + average);
		return;
		
	}
}
