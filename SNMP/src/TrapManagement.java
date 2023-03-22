import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TrapManagement implements Serializable {

	private ArrayList<String> subscribeVariable;

	public TrapManagement() {
		this.subscribeVariable = new ArrayList<String>();
	}
	
	public void setValue(String value) {
		this.subscribeVariable.add(value);
	}
	
	public void change(String key, String oldValue, String newValue) {
		System.out.println(subscribeVariable.toString());
		for (String s : this.subscribeVariable) {
			if (s.equals(key)) {
				LocalDateTime datetime = LocalDateTime.now();
				String result = "[" + datetime + "] " + "MIB Object : " + key + " | Old value : " + oldValue + " | New value : " + newValue;
				TrapSender.send(result);
			}
		}
	}
	
	
	
}
