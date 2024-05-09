# Uber
What works:
My program compiles. The TMUberUI runs without any bugs or errors.
A warning pops up when I compile the TMUberUI the first time but it compiles and runs smoothly after the 2nd compilation. I was advised by the professor to ignore this "Recompile with -Xlint:unchecked for details." It isn't an error, but rather a warning. My code compiles and the TMUberUI is able to run.

My program is able to load in users.txt and drivers.txt files and has the necessary exception handling for loading in other files.
I have several different customized exceptions such as InvalidNameException, DriverNotFoundException, NoServiceRequestException, etc. These are thrown in TMUberSystemManager and caught in TMUberUI in a large try-catch statement.

I have a serviceRequests array of 4 queues that can add and remove the service requests from each zone queue. My pickup functionality works.
I implemented a Linked HashMap for User objects. I completed the sort methods will the help of a userList arrayList instance variable. By looping through the HashMap and adding each value item to the userList, I was able to sort the userList depending on wallet or name, clear the users HashMap and then set the users to the sorted userList.
TMUberSystemManager has the necessary changes and modifications to the program.
