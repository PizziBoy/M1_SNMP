import java.rmi.Naming;
import java.util.ArrayList;

public class testManager2 {

	public static void main(String[] args) throws Exception {
		Manager manager2 = new Manager("Manager2", "localhost", 20999);
		//Set Manager 2
		//manager2.setCurrentAgent("Agent1");
		ArrayList<String> monitoredVariablesManager2 = new ArrayList<String>();
		monitoredVariablesManager2.add("addrIp");
		
		//Add to Manager1 (manager1.addEntity(manager2)
		SNMPEntity manager1 = (SNMPEntity) Naming.lookup("rmi://" + manager2.registryAddr + ":" + manager2.registryPort + "/" + "Manager1");
		System.out.println(manager1.addEntity(manager2.entityName, monitoredVariablesManager2));
		
		
		
		System.out.println();

	}

}
