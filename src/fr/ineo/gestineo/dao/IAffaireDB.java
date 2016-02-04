package fr.ineo.gestineo.dao;

import java.util.List;

import fr.ineo.gestineo.dto.Affaire;
import info.androidhive.slidingmenu.AffaireItem;


public interface IAffaireDB {

<<<<<<< HEAD
	public List<AffaireItem> listeAffaire(int idUtilisateur);
=======
	public List<Affaire> listeAffaire(int idUtilisateur);
>>>>>>> branch 'master' of https://github.com/faridc76/Ping16.git

	public Affaire recupAffaire(String affaire);
	
	public List<String> listeDocument(String nomAffaire);

}
