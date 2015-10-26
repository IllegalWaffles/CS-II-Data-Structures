
/**
 * Kuba Gasiorowski
 * 109776237
 * Homework #1
 * Recitation 03
 * Ka Wing Fong
 * (I don't know who my grading TA is)
 * 
 * This class represents a planner for multiple course objects.
 * There are appropriate methods to add, remove, and display certain courses.
 * 
 * @author Kuba Gasiorowski
 */
public class Planner {

	private final int MAX_COURSES = 50;	//Defines the maximum number of courses in a planner
	private int numCourses;				//Variable to keep track of current number of courses in the planner
	private Course courseList[];		
	
	/**
	 * Generates a planner with MAX_COURSES spaces open
	 * 
	 * <dt><b>Postconditions:</b><dd>
	 * 		The planner has been initialized to an empty list of Courses.
	 */
	public Planner()
	{
		
		courseList = new Course[MAX_COURSES];
		numCourses = 0;
		
	}
	
	/**
	 * Acts as an accessor for 'numCourses', in other words,
	 * it returns how many courses are currently in the planner.
	 * 
	 * <dt><b>Preconditions:<dd></b>
	 * 		This planner has been instantiated.
	 * @return
	 * 		returns number of courses currently in the planner
	 */
	public int size()
	{
		
		return numCourses;
		
	}
	
	/**
	 * Adds a newCourse into the planner at position 'position'.
	 * 
	 * @param newCourse
	 * @param position
	 * <dt><b>Preconditions:<dd></b>
	 * 		This Course object has been instantiated and 1 ? position ? items_currently_in_list + 1. 
	 * 		The number of Course objects in this Planner is less than MAX_COURSES.
	 * <dt><b>Postconditions:<dd></b>
	 * 		The new Course is now listed in the correct preference on the list. 
	 * 		All Courses that were originally greater than or equal to position are moved back one position.
	 * @throws IllegalArgumentException
	 * 		Indicates that position is not within the valid range.
	 * @throws FullPlannerException
	 * 		Indicates that there is no more room in the Planner to record an additional Course.
	 */
	public void addCourse(Course newCourse, int position) throws IllegalArgumentException, FullPlannerException
	{
		
		if(position > numCourses + 1)
			throw new IllegalArgumentException();
		else if(position > MAX_COURSES)
			throw new FullPlannerException();
			
		//code to insert the new course into the array
			
		for(int i = numCourses-1; i >= position-1; i--)
		{
				
			//Shifts each course to the right
			courseList[i+1] = (Course)courseList[i].clone();
				
		}
			
		//Overwrites the course at the desired position with newCourse
		courseList[position-1] = (Course)newCourse.clone();
		numCourses++;
		
	}
	
	/**
	 * Adds a course to the end of the planner.
	 * 
	 * @param newCourse
	 * 		this course is added to the end of the planner
	 * @throws FullPlannerException
	 * 		indicates that there is no room left in the planner
	 */
	public void addCourse(Course newCourse)throws FullPlannerException
	{
		
		//Adds a course to the end of the list.
		
		try{
		
		addCourse((Course)newCourse.clone(), numCourses+1);
		
		}catch(Exception e){}
		
	}
	
	/**
	 * Removes the course at position 'position' from the planner.
	 * 
	 * <dt><b>Preconditions:</b><dd>
	 * 		This Planner has been instantiated and 1 ? position ? items_currently_in_list.
	 * <dt><b>Postconditions:</b><dd>
	 * 		The Course at the desired position has been removed. 
	 * 		All Courses that were originally greater than or equal to position are moved backward one position.
	 * @param position
	 * 		the course at this position is removed
	 * @throws IllegalArgumentException
	 * 		there is no course at the position specified
	 */
	public void removeCourse(int position) throws IllegalArgumentException
	{

		if(position > numCourses+1)
				throw new IllegalArgumentException();
		
		courseList[position-1] = null; //removes the specified course
		
		for(int i = position-1; i < numCourses-1; i++) //moves each course after 
		{										       //this position one position to the left
		
			
			courseList[i] = (Course)courseList[i+1].clone();
			
		}
		
		numCourses--;
		
	}
	
