
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Launcher_ManagerClient {
	private final static Integer PORT = 20999;
	public static void main(String [] args) throws MalformedURLException, RemoteException, NotBoundException, AlreadyBoundException {
		
		Trap trap = new Trap();
		Thread trapThread = new Thread(trap);
		trapThread.start();
		
		Scanner scan = new Scanner(System.in);
		boolean menuOn = true;
		
		String community = "public";

		//Manager instanciation
		Manager manager = new Manager();
		//Publish registry
		manager.bindAgent((Agent) Naming.lookup("rmi://localhost:20999/agent"));
		
		System.out.println("Do you want to be a manager to manager or agent to manager?");
		System.out.println("1 - Manager to Manager");
		System.out.println("2 - Agent to Manager");
		int typeOfTrapSub = scan.nextInt();

		System.out.println("What is the IP address of the server?");
		String ipAddress = scan.nextLine();

		System.out.println("What is the port you want to use?");
		int port = scan.nextInt();

		ArrayList<String> subscribeVariable = new ArrayList<>();
		System.out.println("Which variable(s) you want to subscribe to?");
		System.out.println("Example of use : variable1;variable2;variable3");
		String v = scan.nextLine();
		String [] vTab = v.split(";");
		for (String variable : vTab) {
			subscribeVariable.add(variable);
		}
		ParameterSubscribe parameterSubscribe = new parameterSubscribe(ipAddress,port,subscribeVariable);
		switch(typeOfTrapSub) {
			case 1:
				manager.subscribeAgent(parameterSubscribe);
			break;
			case 2:
				manager.subscribeAgent(parameterSubscribe);
			break;
		}

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
		while(menuOn) {
			//Affichage du menu
			
			
			System.out.println("["+community+"] "+"1 - GET");
			System.out.println("["+community+"] "+"2 - SET");
			System.out.println("["+community+"] "+"3 - GET-NEXT");
			System.out.println("["+community+"] "+"O - LEAVE");
			
			//Parametres requetes
			ParameterGet paramG = new ParameterGet(null,community);
			ParameterSet paramS = new ParameterSet(null,community,null);
			paramG.setCommunity(community);
			paramS.setCommunity(community);
			try {
				valMenu = scan.nextInt();
				scan.nextLine();
				switch(valMenu) {
				case 0:
					menuOn = false;
					System.out.println("["+community+"] "+"You choose to leave!");
					break;
				case 1: 
					System.out.println("["+community+"] "+"You choose GET!\n");
					System.out.println("["+community+"] "+"Please enter the key : \n");
					paramG.setName(scan.next());
					System.out.println(manager.getFromAgent(paramG).getValue());
					break;
				case 2:
					System.out.println("["+community+"] "+"You choose SET!\n");
					System.out.println("["+community+"] "+"Please enter the key : \n");
					paramS.setName(scan.next());
					scan.nextLine();
					
					System.out.println("["+community+"] "+"Please enter the new value : \n");
					paramS.setValue(scan.next());
					System.out.println(manager.setOnAgent(paramS).getValue());
					break;
				case 3: 
					System.out.println("["+community+"] "+"You choose GET-NEXT!\n");
					System.out.println("["+community+"] "+"Please enter the key : \n");
					paramG.setName(scan.next());
					System.out.println(manager.getNextFromAgent(paramG).getValue());
					break;
				}
				
			} catch (InputMismatchException excep) {
				System.err.println("ERROR : Please enter a number between 0 and 3");
			}
			
		}
		
		scan.close();
	}

}