import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * 
 * Class MIB
 *
 */
public class MIB {
	private ArrayList<MibRecord> mib;
	
	/**
	 * Create and initialize the MIB
	 */
	public MIB() {
		this.mib = new ArrayList<MibRecord>();
		this.initializeStructureMib();
	}
	
	/**
	 * initialize the MIB structure
	 */
	public void initializeStructureMib () {
		String[] key = {"os","addrIp","addrMac","statusInterface"};
		String[] value = {"DEF","DEF","DEF","DEF"};
		Droit[] permissions = {Droit.RW,Droit.RW,Droit.RO,Droit.RO};
		
		for (int i = 0; i < key.length; i++) {
			MibRecord tmp = new MibRecord(key[i], value[i], permissions[i]);
			this.mib.add(tmp);
		}	
		System.out.println("Mib structure OK");
	}
	
	public ArrayList<MibRecord> getMib() {
		return mib;
	}

	/**
	 * Method which set the MIB value in trioIndexValeurDroit with the key
	 * @param key is the MIB object name
	 * @param value is the MIB value
	 */
	public void setValueMib(String key,String value) {
		MibRecord mibRecord = this.getMibRecord(key);
		if ( mibRecord != null) {
			mibRecord.setValue(value);
		} 
		
		
	}
	
	/**
	 * Method to get index with the key
	 * @param key is the MIB object name
	 * @return the MIB index with the key
	 */
	public int getIndex(String key) {
		MibRecord mibRecord = this.getMibRecord(key);
		if (mibRecord == null) {
			return -1;
		}
		return this.mib.indexOf(mibRecord);
	}
	


	
	public MibRecord getMibRecord(String key) {
		for (MibRecord mibRecord : this.mib) {
			if (mibRecord.getKey().equals(key)) {
				return mibRecord;

			}
		}
		return null;
	}
	
	public MibRecord getMibNextRecord(MibRecord mibRecord) {
		for (int i = 0; i < this.mib.size(); i++) {
			if (this.mib.get(i).getKey() == mibRecord.getKey()) {
				return this.mib.get(i + 1);
			}
		}
		return null;
	}

	
	
	
	
}
	

