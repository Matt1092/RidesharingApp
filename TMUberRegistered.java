// NAME: MATTHEW MOGA
// STUDENT NUMBER: 501253529
import java.util.ArrayList;
import java.util.Scanner;

// Added import statement for file reading
import java.io.File;
// Added import statement for exception handling
import java.io.IOException;

public class TMUberRegistered {
    // These variables are used to generate user account and driver ids
    private static int firstUserAccountID = 900;
    private static int firstDriverId = 700;

    // Generate a new user account id
    public static String generateUserAccountId(ArrayList<User> current) {
        return "" + firstUserAccountID + current.size();
    }

    // Generate a new driver id
    public static String generateDriverId(ArrayList<Driver> current) {
        return "" + firstDriverId + current.size();
    }

    // Database of Preregistered users
    // In Assignment 2 these will be loaded from a file
    // The test scripts and test outputs included with the skeleton code use these
    // users and drivers below. You may want to work with these to test your code
    // (i.e. check your output with the
    // sample output provided).
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

    // Database of Preregistered users
    // In Assignment 2 these will be loaded from a file
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
