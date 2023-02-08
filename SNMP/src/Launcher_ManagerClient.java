
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Launcher_ManagerClient {

	public static void main(String [] args) throws MalformedURLException, RemoteException, NotBoundException {
		Scanner scan = new Scanner(System.in);
		boolean menuOn = true;
		
		
		Manager manager = new Manager();
		manager.bindAgent((Agent) Naming.lookup("rmi://localhost:20999/agent"));
		
		while(menuOn) {
			//Affichage du menu
			System.out.println("1 - GET");
			System.out.println("2 - SET");
			System.out.println("O - LEAVE");
			
			//Parametres requetes
			ParameterGet paramG = new ParameterGet(null);
			ParameterSet paramS = new ParameterSet(null, null);
			
			try {
				int valMenu = scan.nextInt();
				scan.nextLine();
				switch(valMenu) {
				case 0:
					menuOn = false;
					System.out.println("You choose to leave!");
					break;
				case 1: 
					System.out.println("You choose GET!\n");
					System.out.println("Please enter the key : \n");
					paramG.setName(scan.next());
					System.out.println(manager.getFromAgent(paramG).getValue());
					break;
				case 2:
					System.out.println("You choose SET!\n");
					System.out.println("Please enter the key : \n");
					paramS.setName(scan.next());
					scan.nextLine();
					
					System.out.println("Please enter the new value : \n");
					paramS.setValue(scan.next());
					System.out.println(manager.setOnAgent(paramS).getValue());
					break;
				}
				
			} catch (InputMismatchException excep) {
				System.err.println("ERROR : Please enter a number between 0 and 2");
			}
			
		}
		
		scan.close();
	}

}