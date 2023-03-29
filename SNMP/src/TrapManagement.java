import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TrapManagement implements Serializable {

	private ArrayList<String> monitoredVariables;
	
	private AgentImpl agent;
	

	public TrapManagement(AgentImpl agent) {
		/**
		 * Added agent to attribute in order to call rmi method sendTrap()
		 */
		this.agent = agent;
	}
	
	public void setValue(String value) {
		this.monitoredVariables.add(value);
	}
	
	//Observer 
	public void change(String key, String oldValue, String newValue) {
		for (String s : this.monitoredVariables) {
			if (s.equals(key)) {
				LocalDateTime datetime = LocalDateTime.now();
				String result = "[" + datetime + "] " + "MIB Object : " + key + " | Old value : " + oldValue + " | New value : " + newValue;
				TrapSender.send(result);
			}
		}
	}
	
	public void notifyBadCommunity(String community) {
		LocalDateTime datetime = LocalDateTime.now();
		String result = "[" + datetime + "] Bad community : " + community;
		TrapSender.send(result);
	}
	
	
	
}
