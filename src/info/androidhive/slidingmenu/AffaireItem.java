package info.androidhive.slidingmenu;

public class AffaireItem {
	
    private String nom;
    
    private String commanditaire;
    
    private int statut;

    public AffaireItem(String nom, String commanditaire, int statut) {
       this.setNom(nom);
       this.setCommanditaire(commanditaire);
       this.setStatut(statut);
    }

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCommanditaire() {
		return commanditaire;
	}

	public void setCommanditaire(String commanditaire) {
		this.commanditaire = commanditaire;
	}

	public int getStatut() {
		return statut;
	}

	public void setStatut(int statut) {
		this.statut = statut;
	}
}