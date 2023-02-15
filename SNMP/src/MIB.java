import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * Class MIB
 *
 */
public class MIB {
	private HashMap<String,TrioIndexValeurDroit> mib;
	
	/**
	 * Create and initialize the MIB
	 */
	public MIB() {
		this.mib = new HashMap<String,TrioIndexValeurDroit>();
		this.initializeStructureMib();
	}
	
	/**
	 * initialize the MIB structure
	 */
	public void initializeStructureMib () {
		String[] objectMib = {"os","addrIp","addrMac","statusInterface"};
		Droit[] droitForObjectMib = {Droit.RO,Droit.RW,Droit.RO,Droit.RO};
		
		for (int i = 0; i < droitForObjectMib.length; i++) {
			this.mib.put(objectMib[i], new TrioIndexValeurDroit(i, null, droitForObjectMib[i]));
		}	
	}
	
	/**
	 * Method which set the MIB value in trioIndexValeurDroit with the key
	 * @param key is the MIB object name
	 * @param value is the MIB value
	 */
	public void setValueMib(String key,String value) {
		TrioIndexValeurDroit trioIndexValeurDroit = this.mib.get(key);
		trioIndexValeurDroit.setValeur(value);
	}
	
	/**
	 * Method to get index with the key
	 * @param key is the MIB object name
	 * @return the MIB index with the key
	 */
	public int getIndex(String key) {
		TrioIndexValeurDroit trioIndexValeurDroit = this.mib.get(key);
		return trioIndexValeurDroit.getIndex();
	}
	
	/**
	 * Method to get value with the key
	 * @param key is the MIB object name
	 * @return the MIB value with the key
	 */
	public String getValue(String key) {
		TrioIndexValeurDroit trioIndexValeurDroit = this.mib.get(key);
		return trioIndexValeurDroit.getValeur();
	}
	
	/**
	 * Method to get permission with the key
	 * @param key is the MIB object name
	 * @return the MIB permission with the key
	 */
	public Droit getDroit(String key) {
		TrioIndexValeurDroit trioIndexValeurDroit = this.mib.get(key);
		return trioIndexValeurDroit.getDroit();
	}	
	
}
