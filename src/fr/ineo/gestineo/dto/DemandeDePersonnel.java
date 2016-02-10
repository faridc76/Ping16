package fr.ineo.gestineo.dto;

public class DemandeDePersonnel {

	private int id;
	private Affaire affaire;
	private Utilisateur utilisateur;
	private String objet;
	private String tache;
	private int duree;
	private String date;
	private int statut;
	private String numDemande;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public DemandeDePersonnel() {
		id = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Affaire getAffaire() {
		return affaire;
	}

	public void setAffaire(Affaire affaire) {
		this.affaire = affaire;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public String getTache() {
		return tache;
	}

	public void setTache(String tache) {
		this.tache = tache;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public void ajoutDuree(String d) {
		if (d.equals("")) {
			setDuree(0);
		} else {
			setDuree(Integer.parseInt(d));
		}
	}

	public Boolean estDemandeComplete() {
		if (tache.equals("") || objet.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public int getStatut() {
		return statut;
	}

	public void setStatut(int statut) {
		this.statut = statut;
	}

	public String getNumDemande() {
		return numDemande;
	}

	public void setNumDemande(String numDemande) {
		this.numDemande = numDemande;
	}

}
