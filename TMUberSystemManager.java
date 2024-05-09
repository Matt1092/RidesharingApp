// Import statements
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.lang.RuntimeException;
import java.util.Iterator;
import java.util.LinkedHashMap;


/**
 * 
 * This class contains the main logic of the system.
 *  It keeps track of all users, drivers and service requests (RIDE or DELIVERY).
 * 
 * @author Matthew Moga
 * @version April 12, 2024
 * 
 */
public class TMUberSystemManager {

  // Replacement for ArrayList<User> users and implementation of Linked HashMap
  private Map<String, User> users;

  // User list instance variable
  private ArrayList<User> userList;

  // Drivers instance variable
  private ArrayList<Driver> drivers;

  // Replacement for ArrayList<TMUberService> serviceRequests and implementation of Queues
  private Queue<TMUberService>[] serviceRequests = new Queue[4];

  public double totalRevenue; // Total revenues accumulated via rides and deliveries

  // Rates per city block
  private static final double DELIVERYRATE = 1.2;
  private static final double RIDERATE = 1.5;

  // Portion of a ride/delivery cost paid to the driver
  private static final double PAYRATE = 0.1;

  // These variables are used to generate user account and driver ids
  int userAccountId = 900;
  int driverId = 700;


  /**
   * This no-argument constructor initializes all the instance variables of the class.
   */
  public TMUberSystemManager() {
    users = new LinkedHashMap<>();
    userList = new ArrayList<User>();
    drivers = new ArrayList<Driver>();

    // Looping through serviceRequests and initializing each array to a LinkedList of type TMUberService
    for (int i = 0; i < serviceRequests.length; i++) {
      serviceRequests[i] = new LinkedList<TMUberService>();
    }
    totalRevenue = 0;
  }


  /**
   * This method generates a new user account ID.
   * 
   * @return String - Display of user's account ID
   */
  private String generateUserAccountId() {
    return "" + userAccountId + users.size();
  }


  /**
   * This method generates a new driver ID.
   * 
   * @return String - Display of driver's ID
   */
  private String generateDriverId() {
    return "" + driverId + drivers.size();
  }


  /**
   * Given user account ID, this accessor finds user in list of users.
   * Method is modified for Linked HashMap implementation.
   * 
   * @param accountId (String)
   * 
   * @return User - Returns user if found amongst list of users
   * @return null - If user not found
   */
  public User getUser(String accountId) {
    for (String userId: users.keySet()) {
      if (userId.equals(accountId)) {
        return users.get(userId);
      }
    }
    return null;
  }


  /**
   * This method checks for duplicate user.
   * Method is modified for Linked HashMap implementation.
   * 
   * @param user (User)
   * 
   * @return boolean - Whether or not users contains user
   */
  private boolean userExists(User user) {
    // Simple way for Linked HashMap implementation
    return users.containsValue(user);
  }


  /**
   * This method checks for duplicate driver.
   * 
   * @param driver (Driver)
   * 
   * @return boolean - Whether or not drivers contains driver
   */
  private boolean driverExists(Driver driver) {
    for (int i = 0; i < drivers.size(); i++) {
      if (drivers.get(i).equals(driver))
        return true;
    }
    return false;
  }


  /**
   * Given a user, this method checks if user ride/delivery request already exists in service requests.
   * 
   * @param req (TMUberService)
   * 
   * @return boolean - Whether or not ride/delivery request exists
   */
  private boolean existingRequest(TMUberService req) {
    for (Queue<TMUberService> zoneService: serviceRequests) {
    if (zoneService.contains(req)) 
      return true;
    }
    return false;
  }


  /**
   * This accessor returns the delivery cost based on distance.
   * 
   * @param distance (int)
   * 
   * @return int - Distance multiplied by delivery rate
   */
  private double getDeliveryCost(int distance) {
    return distance * DELIVERYRATE;
  }


  /**
   * This accessor returns the ride cost based on distance.
   * 
   * @param distance (int)
   * 
   * @return int - Distance multiplied by ride rate
   */
  private double getRideCost(int distance) {
    return distance * RIDERATE;
  }


  /**
   * This accessor goes through all drivers and sees if one is available.
   * 
   * @return Driver - Chooses the first available driver
   */
  private Driver getAvailableDriver() {
    for (int i = 0; i < drivers.size(); i++) {
      Driver driver = drivers.get(i);
      if (driver.getStatus() == Driver.Status.AVAILABLE)
        return driver;
    }
    return null;
  }


