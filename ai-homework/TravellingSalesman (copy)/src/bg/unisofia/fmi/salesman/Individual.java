package bg.unisofia.fmi.salesman;

import java.util.ArrayList;
import java.util.Collections;

public class Individual implements Comparable<Individual>{
	private ArrayList<City> route = new ArrayList<City>();
	private double distance = 0;
	private double fitness = 0;

	public Individual() {
		for (int i = 0; i < Country.getNumberOfCities(); i++) {
			route.add(null);
		}
	}

	public Individual(ArrayList<City> route) {
		this.route = route;
	}

	public void generateIndividual() {
		for (int cityIndex = 0; cityIndex < Country.getNumberOfCities(); cityIndex++) {
			setCity(cityIndex, Country.getCity(cityIndex));
		}
		Collections.shuffle(route);
	}

	public City getCity(int tourPosition) {
		return (City) route.get(tourPosition);
	}

	public void setCity(int position, City city) {
		route.set(position, city);
		fitness = 0;
		distance = 0;
	}

	public int numberOfCities() {
		return route.size();
	}

	public double getFitness() {
		if (fitness == 0) {
			fitness = 1 / (double) getDistance();
		}
		return fitness;
	}

	public double getDistance() {
		if (distance == 0) {
			int tourDistance = 0;
			for (int cityIndex = 0; cityIndex < numberOfCities(); cityIndex++) {
				City fromCity = getCity(cityIndex);
				City toCity;
				if (cityIndex + 1 < numberOfCities()) {
					toCity = getCity(cityIndex + 1);
				} else {
					toCity = getCity(0);
				}
				tourDistance += fromCity.distanceTo(toCity);
			}
			distance = tourDistance;
		}
		return distance;
	}

	public boolean containsCity(City city) {
		return route.contains(city);

	}

	@Override
	public int compareTo(Individual other) {
		return Double.compare(this.getDistance(), other.getDistance());
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("("+getDistance()+") ");
		for (City city : route) {
			sb.append(city.getId()).append(" ");			
		}
		return sb.toString();
	}
}
