// Import statements
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;


/**
 * 
 * This class registers all drivers and users through file input.
 * Takes users and drivers from a file, reads file then adds them to ArrayList.
 * 
 * @author Matthew Moga
 * @version April 12, 2024
 * 
 */
public class TMUberRegistered {
    // These variables are used to generate user account and driver ids
    private static int firstUserAccountID = 900;
    private static int firstDriverId = 700;


    /**
     * Method that generates a new user account ID.
     * 
     * @param current (ArrayList<User>)
     * 
     * @return String - user account ID
     */
    public static String generateUserAccountId(ArrayList<User> current) {
        return "" + firstUserAccountID + current.size();
    }


    /**
     * Method that generates a new driver ID.
     * 
     * @param current (ArrayList<Driver>)
     * 
     * @return String - driver ID
     */
    public static String generateDriverId(ArrayList<Driver> current) {
        return "" + firstDriverId + current.size();
    }


    /**
     * Loads users from a file. Reads through each line in file and creates User object.
     * Adds each User object to ArrayList of users then returs this list of users.
     * 
     * @param filename (String)
     * 
     * @return ArrayList<User> - list of users that are pre-registered
     * 
     * @throws IOException
     */
    public static ArrayList<User> loadPreregisteredUsers(String filename) throws IOException {
        // Create array list of users
        ArrayList<User> users = new ArrayList<User>();
        // Create a file that takes in filename as file name
        File usersFile = new File(filename);
        Scanner scanner = new Scanner(usersFile);
        while (scanner.hasNextLine()) {
            // Name is the first line in file
            String name = scanner.nextLine();
            // Next line is address
            String address = scanner.nextLine();
            // Parse the next line into a double value for wallet
            double wallet = Double.parseDouble(scanner.nextLine());
            // Add user
            users.add(new User(generateUserAccountId(users), name, address, wallet));
        }
        // Close scanner to prevent data leak
        scanner.close();
        return users;
    }


    /**
     * Loads drivers from a file. Reads through each line in file and creates Driver object.
     * Adds each Driver object to ArrayList of drivers then returns this list of drivers.
     * 
     * @param filename (String)
     * 
     * @return ArrayList<Driver> - list of drivers that are pre-registered
     * 
     * @throws IOException
     */
    public static ArrayList<Driver> loadPreregisteredDrivers(String filename) throws IOException {
        // Create array list of drivers
        ArrayList<Driver> drivers = new ArrayList<Driver>();
        // Create a file that takes in filename as file name
        File driversFile = new File(filename);
        Scanner scanner = new Scanner(driversFile);
        while (scanner.hasNextLine()) {
            // Name is the first line in file
            String name = scanner.nextLine();
            // Next line is car model
            String carModel = scanner.nextLine();
            // Next line is car license
            String carLicense = scanner.nextLine();
            // Next line is address
            String address = scanner.nextLine();
            // Add driver
            drivers.add(new Driver(generateDriverId(drivers), name, carModel, carLicense, address));
        }
        // Close scanner to prevent data leak
        scanner.close();
        return drivers;
    }
}
