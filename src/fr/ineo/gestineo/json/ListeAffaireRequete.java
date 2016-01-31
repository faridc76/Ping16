package fr.ineo.gestineo.json;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import fr.ineo.gestineo.dao.IAffaireDB;
import fr.ineo.gestineo.dao.db.AffaireDB;

/**
 * Classe permettant de r�cup�rer la liste des noms des affaires
 * @author Ping16
 *
 */

public class ListeAffaireRequete extends AsyncTask<Object, Void, List<String>> {
	
	private Context context = null;
	private int idUtilisateur;
	private ListView listView;
	
	@Override
	protected List<String> doInBackground(Object... params) {
		idUtilisateur = (Integer) params[0];
		listView = (ListView) params[1];
		context = (Context) params[2];
		IAffaireDB affaireDB = new AffaireDB();
		
		return affaireDB.listeAffaire(idUtilisateur);
	}

	@Override
	protected void onPostExecute(final List<String> result) {
		if(context != null && listView != null) {
			if(result != null) {
				System.out.println(result);
				//ArrayAdapter<String> adp1 = new ArrayAdapter<String>(context.getApplicationContext(), R.id.list_item_text, result);
				//listView.setAdapter(adp1);
				ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context.getApplicationContext(), info.androidhive.gestineo.R.layout.fragment_chantier_list_item, info.androidhive.gestineo.R.id.list_item_text, result);
		        listView.setAdapter(arrayAdapter);
				listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						System.out.println("id : " + id);
		        		System.out.println("position : " + position);
						new InfoAffaireRequete().execute(result.get(position), context);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub
						
					}
                    
                });
			}
			else {
				Toast.makeText(context, String.valueOf("Impossible de récupérer la liste des affaires"), Toast.LENGTH_SHORT).show();
			}
		}
	}
}

