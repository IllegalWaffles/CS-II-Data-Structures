/**
 * Kuba Gasiorowski
 * 109776237
 * Homework #2
 * Recitation 03
 * Ka Wing Fong
 * (I don't know who my grading TA is)
 * 
 * This class represents what is loaded onto a train car. This is basically a
 * wrapper class containing nothing more than a few variables and 
 * mutator/accessor methods for those variables.
 * 
 * @author Kuba Gasiorowski
 */
public class ProductLoad {

	private String name;
	private double weight, value;
	private boolean isDangerous;
	
	/**
	 * Empty constructor for ProductLoad - everything is initialized to
	 * zero or null values.
	 */
	public ProductLoad()
	{
		
		name = "";
		weight = 0;
		value = 0;
		isDangerous = false;
		
	}
	
	/**
	 * Basic constructor for the ProductLoad.
	 * Allows for the name, weight, value, and danger status to be initialized right away.
	 * 
	 * @param name
	 * 		the name of the load
	 * @param weight
	 * 		the weight of the load
	 * @param value
	 * 		the value of the load
	 * @param isDangerous
	 * 		determines if this load is hazardous
	 */
	public ProductLoad(String name, double weight, double value, boolean isDangerous)
	{
		
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.isDangerous = isDangerous;
		
	}
	
	/**
	 * Allows the user to modify the name of the load.
	 * 
	 * @param in
	 * 		the new value that "name" is to be assigned
	 */
	public void setName(String in){name = in;}
	
	/**
	 * Allows the user to modify the weight of the load.
	 * 
	 * @param in
	 * 		the new value that "weight" is to be assigned
	 */
	public void setWeight(double in){weight = in;}
	
	/**
	 * Allows the user to modify the value of the load.
	 * 
	 * @param in
	 * 		the new value that "value" is to be assigned
	 */
	public void setValue(double in){value = in;}
	
	/**
	 * Allows the user to modify if the load is dangerous or not.
	 * 
	 * @param in
	 * 		the new value that "isDangerous" is to be assigned
	 */
	public void setDangerous(boolean in){isDangerous = in;}
	
	/**
	 * Allows the user to retrieve the name of the load
	 * 
	 * @return
	 * 		the name of the load
	 */
	public String getName(){return name;}
	
	/**
	 * Allows the user to retrieve the weight of the load
	 * 
	 * @return
	 * 		the weight of the load
	 */
	public double getWeight(){return weight;}
	
	/**
	 * Allows the user to retrieve the value of the load
	 * 
	 * @return
	 * 		the monetary value of the load
	 */
	public double getValue(){return value;}
	
	/**
	 * Allows the user to retrieve the dangerousness of the load
	 * 
	 * @return
	 * 		if the load is dangerous or not
	 */
	public boolean isDangerous(){return isDangerous;}
	
	/**
	 * Creates a clone of this object.
	 * 
	 * @return
	 * 		returns a deep clone of this ProductLoad
	 */
	public Object clone()
	{
		
		return new ProductLoad(name, weight, value, isDangerous);
		
	}
	
	/**
	 * Provides the system with a string representation for this object.
	 * 
	 * @return
	 * 		a string representation of the productLoad
	 */
	public String toString()
	{
		
		return "";
		
	}
	
}
