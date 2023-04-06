import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
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
		
		//Monitored value ON
		ArrayList<String> monitoredVariablesAgent1 = new ArrayList<String>();
		monitoredVariablesAgent1.add("os");
		monitoredVariablesAgent1.add("addrIp");
		System.out.println(agent.addEntity("Agent1", monitoredVariablesAgent1).getValue());
		agent.registerMonitoredVariables();
		
		System.out.println("Agent running ...");
		
	}

}
