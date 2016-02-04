package fr.ineo.gestineo.json;

import java.util.ArrayList;
<<<<<<< HEAD
=======
import java.util.Collection;
>>>>>>> branch 'master' of https://github.com/faridc76/Ping16.git
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
<<<<<<< HEAD
import info.androidhive.slidingmenu.AffaireItem;
import info.androidhive.slidingmenu.AffairesAdapter;
=======
import fr.ineo.gestineo.dto.Affaire;
>>>>>>> branch 'master' of https://github.com/faridc76/Ping16.git

/**
<<<<<<< HEAD
 * Classe permettant de récupérer la liste des noms des affaires
=======
 * Classe permettant de rï¿½cupï¿½rer la liste des noms des affaires
 * 
>>>>>>> branch 'master' of https://github.com/faridc76/Ping16.git
 * @author Ping16
 *
 */

<<<<<<< HEAD
public class ListeAffaireRequete extends AsyncTask<Object, Void, List<AffaireItem>> {
	
=======
public class ListeAffaireRequete extends AsyncTask<Object, Void, List<Affaire>> {

>>>>>>> branch 'master' of https://github.com/faridc76/Ping16.git
	private Context context = null;
	private int idUtilisateur;
	private ListView listView;

	@Override
<<<<<<< HEAD
	protected List<AffaireItem> doInBackground(Object... params) {
=======
	protected List<Affaire> doInBackground(Object... params) {
>>>>>>> branch 'master' of https://github.com/faridc76/Ping16.git
		idUtilisateur = (Integer) params[0];
		listView = (ListView) params[1];
		context = (Context) params[2];
		IAffaireDB affaireDB = new AffaireDB();

		return affaireDB.listeAffaire(idUtilisateur);
	}

	@Override
<<<<<<< HEAD
	protected void onPostExecute(final List<AffaireItem> result) {
		if(context != null && listView != null) {
			if(result != null) {
=======
	protected void onPostExecute(final List<Affaire> result) {
		if (context != null && listView != null) {
			if (result != null) {
>>>>>>> branch 'master' of https://github.com/faridc76/Ping16.git
				System.out.println(result);
<<<<<<< HEAD
				ArrayList<AffaireItem> list = (ArrayList<AffaireItem>) result;
		        
		        AffairesAdapter adapter = new AffairesAdapter(context, list);
		        listView.setAdapter(adapter);
				/*ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context.getApplicationContext(), info.androidhive.gestineo.R.layout.fragment_chantier_list_item, info.androidhive.gestineo.R.id.list_item_text, result);
		        listView.setAdapter(arrayAdapter);*/
		        
=======

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
	
>>>>>>> branch 'master' of https://github.com/faridc76/Ping16.git
				listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						System.out.println("id : " + id);
						System.out.println("position : " + position);
						Log.e("affaire", listAffaire.get(position));
						new InfoAffaireRequete().execute(listAffaire.get(position), context);
					}
<<<<<<< HEAD
                    
                });
			}
			else {
				Toast.makeText(context, String.valueOf("Impossible de récupérer la liste des affaires"), Toast.LENGTH_SHORT).show();
=======

				});
			} else {
				Toast.makeText(context, String.valueOf("Impossible de rÃ©cupÃ©rer la liste des affaires"),
						Toast.LENGTH_SHORT).show();
>>>>>>> branch 'master' of https://github.com/faridc76/Ping16.git
			}
		}
	}
}
