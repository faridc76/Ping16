package fr.ineo.gestineo.dao;

import java.util.List;

import fr.ineo.gestineo.dto.DemandeDePersonnel;
import fr.ineo.gestineo.dto.DemandeItem;

public interface IDemandeDePersonnelDB {

	public boolean demanderDuPersonnel(DemandeDePersonnel ddp);

	public List<DemandeItem> listeDemande(int idAffaire);

	public DemandeDePersonnel demandeFromId(int id);

}
