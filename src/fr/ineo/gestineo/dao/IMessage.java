package fr.ineo.gestineo.dao;

import java.util.List;

import fr.ineo.gestineo.dto.Affaire;
import fr.ineo.gestineo.dto.Message;
import fr.ineo.gestineo.dto.Utilisateur;

public interface IMessage {

	public boolean ajoutMessage(Message m);

	public void listeMessages(Affaire aff, List<Message> messages, Utilisateur userConnected);

}
