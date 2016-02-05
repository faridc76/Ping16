package fr.ineo.gestineo.dto;

public class DemandeDePersonnel {

	private int id;
	private Affaire affaire;
	private Utilisateur utilisateur;
	private String objet;
	private String tache;
	private int duree;

	public DemandeDePersonnel(int id, Affaire affaire, Utilisateur utilisateur, String objet, String tache, int duree) {
		super();
		this.id = id;
		this.affaire = affaire;
		this.utilisateur = utilisateur;
		this.objet = objet;
		this.tache = tache;
		this.duree = duree;
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

}
