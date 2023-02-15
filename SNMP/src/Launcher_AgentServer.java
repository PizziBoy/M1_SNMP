import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Launcher_AgentServer {
	private final static Integer PORT = 20999;

	public static void main(String[] args) throws MalformedURLException, RemoteException, AlreadyBoundException {
		TrioIndexValeurDroit trio_addr = new TrioIndexValeurDroit(1, "locahost", Droit.RW);
		String key_addr = "addr";
		TrioIndexValeurDroit trio_name = new TrioIndexValeurDroit(2, "Agent_test", Droit.RO);
		String key_name = "name";

		AgentImpl agent = new AgentImpl();
		//Create the registry
		LocateRegistry.createRegistry(PORT);
		//Publish registry
		Naming.bind("rmi://localhost:" + PORT.toString() +"/agent", agent);
	}

}
