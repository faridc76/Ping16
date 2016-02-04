package fr.ineo.gestineo.dao;

import java.util.List;

import fr.ineo.gestineo.dto.Affaire;
import info.androidhive.slidingmenu.AffaireItem;


public interface IAffaireDB {

	public List<AffaireItem> listeAffaire(int idUtilisateur);

	public Affaire recupAffaire(String affaire);
	
	public List<String> listeDocument(String nomAffaire);

}
