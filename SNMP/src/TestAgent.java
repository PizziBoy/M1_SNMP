import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;

public class TestAgent {

	private final static Integer PORT = 20999;

	public static void main(String[] args) throws Exception {
		
		//Community config file 
		HashMap<String, Droit> configCommunity = new HashMap<String, Droit>();
		configCommunity.put("public", Droit.RO);
		configCommunity.put("stri", Droit.RW);
		
		//Create the registry
		LocateRegistry.createRegistry(PORT);
		
		//Agent instanciation (published automatically to registry)
		AgentImpl agent = new AgentImpl("Agent1", "localhost", 20999, configCommunity);
		
		System.out.println("Agent running ...");
	}

}
