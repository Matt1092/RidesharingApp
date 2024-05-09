/**
 * 
 * This class simulates a car driver in a simple Uber-like app.
 * 
 * @author Matthew Moga
 * @version April 12, 2024
 *
 */
public class Driver {
  private String id;
  private String name;
  private String carModel;
  private String licensePlate;
  private double wallet;
  private String type;
  // New instance variables
  private TMUberService service;
  private String address;
  private int zone;
  public static enum Status {
    AVAILABLE, DRIVING
  };
  private Status status;


  /**
   * This constructor initializes all the Driver instance variables.
   * 
   * @param id (String)
   * @param name (String)
   * @param carModel (String)
   * @param licensePlate (String)
   * @param address (String)
   */
  public Driver(String id, String name, String carModel, String licensePlate, String address) {
    this.id = id;
    this.name = name;
    this.carModel = carModel;
    this.licensePlate = licensePlate;
    this.status = Status.AVAILABLE;
    this.wallet = 0;
    this.type = "";
    this.service = service;
    this.address = address;
    this.zone = zone;
  }


  /**
   * Mutator method prints information about a driver.
   */
  public void printInfo() {
    int zone = CityMap.getCityZone(address);
    System.out.printf("Id: %-3s Name: %-15s Car Model: %-15s License Plate: %-10s Wallet: %2.2f%nStatus: %-10s Address: %-15s Zone:%2d%n",
        id, name, carModel, licensePlate, wallet, status, address, zone);
  }


  /**
   * Accessor returns type.
   * 
   * @return String - type
   */
  public String getType() {
    return type;
  }


  /**
   * Mutator method sets type.
   * 
   * @param type (String)
   */
  public void setType(String type) {
    this.type = type;
  }


  /**
   * Accessor returns ID.
   * 
   * @return String - ID
   */
  public String getId() {
    return id;
  }


  /**
   * Mutator method sets ID.
   * 
   * @param id (String)
   */
  public void setId(String id) {
    this.id = id;
  }


  /**
   * Accessor returns name.
   * 
   * @return String - name
   */
  public String getName() {
    return name;
  }


  /**
   * Mutator method sets name.
   * 
   * @param name (String)
   */
  public void setName(String name) {
    this.name = name;
  }


  /**
   * Accessor returns car model.
   * 
   * @return String - car model
   */
  public String getCarModel() {
    return carModel;
  }


  /**
   * Mutator method sets car model.
   * 
   * @param carModel (String)
   */
  public void setCarModel(String carModel) {
    this.carModel = carModel;
  }


  /**
   * Accessor returns license plate.
   * 
   * @return String - license plate
   */
  public String getLicensePlate() {
    return licensePlate;
  }


  /**
   * Mutator method sets license plate.
   * 
   * @param licensePlate (String)
   */
  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }


  /**
   * Accessor returns status.
   * 
   * @return Status - AVAILABLE OR DRIVING
   */
  public Status getStatus() {
    return status;
  }


  /**
   * Mutator method sets status.
   * 
   * @param status (Status)
   */
  public void setStatus(Status status) {
    this.status = status;
  }


  /**
   * Accessor returns wallet amount.
   * 
   * @return double - wallet amount
   */
  public double getWallet() {
    return wallet;
  }


  /**
   * Mutator method sets wallet amount.
   * 
   * @param wallet (double)
   */
  public void setWallet(double wallet) {
    this.wallet = wallet;
  }
  

  /**
   * Accessor returns service type.
   * 
   * @return TMUberService - service type
   */
  public TMUberService getService() {
    return service;
  }


  /**
   * Mutator method sets service type.
   * 
   * @param service (TMUberService)
   */
  public void setService(TMUberService service) {
    this.service = service;
  }


  /**
   * Accessor returns address.
   * 
   * @return String - address
   */
  public String getAddress() {
    return address;
  }


  /**
   * Mutator method sets address.
   * 
   * @param address (String)
   */
  public void setAddress(String address) {
    this.address = address;
  }


  /**
   * Accessor method returns zone.
   * 
   * @return int - zone
   */
  public int getZone() {
    return zone;
  }


  /**
   * Mutator method sets zone.
   * 
   * @param zone (int)
   */
  public void setZone(int zone) {
    this.zone = zone;
  }


  /**
   * Two drivers are equal if they have the same name and license plates.
   * This method is overriding the inherited method in superclass Object.
   * 
   * @param other (Object)
   * 
   * @return boolean - true or false depending on the conditions checking
   */
  public boolean equals(Object other) {
    Driver otherDriver = (Driver) other;
    return this.name.equals(otherDriver.name) &&
        this.licensePlate.equals(otherDriver.licensePlate);
  }


  /**
   * Mutator method sets the amount to pay for ride or delivery.
   * Driver earns this fee.
   * 
   * @param fee (double)
   */
  public void pay(double fee) {
    wallet += fee;
  }
}
