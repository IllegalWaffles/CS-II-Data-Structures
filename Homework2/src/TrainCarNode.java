/**
 * Kuba Gasiorowski
 * 109776237
 * Homework #2
 * Recitation 03
 * Ka Wing Fong
 * (I don't know who my grading TA is)
 * 
 * This class represents a node for a TrainCar. Basically
 * a wrapper class for TrainCar so it can be used with
 * the TrainLinkedList class.
 * 
 * @author Kuba Gasiorowski
 */
public class TrainCarNode {
	
	private TrainCarNode prev, next;
	private TrainCar car;
	
	/**
	 * An default constructor to create an empty 
	 * TrainCarNode object.
	 * 
	 */
	public TrainCarNode(){
		
		prev = null;
		next = null;
		car = null;
		
	}
	
	/**
	 * A constructor that initializes the object's car to the desired object.
	 * 
	 * @param car
	 * 		the TrainCar that the node will be initialized to
	 */
	public TrainCarNode(TrainCar car){this.car = (TrainCar)car.clone();}
	
	/**
	 * Sets a new next node for this node.
	 * 
	 * @param newNode
	 * 		the new object of 'next'
	 */
	public void setNext(TrainCarNode newNode){next = newNode;}
	
	/**
	 * Sets a new previous node for this node.
	 * 
	 * @param newNode
	 * 		the new object of 'prev'
	 */
	public void setPrev(TrainCarNode newNode){prev = newNode;}
	
	/**
	 * Sets a new car into the node's traincar.
	 * 
	 * @param newCar
	 * 		the new object to be copied into 'car'
	 */
	public void setTrainCar(TrainCar newCar){car = (TrainCar)newCar.clone();}
	
	/**
	 * Returns the next node in the linked list.
	 * 
	 * @return
	 * 		returns the next node in the linked list
	 */
	public TrainCarNode getNext(){return next;}
	
	/**
	 * Returns the previous node in the linked list.
	 * 
	 * @return
	 * 		returns the previous node in the linked list
	 */
	public TrainCarNode getPrev(){return prev;}
	
	/**
	 * Returns the traincar in the node.
	 * 
	 * @return
	 * 		returns the traincar in the node
	 */
	public TrainCar getTrainCar(){return car;}
	
}
