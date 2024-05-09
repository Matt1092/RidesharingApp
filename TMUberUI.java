// Import statements
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * This class is a simulation of a Simple Command-line based Uber App.
 * This system supports "ride sharing" service and a delivery service.
 *
 * @author Matthew Moga
 * @version April 12, 2024
 */
public class TMUberUI {
  /**
    * This method defines the mainline logic for our Uber loop. 
    * It uses a SystemManager object and various helper methods to run the app.
    * 
    * @param args (String[])
    */
  public static void main(String[] args) {
    // Create the System Manager - the main system code is in here
    TMUberSystemManager tmuber = new TMUberSystemManager();

    Scanner scanner = new Scanner(System.in);
    System.out.print(">");

    // Process keyboard actions
    while (scanner.hasNextLine()) {
      String action = scanner.nextLine();

      // Put all the commands into a try statement
      try {
        if (action == null || action.equals("")) {
          System.out.print("\n>");
          continue;
        }
        // Quit the App
        else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
          return;
        // Print all the registered drivers
        else if (action.equalsIgnoreCase("DRIVERS")) // List all drivers
        {
          tmuber.listAllDrivers();
        }
        // Print all the registered users
        else if (action.equalsIgnoreCase("USERS")) // List all users
        {
          tmuber.listAllUsers();
        }
        // Print all current ride requests or delivery requests
        else if (action.equalsIgnoreCase("REQUESTS")) // List all requests
        {
          tmuber.listAllServiceRequests();
        }
        // Pickup method in system manager
        else if (action.equalsIgnoreCase("PICKUP")) {
          String driverId = "";
          System.out.print("Driver Id: ");
          if (scanner.hasNextLine()) {
            driverId = scanner.nextLine();
          }
          tmuber.pickup(driverId);
        }
        // Loads users from a file
        else if (action.equalsIgnoreCase("LOADUSERS")) {
          String filename = "";
          try {
            System.out.print("User File: ");
            if (scanner.hasNextLine()) {
              filename = scanner.nextLine();
            }
            tmuber.setUsers(TMUberRegistered.loadPreregisteredUsers(filename));
            System.out.println("Users Loaded");
          }
          catch (FileNotFoundException e) {
            System.out.print("Users File: " + filename + " Not Found");
          }
          catch (Exception e) {
            e.printStackTrace();
            return;
          }
        }
        // Loads drivers from a file
        else if (action.equalsIgnoreCase("LOADDRIVERS")) {
          String filename = "";
          try {
            System.out.print("Driver File: ");
            if (scanner.hasNextLine()) {
              filename = scanner.nextLine();
            }
            tmuber.setDrivers(TMUberRegistered.loadPreregisteredDrivers(filename));
            System.out.println("Drivers Loaded");
          }
          catch (FileNotFoundException e) {
            System.out.print("Drivers File: " + filename + " Not Found");
          }
          catch (Exception e) {
            e.printStackTrace();
            return;
          }
        }
        // Drive to method in system manager
        else if (action.equalsIgnoreCase("DRIVETO")) {
          String driverId = "";
          String address = "";
          System.out.print("Driver Id: ");
          if (scanner.hasNextLine()) {
            driverId = scanner.nextLine();
            System.out.print("Address: ");
            address = scanner.nextLine();
          }
          tmuber.driveTo(driverId, address);
        }

        // Register a new driver
        else if (action.equalsIgnoreCase("REGDRIVER")) {
          String name = "";
          System.out.print("Name: ");
          if (scanner.hasNextLine()) {
            name = scanner.nextLine();
          }
          String carModel = "";
          System.out.print("Car Model: ");
          if (scanner.hasNextLine()) {
            carModel = scanner.nextLine();
          }
          String license = "";
          System.out.print("Car License: ");
          if (scanner.hasNextLine()) {
            license = scanner.nextLine();
          }
          String address = "";
          System.out.print("Address: ");
          if (scanner.hasNextLine()) {
            address = scanner.nextLine();
          }
          tmuber.registerNewDriver(name, carModel, license, address);
          System.out.printf("Driver: %-15s Car Model: %-15s License Plate: %-10s", name, carModel, license, address);
        }
        // Register a new user
        else if (action.equalsIgnoreCase("REGUSER")) {
          String name = "";
          System.out.print("Name: ");
          if (scanner.hasNextLine()) {
            name = scanner.nextLine();
          }
          String address = "";
          System.out.print("Address: ");
          if (scanner.hasNextLine()) {
            address = scanner.nextLine();
          }
          double wallet = 0.0;
          System.out.print("Wallet: ");
          if (scanner.hasNextDouble()) {
            wallet = scanner.nextDouble();
            scanner.nextLine(); // consume nl
          }
          tmuber.registerNewUser(name, address, wallet);
          System.out.printf("User: %-15s Address: %-15s Wallet: %2.2f", name, address, wallet);
        }
        // Request a ride
        else if (action.equalsIgnoreCase("REQRIDE")) {
          String account = "";
          System.out.print("User Account Id: ");
          if (scanner.hasNextLine()) {
            account = scanner.nextLine();
          }
          String from = "";
          System.out.print("From Address: ");
          if (scanner.hasNextLine()) {
            from = scanner.nextLine();
          }
          String to = "";
          System.out.print("To Address: ");
          if (scanner.hasNextLine()) {
            to = scanner.nextLine();
          }
          tmuber.requestRide(account, from, to);
          User user = tmuber.getUser(account);
          System.out.printf("\nRIDE for: %-15s From: %-15s To: %-15s", user.getName(), from, to);
        }
        // Request a food delivery
        else if (action.equalsIgnoreCase("REQDLVY")) {
          String account = "";
          System.out.print("User Account Id: ");
          if (scanner.hasNextLine()) {
            account = scanner.nextLine();
          }
          String from = "";
          System.out.print("From Address: ");
          if (scanner.hasNextLine()) {
            from = scanner.nextLine();
          }
          String to = "";
          System.out.print("To Address: ");
          if (scanner.hasNextLine()) {
            to = scanner.nextLine();
          }
          String restaurant = "";
          System.out.print("Restaurant: ");
          if (scanner.hasNextLine()) {
            restaurant = scanner.nextLine();
          }
          String foodOrder = "";
          System.out.print("Food Order #: ");
          if (scanner.hasNextLine()) {
            foodOrder = scanner.nextLine();
          }
          tmuber.requestDelivery(account, from, to, restaurant, foodOrder);
          User user = tmuber.getUser(account);
          System.out.printf("\nDELIVERY for: %-15s From: %-15s To: %-15s", user.getName(), from, to);
        }
        // Sort users by name
        else if (action.equalsIgnoreCase("SORTBYNAME")) {
          tmuber.sortByUserName();
        }
        // Sort users by number of ride they have had
        else if (action.equalsIgnoreCase("SORTBYWALLET")) {
          tmuber.sortByWallet();
        }
        // Cancel a current service (ride or delivery) request
        else if (action.equalsIgnoreCase("CANCELREQ")) {
          int zone = -1;
          int request = -1;
          System.out.print("Zone #: ");
          if (scanner.hasNextInt()) {
            zone = scanner.nextInt();
            scanner.nextLine(); // consume nl character
          }
          if (scanner.hasNextInt()) {
            request = scanner.nextInt();
          }
          scanner.nextLine(); // consume n1 character
          tmuber.cancelServiceRequest(zone, request);
          System.out.println("Service request #" + request + " cancelled");
        }
        // Drop-off the user or the food delivery to the destination address
        else if (action.equalsIgnoreCase("DROPOFF")) {
          // Instead of a request number, takes a (string) driverId
          //int request = -1;
          String driverId = "";
          System.out.print("Driver Id: ");
          if (scanner.hasNextLine()) {
            //request = scanner.nextInt();
            driverId = scanner.nextLine();
          }
          tmuber.dropOff(driverId);
        }
        // Get the Current Total Revenues
        else if (action.equalsIgnoreCase("REVENUES")) {
          System.out.println("Total Revenue: " + tmuber.totalRevenue);
        }
        // Unit Test of Valid City Address
        else if (action.equalsIgnoreCase("ADDR")) {
          String address = "";
          System.out.print("Address: ");
          if (scanner.hasNextLine()) {
            address = scanner.nextLine();
          }
          System.out.print(address);
          if (CityMap.validAddress(address))
            System.out.println("\nValid Address");
          else
            System.out.println("\nBad Address");
        }
        // Unit Test of CityMap Distance Method
        else if (action.equalsIgnoreCase("DIST")) {
          String from = "";
          System.out.print("From: ");
          if (scanner.hasNextLine()) {
            from = scanner.nextLine();
          }
          String to = "";
          System.out.print("To: ");
          if (scanner.hasNextLine()) {
            to = scanner.nextLine();
          }
          System.out.print("\nFrom: " + from + " To: " + to);
          System.out.println("\nDistance: " + CityMap.getDistance(from, to) + " City Blocks");
        }
      }
      // Catch statement for all the possible Runtime errors
      // Prints out the custom error in TMUberSystemManager
      catch (RuntimeException e) {
        System.out.println(e.getMessage());
      }

      System.out.print("\n>");
    }
  }
}
