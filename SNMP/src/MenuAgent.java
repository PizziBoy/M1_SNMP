import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MenuAgent {
	
	private Scanner scan;
    private String agentName;
	private String registryAddress;
	private int port;

	public MenuAgent() {
		this.scan = new Scanner(System.in);
        this.agentName = "Agent1";
		this.registryAddress = "localhost";
		this.port = 20999;
	}
	
	public String requestAgentName() {
		System.out.println("What is your agent name (press Enter for default name = Agent1) ?");
		String agentName = this.scan.nextLine();
        if(agentName.equals("") || agentName.equals(" ")) {
			return this.agentName;
		}else {
			this.agentName = agentName;
			return this.agentName;
		}
	}

    public HashMap<String, Droit> configureCommunity() {
        boolean menuCommunityOn = true;
        HashMap<String, Droit> configCommunity = new HashMap<String, Droit>();
        configCommunity.put("public", Droit.RO);
        System.out.println("Do you want to configure any comunity?");
        System.out.println("1 - YES");
		System.out.println("2 - NO (there is/are "+configCommunity.keySet().size()+" community/ties configured)");
        int confComm = scan.nextInt();
		scan.nextLine();
        while (menuCommunityOn) {
            if (confComm == 1) {
                System.out.println("What is the name of the community you want to configure?");
                String communityName = scan.nextLine();
                System.out.println("What are the rights for this community (RW | RO)?");
                String communityRights = scan.nextLine();
                if (communityRights == "RW") {
                    configCommunity.put(communityName, Droit.RW);
                    System.out.println("The community "+communityName+" has been added with "+communityRights+" !");
                } else if (communityRights == "RO") {
                    configCommunity.put(communityName, Droit.RO);
                    System.out.println("The community "+communityName+" has been added with "+communityRights+" !");
                } else {
                    System.out.println("ERROR : The rights you want to give are not recognizable !!! Please use RW or RO !");
                    System.out.println("You have to restart the procedure !");
                }
                
            } else if (confComm == 2) {
                menuCommunityOn = false;
            }
        }
        return configCommunity;
    }
	
	
	
	public String requestRegistryAddress() {
		System.out.println("What is the registry address (press Enter for default address = localhost) ?");
		String registryAddress = scan.nextLine();
		if(registryAddress.equals("") || registryAddress.equals(" ")) {
			return this.registryAddress;
		}else {
			this.registryAddress = registryAddress;
			return this.registryAddress;
		}
	}
	
	public int requestRegistryPort() {
		System.out.println("What is the registry port you want to use (type 0 for default = 20999)?");
		int port = scan.nextInt();
		scan.nextLine();
		if(port == 0) {
			return this.port;
		}else {
			this.port = port;
			return this.port;
		}
	}
	
}
