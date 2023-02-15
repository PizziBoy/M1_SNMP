import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class AgentImpl extends UnicastRemoteObject implements Agent {
	
	private MIB mib;
	private HashMap<String, Droit> communityConfig;
	
	public AgentImpl(HashMap<String, Droit> communityConfig) throws RemoteException {
		this.mib = new MIB();
		this.communityConfig = communityConfig;
	}
	

	@Override
	public Message get(ParameterGet parameterGet) throws RemoteException {
		/**
		 * If no community match so return NO_RESP 
		 */
		if (!this.isCommunityExists(parameterGet.getCommunity())) {
			return new Message("NO_RESP", "---");
		}
		
		String returnValue = this.mib.getValue(parameterGet.getName());
		String messageType = "GET_RESP";
		return new Message(messageType, returnValue);
	}

	@Override
	public Message set(ParameterSet parameterSet) throws RemoteException {
		/**
		 * If no community match so return NO_RESP 
		 */
		if (!this.isCommunityExists(parameterSet.getCommunity())) {
			return new Message("NO_RESP", "---");
		} 
		/**
		 * Community found, checking permissions for set
		 */
		else if (this.getCommunityPermissions(parameterSet.getCommunity()) != Droit.RW){
			return new Message("NO_RESP", "---");
		} else {
			String name = parameterSet.getName();
			String value = parameterSet.getValue();
			this.mib.setValueMib(name, value);
			String messageType = "SET_RESP";
			String messageValue = "OK";
			return new Message(messageType, messageValue);
		}
		
	}
	
	
	public boolean isCommunityExists(String community) {
		return this.communityConfig.containsKey(community);
	}
	
	public Droit getCommunityPermissions(String community) {
		return this.communityConfig.get(community);
	}
	
	
		




	
}