  /**
   * Mutator method that prints information about all registered users in the system.
   */
  public void listAllUsers() {
    System.out.println();
    int count = 0;
    for (String userId: users.keySet()) {
      int index = count + 1;
      System.out.printf("%-2s. ", index);
      users.get(userId).printInfo();
      System.out.println();
      count++;
    }
  }


  /**
   * Mutator method that prints info about all registered drivers in the system.
   */
  public void listAllDrivers() {
    System.out.println();
    for (int i = 0; i < drivers.size(); i++) {
      int index = i + 1;
      System.out.printf("%-2s. ", index);
      drivers.get(i).printInfo();
      System.out.println();
    }
  }


  /**
   * Mutator method that prints info about all current service requests depending on zone number.
   */
  public void listAllServiceRequests() {
    // Loop through the zones and find all service requests in given zone
    for (int zone = 0; zone < serviceRequests.length; zone++) {
      System.out.println();
      System.out.println("ZONE " + zone);
      System.out.println("======");
      // Create new queue variable with zone for index
      Queue<TMUberService> requestsByZone = serviceRequests[zone];
      // Loop through the requests by zone
      // Initialize count variable to 1 for display of services by zone
      int count = 1;
      for (TMUberService service: requestsByZone) {
        System.out.println();
        System.out.print(count + ". ------------------------------------------------------------");
        count++;
        service.printInfo();
        System.out.println();
      }
    }
  }


  /**
   * Mutator method that adds a new user to the system.
   * 
   * @param name (String)
   * @param address (String)
   * @param wallet (double)
   */
  public void registerNewUser(String name, String address, double wallet) {
    // Check to ensure name is valid
    if (name == null || name.equals("")) {
      throw new InvalidNameException("Invalid name");
    }
    // Check to ensure address is valid
    if (!CityMap.validAddress(address)) {
      throw new InvalidAddressException("Invalid address");
    }
    // Check to ensure wallet amount is valid
    if (wallet < 0) {
      throw new InvalidUserWalletException("Invalid user wallet");
    }
    // Check for duplicate user
    User user = new User(generateUserAccountId(), name, address, wallet);
    if (userExists(user)) {
      throw new UserExistsException("User already exists");
    }
    userList.add(user);
  }


  /**
   * Mutator method that adds a new driver to the system.
   * 
   * @param name (String)
   * @param carModel (String)
   * @param carLicencePlate (String)
   * @param address (String)
   */
  public void registerNewDriver(String name, String carModel, String carLicencePlate, String address) {
    // Check to ensure name is valid
    if (name == null || name.equals("")) {
      throw new InvalidNameException("Invalid name");
    }
    // Check to ensure car models is valid
    if (carModel == null || carModel.equals("")) {
      throw new InvalidCarModelException("Invalid car model");
    }
    // Check to ensure car licence plate is valid
    // i.e. not null or empty string
    if (carLicencePlate == null || carLicencePlate.equals("")) {
      throw new InvalidLicensePlateException("Invalid license plate");
    }
    // Check to ensure address is valid
    // i.e. not null or empty string or invalid syntax for address
    if (address == null || address.equals("") || !CityMap.validAddress(address)) {
      throw new InvalidAddressException("Invalid address");
    }
    // Check for duplicate driver. If not a duplicate, add the driver to the drivers list
    Driver driver = new Driver(generateDriverId(), name, carModel, carLicencePlate, address);
    if (driverExists(driver)) {
      throw new DriverExistsException("Driver already exists");
    }
    drivers.add(driver);
  }


  /**
   * Mutator method that requests a ride.
   * User wallet will be reduced when drop off happens.
   * 
   * @param accountId (String)
   * @param from (String)
   * @param to (String)
   */
  public void requestRide(String accountId, String from, String to) {
    // Check valid user account
    User user = getUser(accountId);
    if (user == null) {
      throw new UserNotFoundException("User account not found");
    }
    // Check for a valid from and to addresses
    if (!CityMap.validAddress(from)) {
      throw new InvalidAddressException("Invalid from address");
    }
    if (!CityMap.validAddress(to)) {
      throw new InvalidAddressException("invalid to address");
    }
    // Get the zone for the from address
    int zone = CityMap.getCityZone(from);
    // Get the distance for this ride
    int distance = CityMap.getDistance(from, to); // city blocks
    // Distance == 0 or == 1 is not accepted - walk!
    if (!(distance > 1)) {
      throw new InsufficientDistanceException("Insufficient travel distance");
    }
    // Check if user has enough money in wallet for this trip
    double cost = getRideCost(distance);
    if (user.getWallet() < cost) {
      throw new InsufficientFundsException("Insufficient funds");
    }
    // Get an available driver
    Driver driver = getAvailableDriver();
    if (driver == null) {
      throw new DriverNotFoundException("Driver not found");
    }
    // Create the request
    TMUberRide req = new TMUberRide(from, to, user, distance, cost);

    // Check if existing ride request for this user - only one ride request per user at a time
    if (existingRequest(req)) {
      throw new ExistingRideRequestException("Existing ride request for user");
    }
    serviceRequests[zone].add(req);
    user.addRide();
  }


