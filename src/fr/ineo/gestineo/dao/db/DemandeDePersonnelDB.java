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
import fr.ineo.gestineo.dao.IDemandeDePersonnelDB;
import fr.ineo.gestineo.dao.IUtilisateurDB;
import fr.ineo.gestineo.dto.Commande;
import fr.ineo.gestineo.dto.DemandeDePersonnel;
import fr.ineo.gestineo.dto.DemandeItem;
import fr.ineo.gestineo.dto.Utilisateur;

public class DemandeDePersonnelDB implements IDemandeDePersonnelDB {

	public final static String DOMAINE = "http://faridchouakria.free.fr/webservices/";

	@Override
	public boolean demanderDuPersonnel(DemandeDePersonnel ddp) {
		String result = "";
		int id = 0;
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			URL url = new URL(DOMAINE + "demande_de_personnel.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("id_affaire=" + ddp.getAffaire().getId() + "&id_utilisateur=" + ddp.getUtilisateur().getId()
					+ "&objet=" + ddp.getObjet() + "&tache=" + ddp.getTache() + "&duree=" + ddp.getDuree());
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String ligne;
			while ((ligne = reader.readLine()) != null) {
				result += ligne;
			}
			Log.e("result", result);
			id = Integer.parseInt(result);
			ddp.setId(id);
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
	public List<DemandeItem> listeDemande(int idAffaire) {
		Log.e("id affaire dans fonction", idAffaire + "");
		String result = "";
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		ArrayList<DemandeItem> list = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);

			URL url = new URL(DOMAINE + "recup_demandes_affaire.php");
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
			Log.e("result", "" + result);
			list = new ArrayList<DemandeItem>();
			JSONObject obj = new JSONObject(result);
			JSONArray jsonArray = obj.getJSONArray("demandes");
			UtilisateurDB dao = new UtilisateurDB();
			if (jsonArray != null) {
				int len = jsonArray.length();
				Log.e("len", "" + len);
				for (int i = 0; i < len; i++) {
					String leStatut = leStatut(jsonArray.getJSONObject(i).getInt("statut"));
					Utilisateur u = dao.getUtilisateurFromId(jsonArray.getJSONObject(i).getInt("auteur"));
					DemandeItem demandeItem = new DemandeItem(jsonArray.getJSONObject(i).getInt("id"), leStatut,
							jsonArray.getJSONObject(i).getString("tache"), u.getPrenom() + " " + u.getNom());
					list.add(demandeItem);
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
	public DemandeDePersonnel demandeFromId(int id) {
		String result = "";
		DemandeDePersonnel demandeDePersonnel = null;
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);

			URL url = new URL(DOMAINE + "recup_info_demande.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("id_demande=" + id);
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String ligne;
			while ((ligne = reader.readLine()) != null) {
				result += ligne;
			}

			IUtilisateurDB utilisateurDB = new UtilisateurDB();
			JSONObject obj = new JSONObject(result);
			demandeDePersonnel = new DemandeDePersonnel();
			demandeDePersonnel.setId(obj.getInt("id"));
			demandeDePersonnel.setUtilisateur(utilisateurDB.getUtilisateurFromId(obj.getInt("utilisateur")));
			demandeDePersonnel.setObjet(obj.getString("objet"));
			demandeDePersonnel.setTache(obj.getString("tache"));
			demandeDePersonnel.setDuree(obj.getInt("periode"));
			demandeDePersonnel.setDate(obj.getString("date"));
			demandeDePersonnel.setStatut(obj.getInt("statut"));
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
		return demandeDePersonnel;
	}
	
	public void validerDemande(int statut, int idDemande) {
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			URL url = new URL(DOMAINE + "valider_demande.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("id_demande=" + idDemande + "&statut=" + statut);
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

}
