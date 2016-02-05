package fr.ineo.gestineo.json;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import fr.ineo.gestineo.dao.IAffaireDB;
import fr.ineo.gestineo.dao.db.AffaireDB;
import fr.ineo.gestineo.dto.AffaireItem;
import info.androidhive.slidingmenu.adapter.AffairesAdapter;

/**
 * Classe permettant de r�cup�rer la liste des noms des affaires
 * 
 * @author Ping16
 *
 */

public class ListeAffaireRequete extends AsyncTask<Object, Void, List<AffaireItem>> {

	private Context context = null;
	private int idUtilisateur;
	private ListView listView;

	@Override
	protected List<AffaireItem> doInBackground(Object... params) {
		idUtilisateur = (Integer) params[0];
		listView = (ListView) params[1];
		context = (Context) params[2];
		IAffaireDB affaireDB = new AffaireDB();

		return affaireDB.listeAffaire(idUtilisateur);
	}

	@Override
	protected void onPostExecute(final List<AffaireItem> result) {
		if (context != null && listView != null) {
			if (result != null) {
				System.out.println(result);
				final ArrayList<AffaireItem> list = (ArrayList<AffaireItem>) result;

				AffairesAdapter adapter = new AffairesAdapter(context, list);
				listView.setAdapter(adapter);

				listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						System.out.println("id : " + id);
						System.out.println("position : " + position);
						Log.e("affaire", list.get(position).getNom());
						new InfoAffaireRequete().execute(list.get(position).getNom(), context);
					}
				});
			} else {
				Toast.makeText(context, String.valueOf("Impossible de récupérer la liste des affaires"),
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}
