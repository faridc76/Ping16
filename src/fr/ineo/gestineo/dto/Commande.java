package fr.ineo.gestineo.dto;

public class Commande {

	private int id;
	private Affaire affaire;
	private Utilisateur utilisateur;
	private String numCommande;
	private String observation;
	private String marque;
	private String reference;
	private String designiation;
	private int quantite;
	private int statut;
	private String date;

	public Commande() {
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

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDesignation() {
		return designiation;
	}

	public void setDesignation(String designation) {
		this.designiation = designation;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Boolean estCommandeComplete() {
		if (designiation.equals("") || reference.equals("") || marque.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public void ajoutQuantite(String q) {
		if (q.equals("")) {
			setQuantite(0);
		} else {
			setQuantite(Integer.parseInt(q));
		}
	}

	public int getStatut() {
		return statut;
	}

	public void setStatut(int statut) {
		this.statut = statut;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNumCommande() {
		return numCommande;
	}

	public void setNumCommande(String numCommande) {
		this.numCommande = numCommande;
	}
}
