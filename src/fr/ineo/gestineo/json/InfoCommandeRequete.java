package fr.ineo.gestineo.json;

import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.widget.Toast;
import fr.ineo.gestineo.dao.ICommandeDB;
import fr.ineo.gestineo.dao.db.CommandeDB;
import fr.ineo.gestineo.dto.Commande;
import info.androidhive.slidingmenu.CommandeActivity;

public class InfoCommandeRequete extends AsyncTask<Object, Void, Commande> {

	private Context context = null;
	private String referenceCommande = null;

	@Override
	protected Commande doInBackground(Object... params) {
		referenceCommande = (String) params[0];
		context = (Context) params[1];
		ICommandeDB commandeDB = new CommandeDB();

		return commandeDB.recupCommande(referenceCommande);
	}

	@Override
	protected void onPostExecute(Commande result) {
		if (context != null && referenceCommande != null) {
			if (result != null) {
				Toast.makeText(context, String.valueOf(result), Toast.LENGTH_SHORT).show();
				SharedPreferences sharedPreferences = context.getSharedPreferences("mesPrefs", context.MODE_PRIVATE);
				Editor editor = sharedPreferences.edit();
				Gson gson = new Gson();
				editor.putString("commande", gson.toJson(result));
				editor.commit();

				Intent intent = new Intent(context, CommandeActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				context.startActivity(intent);

			} else {
				Toast.makeText(context, String.valueOf("nomAffaire ne doit pas être null"), Toast.LENGTH_SHORT).show();
			}
		}

	}

}
