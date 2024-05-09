/**
 * 
 * This class simulates a food delivery service for a simple Uber app.
 * A TMUberDelivery is-a TMUberService with some extra functionality.
 * 
 * @author Matthew Moga
 * @version April 12, 2024
 * 
 */
public class TMUberDelivery extends TMUberService
{
  public static final String TYPENAME = "DELIVERY";
  private String restaurant; 
  private String foodOrderId;


  /**
   * This constructor initializes all instance variables in class.
   * 
   * @param from (String)
   * @param to (String)
   * @param user (User)
   * @param distance (int)
   * @param cost (double)
   * @param restaurant (String)
   * @param order (String)
   */
  public TMUberDelivery(String from, String to, User user, int distance, double cost,
                        String restaurant, String order)
  {
    super(from, to, user, distance, cost, TMUberDelivery.TYPENAME);
    this.restaurant = restaurant;
    this.foodOrderId = order;
  }
 
  
  /**
   * Accessor returns service type.
   * 
   * @return String - service type
   */
  public String getServiceType()
  {
    return TYPENAME;
  }
  

  /**
   * Accessor returns restaurant.
   * 
   * @return String - restaurant
   */
  public String getRestaurant()
  {
    return restaurant;
  }


  /**
   * Mutator method sets restaurant.
   * 
   * @param restaurant (String)
   */
  public void setRestaurant(String restaurant)
  {
    this.restaurant = restaurant;
  }


  /**
   * Accessor returns food order ID.
   * 
   * @return String - food order ID
   */
  public String getFoodOrderId()
  {
    return foodOrderId;
  }


  /**
   * Mutator method sets food order ID.
   * 
   * @param foodOrderId (String)
   */
  public void setFoodOrderId(String foodOrderId)
  {
    this.foodOrderId = foodOrderId;
  }


  /**
   * Method checks if two delivery requests are equal.
   * Equal if hey are equal in terms of TMUberServiceRequest, restaurant and food order id.
   * 
   * @param other (Object)
   * 
   * @return boolean - true or false depending on checks
   */
  public boolean equals(Object other)
  {
    // First check to see if other is a Delivery type
    // Cast other to a TMUService reference and check type
    // If not a delivery, return false
    TMUberService req = (TMUberService)other;
    if (!req.getServiceType().equals(TMUberDelivery.TYPENAME))
      return false;
    // Now check if this delivery and other delivery are equal
    TMUberDelivery delivery = (TMUberDelivery)other;
    return super.equals(other) && delivery.getRestaurant().equals(restaurant) && 
                                  delivery.getFoodOrderId().equals(foodOrderId);
  }


  /**
   * Mutator method prints information about a delivery request
   */
  public void printInfo()
  {
    super.printInfo();
    System.out.printf("\nRestaurant: %-9s Food Order #: %-3s", restaurant, foodOrderId); 
  }
}
