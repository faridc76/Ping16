package fr.ineo.gestineo.dto;

public class Utilisateur {

	private int id = 0;
	private String matricule;
	private String nom;
	private String prenom;
	private String numero;
	private String bureau;
	private String mail;
	private int fonction;
	
	public Utilisateur () {
		id = 0;
	};
	
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBureau() {
		return bureau;
	}

	public void setBureau(String bureau) {
		this.bureau = bureau;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getFonction() {
		return fonction;
	}

	public void setFonction(int fonction) {
		this.fonction = fonction;
	}

}
