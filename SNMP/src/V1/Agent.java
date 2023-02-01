package V1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface Agent extends Remote {
	HashMap get(ParametreGet parametreGet) throws RemoteException;
	Message set(ParametreSet parametreSet) throws RemoteException;
}
