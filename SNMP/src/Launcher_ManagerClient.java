
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Launcher_ManagerClient {

	public static void main(String [] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		boolean menuOn = true;
		String community = "public";
		
		//Set class MenuManager
		MenuManager menuM = new MenuManager();
		
		//request managerName
		String managerName = menuM.requestManagerName();
		
		//request type of strap sub
		int typeOfTrapSub = menuM.requestTypeOfTrapSub();
		
		//request entity name
		String entityName = menuM.requestEntityName();
		
		//request registry ip address 
		String ipAddress = menuM.requestIpAddress();
		
		//request registry port
		int port = menuM.requestPort();
		
		//request suscribed varaible
		ArrayList<String> subscribeVariable = menuM.requestSubscribeVariable();
	
		Manager manager = new Manager(managerName, ipAddress, port);
		
		//set class starterManager
		StarterManager starterManager = new StarterManager();
		
		switch(typeOfTrapSub) {
			case 1:
				
				//start Manager to Manager type
				starterManager.startManagerToManager(manager, entityName, subscribeVariable);
			break;
			case 2:
				
				//start Agent to Manager type
				starterManager.startAgentToManager(manager, entityName, subscribeVariable);
				
				//request community
				community = menuM.requestCommunity(community);
				
				while(menuOn) {
					int valMenu;
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
			break;
		}
		

	}

}