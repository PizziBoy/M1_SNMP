package V1;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class Agent extends UnicastRemoteObject implements Agent_interface{
	
	private HashMap<String,String> mib;
	
	public Agent() throws RemoteException {
		super();
		this.mib = new HashMap<String,String>();
		this.mib.put("nom", "Agent_SNMP");
		this.mib.put("addr", "localhost");
	}


	@Override
	public HashMap get(ParametreGet parametreget) throws RemoteException {
		return this.mib.get(parametreget);		
	}

	@Override
	public Message set(ParametreSet parametreset) throws RemoteException {
		return this.mib.put(parametreset);
		
	}


	
}
