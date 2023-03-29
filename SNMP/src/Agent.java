import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Agent interfaces for RMI implements
 */
public interface Agent extends Remote{
	Message get(ParameterGet parameterGet) throws RemoteException;
	Message getNext(ParameterGet parameterGet) throws RemoteException;
	Message set(ParameterSet parameterSet) throws RemoteException;
	Message subscribe(ParameterSubscribe parameterSuscribe) throws RemoteException;

}
