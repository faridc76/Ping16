package fr.ineo.gestineo.dto;

public class DemandeItem {
	int id;
	String statut;
	String tache;
	String auteur;

	public DemandeItem(int id, String statut, String tache, String auteur) {
		super();
		this.id = id;
		this.statut = statut;
		this.tache = tache;
		this.auteur = auteur;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getTache() {
		return tache;
	}

	public void setTache(String tache) {
		this.tache = tache;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
