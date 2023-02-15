import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;


/**
 * 
 * Class AgentImpl
 *
 */
public class AgentImpl extends UnicastRemoteObject implements Agent {
	
	private MIB mib;
	private HashMap<String, Droit> communityConfig;
	
	/**
	 * 
	 * @param communityConfig is the hashMap containing <community, Permissions>
	 * @throws RemoteException
	 * Create a new MIB object 
	 */
	public AgentImpl(HashMap<String, Droit> communityConfig) throws RemoteException {
		this.mib = new MIB();
		this.communityConfig = communityConfig;
	}
	

	/**
	 * get from the MIB with parameterGet
	 * @return Message 
	 */
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
	
	/**
	 * getNext (value) from the MIB with parameterGet
	 * @return Message 
	 */
	@Override
	public Message getNext(ParameterGet parameterGet) throws RemoteException {
		/**
		 * If no community match so return NO_RESP 
		 */
		if (!this.isCommunityExists(parameterGet.getCommunity())) {
			return new Message("NO_RESP", "---");
		}
		
		String name = parameterGet.getName();
		if (name.contains(".")) {
			String[] requestSplitted = name.split(".");
			int index = Integer.parseInt(requestSplitted[1]);
			name = requestSplitted[0];
			/**
			 * get NEXT so index + 1
			 */
			String returnValue = this.mib.getValue(index + 1);
			String messageType = "GET_RESP";
			return new Message(messageType, returnValue);
		} else {
			
		}
		String[] nameSplitted = name.split(".");
		String returnValue = this.mib.getValue(parameterGet.getName());
		String messageType = "GET_RESP";
		return new Message(messageType, returnValue);
	}

	/**
	 * set from the MIB with parameterSet
	 * @return Message 
	 */
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
	
	/**
	 * Indicate if @param community exists in community config
	 * 
	 * @return boolean
	 */
	public boolean isCommunityExists(String community) {
		return this.communityConfig.containsKey(community);
	}
	
	/**
	 * 
	 * get Permissions for existing @param community given
	 * @return Permissions
	 */
	public Droit getCommunityPermissions(String community) {
		return this.communityConfig.get(community);
	}
	
	
		




	
}
