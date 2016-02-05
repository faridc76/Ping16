package fr.ineo.gestineo.json;

import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.widget.Toast;
import fr.ineo.gestineo.dao.IDemandeDePersonnelDB;
import fr.ineo.gestineo.dao.db.DemandeDePersonnelDB;
import fr.ineo.gestineo.dto.DemandeDePersonnel;
import info.androidhive.slidingmenu.MainActivity;

public class InfoDemandeRequete extends AsyncTask<Object, Void, DemandeDePersonnel> {

	private Context context = null;
	private Integer idDemande = null;

	@Override
	protected DemandeDePersonnel doInBackground(Object... params) {
		idDemande = (Integer) params[0];
		context = (Context) params[1];
		IDemandeDePersonnelDB demandeDePersonnelDB = new DemandeDePersonnelDB();

		return demandeDePersonnelDB.demandeFromId(idDemande);
	}

	@Override
	protected void onPostExecute(DemandeDePersonnel result) {
		if (context != null && idDemande != null) {
			if (result != null) {
				Toast.makeText(context, String.valueOf(result), Toast.LENGTH_SHORT).show();
				SharedPreferences sharedPreferences = context.getSharedPreferences("mesPrefs", context.MODE_PRIVATE);
				Editor editor = sharedPreferences.edit();
				Gson gson = new Gson();
				editor.putString("demande", gson.toJson(result));
				editor.commit();

				Intent intent = new Intent(context, MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				context.startActivity(intent);
			} else {
				Toast.makeText(context, String.valueOf("nomAffaire ne doit pas �tre null"), Toast.LENGTH_SHORT).show();
			}
		}

	}

}
