
/**
 * Kuba Gasiorowski
 * 109776237
 * Homework #1
 * Recitation 03
 * Ka Wing Fong
 * (I don't know who my grading TA is)
 * 
 * A main class that is used to run the entire program.
 * A UI uses commands as specified below.
 * This class is used to manage a planner, by adding, removing, and displaying
 * courses as desired.
 * 
 * The program works by entering a command, each one being one or two letters,
 * and if more input is needed then the program will prompt the user for it.
 * 
 * @author Kuba Gasiorowski
 */

import java.util.*;

public class PlannerManager {

	public static void main(String[] args){
		
		System.out.println("~~~Welcome to the Course Manager~~~");
		
		boolean quitFlag = false;
		Scanner in = new Scanner(System.in);
		String input = "";
		
		Planner myPlanner = new Planner();
		Planner backupPlanner = new Planner();
		
		/* This code is just for debugging purposes, it's just here so I don't have to keep adding courses tediously
		try{
		
		myPlanner.addCourse(new Course("Intro to Eastern Religions", "PHI", "Dillsworth", 111, Byte.parseByte("01")));
		myPlanner.addCourse(new Course("Java II", "CSE", "Esmaili", 214, Byte.parseByte("05")));
		myPlanner.addCourse(new Course("Java I", "CSE", "Wong", 114, Byte.parseByte("08")));
		
		}catch(Exception e){}
		*/
		
		System.out.println("(A) Add Course\n"
				 + "(G) Get Course\n"
				 + "(R) Remove Course\n"
				 + "(P) Print Courses in Planner\n"
				 + "(F) Filter by Department Code\n"
				 + "(L) Look For Course\n"
				 + "(S) Planner Size\n"
				 + "(B) Backup\n"
				 + "(PB) Print Courses in Backup\n"
				 + "(RB) Revert to Backup\n"
				 + "(Q) Quit\n");
		
		while(!quitFlag){
			
			System.out.print("\nPlease enter a selection: ");
			
			String[] splitInput = in.nextLine().split(" ");
			input = splitInput[0];
			
			if(input.length() > 25)
			{
				
				System.out.println("Input is too long!");
				
			}
			else if(splitInput[0].toUpperCase().equals("A"))
			{
				
				//Adds a course to the current planner
				try
				{
				
				Course myCourse;
				
				System.out.print("\nEnter a course name: ");
				String courseName = in.nextLine();
				System.out.print("Enter department: ");
				String department = in.nextLine().toUpperCase();
				System.out.print("Enter course code: ");
				int code = Integer.parseInt(in.nextLine());
				System.out.print("Enter course section: ");
				byte section = Byte.parseByte(in.nextLine());
				System.out.print("Enter instructor: ");
				String instructor = in.nextLine();
				
				myCourse = new Course(courseName, department, instructor, code, section);
				
				System.out.print("\nEnter position: ");
				int position = Integer.parseInt(in.nextLine());
				
				myPlanner.addCourse(myCourse, position);
				
				System.out.println(department + " " + code + "." + section + " successfully added at position " + position);
				
				}
				catch(Exception e)
				{
					if(e instanceof IllegalArgumentException)
						System.out.println("Cannot place course in this position");
					else if(e instanceof FullPlannerException)
						System.out.println("Planner is full! Cannot add this course to the schedule");
					else System.out.println("Bad input, try again");
				}
				
				
			}
			else if(splitInput[0].toUpperCase().equals("G"))
			{
				
				//Prints the course at the prompted position
				try{
					
				System.out.print("What position to display? ");
				System.out.print(myPlanner.getCourse(Integer.parseInt(in.nextLine())));
				
				}
				catch(IllegalArgumentException e)
				{
					System.out.println("No course at this position!");
				}
				
			}
			else if(splitInput[0].toUpperCase().equals("R"))
			{
				
				//Removes a course at the prompted position
				
				try
				{
					
				System.out.print("Which position to remove? ");	
				
				int positionToRemove = Integer.parseInt(in.nextLine());
				
				myPlanner.removeCourse(positionToRemove);
				System.out.println("Course at position " + positionToRemove + " successfully removed.");
				
				}
				catch(IllegalArgumentException e)
				{
					System.out.println("No course at this position!");
				}
				
			}
			else if(splitInput[0].toUpperCase().equals("P"))
			{
				
				//Prints all of the current courses in the planner
				
				if(myPlanner.size() > 0)
					myPlanner.printAllCourses();
				else
					System.out.println("Planner is empty!");
				
			}
			else if(splitInput[0].toUpperCase().equals("F"))
			{
				
				//Filters the current planner with the given 3-letter
				//string input
				
				System.out.print("What to filter for? ");
				Planner.filter(myPlanner, in.nextLine());
				
			}
			else if(splitInput[0].toUpperCase().equals("L"))
			{
				
				//Takes paramaters for a course object and checks if
				//The course exists in the current planner
				
				try{
				
				Course courseToLookFor;
				
				System.out.print("Name of the course: ");
				String name = in.nextLine();
				System.out.print("Department: ");
				String department = in.nextLine();
				System.out.print("Code: ");
				int code = Integer.parseInt(in.nextLine());
				System.out.print("Section: ");
				byte section = Byte.parseByte(in.nextLine());
				System.out.print("Instructor: ");
				String instructor = in.nextLine();
				
				courseToLookFor = new Course(name, department, instructor, code, section);
				
				if(myPlanner.exists(courseToLookFor))
					System.out.println("The planner already contains this course.");
				else
					System.out.println("The planner does not contain this course.");
				
				}
				catch(Exception e)
				{
					
					System.out.println("Bad input. Try again");
					
				}
				
				//myCourse = new Course(courseName, department, instructor, code, section);
				
			}
			else if(splitInput[0].toUpperCase().equals("S"))
			{
				
				//Displays the size of the current planner
				System.out.println("There are currently " + myPlanner.size() + " courses in the planner.");
				
			}
			else if(splitInput[0].toUpperCase().equals("B"))
			{
				
				//Backs up the current planner in the backup slot
				
				System.out.println("Are you sure you want to overwrite the backup slot? (y/n)");
				
				if(in.nextLine().equals("y"))
				{
				
					backupPlanner = (Planner)myPlanner.clone();
					System.out.println("Planner successfully backed up!");
					
				}
				else
					System.out.println("Backup cancelled");
					
			}
			else if(splitInput[0].toUpperCase().equals("PB"))
			{
				
				//Prints all of the courses in the backup planner
				if(backupPlanner.size() > 0)
					backupPlanner.printAllCourses();
				else 
					System.out.println("This planner is empty!");
				
			}
			else if(splitInput[0].toUpperCase().equals("RB"))
			{
				
				//Restores the main planner from the backup
				System.out.println("Are you sure you want to restore the planner from backup? (y/n)");
				
				if(in.nextLine().equals("y")){
					
					myPlanner = (Planner)backupPlanner.clone();
					System.out.println("Planner successfully restored from backup!");
					
				}
				else
					System.out.println("Restore cancelled");
			}
			else if(splitInput[0].toUpperCase().equals("Q") || splitInput[0].toUpperCase().equals("EXIT"))
			{
			
				//Terminates the program
				System.out.println("\nProgram Terminated...");
				quitFlag = true;
				
			}
			else
				System.out.println("Input not recognized! Try again.");
			
		}
		
		in.close();
		
	}
	
}

