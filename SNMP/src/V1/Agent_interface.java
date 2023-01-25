package V1;

import java.rmi.Remote;
import java.util.HashMap;

public interface Agent_interface extends Remote {
	HashMap get(ParametreGet parametreget) throws java.rmi.RemoteException;
	Message set(ParametreSet parametreset) throws java.rmi.RemoteException;
}
