import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Launcher_Registry {

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(20999);
			System.out.println("Registry successfully launched");
			while (true) {
				
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
