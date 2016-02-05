package fr.ineo.gestineo.dao;

import java.util.List;

import fr.ineo.gestineo.dto.Commande;
import fr.ineo.gestineo.dto.CommandeItem;

public interface ICommandeDB {

	public boolean passerCommande(Commande c);

	public List<CommandeItem> listeCommandes(int idAffaire);

	public Commande recupCommande(String referenceCommande);
}
