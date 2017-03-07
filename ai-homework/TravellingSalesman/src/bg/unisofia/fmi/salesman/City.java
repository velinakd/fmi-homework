package bg.unisofia.fmi.salesman;

import java.lang.Math;

public class City {
	private double x;
	private double y;
	private int id;
	
	public City(double x, double y, int id) {
		this.x = x;
		this.y = y;
		this.setId(id);
	}
	
	public City(int id) {
		this(Math.random()*100, Math.random()*100, id);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double square(double a) {
		return a * a;
	}
	
	public double distanceTo(City other) {
		return Math.sqrt(square(this.x + other.x) + square(this.y + other.y)); 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
