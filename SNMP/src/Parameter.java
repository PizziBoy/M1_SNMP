import java.io.Serializable;

/**
 * 
 * Class Parameter
 *
 */
public class Parameter implements Serializable{

	private String name;
	private String community;
	
	/**
	 * Parameter Constructor
	 * @param <String> name
	 */
	public Parameter(String name, String community) {
		this.name = name;
		this.community = community;
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
	
	/**
	 * Get value of the attribute community of Parameter
	 * @return <String> attribute community
	 */
	public String getCommunity() {
		return community;
	}
	
	/**
	 * Set value of the attribute community of Parameter
	 * @param <String> community
	 */
	public void setCommunity(String community) {
		this.community = community;
	}
	
	
	
	
	
	
}
