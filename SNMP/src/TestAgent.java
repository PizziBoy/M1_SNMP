import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.HashMap;

public class TestAgent {


	public TestAgent() throws Exception {
		MenuAgent menuAgent = new MenuAgent();

		//Community config file 
		HashMap<String, Droit> configCommunity = menuAgent.configureCommunity();

		String agentName = menuAgent.requestAgentName();
		int port = menuAgent.requestRegistryPort();
		String registryAddress = menuAgent.requestRegistryAddress();

		System.out.println(port);

		//Create the registry
		LocateRegistry.createRegistry(20999);

		//Agent instanciation (published automatically to registry)
		AgentImpl agent = new AgentImpl("a1", "localhost", 20999, configCommunity);

		//Monitored value ON on Agent
		ArrayList<String> monitoredVariablesAgent1 = new ArrayList<String>();
		//Valeurs à observer 
		monitoredVariablesAgent1.add("os");
		monitoredVariablesAgent1.add("addrIp");
		monitoredVariablesAgent1.add("addrMac");
		monitoredVariablesAgent1.add("statusInterface");
		System.out.println(agent.addEntity("Agent1", monitoredVariablesAgent1).getValue());
		agent.registerMonitoredVariables();

		System.out.println("Agent running ...");

		//Community config file 
		/*HashMap<String, Droit> configCommunity = new HashMap<String, Droit>();
		configCommunity.put("public", Droit.RO);
		configCommunity.put("stri", Droit.RW);
		

		//Create the registry
		LocateRegistry.createRegistry(PORT);

		//Agent instanciation (published automatically to registry)
		AgentImpl agent = new AgentImpl("Agent1", "localhost", 20999, configCommunity);

		//Monitored value ON on Agent
		ArrayList<String> monitoredVariablesAgent1 = new ArrayList<String>();
		//Valeurs à observer 
		monitoredVariablesAgent1.add("os");
		monitoredVariablesAgent1.add("addrIp");
		System.out.println(agent.addEntity("Agent1", monitoredVariablesAgent1).getValue());
		agent.registerMonitoredVariables();

		System.out.println("Agent running ...");*/
				
	}

}
