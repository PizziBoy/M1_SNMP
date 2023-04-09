import java.rmi.Naming;
import java.util.ArrayList;

public class StarterManager {

	
	public StarterManager() {
		
	}
	
	
	public void startManagerToManager(Manager manager, String entityName, ArrayList<String> subscribeVariable) throws Exception {
		SNMPEntity managerEntity = (SNMPEntity) Naming.lookup("rmi://" + manager.registryAddr + ":" + manager.registryPort + "/" + entityName);
		System.out.println(managerEntity.addEntity(manager.entityName, subscribeVariable));
		System.out.println("Manager is listening to traps from "+ entityName);
	}
	
	public void startAgentToManager(Manager manager, String entityName, ArrayList<String> subscribeVariable) throws Exception {
		manager.setCurrentAgent(entityName);
		((SNMPEntity) manager.getAgent()).addEntity(manager.entityName, subscribeVariable);
	}
	
}
