package V1;

public class Router extends Equipment {

	public Router(String manufacturer, String reference, String nbInterfaces) {
		super("Router",manufacturer, reference);
		this.addEntry("nbInterfaces", nbInterfaces);
		// TODO Auto-generated constructor stub
	}

	
}
