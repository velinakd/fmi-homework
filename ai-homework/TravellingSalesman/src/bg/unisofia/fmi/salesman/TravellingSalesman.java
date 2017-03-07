package bg.unisofia.fmi.salesman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class TravellingSalesman {
	
	private final static int CITIES = 20;
	private final static int POPULATION_COUNT = 100;
	private final static int EPOCH_COUNT = 50;
	private final static double MUTATION_RATE = 0.6;
	private final static int ELITISM_RATE = 10;
	private final static Random rand = new Random(10000);
	
	
	public static Individual crossover(Individual parent1, Individual parent2) {
	    int mid = rand.nextInt(CITIES - 1);
	    int[] lst1 = new int[CITIES];
	    int[] lst2 = new int[CITIES];
	    int[] result = new int[CITIES];
	    boolean[] used = new boolean[CITIES + 1];
	    for(int i=0; i<CITIES; ++i) {
	    	lst1[i] = parent1.getCity(i).getId();
	    	lst2[i] = parent2.getCity(i).getId();
	        if(i > mid) {
	            used[lst1[i]] = true;
	        } else {
	            result[i] = lst1[i];
	        }
	    }
	    ++mid;
	    for(int i=0;i<CITIES;++i) {
	        if(used[lst2[i]]) {
	        	result[mid++] = lst2[i];
	        }
	    }
	    Individual ret = new Individual();
	    for(int i=0;i<CITIES;++i) {
	        ret.setCity(i, new City(result[i]));
	    }
	    return ret;
	}
	/*
	public static Individual crossover(Individual parent1, Individual parent2) {
        Individual child = new Individual();
        
        int startPos = (int) (Math.random() * parent1.numberOfCities());
        int endPos = (int) (Math.random() * parent1.numberOfCities());

        for (int i = 0; i < parent1.numberOfCities(); i++) {
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            }
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }
        for (int i = 0; i < parent2.numberOfCities(); i++) {
            if (!child.containsCity(parent2.getCity(i))) {
                for (int ii = 0; ii < parent1.numberOfCities(); ii++) {
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, parent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
        }
    */
	
	private static void mutate(Individual individual) {
        int indPos1 = (int) (individual.numberOfCities() * Math.random());
        int indPos2 = (int) (individual.numberOfCities() * Math.random());
		City city1 = individual.getCity(indPos1);
        City city2 = individual.getCity(indPos2);
        individual.setCity(indPos2, city1);
        individual.setCity(indPos1, city2);
    }
	
	public static Population evolvePopulation(Population population) {
		int elitism = POPULATION_COUNT * ELITISM_RATE / 100;
		Population nextGeneration = new Population(POPULATION_COUNT);
		ArrayList<Individual> pop = population.getPopulation();
		
		Set<Individual> newPop = new TreeSet<>();
        
		for (int i = 0; i < pop.size(); i++) {
			newPop.add(new Individual(pop.get(i)));
        	if(Math.random() < MUTATION_RATE) {
        		Individual tmp = new Individual(pop.get(i));
            	mutate(tmp);
        		newPop.add(tmp);
            }
		}
		
		int upper = nextGeneration.getPopulationSize();
		upper *= upper;
		upper *= 6;
        
        for (int i = 0; i < upper; i++) {
            Individual parent1 = pop.get(rand.nextInt(POPULATION_COUNT));
            Individual parent2 = pop.get(rand.nextInt(POPULATION_COUNT));
            Individual child = crossover(parent1, parent2);
            // System.out.println(parent1);
            //System.out.println(parent2);
            //System.out.println(child);
            newPop.add(child);
        }
		
        ArrayList<Individual> tmp = new ArrayList<>(newPop);
		Collections.sort(tmp);
		//for(Individual a : newPop) {
		//    System.out.println(a);
		//}
		//Scanner in = new Scanner(System.in);
		//in.nextInt();
		Population result = new Population(POPULATION_COUNT);
		
		for(int i=0;i < POPULATION_COUNT; ++i) {
		    result.addIndividual(i, tmp.get(i));
		}
		
		Set<Double> cnt = new HashSet<>();
		for(Individual i : result.getPopulation()){
		    cnt.add(i.getDistance());
		}
		System.out.println(cnt.size());
		return result;
    }
	

	public static void main1(String[] args) {
		Country.initCountry(CITIES);
		Individual i1 = new Individual();
		i1.generateIndividual();
		Individual i2 = new Individual();
		i2.generateIndividual();
		Individual i3 = crossover(i1, i2);
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
		
	}
	public static void main(String[] args) {
		Country.initCountry(CITIES);
		Population population = new Population(POPULATION_COUNT);
		population.initRandomPopulation();
		for(int i = 0; i < 1; i++) {
			for(int j = 0; j < EPOCH_COUNT; j++) {
				population = evolvePopulation(population);
				System.out.println(population.getPopulation().get(0).getDistance());
				//Scanner in = new Scanner(System.in);
				//in.nextInt();
//				for (Individual ind : population.getPopulation()) {
//					System.out.println(ind);
//				}
//				System.out.println("-----------------------------------");
			}
			//System.out.println(population.getPopulation().get(0).getDistance());
			//System.out.println(population.getPopulation().get(0).toString());
		}
	}

}
