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
		this.mib.put("name", "Agent_SNMP");
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
	public Message get(ParameterGet parameterGet) throws RemoteException {
		String returnValue = this.getEntryFromMIB(parameterGet.getName());
		String messageType = "GET_RESP";
		return new Message(messageType, returnValue);
	}

	@Override
	public Message set(ParameterSet parameterSet) throws RemoteException {
		String name = parameterSet.getName();
		String value = parameterSet.getValue();
		String messageType = "SET_RESP";
		String messageValue = "OK";
		this.setEntryMIB(name, value);
		return new Message(messageType, messageValue);
	}
		




	
}
