package V1;

import java.util.HashMap;

public class Equipment {
	private HashMap<String,String> tableEquipment;
	
	protected void addEntry(String key, String value) {
		this.tableEquipment.put(key, value);
	}
	
	public Equipment(String type,String manufacturer, String reference) {
		super();
		this.tableEquipment = new HashMap<String,String>();
		this.addEntry("type", type);
		this.addEntry("manufacturer", manufacturer);
		this.addEntry("reference", reference);
	}
}
