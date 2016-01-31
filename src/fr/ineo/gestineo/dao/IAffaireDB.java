package fr.ineo.gestineo.dao;

import java.util.List;

import fr.ineo.gestineo.dto.Affaire;


public interface IAffaireDB {

	public List<String> listeAffaire(int id_utilisateur);

	public Affaire recupAffaire(String affaire);
	
	public List<String> listeDocument(String nom_affaire);

}
