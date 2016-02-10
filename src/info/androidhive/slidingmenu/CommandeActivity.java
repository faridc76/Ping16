package info.androidhive.slidingmenu;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import fr.ineo.gestineo.dao.db.CommandeDB;
import fr.ineo.gestineo.dao.db.UtilisateurDB;
import fr.ineo.gestineo.dto.Commande;
import fr.ineo.gestineo.dto.Utilisateur;
import info.androidhive.gestineo.R;

public class CommandeActivity extends Activity implements OnClickListener {

	Button abtn;
	Button refusebtn;
	Button retourbtn;

	TextView statut;
	Commande commande;
	CommandeDB dao;

	public CommandeActivity() {
		dao = new CommandeDB();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_commande);

		SharedPreferences sharedPreferences = getSharedPreferences("mesPrefs", MODE_PRIVATE);
		Gson gson = new Gson();
		String jsonCommande = sharedPreferences.getString("commande", "");
		commande = gson.fromJson(jsonCommande, Commande.class);
		String jsonUtilisateur = sharedPreferences.getString("utilisateur", "");
		Utilisateur utilisateur = gson.fromJson(jsonUtilisateur, Utilisateur.class);

		abtn = (Button) this.findViewById(R.id.accepterbtn);
		abtn.setOnClickListener(this);
		refusebtn = (Button) this.findViewById(R.id.refuserbtn);
		refusebtn.setOnClickListener(this);
		retourbtn = (Button) this.findViewById(R.id.retourbtn);
		retourbtn.setOnClickListener(this);

		TextView nom = (TextView) this.findViewById(R.id.demandeurTV);
		nom.setText(commande.getUtilisateur().getPrenom() + " " + commande.getUtilisateur().getNom());

		TextView marque = (TextView) this.findViewById(R.id.marqueTV);
		marque.setText(commande.getMarque());

		TextView ref = (TextView) this.findViewById(R.id.refTV);
		ref.setText(commande.getReference());

		TextView des = (TextView) this.findViewById(R.id.designationTV);
		des.setText(commande.getDesignation());

		TextView quant = (TextView) this.findViewById(R.id.qtyTV);
		quant.setText(Integer.toString((commande.getQuantite())));

		TextView num = (TextView) this.findViewById(R.id.numTV);
		num.setText(commande.getNumCommande());

		TextView observation = (TextView) this.findViewById(R.id.observationTV);
		observation.setText(commande.getObservation());

		statut = (TextView) this.findViewById(R.id.statutTV);
		statut.setText(CommandeDB.leStatut(commande.getStatut()));

		if (commande.getStatut() != CommandeDB.EN_ATTENTE
				|| utilisateur.getFonction() == UtilisateurDB.FONCTION_CHEF_DE_CHANTIER) {
			abtn.setVisibility(View.INVISIBLE);
			refusebtn.setVisibility(View.INVISIBLE);
			retourbtn.setVisibility(View.VISIBLE);
		}

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == abtn.getId()) {
			statut.setText("validé");
			dao.validerCommande(CommandeDB.VALIDE, commande.getId());
		}
		if (v.getId() == refusebtn.getId()) {
			statut.setText("refusé");
			dao.validerCommande(CommandeDB.REFUSE, commande.getId());
		}
		if (v.getId() == retourbtn.getId()) {
			finish();
		}
		abtn.setVisibility(View.INVISIBLE);
		refusebtn.setVisibility(View.INVISIBLE);
		retourbtn.setVisibility(View.VISIBLE);
	}
}
