import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Agent interfaces for RMI implements
 */
public interface Manager extends Remote{
	Message getFromAgent(ParameterGet pGet) throws RemoteException;
	Message getNextFromAgent(ParameterGet pGet) throws RemoteException;
	Message setOnAgent(ParameterSet pSet) throws RemoteException;

}

