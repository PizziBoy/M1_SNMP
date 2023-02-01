package V1;

import java.util.HashMap;

public class Equipement {
	private HashMap<String,String> tableEquipement;
	
	protected void addEntry(String key, String value) {
		this.tableEquipement.put(key, value);
	}
	
	public Equipement(String type,String marque, String reference) {
		super();
		this.tableEquipement = new HashMap<String,String>();
		this.addEntry("type", type);
		this.addEntry("marque", marque);
		this.addEntry("reference", reference);
	}
}
