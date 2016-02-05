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
import fr.ineo.gestineo.dao.ICommandeDB;
import fr.ineo.gestineo.dao.db.CommandeDB;
import fr.ineo.gestineo.dto.CommandeItem;
import info.androidhive.slidingmenu.adapter.CommandeAdapter;

public class ListeCommandeRequete extends AsyncTask<Object, Void, List<CommandeItem>> {

	private Context context = null;
	private int idAffaire;
	private ListView listView;

	@Override
	protected List<CommandeItem> doInBackground(Object... params) {
		idAffaire = (Integer) params[0];
		listView = (ListView) params[1];
		context = (Context) params[2];
		ICommandeDB dao = new CommandeDB();

		return dao.listeCommandes(idAffaire);
	}

	@Override
	protected void onPostExecute(final List<CommandeItem> result) {
		if (context != null && listView != null) {
			if (result != null) {
				Log.e("result", result.toString());
				final ArrayList<CommandeItem> list = (ArrayList<CommandeItem>) result;

				CommandeAdapter adapter = new CommandeAdapter(context, list);
				listView.setAdapter(adapter);

				listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						System.out.println("id : " + id);
						System.out.println("position : " + position);
						Log.e("affaire", list.get(position).getReference());
						new InfoCommandeRequete().execute(list.get(position).getReference(), context);
					}
				});
			} else {
				Toast.makeText(context, String.valueOf("Impossible de récupérer la liste des affaires"),
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}