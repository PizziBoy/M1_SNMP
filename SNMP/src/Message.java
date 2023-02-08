import java.io.Serializable;

/**
 * 
 * Class Message
 *
 */
public class Message implements Serializable {
	private String type;
	private String value;
	
	/**
	 * Message Constructor
	 * @param  <String> type
	 * @param  <String> value
	 */
	public Message(String type, String value) {
		this.type = type;
		this.value = value;
	}
	
	/**
	 * Get value of the attribute type of Message
	 * @return <String> attribute type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Get value of the attribute value of Message
	 * @return <String> attribute value
	 */
	public String getValue() {
		return value;
	}
	
	
	
}
