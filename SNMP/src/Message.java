import java.io.Serializable;

public class Message implements Serializable {
	private String type;
	private String value;
	
	public Message(String type, String value) {
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}
	
	
	
}
