package info.androidhive.slidingmenu;

import fr.ineo.gestineo.dao.db.AffaireDB;
import fr.ineo.gestineo.dto.Commande;
import fr.ineo.gestineo.dto.Utilisateur;
import info.androidhive.gestineo.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;


public class CommandeActivity extends Activity{


	public CommandeActivity() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_commande);

		SharedPreferences sharedPreferences = getSharedPreferences("mesPrefs", MODE_PRIVATE);
		Gson gson = new Gson();
		String jsonCommande = sharedPreferences.getString("commande", "");
		Commande commande = gson.fromJson(jsonCommande, Commande.class);
		String jsonUtilisateur = sharedPreferences.getString("utilisateur", "");
		Utilisateur utilisateur = gson.fromJson(jsonUtilisateur, Utilisateur.class);


		TextView marque = (TextView) this.findViewById(R.id.marqueTV);
		marque.setText(commande.getMarque());


		TextView ref = (TextView) this.findViewById(R.id.refTV);
		ref.setText(commande.getReference());


		TextView des = (TextView) this.findViewById(R.id.designationTV);
		des.setText(commande.getDesignation());


		TextView quant = (TextView) this.findViewById(R.id.qtyTV);
		quant.setText(AffaireDB.leStatut(commande.getQuantite()));


		TextView date = (TextView) this.findViewById(R.id.dateTV);
		date.setText(commande.getDate());

		if(utilisateur.getFonction() == 0){ //changer condition pour chef de chantier
			Button abtn = (Button) this.findViewById(R.id.accepterbtn);
			Button rbtn = (Button) this.findViewById(R.id.refuserbtn);
			abtn.setText("Retour");
			rbtn.setVisibility(View.GONE);
		}
	}

	public void openMainMenu(View v){
		Intent intent = new Intent(this, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		this.startActivity(intent);
	}
}
