import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * Agent interfaces for RMI implements
 */
public interface Agent extends Remote{
	Message get(ParameterGet parameterGet) throws RemoteException;
	Message set(ParameterSet parameterSet) throws RemoteException;

}
