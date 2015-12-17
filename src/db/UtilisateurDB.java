package db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

import android.os.StrictMode;
import fr.free.gestineo.Utilisateur;

public class UtilisateurDB {
	
	public static String MY_PREFS_NAME = "Pref_pour_apli_mohamed";
	public final static String CHEF_DE_CHANTIER = "Chef de chantier";
	public final static String CONDUCTEUR_DE_TRAVAUX = "Conducteur de travaux";
	public final static String RESPONSABLE_DAFFAIRES = "Responsable d'affaires";
	public final static String GOOD_RESULT = "True";
	
	public final static String DOMAINE = "http://gestineo.free.fr/";


	
	
	//Un truc dans le genre le model de base de données est dans le package
	public static void AjoutPersonne(Utilisateur u) {
		String result = "";
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
			writer.write("matricule=" + u.getMatricule() + "&nom=" + u.getNom() + "&prenom=" + u.getPrenom() + "&numero=" + u.getNumero() + "&bureau=" + u.getBureau() + "&mail=" + u.getMail() + "&password=" + u.getPassword());
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                result += ligne;
            }
            int id = Integer.parseInt(result);
            u.setId(id);
         } catch (Exception e) {
            e.printStackTrace();
         }finally{
            try{writer.close();}catch(Exception e){}
            try{reader.close();}catch(Exception e){}
         }
	}

	public static boolean checkLogin(String matricule, String password) {
		String result = "";
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy); 
			URL url = new URL(DOMAINE + "checkLogin.php");
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
         } catch (Exception e) {
            e.printStackTrace();
         }finally{
            try{writer.close();}catch(Exception e){}
            try{reader.close();}catch(Exception e){}
         }
		return result.equals(GOOD_RESULT);
	}
	
	public static boolean isMatriculeFree(String matricule) {
		String result = "";
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy); 
			URL url = new URL(DOMAINE + "matriculeFree.php");
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
         } catch (Exception e) {
            e.printStackTrace();
         }finally{
            try{writer.close();}catch(Exception e){}
            try{reader.close();}catch(Exception e){}
         }
		return result.equals(GOOD_RESULT);
	}
	
	
	
	public static Utilisateur getUtilisateurFromMatricule(String matricule) {
		return getUtilisateur("matricule", matricule);	
	}

	public static Utilisateur getUtilisateurFromId(int id) {
		return getUtilisateur("id", String.valueOf(id));
	}
	
	//Fonction qui recupere l'utilisateur en fonction du mode et de la valeur
	private static Utilisateur getUtilisateur(String mode, String valeur) {
		Utilisateur u = null;
		String result = "";
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy); 
			URL url = new URL(DOMAINE + "recupUtilisateur.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST"); 
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("mode=" + mode + "&valeur=" + valeur);
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                result += ligne;
            }
            JSONObject obj = new JSONObject(result);
            u = new Utilisateur();
            u.setId(obj.getInt("id"));
            u.setMatricule(obj.getString("matricule"));
            u.setNom(obj.getString("nom"));
            u.setPrenom(obj.getString("prenom"));
            u.setNumero(obj.getString("numero"));
            u.setBureau(obj.getString("bureau"));
            u.setMail(obj.getString("email"));
            u.setFonction(obj.getString("fonction"));
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	try{writer.close();}catch(Exception e){}
            try{reader.close();}catch(Exception e){}
        }
		return u;
	}
}
 