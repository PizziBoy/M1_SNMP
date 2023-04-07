import java.rmi.Naming;
import java.util.ArrayList;

public class TestManager {

	public static void main(String[] args) throws Exception {
		Manager manager = new Manager("Manager1", "localhost", 20999);
		//Set existing agent 
		manager.setCurrentAgent("Agent1");
		ArrayList<String> monitoredVariablesManager1 = new ArrayList<String>();
		monitoredVariablesManager1.add("os");
		
		//Set monitored Value ON for first manager (subscribe to Agent)
		((SNMPEntity) manager.getAgent()).addEntity(manager.entityName, monitoredVariablesManager1);
		
		System.out.println("GET OS FROM Manager1 -> " + manager.getFromAgent(new ParameterGet("os", "stri")).getValue());

		Thread.sleep(10000);
		System.out.println("SET OS FROM Manager1 -> " + manager.setOnAgent(new ParameterSet("os", "WIN7", "striz")).getValue());

	
	}
}
