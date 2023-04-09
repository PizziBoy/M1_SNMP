import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager {
	
	private Scanner scan;
	private String ipAddress;
	private int port;
	private ArrayList<String> subscribeVariable;

	public MenuManager() {
		this.scan = new Scanner(System.in);
		this.ipAddress = "localhost";
		this.port = 20999;
		this.subscribeVariable = new ArrayList<String>();
	}
	
	public String requestManagerName() {
		System.out.println("What is your manager name?");
		String managerName = this.scan.nextLine();
		return managerName;
	}
	
	public int requestTypeOfTrapSub() {
		System.out.println("Do you want to be a manager to manager or agent to manager?");
		System.out.println("1 - Manager to Manager");
		System.out.println("2 - Agent to Manager");
		int typeOfTrapSub = scan.nextInt();
		scan.nextLine();
		return typeOfTrapSub;
	}
	
	public String requestEntityName() {
		System.out.println("What is its name ?");
		String entityName = scan.nextLine();
		return entityName;
	}
	
	public String requestIpAddress() {
		System.out.println("What is the IP address of the server (press Enter for default ip = localhost) ?");
		String ipAddress = scan.nextLine();
		if(ipAddress.equals("") || ipAddress.equals(" ")) {
			return this.ipAddress;
		}else {
			this.ipAddress = ipAddress;
			return this.ipAddress;
		}
	}
	
	public int requestPort() {
		System.out.println("What is the port you want to use (type 0 for default = 20999)?");
		int port = scan.nextInt();
		scan.nextLine();
		if(port == 0) {
			return this.port;
		}else {
			this.port = port;
			return this.port;
		}
	}
	
	public ArrayList<String> requestSubscribeVariable(){
		System.out.println("Which variable(s) you want to subscribe to?");
		System.out.println("Example of use : variable1;variable2;variable3");
		String v = scan.nextLine();
		String [] vTab = v.split(";");
		for (String variable : vTab) {
			this.subscribeVariable.add(variable);
		}
		return this.subscribeVariable;
	}
	
	public String requestCommunity(String community) {
		System.out.println("Do you have a community");
		System.out.println("1 - YES");
		System.out.println("2 - NO");
		int valMenu = scan.nextInt();
		switch(valMenu) {
		case 1:
			System.out.println("Enter your community : ");
			community = scan.next();
			break;
		case 2:
			System.out.println("Your default community is public");
			break;
		}
		return community;
		
	}
}
