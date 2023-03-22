import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class TrapSender  {
	
	//socket 
	
	public static void send(String msg) {
		Socket clientSocket;
		try {
			clientSocket = new Socket("127.0.0.1", 54321);
			PrintStream out = new PrintStream(clientSocket.getOutputStream());
			out.println(msg);
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	
	

}
