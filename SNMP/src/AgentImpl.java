import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
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
	 * @throws UnknownHostException 
	 * @throws SocketException 
	 */
	public AgentImpl(HashMap<String, Droit> communityConfig) throws RemoteException, SocketException, UnknownHostException {
		this.mib = new MIB();
		this.communityConfig = communityConfig;
		this.putMibValue();
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
	
	/**
	 * get the PC operating system
	 * @return the OS
	 */
	public String getOs() {
		String os = System.getProperty("os.name").toLowerCase();
		return os;
	}
	
	/**
	 * get the localhost with his name and his ip
	 * @return the host
	 * @throws UnknownHostException
	 */
	public InetAddress getLocalHost() throws UnknownHostException {
		InetAddress hostInterface = InetAddress.getLocalHost();
		return hostInterface;
	}
	
	/**
	 * get the inet host interface 
	 * @param hostInterface
	 * @return this inetInterface
	 * @throws SocketException
	 */
	public NetworkInterface getInetInterface(InetAddress hostInterface) throws SocketException {
		 NetworkInterface inetInterface = NetworkInterface.getByInetAddress(hostInterface);
		 return inetInterface;
	}
	
	/**
	 * get the interface status (true = up and false = down) 
	 * @param inetInterface
	 * @return the interface status
	 * @throws SocketException
	 */
	public String statusInterfaceUp(NetworkInterface inetInterface) throws SocketException {
		 boolean interfaceUp = inetInterface.isUp();
		 return String.valueOf(interfaceUp);
	}

	/**
	 * get the host ip address
	 * @param hostInterface
	 * @return the ip address of that host
	 * @throws UnknownHostException
	 */
	public String getIpAdress(InetAddress hostInterface) throws UnknownHostException {
		String ipAddress = hostInterface.getHostAddress();
		return ipAddress;
	}
	
	/**
	 * get the interface mac address
	 * @param inetInterface
	 * @return te mac address of that interface
	 * @throws SocketException
	 */
	public String getMacAdress(NetworkInterface inetInterface) throws SocketException {
		 byte[] hardwareAddress = inetInterface.getHardwareAddress();
		 String[] hexadecimal = new String[hardwareAddress.length];
		 for (int i = 0; i < hardwareAddress.length; i++) {
			 hexadecimal[i] = String.format("%02X", hardwareAddress[i]);
		 }
		 String macAddress = String.join("-", hexadecimal);
		 return macAddress;
	}
	
	/**
	 * put the values the agent MIB
	 * @throws SocketException
	 * @throws UnknownHostException
	 */
	public void putMibValue() throws SocketException, UnknownHostException {
		String[] objectMib = {"os","addrIp","addrMac","statusInterface"};
		
		String os = this.getOs();
		
		InetAddress host = this.getLocalHost();
		String ipAddress = this.getIpAdress(host);
		
		NetworkInterface inetInterface = this.getInetInterface(host);
		String macAddress = this.getMacAdress(inetInterface);
		String interfaceIsUp = this.statusInterfaceUp(inetInterface);
	  
	    String[] MibValue = {os,ipAddress,macAddress,interfaceIsUp};
	    
	    
	    for (int i = 0; i < objectMib.length; i++) {
			mib.setValueMib(objectMib[i], MibValue[i]);
		}
	}	
	
}
