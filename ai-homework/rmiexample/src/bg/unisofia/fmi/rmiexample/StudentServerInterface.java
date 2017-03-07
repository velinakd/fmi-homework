/**
 * 
 */
package bg.unisofia.fmi.rmiexample;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StudentServerInterface extends Remote {
	public String getStudentData(String fn) throws RemoteException;
}
