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
	public Message get(ParameterGet parameterGet) throws RemoteException {
		String valeurRetour = this.getEntryFromMIB(parameterGet.getName());
		String typeMessage = "GET_RESP";
		return new Message(typeMessage, valeurRetour);
	}

	@Override
	public Message set(ParameterSet parameterSet) throws RemoteException {
		String nom = parameterSet.getName();
		String valeur = parameterSet.getValue();
		String typeMessage = "SET_RESP";
		String valeurRetour = "OK";
		this.setEntryMIB(nom, valeur);
		return new Message(typeMessage, valeurRetour);
	}
		




	
}
