package bg.unisofia.fmi.rmiexample;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class StudentServerInterfaceImpl  extends UnicastRemoteObject
											implements StudentServerInterface {

	private static final String NOT_FOUND = "Student is not found.";
	private HashMap<String, Student> data = new HashMap<String, Student>();
	
	protected StudentServerInterfaceImpl() throws RemoteException {
		super();
		initializeStudent();
	}
	
	protected void initializeStudent() {
		Student s1 = new Student("Ivan", "Ivanov", "61272", 6.00);
		Student s2 = new Student("Maria", "Koleva", "61324", 5.72);
		Student s3 = new Student("Petar", "Nikolov", "61284", 3.84);
		
		data.put(s1.getFn(), s1);
		data.put(s2.getFn(), s2);
		data.put(s3.getFn(), s3);
	}

	@Override
	public String getStudentData(String fn) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
