import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SNMPEntityImpl extends UnicastRemoteObject implements SNMPEntity {
	
	//List of entities (Manager/Agent) with MIB values subscribed
	protected HashMap<SNMPEntity, List<String>> registeredEntities;
	protected String entityName;
	
	protected String registryAddr;
	protected int registryPort;
	
	
	public SNMPEntityImpl(String entityName, String registryAddr, int registryPort) throws RemoteException, Exception {
		super();
		this.registeredEntities = new HashMap<SNMPEntity, List<String>>();
		this.entityName = entityName;
		this.registryAddr = registryAddr;
		this.registryPort = registryPort;
		
		try {
			//Check if registry Addr & Port are pointing on a registry
			Naming.list("rmi://" + registryAddr + ":" + registryPort);
			//Register entity to registry
			this.registerEntity();
		} catch (RemoteException | MalformedURLException e) {
			throw new Exception("Registry setup failed");
		}
	}
	
	/*
	 * Self register entity to Registry declared in constructor
	 */
	public void registerEntity() throws Exception {
		try {
			Naming.bind("rmi://" + registryAddr + ":" + registryPort + "/" + this.entityName, this);
		} catch (MalformedURLException | RemoteException | AlreadyBoundException e) {
			throw new Exception("Error while registering entity please check Registry or name of entity");
		}
	}
	
	@Override
	public Message addEntity(String entityName, List<String> monitoredVariables) throws Exception {
		try {
			//Check if exists
			SNMPEntity entity = (SNMPEntity) Naming.lookup("rmi://" + this.registryAddr + ":" + this.registryPort + "/" + entityName);
			//Then add to registeredEntities
			this.registeredEntities.put((SNMPEntity) entity, monitoredVariables);
			return new Message("OK", "" + entityName + " successfuly added to " + this.entityName);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			throw new Exception("Error while retrieve entity in registry please check and retry");
		}
	}

	@Override
	public Message receiveTrap(String trap) throws Exception {
		String str = "######################################### TRAP RECEIVER START ##########################################\n";
		str = str +  "- Trap received from agent ("+this.entityName+")\n";
		str = str +  "- Message received is :\n";
		str = str +   trap + " Value CHANGED" + "\n";
		str = str +  "########################################## TRAP RECEIVER END ###########################################\n";
		System.out.println(str);
		return new Message("TRAP", "TRAP"); //TOBEMODIF
	}

	

}
