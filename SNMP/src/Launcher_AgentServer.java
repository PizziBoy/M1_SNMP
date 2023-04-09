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
		
		MenuAgent menuAgent = new MenuAgent();

		//Community config file 
		HashMap<String, Droit> configCommunity = menuAgent.configureCommunity();

		String agentName = menuAgent.requestAgentName();
		int port = menuAgent.requestRegistryPort();
		String registryAddress = menuAgent.requestRegistryAddress();


		//Agent instanciation (published automatically to registry)
		AgentImpl agent;
		try {
			agent = new AgentImpl(agentName, registryAddress, port, configCommunity);
			//TRAPS ON
			agent.activateTraps();
			System.out.println("Agent running ...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

}
