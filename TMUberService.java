/**
 * 
 * This is a general class that simulates a ride or a delivery in a simple Uber app.
 * This class is made abstract since we never create an object.
 * 
 * @author Matthew Moga
 * @version April 12, 2024
 * 
 */
abstract public class TMUberService implements Comparable<TMUberService>
{ 
  private String from;
  private String to;
  private User user;
  private String type;  // Currently Ride or Delivery but other services could be added      
  private int distance; // Units are City Blocks
  private double cost;  // Cost of the service


  /**
   * This constructor initializes all instance variables in class.
   * 
   * @param from (String)
   * @param to (String)
   * @param user (User)
   * @param distance (int)
   * @param cost (double)
   * @param type (String)
   */ 
  public TMUberService(String from, String to, User user, int distance, double cost, String type)
  {
    this.from = from;
    this.to = to;
    this.user = user;
    this.distance = distance;
    this.cost = cost;
    this.type = type;
    this.distance = 0;
  }


  /**
   * Subclasses define their type (e.g. "RIDE" OR "DELIVERY").
   * Abstract accessor that gets service type.
   * 
   * @return String - Service type
   */
  abstract public String getServiceType();


  /**
   * Accessor method returns from location.
   * 
   * @return String - from location
   */
  public String getFrom()
  {
    return from;
  }


  /**
   * Mutator method sets from location.
   * 
   * @param from (String)
   */
  public void setFrom(String from)
  {
    this.from = from;
  }


  /**
   * Accessor method returns to location.
   * 
   * @return String - to location
   */
  public String getTo()
  {
    return to;
  }


  /**
   * Mutator method sets to location.
   * 
   * @param to (String)
   */
  public void setTo(String to)
  {
    this.to = to;
  }


  /**
   * Accessor method returns user.
   * 
   * @return User - user
   */
  public User getUser()
  {
    return user;
  }


  /**
   * Mutator method sets user.
   * 
   * @param user (User)
   */
  public void setUser(User user)
  {
    this.user = user;
  }


  /**
   * Accessor method returns distance.
   * 
   * @return int - distance
   */
  public int getDistance()
  {
    return distance;
  }


  /**
   * Mutator method sets distance.
   * 
   * @param distance (int)
   */
  public void setDistance(int distance)
  {
    this.distance = distance;
  }


  /**
   * Accessor method returns cost.
   * 
   * @return double - cost
   */
  public double getCost()
  {
    return cost;
  }


  /**
   * Mutator method sets cost.
   * 
   * @param cost (double)
   */
  public void setCost(double cost)
  {
    this.cost = cost;
  }


  /**
   * Method that compares 2 service requests based on distance.
   * 
   * @param other (TMUberService)
   */
  public int compareTo(TMUberService other)
  {
    return this.distance - other.distance;
  }
  

  /**
   * Method that checks if two service requests are equal.
   * They are equal if they have the same type and same user. Makes sure type is checked first!
   * 
   * @param other (Object)
   */
  public boolean equals(Object other)
  {
    TMUberService otherService = (TMUberService) other;
    return type.equals(otherService.type) && user.equals(otherService.user);
  }
  

  /**
   * Mutator method that prints information.
   */
  public void printInfo()
  {
    System.out.printf("\nType: %-9s From: %-15s To: %-15s", type, from, to);
    System.out.print("\nUser: ");
    user.printInfo();
  }
}
