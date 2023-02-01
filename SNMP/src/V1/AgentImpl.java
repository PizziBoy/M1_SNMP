package V1;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class AgentImpl extends UnicastRemoteObject implements Agent{
	
	private HashMap<String,String> mib;
	
	public AgentImpl() throws RemoteException {
		super();
		this.mib = new HashMap<String,String>();
		this.mib.put("nom", "Agent_SNMP");
		this.mib.put("addr", "localhost");
	}
	
	
	protected void setEntryMIB(String key, String value) {
		//If entry exists => PUT WILL UPDATE VALUE
		this.mib.put(key, value);
	}
	
	protected String getEntryFromMIB(String key) {
		return this.mib.get(key);
	}




	@Override
	public HashMap get(ParametreGet parametreGet) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Message set(ParametreSet parametreSet) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}





	
}
