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
	public Message get(ParametreGet parametreget) throws RemoteException {
		String valeur_retour = this.mib.get(parametreget.getNom());
		String type_message = "GET_RESP";
		return new Message(type_message, valeur_retour);
	}

	@Override
	public Message set(ParametreSet parametreset) throws RemoteException {
		String nom = parametreset.getNom();
		String valeur = parametreset.getValeur();
		String type_message = "SET_RESP";
		String valeur_retour = "OK";
		this.mib.put(nom, valeur);
		return new Message(type_message, valeur_retour);
	}

}
