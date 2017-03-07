package bg.unisofia.fmi.rmiexample;

public class Student {
	private String firstName;
	private String lastName;
	private String fn;
	private double avgGrade;
	
	Student() {
		firstName = "";
		lastName = "";
		fn = "";
		avgGrade = 0.00;
	}
	
	Student(String firstName, String lastName, String fn, double avgGrade) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.fn = fn;
		this.avgGrade = avgGrade;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFn() {
		return fn;
	}
	public void setFn(String fn) {
		this.fn = fn;
	}
	public double getAvgGrade() {
		return avgGrade;
	}
	public void setAvgGrade(double avgGrade) {
		this.avgGrade = avgGrade;
	}
	
	
}
