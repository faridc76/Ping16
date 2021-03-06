package fr.ineo.gestineo.dao.db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.StrictMode;
import android.util.Log;
import fr.ineo.gestineo.dao.ICommandeDB;
import fr.ineo.gestineo.dao.IUtilisateurDB;
import fr.ineo.gestineo.dto.Affaire;
import fr.ineo.gestineo.dto.Commande;
import fr.ineo.gestineo.dto.CommandeItem;
import fr.ineo.gestineo.dto.Utilisateur;

public class CommandeDB implements ICommandeDB {

	public final static String DOMAINE = "http://faridchouakria.free.fr/webservices/";
	public final static int EN_ATTENTE = 0;
	public final static int VALIDE = 1;
	public final static int REFUSE = 2;

	@Override
	public boolean passerCommande(Commande c) {
		String result = "";
		int id = 0;
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		Log.i("num commande", c.getNumCommande());
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			URL url = new URL(DOMAINE + "passer_commande.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("id_affaire=" + c.getAffaire().getId() + "&id_utilisateur=" + c.getUtilisateur().getId()
					+ "&num_commande=" + c.getNumCommande() + "&observation=" + c.getObservation() + "&marque="
					+ c.getMarque() + "&reference=" + c.getReference() + "&designiation=" + c.getDesignation()
					+ "&quantite=" + c.getQuantite());
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String ligne;
			while ((ligne = reader.readLine()) != null) {
				result += ligne;
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
		return (!result.equals("0"));
	}

	@Override
	public List<CommandeItem> listeCommandes(int idAffaire) {
		Log.e("id affaire dans fonction", idAffaire + "");
		String result = "";
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		ArrayList<CommandeItem> list = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);

			URL url = new URL(DOMAINE + "recup_commandes_affaire.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("id_affaire=" + String.valueOf(idAffaire));
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String ligne;
			while ((ligne = reader.readLine()) != null) {
				result += ligne;
			}

			list = new ArrayList<CommandeItem>();
			JSONObject obj = new JSONObject(result);
			JSONArray jsonArray = obj.getJSONArray("commandes");
			UtilisateurDB dao = new UtilisateurDB();
			if (jsonArray != null) {
				int len = jsonArray.length();
				for (int i = 0; i < len; i++) {
					Log.e("boucle", "" + i);
					String leStatut = leStatut(jsonArray.getJSONObject(i).getInt("statut"));
					Utilisateur u = dao.getUtilisateurFromId(jsonArray.getJSONObject(i).getInt("auteur"));
					CommandeItem commandeItem = new CommandeItem(leStatut,
							jsonArray.getJSONObject(i).getString("numero"), u.getPrenom() + " " + u.getNom());
					list.add(commandeItem);
				}
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
		return list;
	}

	@Override
	public Commande recupCommande(String numCommande) {
		Log.i("numcommande", numCommande);
		String result = "";
		Commande commande = null;
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);

			URL url = new URL(DOMAINE + "recup_info_commande.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("num_commande=" + numCommande);
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String ligne;
			while ((ligne = reader.readLine()) != null) {
				result += ligne;
			}
			IUtilisateurDB utilisateurDB = new UtilisateurDB();
			JSONObject obj = new JSONObject(result);
			commande = new Commande();
			if (obj.getBoolean("result")) {
				commande.setId(obj.getInt("id"));
				commande.setUtilisateur(utilisateurDB.getUtilisateurFromId(obj.getInt("utilisateur")));
				commande.setNumCommande(obj.getString("numero"));
				commande.setObservation(obj.getString("observation"));
				commande.setMarque(obj.getString("marque"));
				commande.setReference(obj.getString("reference"));
				commande.setDesignation(obj.getString("designiation"));
				commande.setQuantite(obj.getInt("quantite"));
				commande.setStatut(obj.getInt("statut"));
				System.out.println(commande.getNumCommande());
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
		return commande;
	}

	public void validerCommande(int statut, int idCommande) {
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			URL url = new URL(DOMAINE + "valider_commande.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("id_commande=" + idCommande + "&statut=" + statut);
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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

	public static String leStatut(int statut) {
		if (statut == 0) {
			return "En attente";
		} else if (statut == 1) {
			return "Validée";
		} else if (statut == 2) {
			return "Refusée";
		} else {
			return "Indeterminé";
		}
	}

	public String getFreeNumCommande(Affaire affaire, Utilisateur utilisateur) {
		String result = "";
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			URL url = new URL(DOMAINE + "is_free_numero_commande.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("num_commande=" + affaire.getNom() + "_" + utilisateur.getMatricule() + "_C_");
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String ligne;
			while ((ligne = reader.readLine()) != null) {
				result += ligne;
			}
			Log.i("result numero de commande", result);
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
		return result.trim();
	}
}
