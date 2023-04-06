import java.rmi.Naming;
import java.util.ArrayList;

public class TestManager {

	public static void main(String[] args) throws Exception {
		Manager manager = new Manager("Manager1", "localhost", 20999);
		//Set existing agent 
		manager.setCurrentAgent("Agent1");
		
		System.out.println("GET OS FROM Manager1 -> " + manager.getFromAgent(new ParameterGet("os", "stri")).getValue());
		
		
		Manager manager2 = new Manager("Manager2", "localhost", 20999);
		
		ArrayList<String> monitoredVariablesManager2 = new ArrayList<String>();
		monitoredVariablesManager2.add("os");
		
		System.out.println(manager.addEntity("Manager2", monitoredVariablesManager2).getValue());
	
	}
}
