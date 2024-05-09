/**
 * This class simulates a user of a simple Uber app.
 *
 * @author Matthew Moga
 * @version April 12, 2024
 */
public class User {
  private String accountId;
  private String name;
  private String address;
  private double wallet; // load up with money
  private int rides;
  private int deliveries;


  /**
   * This constructor initializes all the User instance variables.
   * 
   * @param id (String)
   * @param name (String)
   * @param address (String)
   * @param wallet (double)
   */
  public User(String id, String name, String address, double wallet) {
    this.accountId = id;
    this.name = name;
    this.address = address;
    this.wallet = wallet;
    this.rides = 0;
    this.deliveries = 0;
  }


  /**
   * This accessor returns the account ID of user.
   *
   * @return int - accountId
   */
  public String getAccountId() {
    return accountId;
  }


  /**
   * This mutator sets the account ID of user.
   * 
   * @param accountId (String)
   */
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }


  /**
   * This accessor returns the name of user. 
   * 
   * @return int - name
   */
  public String getName() {
    return name;
  }


  /**
   * This mutator sets the name of user.
   * 
   * @param name (String)
   */
  public void setName(String name) {
    this.name = name;
  }


  /**
   * This accessor returns the address of user.
   * 
   * @return String - address
   */
  public String getAddress() {
    return address;
  }


  /**
   * This mutator sets the address of user.
   * 
   * @param address (String)
   */
  public void setAddress(String address) {
    this.address = address;
  }


  /**
   * This accessor returns the wallet amount of user.
   * 
   * @return double - wallet
   */
  public double getWallet() {
    return wallet;
  }


  /**
   * This mutator sets the wallet amount of user.
   * 
   * @param wallet (double)
   */
  public void setWallet(int wallet) {
    this.wallet = wallet;
  }


  /**
   * This accessor returns the rides of user.
   * 
   * @return int - rides
   */
  public int getRides() {
    return rides;
  }


  /**
   * This mutator increments ride by one.
   */
  public void addRide() {
    this.rides++;
  }


  /**
   * This mutator increments deliveries by one.
   */
  public void addDelivery() {
    this.deliveries++;
  }


  /**
   * This accessor returns deliveries.
   * 
   * @return int - deliveries
   */
  public int getDeliveries() {
    return deliveries;
  }


  /**
   * This mutator pays for the cost of the service.
   * This method assumes that there are sufficient funds in the wallet.
   * 
   * @param cost (double)
   */
  public void payForService(double cost) {
    wallet -= cost;
  }


  /**
   * This mutator prints information about a User.
   */
  public void printInfo() {
    System.out.printf("Id: %-5s Name: %-15s Address: %-15s Wallet: %2.2f", accountId, name, address, wallet);
  }


  /**
   * Two users are equal if they have the same name and address.
   * This method is overriding the inherited method in superclass Object.
   * 
   * @param other (Object)
   * 
   * @return boolean - If two users are equal or not
   */
  public boolean equals(Object other) {
    User otherUser = (User) other;
    return this.name.equals(otherUser.name) && this.address.equals(otherUser.address);
  }
}
