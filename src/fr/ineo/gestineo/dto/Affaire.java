package fr.ineo.gestineo.dto;

import java.util.ArrayList;
import java.util.List;

public class Affaire {
	
	private int id;
	
	private String nom;
	
	private String lieu;
	
	private String budget;
	
	private String commenditaire;
	
	private int statut;
	
	private Utilisateur responsable;
	
	private Utilisateur conducteur;
	
	private Utilisateur chef;
	
	private List<String> documents = new ArrayList<String>();
	
	public Affaire(){
		id = 0;
	}
	
	public Affaire(int id, String nom, String lieu, String budget, String commenditaire, int statut, Utilisateur responsable, Utilisateur conducteur, Utilisateur chef) {
		this.id = id;
		this.nom = nom;
		this.lieu = lieu;
		this.budget = budget;
		this.commenditaire = commenditaire;
		this.statut = statut;
		this.responsable = responsable;
		this.conducteur = conducteur;
		this.chef = chef;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getCommenditaire() {
		return commenditaire;
	}

	public void setCommenditaire(String commenditaire) {
		this.commenditaire = commenditaire;
	}

	public int getStatut() {
		return statut;
	}

	public void setStatut(int statut) {
		this.statut = statut;
	}

	public Utilisateur getResponsable() {
		return responsable;
	}

	public void setResponsable(Utilisateur responsable) {
		this.responsable = responsable;
	}

	public Utilisateur getConducteur() {
		return conducteur;
	}

	public void setConducteur(Utilisateur conducteur) {
		this.conducteur = conducteur;
	}

	public Utilisateur getChef() {
		return chef;
	}

	public void setChef(Utilisateur chef) {
		this.chef = chef;
	}

	public List<String> getDocuments() {
		return documents;
	}

	public void setDocuments(List<String> documents) {
		this.documents = documents;
	}

	@Override
	public String toString() {
		return "Affaire [id=" + id + ", nom=" + nom + ", lieu=" + lieu + ", budget=" + budget + ", commenditaire="
				+ commenditaire + ", statut=" + statut + ", responsable=" + responsable + ", conducteur=" + conducteur
				+ ", chef=" + chef + ", documents=" + documents + "]";
	}

	
}
