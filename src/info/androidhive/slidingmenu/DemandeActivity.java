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
import fr.ineo.gestineo.dao.db.DemandeDePersonnelDB;
import fr.ineo.gestineo.dao.db.UtilisateurDB;
import fr.ineo.gestineo.dto.DemandeDePersonnel;
import fr.ineo.gestineo.dto.Utilisateur;
import info.androidhive.gestineo.R;

public class DemandeActivity extends Activity implements OnClickListener {

	Button abtn;
	Button refusebtn;
	Button retourbtn;
	DemandeDePersonnel demande;
	TextView statut;
	DemandeDePersonnelDB dao;

	public DemandeActivity() {
		dao = new DemandeDePersonnelDB();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demande);

		SharedPreferences sharedPreferences = getSharedPreferences("mesPrefs", MODE_PRIVATE);
		Gson gson = new Gson();
		String jsonDemande = sharedPreferences.getString("demande", "");
		demande = gson.fromJson(jsonDemande, DemandeDePersonnel.class);
		String jsonUtilisateur = sharedPreferences.getString("utilisateur", "");
		Utilisateur utilisateur = gson.fromJson(jsonUtilisateur, Utilisateur.class);

		// numero demande
		TextView num = (TextView) this.findViewById(R.id.numeroTV);
		num.setText(demande.getNumDemande());

		// demandeur
		TextView demandeur = (TextView) this.findViewById(R.id.demandeurTV);
		demandeur.setText(demande.getUtilisateur().getNom());

		// objet
		TextView objet = (TextView) this.findViewById(R.id.objetTV);
		objet.setText(demande.getObjet());

		// Tache
		TextView tache = (TextView) this.findViewById(R.id.tacheTV);
		tache.setText(demande.getTache());

		// Duree
		TextView duree = (TextView) this.findViewById(R.id.dureeTV);
		duree.setText(Integer.toString(demande.getDuree()));

		statut = (TextView) this.findViewById(R.id.statutTV);
		statut.setText(DemandeDePersonnelDB.leStatut(demande.getStatut()));

		abtn = (Button) this.findViewById(R.id.accepterbtn);
		abtn.setOnClickListener(this);
		refusebtn = (Button) this.findViewById(R.id.refuserbtn);
		refusebtn.setOnClickListener(this);
		retourbtn = (Button) this.findViewById(R.id.retourbtn);
		retourbtn.setOnClickListener(this);

		if (demande.getStatut() != CommandeDB.EN_ATTENTE
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
			dao.validerDemande(CommandeDB.VALIDE, demande.getId());
		}
		if (v.getId() == refusebtn.getId()) {
			statut.setText("refusé");
			dao.validerDemande(CommandeDB.REFUSE, demande.getId());
		}
		if (v.getId() == retourbtn.getId()) {
			finish();
		}
		abtn.setVisibility(View.INVISIBLE);
		refusebtn.setVisibility(View.INVISIBLE);
		retourbtn.setVisibility(View.VISIBLE);

	}
}