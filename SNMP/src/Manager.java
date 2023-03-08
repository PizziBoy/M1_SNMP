import java.rmi.RemoteException;

/**
 * 
 * This class allow user to instanciate manager object
 *
 */
public class Manager {

	private Agent agent;
	
	/**
	 * Manager constructor
	 */
	public Manager() {
		super();
	}
	
	/**
	 * Method for allow Manager to manage @param agent 
	 */
	public void bindAgent(Agent agent) {
		this.agent = agent;
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
	
	
}
