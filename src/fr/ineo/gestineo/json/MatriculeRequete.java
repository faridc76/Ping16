package fr.ineo.gestineo.json;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import fr.ineo.gestineo.dao.IUtilisateurDB;
import fr.ineo.gestineo.dao.db.UtilisateurDB;

/**
 * Classe permettant de savoir si un matricule est libre
 * 
 * @author Ping16
 *
 */

public class MatriculeRequete extends AsyncTask<Object, Void, Boolean> {

	private Context context = null;
	private String matricule = null;

	@Override
	protected Boolean doInBackground(Object... params) {
		matricule = (String) params[0];
		context = (Context) params[1];
		IUtilisateurDB utilisateurDB = new UtilisateurDB();

		return utilisateurDB.isMatriculeFree(matricule);
	}

	@Override
	protected void onPostExecute(Boolean result) {
		if (context != null && matricule != null) {
			if (!result) {
				Toast.makeText(context, String.valueOf("Not available"), Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(context, String.valueOf("Available"), Toast.LENGTH_SHORT).show();
			}
		}

	}

}
