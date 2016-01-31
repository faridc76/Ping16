package fr.ineo.gestineo.json;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import fr.ineo.gestineo.dao.IAffaireDB;
import fr.ineo.gestineo.dao.db.AffaireDB;
import fr.ineo.gestineo.dto.Affaire;

/**
 * Classe permettant de r�cup�rer la liste des noms des documents d'une affaire
 * @author Ping16
 *
 */

public class ListeDocumentRequete extends AsyncTask<Object, Void, List<String>> {
	
	private Context context = null;
	private Affaire affaire;
	private Spinner spinner;
	
	@Override
	protected List<String> doInBackground(Object... params) {
		affaire = (Affaire) params[0];
		spinner = (Spinner) params[1];
		context = (Context) params[2];
		IAffaireDB affaireDB = new AffaireDB();
		
		return affaireDB.listeDocument(affaire.getNom());
	}

	@Override
	protected void onPostExecute(final List<String> result) {
		if(context != null && spinner != null) {
			if(result != null) {
				System.out.println(result);
				ArrayAdapter<String> adp1 = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_item, result);
				adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinner.setAdapter(adp1);
				spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						//new InfoAffaireRequete().execute(result.get(position), context);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub
						
					}
                    
                });
			}
			else {
				Toast.makeText(context, String.valueOf("Impossible de r�cup�rer la liste des documents"), Toast.LENGTH_SHORT).show();
			}
		}
	}
}

