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
import fr.ineo.gestineo.dao.IDemandeDePersonnelDB;
import fr.ineo.gestineo.dao.db.DemandeDePersonnelDB;
import fr.ineo.gestineo.dto.DemandeItem;
import info.androidhive.slidingmenu.adapter.DemandeAdapter;

public class ListeDemandeDePersonnelRequete extends AsyncTask<Object, Void, List<DemandeItem>> {

	private Context context = null;
	private int idAffaire;
	private ListView listView;

	@Override
	protected List<DemandeItem> doInBackground(Object... params) {
		idAffaire = (Integer) params[0];
		listView = (ListView) params[1];
		context = (Context) params[2];
		IDemandeDePersonnelDB dao = new DemandeDePersonnelDB();

		return dao.listeDemande(idAffaire);
	}

	@Override
	protected void onPostExecute(final List<DemandeItem> result) {
		if (context != null && listView != null) {
			if (result != null) {
				Log.e("result", result.toString());
				final ArrayList<DemandeItem> list = (ArrayList<DemandeItem>) result;

				DemandeAdapter adapter = new DemandeAdapter(context, list);
				listView.setAdapter(adapter);

				listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						System.out.println("id : " + id);
						System.out.println("position : " + position);
						new InfoDemandeRequete().execute(list.get(position).getNumero(), context);
					}
				});
			} else {
				Toast.makeText(context, String.valueOf("Impossible de récupérer la liste des affaires"),
						Toast.LENGTH_SHORT).show();
			}
		}
	}

}
