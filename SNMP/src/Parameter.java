import java.io.Serializable;

/**
 * 
 * Class Parameter
 *
 */
public class Parameter implements Serializable{

	private String name;
	
	/**
	 * Parameter Constructor
	 * @param <String> name
	 */
	public Parameter(String name) {
		this.name = name;
	}
	
	/**
	 * Get value of the attribute name of Parameter
	 * @return <String> attribute name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set value of the attribute name of Parameter
	 * @param <String> name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
