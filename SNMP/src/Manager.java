import java.rmi.RemoteException;

public class Manager {

	private Agent agent;
	
	public Manager() {
		super();
	}
	
	public void bindAgent(Agent agent) {
		this.agent = agent;
	}
	
	public Message getFromAgent(ParameterGet pGet) throws RemoteException {
		return this.agent.get(pGet);
	}
	
	public Message setOnAgent(ParameterSet pSet) throws RemoteException {
		return this.agent.set(pSet);
	}
	
	
}
