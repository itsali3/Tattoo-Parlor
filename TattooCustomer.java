/**
 * Scope of TattooCustomer Class: A Constructor and getters
 * are created for all private values for a specific customer
 * Getters: getName, getMinutes, getTattoo
 *
 */
class TattooCustomer {
		
private final String name;
private final String tattoo;
private final int minutes;



//Constructor made to take Three variables for each customer. Will be used in main
public TattooCustomer(String name, String tattoo, int minutes){
	this.name = name;
	this.tattoo = tattoo;
	this.minutes = minutes;
}

//Three getters to grab specific customer info (name, tattoo, minutes)
public String getName(){
	return name;
}
public String getTattoo(){
	return tattoo;
}
public int getMinutes(){
	return minutes;
}
}
