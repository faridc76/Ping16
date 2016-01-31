package fr.ineo.gestineo.json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;


/**
 * Ne marche pas encore
 * @author Damien Bellenger
 *
 */
public class AutoCompleteContactRequete extends AsyncTask<Object, Void, List<String>> {

	private Context context = null;
	private AutoCompleteTextView selectContactTextView = null;

	@Override
	protected List<String> doInBackground(Object... params) {
		context = (Context)params[1];
		AutoCompleteTextView selectContactTextView = (AutoCompleteTextView) params[0];


		String result = "";
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		ArrayList<String> list = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy); 

			URL url = new URL("http://faridchouakria.free.fr/webservices/recup_liste_utilisateur.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // Pour pouvoir envoyer des donn�es
			connection.setRequestMethod("POST");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write("term=" + String.valueOf(selectContactTextView.getText().toString()));
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String ligne;
			while ((ligne = reader.readLine()) != null) {
				result += ligne;
			}

			list = new ArrayList<String>();
			JSONObject obj = new JSONObject(result);
			JSONArray jsonArray = obj.getJSONArray("utilisateurs");
			if (jsonArray != null) { 
				int len = jsonArray.length();
				for (int i=0;i<len;i++) { 
					System.out.println(jsonArray.getJSONObject(i).getString("nom") + " " + jsonArray.getJSONObject(i).getString("prenom"));
					list.add(jsonArray.getJSONObject(i).getString("nom") + " " + jsonArray.getJSONObject(i).getString("prenom"));
				} 
			} 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try{writer.close();}catch(Exception e){}
			try{reader.close();}catch(Exception e){}
		}
		return list;
	}

	@Override
	protected void onPostExecute(List<String> result) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_item, result);
		selectContactTextView.setAdapter(adapter);
		System.out.println("a" + result);

	}

}

