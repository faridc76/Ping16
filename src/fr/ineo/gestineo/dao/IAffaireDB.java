package fr.ineo.gestineo.dao;

import java.util.List;

import fr.ineo.gestineo.dto.Affaire;
import fr.ineo.gestineo.dto.AffaireItem;

public interface IAffaireDB {

	public List<AffaireItem> listeAffaire(int idUtilisateur);

	public Affaire recupAffaire(String affaire);

	public List<String> listeDocument(String nomAffaire);

	List<String> listeDocumentDossier(String nomAffaire, String dossier);

}
