package fr.ineo.gestineo.dao.db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.StrictMode;
import fr.ineo.gestineo.dao.IMessage;
import fr.ineo.gestineo.dto.Affaire;
import fr.ineo.gestineo.dto.Message;
import fr.ineo.gestineo.dto.Utilisateur;

public class MessageDB implements IMessage {

	public final static String DOMAINE = "http://faridchouakria.free.fr/webservices/";

	@Override
	public boolean ajoutMessage(Message m) {
		String result = "";
		int id = 0;
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			URL url = new URL(DOMAINE + "envoie_message.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("id_affaire=" + m.getAffaire().getId() + "&id_utilisateur=" + m.getUtilisateur().getId()
					+ "&message=" + m.getMessage());
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String ligne;
			while ((ligne = reader.readLine()) != null) {
				result += ligne;
			}
			id = Integer.parseInt(result);
			m.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
			}
			try {
				reader.close();
			} catch (Exception e) {
			}
		}
		return (id != 0);
	}

	@Override
	public void listeMessages(Affaire aff, List<Message> messages, Utilisateur userConnected) {
		String a = "";
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			a = "";
			URL url = new URL(DOMAINE + "liste_messages_affaire.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des données
			connection.setRequestMethod("POST");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("id_affaire=" + aff.getId());
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String ligne;
			while ((ligne = reader.readLine()) != null) {
				a += ligne;
			}
			JSONObject jsonObject = new JSONObject(a);
			JSONArray array = new JSONArray(jsonObject.getString("messages"));
			for (int i = 0; i < array.length(); i++) {
				// On récupère un objet JSON du tableau
				JSONObject obj = new JSONObject(array.getString(i));
				// On fait le lien Message - Objet JSON
				UtilisateurDB dao = new UtilisateurDB();
				Message m = new Message();
				m.setId(obj.getInt("id"));
				m.setAffaire(aff);
				m.setUtilisateur(dao.getUtilisateurFromId(obj.getInt("utilisateur")));
				m.setMessage(obj.getString("message"));
				m.setSelf(m.getUtilisateur().equals(userConnected));
				messages.add(m);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
			}
			try {
				reader.close();
			} catch (Exception e) {
			}
		}
	}

}
