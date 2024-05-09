/**
 * 
 * This class simulates an ride service for a simple Uber app. 
 * A TMUberRide is-a TMUberService with some extra functionality.
 * 
 * @author Matthew Moga
 * @version April 12, 2024
 * 
 */
public class TMUberRide extends TMUberService
{
  private int numPassengers;
  private boolean requestedXL;
  public static final String TYPENAME = "RIDE";


  /**
   * This constructor initializes all instance variables in class.
   * 
   * @param from (String)
   * @param to (String)
   * @param user (User)
   * @param distance (int)
   * @param cost (double)
   */
  public TMUberRide(String from, String to, User user, int distance, double cost)
  {
    super(from, to, user, distance, cost, TMUberRide.TYPENAME);
    requestedXL = false;
    numPassengers = 1;
  }
  

  /**
   * Accessor method returns service type.
   * 
   * @return String - Type name
   */
  public String getServiceType()
  {
    return TYPENAME;
  }


  /**
   * Accessor method returns number of passengers.
   * 
   * @return int - number of passengers
   */
  public int getNumPassengers()
  {
    return numPassengers;
  }


  /**
   * Mutator method sets number of passengers.
   * 
   * @param numPassengers (int)
   */
  public void setNumPassengers(int numPassengers)
  {
    this.numPassengers = numPassengers;
  }


  /**
   * Method returns boolean variable (whether or not requestedXL).
   * 
   * @return boolean - true or false
   */
  public boolean isRequestedXL()
  {
    return requestedXL;
  }


  /**
   * Mutator method sets requestedXL instance variable.
   * 
   * @param requestedXL (boolean)
   */
  public void setRequestedXL(boolean requestedXL)
  {
    this.requestedXL = requestedXL;
  }
}
