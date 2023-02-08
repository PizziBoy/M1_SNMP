import java.io.Serializable;

/**
 * 
 * Class ParameterSet extends Parameter
 *
 */
public class ParameterSet extends Parameter implements Serializable {
	
	private String value;
	
	/**
	 * ParameterSet constructor
	 * @param <String> name
	 * @param <String> value
	 */
	public ParameterSet(String name,String value) {
		super(name);
		this.value = value;
	}
	
	/**
	 * Get value of the attribute value of ParameterSet
	 * @return <String> attribute value
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Set value of the attribute value of ParameterSet
	 * @param <String> value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	

}
