import java.io.Serializable;

public class Parameter implements Serializable{

	private String name;
	
	public Parameter(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
