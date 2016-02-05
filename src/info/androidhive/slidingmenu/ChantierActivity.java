package info.androidhive.slidingmenu;

import java.util.ArrayList;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fr.ineo.gestineo.dto.Utilisateur;
import fr.ineo.gestineo.json.ListeAffaireRequete;
import info.androidhive.gestineo.R;

public class ChantierActivity extends Activity {

	ArrayList<String> arrayList;
	ArrayAdapter<String> arrayAdapter;
	ListView listView;

	public ChantierActivity() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chantier);

		listView = (ListView) findViewById(R.id.chantier_list_view);

		SharedPreferences sharedPreferences = getSharedPreferences("mesPrefs", MODE_PRIVATE);
		Gson gson = new Gson();
		String jsonUtilisateur = sharedPreferences.getString("utilisateur", "");
		Utilisateur utilisateur = gson.fromJson(jsonUtilisateur, Utilisateur.class);
		new ListeAffaireRequete().execute(utilisateur.getId(), listView, this.getApplicationContext());
	}
}