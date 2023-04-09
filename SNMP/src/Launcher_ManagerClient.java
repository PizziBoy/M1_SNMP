
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
					//Parametres requetes
					ParameterGet paramG = new ParameterGet(null,community);
					ParameterSet paramS = new ParameterSet(null,community,null);
					paramG.setCommunity(community);
					paramS.setCommunity(community);
					
					menuOn = menuM.requestCommande(community, manager, menuOn, paramG, paramS);
					
				}
				
			scan.close();
			break;
		}
		

	}

}