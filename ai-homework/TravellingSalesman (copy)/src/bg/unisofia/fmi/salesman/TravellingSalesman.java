package bg.unisofia.fmi.salesman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TravellingSalesman {
	
	private final static int CITIES = 20;
	private final static int POPULATION_COUNT = 100;
	private final static int EPOCH_COUNT = 100;
	private final static double MUTATION_RATE = 0.5;
	private final static int ELITISM_RATE = 10;
	private final static Random rand = new Random(10000);
	
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
	
	private static void mutate(Individual individual) {
        for(int indPos1=0; indPos1 < individual.numberOfCities(); indPos1++){
            if(Math.random() < MUTATION_RATE){
                int indPos2 = (int) (individual.numberOfCities() * Math.random());
                City city1 = individual.getCity(indPos1);
                City city2 = individual.getCity(indPos2);
                individual.setCity(indPos2, city1);
                individual.setCity(indPos1, city2);
            }
        }
    }
	
	public static Population evolvePopulation(Population population) {
		int elitism = POPULATION_COUNT * ELITISM_RATE / 100;
		Population nextGeneration = new Population(POPULATION_COUNT);
		ArrayList<Individual> pop = population.getPopulation();
        for (int i = 0; i < elitism; i++) {
        	nextGeneration.addIndividual(i, pop.get(i));
        }
        
        for (int i = elitism; i < nextGeneration.getPopulationSize(); i++) {
            Individual parent1 = pop.get(rand.nextInt(elitism));
            Individual parent2 = pop.get(rand.nextInt(elitism));
            Individual child = crossover(parent1, parent2);
           // System.out.println(parent1);
            //System.out.println(parent2);
            //System.out.println(child);
            nextGeneration.addIndividual(i, child);
        }

        for (int i = elitism; i < nextGeneration.getPopulationSize(); i++) {
            mutate(nextGeneration.getIndividual(i));
        }
        nextGeneration.sortByFitness();

        return nextGeneration;
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
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < EPOCH_COUNT; j++) {
				population = evolvePopulation(population);
				System.out.println(population.getPopulation().get(0).getDistance());
				Scanner in = new Scanner(System.in);
				in.nextInt();
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
