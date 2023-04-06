import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * 
 * This class allow user to instanciate manager object
 *
 */
public class Manager extends SNMPEntityImpl{

	//Only one Agent ON => Pointing on 1 SNMPEntity
	private Agent agent;
	
	/**
	 * Manager constructor
	 */
	public Manager(String name, String registryAddr, int registryPort) throws Exception {
		//Call SNMPEntity to register registry and name 
		super(name, registryAddr, registryPort);
	}
	
	/**
	 * Method for allow Manager to manage @param agent 
	 * @throws Exception 
	 */
	//Agent must be present in registry before setting it !
	public void setCurrentAgent(String agentName) throws Exception {
		try {
			this.agent = (Agent) Naming.lookup("rmi://" + this.registryAddr + ":" + this.registryPort + "/" + agentName);
			System.out.println("" + agentName + " successfuly linked to " + this.entityName);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			throw new Exception("Error while lookup Agent entity please check Registry or name of entity");
		}
	}
	

	/**
	 * Get variable value by requesting managed agent with parameter @param pGet
	 * @throws RemoteException
	 */
	public Message getFromAgent(ParameterGet pGet) throws RemoteException {
		return this.agent.get(pGet);
	}
	
	/**
	 * Get next variable value by requesting managed agent with parameter @param pGet
	 * @throws RemoteException
	 */
	public Message getNextFromAgent(ParameterGet pGet) throws RemoteException {
		return this.agent.getNext(pGet);
	}
	
	/**
	 * Set variable value by requesting managed agent with parameter @param pSet
	 * @throws RemoteException
	 */
	public Message setOnAgent(ParameterSet pSet) throws RemoteException {
		return this.agent.set(pSet);
	}
	
	public Agent getAgent() {
		return agent;
	}
	
	
}
