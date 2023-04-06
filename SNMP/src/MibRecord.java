import java.io.Serializable;
import java.util.Observable;

public class MibRecord extends Observable implements Serializable {
	private String key;
	private String value;
	private Droit permission;
	
	
	
	public MibRecord(String key, String value, Droit permission) {
		super();
		this.key = key;
		this.value = value;
		this.permission = permission;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
		this.notifyObservers(this);
	}
	public Droit getPermission() {
		return permission;
	}
	public void setPermission(Droit permission) {
		this.permission = permission;
	}
	public void notifyObservers(MibRecord mibRecord) {
		this.setChanged();
		super.notifyObservers(mibRecord);
	}
	
	
}