  /**
   * Mutator method that requests a food delivery.
   * User wallet will be reduced when drop off happens.
   * 
   * @param accountId (String)
   * @param from (String)
   * @param to (String)
   * @param restaurant (String)
   * @param foodOrderId (String)
   */
  public void requestDelivery(String accountId, String from, String to, String restaurant, String foodOrderId) {
    // Check for valid user account
    User user = getUser(accountId);
    if (user == null) {
      throw new UserNotFoundException("User account not found");
    }
    // Check for valid from and to address
    if (!CityMap.validAddress(from)) {
      throw new InvalidAddressException("Invalid from address");
    }
    if (!CityMap.validAddress(to)) {
      throw new InvalidAddressException("Invalid to address");
    }
    // Get the zone for the from address
    int zone = CityMap.getCityZone(from);
    // Get the distance to travel
    int distance = CityMap.getDistance(from, to); // city blocks
    // Distance must be at least 1 city block
    if (distance == 0) {
      throw new InsufficientDistanceException("Insufficient delivery distance");
    }
    // Check if user has enough money in wallet for this delivery
    double cost = getDeliveryCost(distance);
    if (user.getWallet() < cost) {
      throw new InsufficientFundsException("Insufficient funds");
    }
    // Find an available driver, if any
    Driver driver = getAvailableDriver();
    if (driver == null) {
      throw new DriverNotFoundException("Driver not found");
    }
    TMUberDelivery delivery = new TMUberDelivery(from, to, user, distance, cost, restaurant, foodOrderId);
    // Check if existing delivery request for this user for this restaurant and food
    // order #
    if (existingRequest(delivery)) {
      throw new ExistingDeliveryRequestException("Existing delivery request for user");
    }
    serviceRequests[zone].add(delivery);
    user.addDelivery();
  }


  /**
   * Mutator method that cancels an existing service request.
   * Paramater zone is the index in the service requests array of queues.
   * Parameter request is the index.
   * 
   * @param zone (int)
   * @param request (int)
   */
  public void cancelServiceRequest(int zone, int request) {
    // Check if valid zone #
    if (zone < 0 || zone > 3) {
      throw new InvalidZoneException("Invalid zone number");
    }
    // Make Queue of index zone in serviceRequests
    Queue<TMUberService> zoneRequests = serviceRequests[zone];
    if (zoneRequests.isEmpty()) {
      throw new NoServiceRequestException("No service request");
    }
    // Check if valid request #
    if (request < 1 || request > zoneRequests.size()) {
      throw new InvalidRequestException("Invalid request number");
    }
    // Loop through zone requests and remove specified request #
    Iterator<TMUberService> iterator = zoneRequests.iterator();
    for (int i = 1; i < request; i++) {
      iterator.next();
    }
    iterator.remove();
  }


  /**
   * Pickup mutator method that takes in driver ID as parameter.
   * Takes service object and removes it from queue.
   * 
   * @param driverId (String)
   */
  public void pickup(String driverId) {
    // Initialize a driver variable to null
    Driver myDriver = null;
    // Loop through drivers
    for (Driver driver: drivers) {
      // If driver id equals our method parameter, set local variable to driver
      if (driver.getId().equals(driverId)) {
        myDriver = driver;
        break;
      }
    }
    // Check if our driver variable is still null and if so, throw exception
    if (myDriver == null) {
      throw new DriverNotFoundException("Driver not found");
    }
    // Driver zone found through driver address
    int zone = CityMap.getCityZone(myDriver.getAddress());
    Queue<TMUberService> queue = serviceRequests[zone];
    // Check if queue is empty
    if (queue.isEmpty()) {
      throw new NoServiceRequestException("No Service Request in Zone " + zone);
    }
    // TMUberService object removed from front of queue
    TMUberService service = queue.poll();
    myDriver.setService(service);
    myDriver.setStatus(Driver.Status.DRIVING);
    myDriver.setAddress(service.getFrom());
    System.out.print("Driver " + driverId + " Picking Up in Zone " + zone);
  }