	/**
	 * Returns the course object at the specified position in the planner.
	 * 
	 * <dt><b>Preconditions:</b><dt>
	 * 		The Planner object has been instantiated and 1 ? position ? items_currenyly_in_list.
	 * @param position
	 * 		the course at this position is returned
	 * @return
	 * 		returns the course at the specified position
	 * @throws IllegalArgumentException
	 * 		if there is no course
	 */
	public Course getCourse(int position) throws IllegalArgumentException
	{
		
		if(position > numCourses + 1)
			throw new IllegalArgumentException();
		
		return courseList[position-1];
		
		
	}

	/**
	 * Displays all of the course objects in the planner that have
	 * a matching department as what is passed in.
	 * 
	 * <dt><b>Preconditions:</b><dd>
	 * 		The Planner object has been instantiated.
	 * <dt><b>Postconditions:</b><dd>
	 * 		Displays a neatly formatted table of each course filtered from the Planner. 
	 * 		Keeps the preference numbers the same.
	 * @param planner
	 * 		the planner that will be filtered through
	 * @param department
	 * 		the 3 letter department code
	 */
	public static void filter(Planner planner, String department)
	{
		
		int i = 1;
		Course currentCourse;
		
		try{
		
			while(planner.getCourse(i) != null)
			{
				
				currentCourse = (Course)planner.getCourse(i).clone();//Gets the current course
				
				if(currentCourse.getDepartment().equals(department))//Compares the two departments
					System.out.println(currentCourse);
				
				i++;
			
			}
			
		}catch(IllegalArgumentException e){System.out.println("Error while filtering");}
			
	}

	/**
	 * Determines whether course 'course' is in the planner
	 * 
	 * @param course
	 * 		the course that will be compared to each course in the planner
	 * @return
	 * 		true if course exists in the planner, false if not
	 */
	public boolean exists(Course course)
	{
		
		//Loops through the array until the end is reached.
		//If the course matches anything in the array, returns true
		
		int i = 0;
		
		while(courseList[i] != null)
		{
			
			if(courseList[i].equals(course))
				return true;
			
			i++;
			
		}
		
		return false;
		
	}
	
	/**
	 * Creates a deep clone of the object that calls this method
	 * 
	 * <dt><b>Preconditions:<dd></b>
	 * 		This Planner object has been instantiated.
	 * 
	 * @return
	 * 		returns an identical copy of the object that calls this method
	 */
	public Object clone()
	{
		
		Planner plannerClone = new Planner();
		
		int i = 0;
		
		while(this.courseList[i] != null)//Copies the array of courses into the new planner
		{
			
			plannerClone.courseList[i] = (Course)this.courseList[i].clone();
			i++;
			
		}
		
		plannerClone.numCourses = this.numCourses;
		
		return plannerClone;
		
	}

	/**
	 * <dt><b>Preconditions:</b><dd>
	 * 		This Planner has been instantiated.
	 * <dt><b>Postconditions:</b><dd>
	 * 		Displays a neatly formatted table except not really of each course.
	 */
	public void printAllCourses()
	{
		
		System.out.println("Course Name | Instructor | Class Code & Section");
		
		for(int i = 0; i < numCourses; i++)
		{
			System.out.println(i+1 + ". " + courseList[i]);
		}
		
	}
	
	/**
	 * Allows the planner to be represented as a string
	 * 
	 * @return
	 * 		returns a string representation of this planner object
	 */
	public String toString()
	{
	
		String toReturn = "";
		
		for(int i = 0; i < numCourses;i++)
		{
			
			toReturn += courseList[i] + "\n";
			//Creates a string containing each course in the planner
			
		}
		
		return toReturn;
		
	}
	
	
}

