package fr.ineo.gestineo.dto;

public class Message {
	@Override
	public String toString() {
		return "Message [id=" + id + ", message=" + message + ", isSelf=" + isSelf + ", affaire=" + affaire.getId()
				+ ", utilisateur=" + utilisateur.getId() + "]";
	}

	private int id;
	private String message;
	private boolean isSelf;
	private Affaire affaire;
	private Utilisateur utilisateur;

	public Message() {
	}

	public Message(Utilisateur utilisateur, String message, boolean isSelf, Affaire affaire) {
		this.utilisateur = utilisateur;
		this.message = message;
		this.isSelf = isSelf;
		this.affaire = affaire;
		id = 0;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSelf() {
		return isSelf;
	}

	public void setSelf(boolean isSelf) {
		this.isSelf = isSelf;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}