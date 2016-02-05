package fr.ineo.gestineo.dto;

public class AffaireItem {

	private String nom;

	private String commanditaire;

	private String statut;

	public AffaireItem(String nom, String commanditaire, String statut) {
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

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
}