import java.io.Serializable;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;


/**
 * 
 * Class AgentImpl
 *
 */
public class AgentImpl extends SNMPEntityImpl implements Agent, Observer {
	
	private MIB mib;
	private HashMap<String, Droit> communityConfig;
	
	/**
	 * 
	 * @param communityConfig is the hashMap containing <community, Permissions>
	 * @throws Exception 
	 */
	public AgentImpl(String name, String registryAddr, int registryPort, HashMap<String, Droit> communityConfig) throws Exception {
		//Call SNMPEntity to register registry and name 
		super(name, registryAddr, registryPort);
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
		String returnValue = this.mib.getMibRecord(parameterGet.getName()).getValue();
		String messageType = "GET_RESP";
		return new Message(messageType, returnValue);
	}
	
	/**
	 * getNext (value) from the MIB with parameterGet
	 * @return Message 
	 */
	@Override
	public Message getNext(ParameterGet parameterGet) throws RemoteException {
		String returnValue = "---";
		String messageType = "NO_RESP";
		/**
		 * If no community match so return NO_RESP 
		 */
		if (!this.isCommunityExists(parameterGet.getCommunity())) {
			return new Message(messageType, returnValue);
		}
		
		String name = parameterGet.getName();
		/**
		 * Only .1 is allowed due to our MIB conception
		 */
		if (name.endsWith(".1")) {
			String[] requestSplitted = name.split("\\.");
			name = requestSplitted[0];
			int index = this.mib.getIndex(name);
			if (index == -1) {
				return new Message(messageType, returnValue);
			}
			/**
			 * get NEXT so index + 1
			 */
			MibRecord next = this.mib.getMibNextRecord(this.mib.getMibRecord(name));
			if (next != null) {
				returnValue = next.getKey() + ".1 = " + next.getValue();
				messageType = "GETNEXT_RESP";
			}
			
		/**
		 * If name contains only letters
		 */
		} else if(name.matches("[a-zA-Z]+")) {
			/**
			 * get standard value if not .[0-9] so find index of value provided 
			 */
			int index = this.mib.getIndex(parameterGet.getName());
			if (index == -1) {
				return new Message(messageType, returnValue);
			}
			returnValue = this.mib.getMibRecord(name).getKey() + ".1 = " + this.mib.getMibRecord(name).getValue();
			messageType = "GETNEXT_RESP";	
		} 
		
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
			if (this.mib.getMibRecord(parameterSet.getName()).getPermission() != Droit.RW) {
				return new Message("NO_RESP", "---");
			}
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
	
	public void activateTraps() {
		//Activate observers for ALL RECORDS of the MIB then filter by sending Trap on managers
		ArrayList<MibRecord> mib = this.mib.getMib();
		for (int i = 0; i < mib.size(); i++) {
			mib.get(i).addObserver(this);
			System.out.println("[" + mib.get(i).getKey() + "] monitored ON");
		}
	}

	//Callback method TRAP RECEIVER
	@Override
	public void update(Observable o, Object arg) {
		//TRAP
		for (Map.Entry<SNMPEntity, List<String>> entry : this.registeredEntities.entrySet()) {
			//Ignoring self entity trap sending
			try {
				if (!entry.getKey().equals(Naming.lookup("rmi://" + this.registryAddr + ":" + this.registryPort + "/" + this.entityName))) {
					if (entry.getValue().contains(((MibRecord) arg).getKey())) {
						SNMPEntity sendTo = (SNMPEntity) entry.getKey();
						sendTo.receiveTrap(((MibRecord) arg).getKey());
						System.out.println("TRAP send successfuly to entity");
					}
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}	
	
}
}
