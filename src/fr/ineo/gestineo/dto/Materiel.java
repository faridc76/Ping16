package fr.ineo.gestineo.dto;

public class Materiel {
	
	private int id;
	private String libelle;
	private String categorie;
	private String reference;
	private String fournisseur;
	private double prix;
	
	public Materiel() {
		id = 0;
	}

	public Materiel(int id, String libelle, String categorie, String reference, String fournisseur, double prix) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.categorie = categorie;
		this.reference = reference;
		this.fournisseur = fournisseur;
		this.prix = prix;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

}