  /**
   * Mutator method that drops off a ride or delivery. This completes a service.
   * Parameter driverId is the given driver ID.
   * 
   * @param driverId (String)
   */
  public void dropOff(String driverId) {
    // Use the given driver id to find the Driver object
    Driver driver = null;
    // Loop through drivers until driver id equals method parameter and driver is driving
    for (int i = 0; i < drivers.size(); i++) {
      if (drivers.get(i).getId().equals(driverId) && drivers.get(i).getStatus() == Driver.Status.DRIVING) {
        driver = drivers.get(i);
        break;
      }
    }
    if (driver == null) {
      throw new DriverNotFoundException("Driver not found");
    }
    TMUberService service = driver.getService();
    if (service == null) {
      throw new NoServiceRequestException("No service request");
    }
    totalRevenue += service.getCost(); // add service cost to revenues
    driver.pay(service.getCost() * PAYRATE); // pay the driver
    totalRevenue -= service.getCost() * PAYRATE; // deduct driver fee from total revenues
    driver.setStatus(Driver.Status.AVAILABLE); // driver is now available again
    User user = service.getUser();
    user.payForService(service.getCost()); // user pays for ride or delivery
    String address = service.getTo();
    driver.setAddress(address); // set driver address to service's to address
    driver.setZone(CityMap.getCityZone(address)); // set driver zone
    System.out.print("Driver " + driverId + " Dropping Off");
  }


  /**
   * driveTo mutator method takes in a driverId and address.
   * Driver drives to address.
   * 
   * @param driverId (String)
   * @param address (String)
   */
  public void driveTo(String driverId, String address) {
    Driver myDriver = null;
    // Check for valid address
    if (!CityMap.validAddress(address)) {
      throw new InvalidAddressException("Invalid address");
    }
    // Check for driver that has same driverId as parameter
    for (Driver driver: drivers) {
      if (driver.getId().equals(driverId) && driver.getStatus() == Driver.Status.AVAILABLE) {
        myDriver = driver;
      }
    }
    // Check if our driver variable is null and throw exception
    if (myDriver == null) {
      throw new DriverNotFoundException("Driver not found");
    }
    myDriver.setAddress(address);
    myDriver.setZone(CityMap.getCityZone(address));
    System.out.print("Driver " + driverId + " Now in Zone " + CityMap.getCityZone(address));
  }


  /**
   * Mutator method that sorts users by name.
   */
  public void sortByUserName() {
    // Loop through the users map and add each value item to array list userList
    for (String userid: users.keySet()) {
      userList.add(users.get(userid));
    }
    Collections.sort(userList, new NameComparator());
    users.clear();
    setUsers(userList);
    listAllUsers();
  }


  /**
   * This private class implements Comparator for data type User.
   * Used for sortByUserName() method.
   */
  private class NameComparator implements Comparator<User> {
    public int compare(User a, User b) {
      return a.getName().compareTo(b.getName());
    }
  }


  /**
   * Mutator method that sorts users by amount in wallet.
   */
  public void sortByWallet() {
    // Loop through the users map and add each value item to array list userList
    for (String userid: users.keySet()) {
      userList.add(users.get(userid));
    }
    Collections.sort(userList, new UserWalletComparator());
    users.clear();
    setUsers(userList);
    listAllUsers();
  }


  /**
   * This private class implements Comparator for data type user.
   * Used for sortByWallet() method.
   */
  private class UserWalletComparator implements Comparator<User> {
    public int compare(User a, User b) {
      if (a.getWallet() > b.getWallet())
        return 1;
      if (a.getWallet() < b.getWallet())
        return -1;
      return 0;
    }
  }


  /**
   * Mutator method that adds key and value pairs to Linked HashMap of users.
   * 
   * @param userList (ArrayList<User>)
   */
  public void setUsers(ArrayList<User> userList) {
    // Loop through userList
    for (User user: userList) {
      // Key is account id and value is simply user
      users.put(user.getAccountId(), user);
    }
  }


  /**
   * Mutator method that sets all drivers in drivers ArrayList.
   * 
   * @param drivers (ArrayList<Driver>)
   */
  public void setDrivers(ArrayList<Driver> drivers) {
    this.drivers = drivers;
  }
}


