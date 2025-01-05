# RidesharingApp

This project is a simulation of a simple Uber-like application developed as part of a university project. It includes various functionalities such as ride-sharing and food delivery services, implemented using different data structures to represent service requests and drivers.

## Features

- **CityMap**: Represents the logic for the address, block, and zone functions within a 9x9 grid of city blocks.
- **Driver**: Simulates a car driver with attributes like ID, name, car model, license plate, address, and status.
- **TMUberDelivery**: Simulates a food delivery service with attributes for restaurant and food order ID.
- **TMUberRegistered**: Registers all drivers and users through file input.
- **TMUberRide**: Simulates a ride service with attributes for number of passengers and whether an XL ride is requested.
- **TMUberService**: Abstract class that generalizes ride or delivery services.
- **TMUberSystemManager**: Contains the main logic of the system, tracking users, drivers, and service requests.
- **TMUberUI**: Command-line interface for interacting with the system.
- **User**: Simulates a user with attributes like account ID, name, address, wallet, rides, and deliveries.

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/Matt1092/RidesharingApp.git
   ```
2. Navigate to the project directory:
   ```sh
   cd RidesharingApp
   ```
3. Compile the Java files:
   ```sh
   javac *.java
   ```

## Usage

1. Run the application:
   ```sh
   java TMUberUI
   ```
2. Use the following commands to interact with the application:

    `DRIVERS`: List all registered drivers.
    `USERS`: List all registered users.
    `REQUESTS`: List all current ride or delivery requests.
    `PICKUP`: Pickup a ride or delivery by driver ID.
    `LOADUSERS`: Load users from a file.
    `LOADDRIVERS`: Load drivers from a file.
    `DRIVETO`: Drive to a specified address by driver ID.
    `REGDRIVER`: Register a new driver.
    `REGUSER`: Register a new user.
    `REQRIDE`: Request a ride.
    `REQDLVY`: Request a food delivery.
    `SORTBYNAME`: Sort users by name.
    `SORTBYWALLET`: Sort users by wallet amount.
    `CANCELREQ`: Cancel a current service request.
    `DROPOFF: Drop off a ride or delivery.
    `REVENUES`: Display total revenue.
    `ADDR`: Validate a city address.
    `DIST`: Calculate the distance between two addresses.
    `Q` or `QUIT`: Quit the application.

