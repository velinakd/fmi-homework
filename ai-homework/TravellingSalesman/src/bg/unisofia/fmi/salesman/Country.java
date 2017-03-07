package bg.unisofia.fmi.salesman;

import java.util.ArrayList;

public class Country {
		private static int numberOfCities;
	    private static ArrayList<City> cities = new ArrayList<City>();

	    public static void initCountry(int citiesCount) {
	    	numberOfCities = citiesCount;
	    	for(int i=0; i<numberOfCities; i++) {
	    		cities.add(new City(i));
	    	}
	    }
	    
	    public static int getNumberOfCities(){
	        return numberOfCities;
	    }
	    
	    public static City getCity(int index){
	        return (City)cities.get(index);
	    }
	    
	    

}
