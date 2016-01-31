package fr.ineo.gestineo.dao;

import fr.ineo.gestineo.dto.Utilisateur;

public interface IUtilisateurDB {
	
	boolean AjoutPersonne(Utilisateur u, String password);
	
	public Utilisateur checkLogin(String matricule, String password);
	
	public boolean isMatriculeFree(String matricule);
	
	public Utilisateur getUtilisateurFromMatricule(String matricule);
	
	public Utilisateur getUtilisateurFromId(int id);
	
	public Utilisateur getUtilisateur(String mode, String valeur);

}
