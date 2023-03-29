import java.util.ArrayList;

public class ParameterSubscribe {

	private String addrIp;
	private int numPort;
	private String managerName;
	private ArrayList<String> monitoredVariables;
	
	public ParameterSubscribe(String addrIp, int numPort, ArrayList<String> monitoredVariables,String managerName) {
		this.addrIp = addrIp;
		this.numPort = numPort;
		this.monitoredVariables = monitoredVariables;
		this.managerName = managerName;
	}
	

	public String getAddrIp() {
		return this.addrIp;
	}

	public int getNumPort() {
		return this.numPort;
	}

	public ArrayList<String> getMonitoredVariables() {
		return this.monitoredVariables;
	}


	public String getManagerName() {
		return managerName;
	} 
	
	
	
	
}
