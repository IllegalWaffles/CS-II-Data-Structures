
/**
 * Kuba Gasiorowski
 * 109776237
 * Homework #1
 * Recitation 03
 * Ka Wing Fong
 * (I don't know who my grading TA is)
 * 
 * Represents a college course. Has all of the necessary specifications,
 * including accessor methods and a toString method to neatly display the course.
 * 
 * @author Kuba Gasiorowski
 */
public class Course{

	private String courseName, department, instructor;
	private int code;
	private byte section;
	
	/**
	 * Generates a Course object with empty fields
	 */
	public Course()
	{
		
		
		
		courseName = "";
		department = "";
		instructor = "";
		code = 0;
		section = 0;
		
	}
	
	/**
	 * Generates a Course object with only the name
	 * Generally used for debugging purposes
	 * 
	 * @param courseName
	 * 		name of the course
	 */
	public Course(String courseName)
	{
		
		this.courseName = courseName;
		
	}
	
	/**
	 * Generates a new course with the given values
	 * 
	 * @param courseName
	 * 		name of the course
	 * @param department
	 * 		the department of the course (eg. CSE or PHI)
	 * @param instructor
	 * 		the instructor of the class
	 * @param code
	 * 		the three digit class code
	 * @param section
	 * 		the two digit section code
	 */
	public Course(String courseName, String department, 
			      String instructor, int code, byte section)
	{
		
		this.courseName = courseName;
		this.department = department;
		this.instructor = instructor;
		this.code = code;
		this.section = section;
		
	}
	
	/**
	 * Generates an exact copy of the passed in Course object
	 * 
	 * @param copy
	 * 		the Course object which will be copied
	 */
	public Course(Course copy)
	{
		
		courseName = copy.courseName;
		department = copy.department;
		instructor = copy.instructor;
		code = copy.code;
		section = copy.section;
		
	}
	
	/**
	 * Accessor method for courseName
	 * 
	 * @return
	 * 		returns the name of the course
	 */
	public String getName(){return courseName;}
	
	/**
	 * Accessor method for instructor
	 * 
	 * @return
	 * 		returns the name of the course
	 */
	public String getInstructor(){return instructor;}
	
	/**
	 * Accessor method for department
	 * 
	 * @return
	 * 		returns the instructor of the course
	 */
	public String getDepartment(){return department;}
	
	/**
	 * Accessor method for code
	 * 
	 * @return
	 * 		returns the department of the course
	 */
	public int getCode(){return code;}
	
	/**
	 * Accessor method for section
	 *
	 * @return
	 * 		returns the section number
	 */
	public byte getSection(){return section;}
	
	/**
	 * Creates a deep clone for the course that calls this method
	 * 
	 * @return
	 * 		returns a copy of the object that calls this method
	 */
	public Object clone()
	{
		
		return new Course(this.courseName, this.department, 
						  this.instructor, this.code, this.section);
		
	}
	
	/**
	 * Compares two Courses
	 * 
	 * @return 
	 * 		returns true if the objects are identical, 
	 * 		returns false otherwise
	 */
	
	public boolean equals(Object obj)
	{
		
		if(!(obj instanceof Course))
			return false;
		
		Course otherCourse = (Course)obj;
		
		if(courseName.equals(otherCourse.courseName) && 
		   department.equals(otherCourse.department) &&
		   instructor.equals(otherCourse.instructor) &&
		   code == otherCourse.code && section == otherCourse.section)
			return true; 
		else 
			return false;
		
	}
	
	
	/**
	 * Returns a string representation of the object so organized output is possible.
	 * 
	 * @return
	 * 		returns a string representation of the object
	 */
	public String toString()
	{
		
		return courseName + " | " + instructor + " | " + department + code + "." + section;
		
	}
	
}
