package fr.ineo.gestineo.dto;

public class CommandeItem {

	String statut;
	String reference;
	String auteur;

	public CommandeItem(String statut, String reference, String auteur) {
		super();
		this.statut = statut;
		this.reference = reference;
		this.auteur = auteur;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

}
