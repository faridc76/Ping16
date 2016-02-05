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
import fr.ineo.gestineo.dao.IAffaireDB;
import fr.ineo.gestineo.dao.IUtilisateurDB;
import fr.ineo.gestineo.dto.Affaire;
import fr.ineo.gestineo.dto.AffaireItem;

public class AffaireDB implements IAffaireDB {

	public final static String DOMAINE = "http://faridchouakria.free.fr/webservices/";
	public final static String DOCUMENT = "http://faridchouakria.free.fr/documents/";

	@Override
	public List<AffaireItem> listeAffaire(int idUtilisateur) {
		String result = "";
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		ArrayList<AffaireItem> list = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);

			URL url = new URL(DOMAINE + "recup_affaire_utilisateur.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("id_utilisateur=" + String.valueOf(idUtilisateur));
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String ligne;
			while ((ligne = reader.readLine()) != null) {
				result += ligne;
			}

			list = new ArrayList<AffaireItem>();
			JSONObject obj = new JSONObject(result);
			JSONArray jsonArray = obj.getJSONArray("affaire");
			if (jsonArray != null) {
				int len = jsonArray.length();
				for (int i = 0; i < len; i++) {
					String leStatut = leStatut(jsonArray.getJSONObject(i).getInt("statut"));
					AffaireItem affaireItem = new AffaireItem(jsonArray.getJSONObject(i).getString("nom"),
							jsonArray.getJSONObject(i).getString("commenditaire"), leStatut);
					list.add(affaireItem);
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
	public List<String> listeDocument(String nomAffaire) {
		String result = "";
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		ArrayList<String> list = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);

			URL url = new URL(DOMAINE + "liste_documents_affaire.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("affaire=" + String.valueOf(nomAffaire));
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String ligne;
			while ((ligne = reader.readLine()) != null) {
				result += ligne;
			}
			System.out.println(result);
			list = new ArrayList<String>();
			JSONObject obj = new JSONObject(result);
			JSONArray jsonArray = obj.getJSONArray("documents");
			if (jsonArray != null) {
				int len = jsonArray.length();
				for (int i = 0; i < len; i++) {
					list.add(jsonArray.get(i).toString());
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

	public Affaire recupAffaire(String nomAffaire) {
		String result = "";
		Affaire affaire = null;
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);

			URL url = new URL(DOMAINE + "recup_info_affaire.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("nom_affaire=" + nomAffaire);
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String ligne;
			while ((ligne = reader.readLine()) != null) {
				result += ligne;
			}

			IUtilisateurDB utilisateurDB = new UtilisateurDB();
			JSONObject obj = new JSONObject(result);
			affaire = new Affaire();
			affaire.setId(obj.getInt("id"));
			affaire.setNom(obj.getString("nom"));
			affaire.setLieu(obj.getString("lieu"));
			affaire.setBudget(obj.getString("budget"));
			affaire.setCommenditaire(obj.getString("commenditaire"));
			affaire.setStatut(obj.getInt("statut"));
			affaire.setResponsable(utilisateurDB.getUtilisateurFromId(obj.getInt("responsable")));
			affaire.setConducteur(utilisateurDB.getUtilisateurFromId(obj.getInt("conducteur")));
			affaire.setChef(utilisateurDB.getUtilisateurFromId(obj.getInt("chef")));
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
		return affaire;
	}

	@Override
	public List<String> listeDocumentDossier(String nomAffaire, String dossier) {
		String result = "";
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		ArrayList<String> list = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);

			URL url = new URL(DOMAINE + "liste_documents_affaire_dossier.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("affaire=" + nomAffaire + "&dossier=" + dossier);
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String ligne;
			while ((ligne = reader.readLine()) != null) {
				result += ligne;
			}
			System.out.println(result);
			list = new ArrayList<String>();
			JSONObject obj = new JSONObject(result);
			JSONArray jsonArray = obj.getJSONArray("documents");
			if (jsonArray != null) {
				int len = jsonArray.length();
				for (int i = 0; i < len; i++) {
					list.add(jsonArray.get(i).toString());
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

	public static String leStatut(int statut) {
		if (statut == 0) {
			return "Non démarré";
		} else if (statut == 1) {
			return "En cours";
		} else if (statut == 2) {
			return "Terminé";
		} else {
			return "Indeterminé";
		}
	}

}
