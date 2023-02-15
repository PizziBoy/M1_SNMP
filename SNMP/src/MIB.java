import java.util.HashMap;

public class MIB {
	private HashMap<String,TrioIndexValeurDroit> mib;

	public MIB() {
		this.mib = new HashMap<String,TrioIndexValeurDroit>();
	}
	
	public void initializeStructureMib () {
		HashMap<String,Droit> structureObjectAnd = {};
		
		this.mib.put("os", new TrioIndexValeurDroit(1, null, Droit.RO));
		this.mib.put("addr", new TrioIndexValeurDroit(2, null, Droit.RW));
		this.mib.ge
	}
	
}

