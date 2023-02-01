import java.io.Serializable;

public class ParameterSet extends Parameter implements Serializable {
	
	private String value;

	public ParameterSet(String name,String value) {
		super(name);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	

}
