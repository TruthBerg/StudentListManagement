package main;

/**
 * This class manages a singly linked list of Student objects
 * 
 * @author emmettgreenberg
 */
import java.util.Scanner;

public class StudentListManagement {

	public static void main(String[] args) throws InterruptedException {

		// Store students’ information in a Singly Linked List
		SinglyLinkedList<Student> students = new SinglyLinkedList<>();

		Scanner input = new Scanner(System.in);
		boolean done = false;

		// loop until the user quits
		while (!done) {
			displayMenu();

			// get users option
			int option;
			if (input.hasNextInt()) {
				option = input.nextInt();
			} else {
				System.out.println("Invalid option. Please try again");
				input.next(); // clears the bad input
				continue;
			}
			// check that the user's input is one of the valid options 1-5
			if (!(option >= 1 && option <= 5)) {
				System.out.println("Did not recognize that option. Please try again"); // invalid input
				continue;
			}

			// perform a task based on the user's input
			switch (option) {
			case 1:
				System.out.println("* Add Student *");
				String id, ssn, name, address, email, major;
				double gpa = 0.00;

				// prompt user to enter student ID
				System.out.println("Please enter the student's ID:");
				id = input.next();
				// check that it does not already exist
				Student st = students.find(studentWithID(id)); // returns null if a student with that ID does not exist
				if (st != null) {
					System.out.println("That student ID already exists.");
					continue;
				}

				// prompt user to enter SSN
				input.nextLine(); // clears the input
				System.out.println("Please enter the student's SSN:");
				ssn = input.next();

				// prompt user to enter name
				input.nextLine();
				System.out.println("Please enter the student's name:");
				name = input.nextLine();

				// prompt user for address
				// input.next();
				System.out.println("Please enter the student's address:");
				address = input.nextLine();

				// prompt user for email
				System.out.println("Please enter the student's email:");
				email = input.next();

				// prompt user for major
				input.nextLine();
				System.out.println("Please enter the student's major:");
				major = input.nextLine();

				// prompt user for GPA (must be a double)..
				gpa = getGPA(input); // static method call

				// create student object
				Student stud = new Student(ssn, name, address, email, id, major, gpa);
				students.addLast(stud); // add Student to end of the list

				System.out.println("Added student " + stud.getStudentID());
				break; // exit the case

			case 2:
				System.out.println("* Remove Student *");
				/*
				 * Prompts the user to enter a studentID, and reads it. If a student with the
				 * given studentID does not exist in the list, displays an appropriate error
				 * message, and displays the main menu. Otherwise, removes the student with the
				 * given studentID from the list, displays appropriate message indicating a
				 * successful operation, and displays the main menu.
				 */

				// Prompt the user to enter a studentID
				System.out.println("Please enter the student's ID:");
				String id2 = input.next();

				Student st2 = students.find(studentWithID(id2)); // locates the student to be removed

				// check that ID exists
				if (st2 == null) {
					System.out.println("Operation failed: There is  no student with that ID.");
					continue; // return to main menu
				}

				// remove student
				try {
					Student removedStud = students.remove(st2);
					System.out.println("Successfully removed student " + removedStud.getStudentID());
				} catch (NullPointerException e) {
					e.printStackTrace();
				}

				break;

			case 3:
				System.out.println("* List of all students *");

				// Print all student information
				System.out.println(students.toString());

				break;

			case 4:
				/*
				 * Prompts the user to enter a studentID, and reads it. If a student with the
				 * given studentID does not exist in the list, displays an appropriate error
				 * message, and displays the main menu. Otherwise, displays the student
				 * information (s.printStudent())
				 */

				System.out.println("* Display Student *");
				String id4 = getStudentIDFromUser(input);

				// find student with given ID
				Student st4 = students.find(studentWithID(id4));
				if (st4 == null) {
					System.out.println("There is no student with that ID.");
					continue;
				}

				st4.printStudent();

				break;

			case 5:
				/* Program terminates */
				System.out.println("Goodbye.");
				done = true;

			}
		}
	}

	/**
	 * Creates a student having the given studentID
	 * 
	 * @param id
	 *            the studentID
	 * @return a new student with the given studentID
	 */
	public static Student studentWithID(String id) {
		return new Student("some_ssn", "some_name", "some_address", "some_email", id, "some_major", 0);
	}

	/**
	 * Function prompts user for input and returns it
	 * 
	 * @param input
	 *            the scanner object
	 * @return id the user input
	 */
	public static String getStudentIDFromUser(Scanner input) {
		System.out.println("Please enter the student's ID:");
		if (!input.hasNext()) {
			input.next();
		}
		String id = input.next();
		return id;
	}

	/**
	 * Function prompts user for student GPA and returns it
	 * 
	 * @param input
	 *            the Scanner object
	 * @return gpa
	 */
	@SuppressWarnings("resource")
	public static double getGPA(Scanner input) {
		input = new Scanner(System.in);
		System.out.println("Please enter the student's gpa (two decimals):");
		double gpa;
		while (true) {
			if (input.hasNextDouble()) {
				gpa = input.nextDouble();
				break;
			} else {
				System.out.println("Invalid input. Please try again:");
				input.nextLine();
			}
		}
		// format gpa to two decimals (DecimalFormat)
		// String gpaToString = String.format(gpa, )
		return gpa;
	}

	/**
	 * Function prints the list of user options
	 */
	public static void displayMenu() throws InterruptedException {
		System.out.println("*** Menu ***\n" + "Please choose an option:\n" + "1. Add a student\n"
				+ "2. Remove a student\n" + "3. List all students\n" + "4. Display student information\n" + "5. Exit");
	}
}
