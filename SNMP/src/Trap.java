import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Trap implements Runnable{

	private ServerSocket socketServer;
	private Socket socketServise;
	
	@Override
	public void run() {
		try {
			this.socketServer = new ServerSocket(54321);
			while(true) {
				this.socketServise = this.socketServer.accept();
				
				BufferedReader inSocket = new BufferedReader(new InputStreamReader(this.socketServise.getInputStream()));
				
				String trapReceived = inSocket.readLine();
				
				System.out.println(this.TrapDisplayStructure(trapReceived));
				
				this.socketServise.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.socketServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String TrapDisplayStructure(String trapReceived) {
		String str = "######################################### TRAP RECEIVER START ##########################################\n";
		str = str +  "- Trap received from agent ("+this.socketServise.getInetAddress().toString()+")\n";
		str = str +  "- Message received is :\n";
		str = str +   trapReceived + "\n";
		str = str +  "########################################## TRAP RECEIVER END ###########################################\n";
		return str;
	}
	

}
