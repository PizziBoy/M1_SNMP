package V1;

public class Routeur extends Equipement {

	public Routeur(String marque, String reference, String nbInterfaces) {
		super("Routeur",marque, reference);
		this.addEntry("nbInterfaces", nbInterfaces);
		// TODO Auto-generated constructor stub
	}

	
}
