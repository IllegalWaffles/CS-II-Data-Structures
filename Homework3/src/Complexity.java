/**
 * Kuba Gasiorowski
 * 109776237
 * kuba.gasiorowski@stonybrook.edu
 * Homework #3
 * Recitation 03
 * Ka Wing Fong
 * Zhichuang Sun 
 * 
 * Acts as a wrapper class for two ints. Represents
 * a big-oh complexity as a function of O(n^x * log(n)^y)
 * where x and y are the two ints.
 * 
 * @author Kuba Gasiorowski
 */
public class Complexity {

	private int n_power, log_power;
	
	/**
	 * Empty constructor. Initalizes everything to 0.
	 */
	public Complexity()
	{
		
		n_power = 0;
		log_power = 0;
		
	}
	
	/**
	 * Constructor that allows for the initialization of both fields to some value.
	 * 
	 * @param n_power
	 * 		the value for n_power
	 * @param log_power
	 * 		the value for log_power
	 */
	public Complexity(int n_power, int log_power)
	{
		
		this.n_power = n_power;
		this.log_power = log_power;
		
	}
	
	/**
	 * Creates a copy of the object passed in.
	 * 
	 * @param copy
	 * 		the new object instantiated will be identical to this
	 */
	public Complexity(Complexity copy)
	{
		
		this.n_power = copy.n_power;
		this.log_power = copy.log_power;
		
	}
	
	/**
	 * Returns the n_power of this object.
	 * 
	 * @return
	 * 		the n_power of this object
	 */
	public int getNPower(){return n_power;}
	
	/**
	 * Returns the log_power of this object.
	 * 
	 * @return
	 * 		the log_power of this object
	 */	
	public int getLogPower(){return log_power;}
	
	/**
	 * Sets this object's n_power to a new value.
	 * 
	 * @param in
	 * 		the new value for n_power
	 */
	public void setNPower(int in){n_power = in;}
	
	/**
	 * Sets this object's log_power to a new value.
	 * 
	 * @param in
	 * 		the new value for log_power
	 */
	public void setLogPower(int in){log_power = in;}
	
	/**
	 * Returns a string representation for this object.
	 * 
	 * @return
	 * 		a string representation of this object
	 */
	public String toString()
	{
		
		/*
		 * Case returns:
		 * n = 0, log = 0 : O(1)
		 * n = 1, log = 0 : O(n)
		 * n = 0, log = 1 : O(log(n))
		 * n = 1, log = 1 : O(n * log(n))
		 * n > 1, log = 0 : O(n^x)
		 * n = 0, log > 1 : O(log(n)^x)
		 * n > 1, log = 1 : O(n^x * log(n))
		 * n = 1, log > 1 : O(n * log(n)^x)
		 * n > 1, log > 1 : O(n^x * log(n)^y)
		 * 
		 */
		
		if(n_power == 0 && log_power == 0)
			return "O(1)";
		else if(n_power == 1 && log_power == 0)
			return "O(n)";
		else if(n_power == 0 && log_power == 1)
			return "O(log(n))";
		else if(n_power == 1 && log_power == 1)
			return "O(n * log(n))";
		else if(n_power > 1 && log_power == 0)
			return "O(n^" + n_power + ")";
		else if(n_power == 0 && log_power > 1)
			return "O(log(n)^" + log_power + ")";
		else if(n_power > 1 && log_power == 1)
			return "O(n^" + n_power + " * log(n)";
		else if(n_power == 1 && log_power > 1)
			return "O(n * log(n)^" + log_power + ")";
		else
			return "O(n^" + n_power + " * log(n)^" + log_power + ")";
			
		
	}
	
	/**
	 * Compares the two objects and returns true if they are identical.
	 * 
	 * @return
	 * 		true if the two objects are the same, false otherwise
	 */
	public boolean equals(Object o)
	{
		
		if(o instanceof Complexity)
			if(this.n_power == ((Complexity)o).n_power || this.log_power == ((Complexity)o).n_power)
				return true;
			else
				return false;
		else
			return false;
		
		
	}
	
	public Complexity add(Complexity other)
	{
		
		int newNPower, newLogPower;
		
		newNPower = this.getNPower() + other.getNPower();
		newLogPower = this.getLogPower() + other.getLogPower();
		
		return new Complexity(newNPower, newLogPower);
		
	}
	
	/**
	 * Compares this to another complexity, returns true if it is higher order complexity.
	 * False otherwise.
	 * 
	 * @param o
	 * 		the complexity to be compared to this object
	 * @return
	 * 		true if this is higher order than o
	 */
	public boolean isHigherOrder(Complexity o)
	{
		
		//This method is accurate until about exponents reach 4 or 5.
		
		if(this.n_power == o.n_power)
			return this.log_power > o.log_power;
		else
			return this.n_power > o.n_power;
			
	}
	
}
