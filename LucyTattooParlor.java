/**
 * Lucy's Tattoo Parlor Project 3

 * Ali Sammour
 * UFID: 1247-6981
 * 
 * Scope: Create a tattoo parlor system that will manage customers
 * and help assign them to a specific artist based off their preference
 * and available times
 */

import java.util.Scanner;

public class LucyTattooParlor {

	/**
	 * Main method: Takes in arguments for artist count and customer count
	 * Creates a menu with options for the customer at the parlor
	 * Other methods used to add customers based off the preference and print a waitlist
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Forming the array for the Tattoo Parlor
		int artistCount = Integer.parseInt(args[0]);//First argument in list is amount of artists to work
		int customerCount = Integer.parseInt(args[1]);//Second argument in list is amount of customers they can take
		TattooCustomer[][] tattooParlor = new TattooCustomer[artistCount][customerCount]; //Creating the array to proper size of artists and customers
		
		//Initializing the three private variables that will be used in the constructor for each customer
		String name;
		String tattoo;
		int minutes;
		
		int counter = artistCount * (customerCount - 1);//Initializing a counter variable to the amount of customers the parlor can see
		
		
		for(int i = 0; i < counter; i++){//loop until no more customer spots left
			System.out.println("Hello and Welcome to Lucy's Tattoo Parlor!");
			Scanner input = new Scanner(System.in);
			System.out.println("");

			
			
			System.out.println("What is your name? ");
			name = input.nextLine();
			
			if (name.equalsIgnoreCase("Print waitlist")){//Special customer name that will cause the program to print the waitlist
				printWaitList(tattooParlor);//Method to print an organized table displaying the wait list- see below
				break;
			}
			
			System.out.println("What is tattoo would you like? ");
			tattoo = input.nextLine();
			System.out.println("How many minutes do you think it will take? ");
			minutes = input.nextInt();
			
			TattooCustomer customer = new TattooCustomer(name, tattoo, minutes);//Using the constructor to store the customers data
		
			
			//Menu specifying options for the customer
			System.out.println("\n\t\tMain Menu"); 
			System.out.println("1. Choose a Specific Tattoo Artist 0-" + (artistCount - 1)); //displays artists number baed off of what is entered as args 
			System.out.println("2. Enter the Shortest Waitlist");

			System.out.print("\nEnter either 1 or 2 for your selection: ");
			int option = input.nextInt();
			System.out.println();
			
			//Choosing a Specific Artist
			if (option==1){
				System.out.print("Enter which artist you would like: ");
				int artistNum = input.nextInt();
				System.out.println();
				boolean x = addCustomer(tattooParlor, customer, artistNum);//Running the addCustomer method for a specific artist and storing it's true/false return as x.
				
				//True vs False return will provide 2 outcomes if they could be added or not
				if (x == false){
					System.out.println("There are no available hours or spots for that artist!");
					System.out.println("\n\n");
					counter++;//Add one to counter because the spot was not taken
				}
				else{
					System.out.println("You have sucesfully been added to artist " + artistNum + " waitlist!");
					System.out.println("\n\n");
				}
			}
			//Choosing shortest wait time
			else if (option==2){
				boolean y = addCustomer(tattooParlor, customer);//Running the addCustomer method for a specific artist and storing it's true/false return as y.
				//True vs False return will provide 2 outcomes if they could be added or not
				if (y == false){
					System.out.println("There are no available hours or spots!");
					System.out.println("\n\n");
					counter++;//Add one to counter because the spot was not taken
				}
				else{
					System.out.println("You have sucesfully been added to Shortest Waitlist!");
					System.out.println("\n\n");
				}
			} 
					
		
		}
	}

		
		

	
	/**
	* Computes how many minutes of work the specified tattoo artist has.
	* @param The array of customers for one particular tattoo artist
	*/
	public static int computeMinutesOfWork(TattooCustomer [] a) {
		int minutesTotal = 0;//Setting initial minutes to 0
		for (int i = 0; i < a.length; i++){//looping through all customers
			if (a[i]!= null){//Making sure a customer exists at that index for the specified artist 
				minutesTotal += a[i].getMinutes();//using the getMinutes getter to add minutes to the artist's total
			}
			
		}
		
	return minutesTotal;
	}
	/**
	* Adds customer to the waitlist for a specific artist.
	* If the artist is at capacity (in terms of number of customers or minutes)
	* Then the customer is not added and the method returns false
	* If the customer is successfully added the method returns true
	* @param
	*/
	public static boolean addCustomer(TattooCustomer [][] a, TattooCustomer c, int artistNum) {
		int workTime = computeMinutesOfWork(a[artistNum]) + c.getMinutes();//Setting the projected work time for the current artist with the new customer added as well. 
		if (workTime <= 480){//Making sure the projected work time is under 8 hours
			for (int i = 0; i < a[artistNum].length; i++){//Looping through each spot for the specific artist
				if (a[artistNum][i] == null) {//Verifying the spot is open
					a[artistNum][i] = c;//Adding the customer to that spot
					return true;//Was successful- will return true
				}
			}
		}
		return false;//Was not successful will return false
	}
	/**
	* Adds customer to the shortest waitlist in terms of minutes. If some artists have equal length waitlists
	* then the customer is added to the lowest numbered artist. If no artist has space then the method does not
	* add the customer, and returns false.
	* TODO - finish this javadoc
	* @return true if the customer was added to the waitlist, false otherwise (if all artists were full)
	*/
	public static boolean addCustomer(TattooCustomer [][] a, TattooCustomer c) {
		int shortestWait = 480;//Initial shortest wait set to 8 hours
		int artist = a.length + 1;//initial artist will always be out of array
		
		//Loops through and identifies which artist has the shortest wait list
		for (int i = 0; i < a.length; i++){
			for(int j = 0; j < a[i].length; j++){
				if (a[i][j] == null){
					//Next if is used to make sure that the artist is the shortest and that the wait + the new tattoo isn't over 8 hours
					if (computeMinutesOfWork(a[i]) < shortestWait && computeMinutesOfWork(a[i]) + c.getMinutes() <= 480 ){											
						shortestWait = computeMinutesOfWork(a[i]);//setting shortestWait to the new shortestWat artist
						artist = i;//setting variable artist to the shortest wait artist
					}
				}
			}
		}
		//Using the artist with the smallest wait list we can put that all into the method above
		if (artist < a.length){//verifies there was an available artist
			addCustomer(a, c, artist);
			return true;//Was successful- will return true
		}
		else {
			return false;//Was not successful will return false
		}
		
		}
	
	/**
	 * Extra method implemented to organize and print the array/waitList in a neat manner
	 * 
	 * @param  a
	 */
	public static void printWaitList(TattooCustomer [][] a) {
	    for(int row = 0; row < a.length; row++) {
	    	System.out.print("Artist "  + new Integer(row).toString());//Setting the Y axis to Artists and their number
	        for(int column = 0; column<a[row].length; column++)
	           //If else used to verify if a spot is null or a customer holds it
	        	if (a[row][column]!= null){//If not null a customer has
	            	System.out.print("\t|\t" + a[row][column].getName());//Will print the name of a customer using getName getter
	            }
	            else{
	            	System.out.print("\t|\t" + a[row][column]);//If the spot is Null this will display it as OPEN
	            }
	        System.out.println();
	     }
	 }
}

