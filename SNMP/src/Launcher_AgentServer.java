import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;

public class Launcher_AgentServer {
	private final static Integer PORT = 20999;

	public static void main(String[] args) throws MalformedURLException, RemoteException, AlreadyBoundException, SocketException, UnknownHostException {
		
		//Community config file 
		HashMap<String, Droit> configCommunity = new HashMap<String, Droit>();
		configCommunity.put("public", Droit.RO);
		configCommunity.put("stri", Droit.RW);
		
		//Agent instanciation
		AgentImpl agent = new AgentImpl(configCommunity);
		
		//Create the registry
		LocateRegistry.createRegistry(PORT);
		
		//Publish registry
		Naming.bind("rmi://localhost:" + PORT.toString() +"/agent", agent);
		
		System.out.println("Agent running ...");
		System.out.println(agent.getNext(new ParameterGet("caca.1", "stri")).getValue());
	}

}
