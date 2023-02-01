import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;


public interface Agent extends Remote{
	Message get(ParameterGet parameterGet) throws RemoteException;
	Message set(ParameterSet parameterSet) throws RemoteException;

}
