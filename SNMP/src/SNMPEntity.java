import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

/**
 * SNMPEntiry interface for RMI implements
 */
public interface SNMPEntity extends Remote {
	Message addEntity(String entityName, List<String> monitoredVariables) throws Exception;
	//String trap => variable name that has changed
	Message receiveTrapChange(String trap) throws Exception;
	//String trap => community name 
	Message receiveTrapBadCommunity(String trap) throws Exception;

}
