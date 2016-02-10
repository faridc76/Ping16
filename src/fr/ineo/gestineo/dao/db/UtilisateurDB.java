package fr.ineo.gestineo.dao.db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.os.StrictMode;
import fr.ineo.gestineo.dao.IUtilisateurDB;
import fr.ineo.gestineo.dto.Utilisateur;

public class UtilisateurDB implements IUtilisateurDB {

	public static String MY_PREFS_NAME = "Pref_pour_gestineo";
	public final static String CHEF_DE_CHANTIER = "Chef de chantier";
	public final static String CONDUCTEUR_DE_TRAVAUX = "Conducteur de travaux";
	public final static String RESPONSABLE_DAFFAIRES = "Responsable d'affaires";
	public final static String GOOD_RESULT = "true";
	public final static int FONCTION_CHEF_DE_CHANTIER = 0;

	public final static String DOMAINE = "http://faridchouakria.free.fr/webservices/";

	public boolean AjoutPersonne(Utilisateur u, String password) {
		String result = "";
		int id = 0;
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			URL url = new URL(DOMAINE + "ajoutUtilisateur.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("matricule=" + u.getMatricule() + "&nom=" + u.getNom() + "&prenom=" + u.getPrenom()
					+ "&numero=" + u.getNumero() + "&bureau=" + u.getBureau() + "&mail=" + u.getMail() + "&password="
					+ password);
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String ligne;
			while ((ligne = reader.readLine()) != null) {
				result += ligne;
			}
			id = Integer.parseInt(result);
			u.setId(id);
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
	public Utilisateur checkLogin(String matricule, String password) {
		String result = "";
		Utilisateur utilisateur = null;
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			URL url = new URL(DOMAINE + "recup_utilisateur.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("matricule=" + matricule + "&password=" + password);
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String ligne;
			while ((ligne = reader.readLine()) != null) {
				result += ligne;
			}
			JSONObject obj = new JSONObject(result);
			if (obj.getBoolean("result")) {
				utilisateur = new Utilisateur();
				utilisateur.setId(obj.getInt("id"));
				utilisateur.setMatricule(obj.getString("matricule"));
				utilisateur.setNom(obj.getString("nom"));
				utilisateur.setPrenom(obj.getString("prenom"));
				utilisateur.setNumero(obj.getString("numero"));
				utilisateur.setBureau(obj.getString("bureau"));
				utilisateur.setMail(obj.getString("mail"));
				utilisateur.setFonction(obj.getInt("fonction"));
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
		return utilisateur;
	}

	@Override
	public boolean isMatriculeFree(String matricule) {
		boolean value = true;
		String result = "";
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);

			URL url = new URL(DOMAINE + "is_free_matricule.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("matricule=" + matricule);
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String ligne;
			while ((ligne = reader.readLine()) != null) {
				result += ligne;
			}
			JSONObject obj = new JSONObject(result);
			value = obj.getBoolean("free");
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
		return value;
	}

	@Override
	public Utilisateur getUtilisateurFromMatricule(String matricule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur getUtilisateurFromId(int id) {
		String result = "";
		Utilisateur utilisateur = null;
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			URL url = new URL(DOMAINE + "recup_utilisateur_id.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("id=" + id);
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String ligne;
			while ((ligne = reader.readLine()) != null) {
				result += ligne;
			}
			JSONObject obj = new JSONObject(result);
			if (obj.getBoolean("result")) {
				utilisateur = new Utilisateur();
				utilisateur.setId(obj.getInt("id"));
				utilisateur.setMatricule(obj.getString("matricule"));
				utilisateur.setNom(obj.getString("nom"));
				utilisateur.setPrenom(obj.getString("prenom"));
				utilisateur.setNumero(obj.getString("numero"));
				utilisateur.setBureau(obj.getString("bureau"));
				utilisateur.setMail(obj.getString("mail"));
				utilisateur.setFonction(obj.getInt("fonction"));
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
		return utilisateur;
	}

	@Override
	public Utilisateur getUtilisateur(String mode, String valeur) {
		// TODO Auto-generated method stub
		return null;
	}

}
