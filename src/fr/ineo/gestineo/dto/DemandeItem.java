package fr.ineo.gestineo.dto;

public class DemandeItem {
	String statut;
	String numero;
	String auteur;

	public DemandeItem(String statut, String numero, String auteur) {
		super();
		this.statut = statut;
		this.numero = numero;
		this.auteur = auteur;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

}
