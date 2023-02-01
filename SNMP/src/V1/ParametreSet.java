package V1;

public class ParametreSet extends Parametre{
	
	private String valeur;

	public ParametreSet(String nom,String valeur) {
		super(nom);
		this.valeur = valeur;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	
	
	
	

}
