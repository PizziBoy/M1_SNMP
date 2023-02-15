import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class AgentImpl extends UnicastRemoteObject implements Agent {
	
	private MIB mib;
	
	public AgentImpl(String key, TrioIndexValeurDroit trioIndexValeurDroit) throws RemoteException {
		this.mib = new MIB();
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
		this.setEntryMIB(name, value);
		String messageType = "SET_RESP";
		String messageValue = "OK";
		return new Message(messageType, messageValue);
	}
	
	
		




	
}
