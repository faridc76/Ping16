package fr.ineo.gestineo.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import fr.ineo.gestineo.dao.IAffaireDB;
import fr.ineo.gestineo.dao.db.AffaireDB;
import fr.ineo.gestineo.dto.Affaire;

/**
 * Classe permettant de r�cup�rer la liste des noms des affaires
 * 
 * @author Ping16
 *
 */

public class ListeAffaireRequete extends AsyncTask<Object, Void, List<Affaire>> {

	private Context context = null;
	private int idUtilisateur;
	private ListView listView;

	@Override
	protected List<Affaire> doInBackground(Object... params) {
		idUtilisateur = (Integer) params[0];
		listView = (ListView) params[1];
		context = (Context) params[2];
		IAffaireDB affaireDB = new AffaireDB();

		return affaireDB.listeAffaire(idUtilisateur);
	}

	@Override
	protected void onPostExecute(final List<Affaire> result) {
		if (context != null && listView != null) {
			if (result != null) {
				System.out.println(result);

				final List<String> listAffaire = new ArrayList<String>();
				List<String> listStatut = new ArrayList<String>();
				List<String> listCommenditaire = new ArrayList<String>();
				for (Affaire a : result) {
					listAffaire.add(a.getNom());
					listStatut.add(a.leStatut());
					listCommenditaire.add(a.getCommenditaire());
				}
				
				ArrayAdapter<String> arrayAdapterAffaire = new ArrayAdapter<String>(context.getApplicationContext(),
						info.androidhive.gestineo.R.layout.fragment_chantier_list_item,
						info.androidhive.gestineo.R.id.list_item_text, listAffaire);
				listView.setAdapter(arrayAdapterAffaire);
				// Statut
				ArrayAdapter<String> arrayAdapterStatut = new ArrayAdapter<String>(context.getApplicationContext(),
						info.androidhive.gestineo.R.layout.fragment_chantier_list_item,
						info.androidhive.gestineo.R.id.list_item_statut, listStatut);
				//listView.setAdapter(arrayAdapterStatut);
				
				// Commenditaire
				ArrayAdapter<String> arrayAdapterCommenditaire = new ArrayAdapter<String>(
						context.getApplicationContext(), info.androidhive.gestineo.R.layout.fragment_chantier_list_item,
						info.androidhive.gestineo.R.id.list_item_commanditaire, listCommenditaire);
				//listView.setAdapter(arrayAdapterCommenditaire);
	
				listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						System.out.println("id : " + id);
						System.out.println("position : " + position);
						Log.e("affaire", listAffaire.get(position));
						new InfoAffaireRequete().execute(listAffaire.get(position), context);
					}

				});
			} else {
				Toast.makeText(context, String.valueOf("Impossible de récupérer la liste des affaires"),
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}
