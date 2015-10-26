/**
 * Kuba Gasiorowski
 * 109776237
 * Homework #2
 * Recitation 03
 * Ka Wing Fong
 * (I don't know who my grading TA is)
 * 
 * This class represents a train car of a train. It has 
 * a load, a length, and a weight. It also contains appropriate
 * accessor/mutator methods as well as a method that determines
 * if the car is empty or not.
 * 
 * @author Kuba Gasiorowski
 */
public class TrainCar {

	private double carLength, carWeight;
	private ProductLoad load;
	
	/**
	 * Constructor for TrainCarClass, allows for initialization of
	 * weight and length for the car.
	 * 
	 * @param carWeight
	 * 		the new weight of the car
	 * @param carLength
	 * 		the new length of the car
	 */
	public TrainCar(double carLength, double carWeight)
	{

		this.carLength = carLength;
		this.carWeight = carWeight;
		load = null;
		
	}
	
	/**
	 * Allows the user to retrieve the weight of the car
	 * 
	 * @return
	 * 		the current weight of the car
	 */
	public double getCarWeight(){return carWeight;}
	
	/**
	 * Allows the user to retrieve the length of the car
	 * 
	 * @return
	 * 		the current length of the car
	 */
	public double getCarLength(){return carLength;}
	
	/**
	 * Allows the user to retrieve the current product load
	 * 
	 * @return
	 * 		what load the current car is carrying
	 */
	public ProductLoad getProductLoad()
	{
	
		if(load == null)
			return null;
		else 
			return (ProductLoad)load.clone();
		
	}
	
	/**
	 * Allows the user to set what the load of the car is.
	 * 
	 * @param newLoad
	 * 		what the new load of the car will be
	 */
	public void setProductLoad(ProductLoad newLoad)
	{
		
		if(newLoad != null)
			load = (ProductLoad)newLoad.clone();
		else
			load = null;
	
	}
	
	/**
	 * A method used to determine if the car is carrying any load or not.
	 * 
	 * @return
	 * 		true if the car is empty, false if not
	 */
	public boolean isEmpty()
	{
		
		return (load == null);
		
	}
	
	public boolean isDangerous()
	{
		
		if(load != null)
			return load.isDangerous();
		else
			return false;
		
	}
	
	/**
	 * Allows this TrainCar to be neatly represented as a string.
	 * If the TrainCar is empty, i.e. the product load is null,
	 * all displays are 0 or false.
	 * 
	 * @return
	 * 		a string representation of this TrainCar
	 */
	public String toString()
	{
		
		Object[] arrayOut = new Object[6];
		
		arrayOut[0] = new Double(carLength);
		arrayOut[1] = new Double(carWeight);
		
		if(load != null)
		{
			//If the ProductLoad exists, do this
			arrayOut[2] = load.getName();
			arrayOut[3] = new Double(load.getWeight());
			arrayOut[4] = new Double(load.getValue());
			
			if(load.isDangerous())
				arrayOut[5] = "YES";
			else
				arrayOut[5] = "NO";
		
		}
		else
		{
			//If the TrainCar is empty, do this
			arrayOut[2] = "Empty";
			arrayOut[3] = new Double(0);
			arrayOut[4] = new Double(0);
			arrayOut[5] = "NO";
			
		}
			
		String format = "%14.1f%14.1f  |%10s%14.1f%14.2f%12s";
		
		return String.format(format, arrayOut);
		
	}
	
	/**
	 * Creates a deep clone of this TrainCar.
	 * 
	 * @return
	 * 		a duplicate object of this TrainCar
	 */
	public Object clone()
	{
		
		TrainCar newCar = new TrainCar(this.carLength, this.carWeight);
		
		if(load == null)
			newCar.setProductLoad(null);
		else
			newCar.setProductLoad((ProductLoad)load.clone());
		
		return newCar;
		
	}
	
}