/**
 * Custom exception class InvalidNameException.
 * Exception is thrown when invalid names are used in declaration.
 */
class InvalidNameException extends RuntimeException {
  public InvalidNameException() {}
  public InvalidNameException(String message) {
      super(message);
  }
}


/**
 * Custom exception class InvalidAddressException.
 * Exception is thrown when invalid addresses are used in declaration.
 */
class InvalidAddressException extends RuntimeException {
  public InvalidAddressException() {}
  public InvalidAddressException(String message) {
    super(message);
  }
}


/**
 * Custom exception class InvalidUserWalletException.
 * Exception is thrown when invalid user wallet amounts are used in declaration.
 */
class InvalidUserWalletException extends RuntimeException {
  public InvalidUserWalletException() {}
  public InvalidUserWalletException(String message) {
    super(message);
  }
}


/**
 * Custom exception class UserExistsException.
 * Exception is thrown when user already exists in user declaration.
 */
class UserExistsException extends RuntimeException {
  public UserExistsException() {}
  public UserExistsException(String message) {
    super(message);
  }
}


/**
 * Custom exception class InvalidCarModelException.
 * Exception is thrown when invalid car models are used in declaration.
 */
class InvalidCarModelException extends RuntimeException {
  public InvalidCarModelException() {}
  public InvalidCarModelException(String message) {
    super(message);
  }
}


/**
 * Custom exception class InvalidLicensePlateException.
 * Exception is thrown when invalid license plates are used in declaration.
 */
class InvalidLicensePlateException extends RuntimeException {
  public InvalidLicensePlateException() {}
  public InvalidLicensePlateException(String message) {
    super(message);
  }
}


/**
 * Custom exception class DriverExistsException.
 * Exception is thrown when driver already exists in driver declaration.
 */
class DriverExistsException extends RuntimeException {
  public DriverExistsException() {}
  public DriverExistsException(String message) {
    super(message);
  }
}


/**
 * Custom exception class UserNotFoundException.
 * Exception is thrown when user is not found in search for user.
 */
class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {}
  public UserNotFoundException(String message) {
    super(message);
  }
}


/**
 * Custom exception class InsufficientDistanceException.
 * Exception is thrown when user is too close to destination or restaurant.
 */
class InsufficientDistanceException extends RuntimeException {
  public InsufficientDistanceException() {}
  public InsufficientDistanceException(String message) {
    super(message);
  }
}


/**
 * Custom exception class InsufficientFundsException.
 * Exception is thrown when user has insufficient funds in wallet declaration.
 */
class InsufficientFundsException extends RuntimeException {
  public InsufficientFundsException() {}
  public InsufficientFundsException(String message) {
    super(message);
  }
}


/**
 * Custom exception class DriverNotFoundException.
 * Exception is thrown when driver is not found in search for driver.
 */
class DriverNotFoundException extends RuntimeException {
  public DriverNotFoundException() {}
  public DriverNotFoundException(String message) {
      super(message);
  }
}


/**
 * Custom exception class ExistingRideRequestException.
 * Exception is thrown when ride request already exists in ride request declaration.
 */
class ExistingRideRequestException extends RuntimeException {
  public ExistingRideRequestException() {}
  public ExistingRideRequestException(String message) {
    super(message);
  }
}


/**
 * Custom exception class ExistingDeliveryRequestException.
 * Exception is thrown when delivery request already exists in delivery request declaration.
 */
class ExistingDeliveryRequestException extends RuntimeException {
  public ExistingDeliveryRequestException() {}
  public ExistingDeliveryRequestException(String message) {
    super(message);
  }
}


/**
 * Custom exception class InvalidZoneException.
 * Exception is thrown when zone is invalid in request declaration.
 */
class InvalidZoneException extends RuntimeException {
  public InvalidZoneException() {}
  public InvalidZoneException(String message) {
    super(message);
  }
}


/**
 * Custom exception class NoServiceRequestException.
 * Exception is thrown when there is no service in declaration.
 */
class NoServiceRequestException extends RuntimeException {
  public NoServiceRequestException() {}
  public NoServiceRequestException(String message) {
    super(message);
  }
}


/**
 * Custom exception class InvalidRequestException.
 * Exception is thrown when request is invalid.
 */
class InvalidRequestException extends RuntimeException {
  public InvalidRequestException() {}
  public InvalidRequestException(String message) {
    super(message);
  }
}
