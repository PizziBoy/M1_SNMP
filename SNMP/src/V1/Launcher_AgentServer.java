package V1;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Launcher_AgentServer {
	private final static Integer PORT = 20999;

	public static void main(String[] args) throws MalformedURLException, RemoteException, AlreadyBoundException {
		AgentImpl agent = new AgentImpl();
		//Create the registry
		LocateRegistry.createRegistry(PORT);
		//Publish registry
		Naming.bind("rmi://localhost:" + PORT.toString() +"/agent", agent);
	}

}
