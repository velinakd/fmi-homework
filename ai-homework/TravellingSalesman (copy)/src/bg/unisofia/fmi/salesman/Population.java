package bg.unisofia.fmi.salesman;

import java.util.ArrayList;
import java.util.Collections;

public class Population {
	public ArrayList<Individual> individuals = new ArrayList<Individual>();
	public int populationSize;
	
	public Population(int populationSize) {
		this.populationSize = populationSize;
		
	}
	
	public void initRandomPopulation(){
		for (int i = 0; i < populationSize; i++) {
			Individual newIndividual = new Individual();
            newIndividual.generateIndividual();
            individuals.add(newIndividual);
        }
	}
	
	public void sortByFitness(){
		Collections.sort(individuals);
	}
	
	public ArrayList<Individual> getPopulation() {
		return individuals;
	}
	
	public int getPopulationSize() {
		return populationSize;
	}
	
	public Individual getIndividual(int index) {
		return individuals.get(index);
	}
	
	public void addIndividual(int index, Individual ind) {
		individuals.add(ind);
	}
	
	public void sort() {
		Collections.sort(individuals);
	}
}
