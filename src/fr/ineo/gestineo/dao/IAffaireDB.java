package fr.ineo.gestineo.dao;

import java.util.List;

import fr.ineo.gestineo.dto.Affaire;


public interface IAffaireDB {

	public List<Affaire> listeAffaire(int idUtilisateur);

	public Affaire recupAffaire(String affaire);
	
	public List<String> listeDocument(String nomAffaire);

}
