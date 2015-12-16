package db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.StrictMode;
import android.util.Log;
import fr.free.gestineo.Utilisateur;

public class UtilisateurDB {
	
	public static String MY_PREFS_NAME = "Pref_pour_apli_mohamed";
	public final static String CHEF_DE_CHANTIER = "Chef de chantier";
	public final static String CONDUCTEUR_DE_TRAVAUX = "Conducteur de travaux";
	public final static String RESPONSABLE_DAFFAIRES = "Responsable d'affaires";
	
	public final static String DOMAINE = "http://gestineo.free.fr/";


	
	
	//Un truc dans le genre le model de base de données est dans le package
	public static void AjoutPersonne(Utilisateur u) {
		String a = "";
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy); 
			a = "";
			URL url = new URL(DOMAINE + "ajoutUtilisateur.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST"); 
			writer = new OutputStreamWriter(connection.getOutputStream());
			//writer.write("email=" + p.getEmail() + "&nom=" + p.getNom() + "&prenom=" + p.getPrenom() + "&password=" + p.getPassword() + );
			writer.flush();
			
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                a += ligne;
            }
            int id = Integer.parseInt(a);
            u.setId(id);
         } catch (Exception e) {
            e.printStackTrace();
         }finally{
            try{writer.close();}catch(Exception e){}
            try{reader.close();}catch(Exception e){}
         }
	}

	public static boolean checkLogin(String matricule, String password) {
		return false;
	}
	
	public static boolean isMatriculeFree(String matricule) {
		return false;
	}
	
	
	//Pour les deux prochaines classes on recupere toute les infos sauf le mot de passe
	//le mot de passe on le test avec check login
	public static Utilisateur getUtilisateurFromMatricule(String matricule) {
		return null;
		
	}

	public static Utilisateur getUtilisateurFromId(int id) {
		return null;
		
	}
}
 