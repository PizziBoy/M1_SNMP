import java.io.Serializable;

public class TrioIndexValeurDroit implements Serializable{
	private int index;
	private String valeur;
	private Droit droit;
	
	public TrioIndexValeurDroit(int index, String valeur, Droit droit) {
		this.index = index;
		this.valeur = valeur;
		this.droit = droit;
	}

	public int getIndex() {
		return index;
	}

	public String getValeur() {
		return valeur;
	}

	public Droit getDroit() {
		return droit;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	
	
}
