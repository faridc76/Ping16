package info.androidhive.slidingmenu;

import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import fr.ineo.gestineo.dao.db.AffaireDB;
import fr.ineo.gestineo.dao.db.UtilisateurDB;
import fr.ineo.gestineo.dto.Affaire;
import info.androidhive.gestineo.R;

public class ChantierFragment extends Fragment {

	public ChantierFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_chantier, container, false);

		Gson gson = new Gson();
		SharedPreferences preferences = this.getActivity().getSharedPreferences("mesPrefs", Context.MODE_PRIVATE);
		String jsonAffaire = preferences.getString("affaire", "");
		Affaire affaire = gson.fromJson(jsonAffaire, Affaire.class);

		// dao utilisatur
		UtilisateurDB dao = new UtilisateurDB();

		// Nom affaire
		TextView nomAffaire = (TextView) rootView.findViewById(R.id.affaireTV);
		nomAffaire.setText(affaire.getNom());

		// Commenditaire
		TextView commenditaire = (TextView) rootView.findViewById(R.id.commanditaireTV);
		commenditaire.setText(affaire.getCommenditaire());

		// Lieu
		TextView lieu = (TextView) rootView.findViewById(R.id.lieuTV);
		lieu.setText(affaire.getLieu());

		// Budget
		TextView budget = (TextView) rootView.findViewById(R.id.budgetTV);
		budget.setText(affaire.getBudget() + "€");

		// Statut
		TextView statut = (TextView) rootView.findViewById(R.id.statusTV);
		statut.setText(AffaireDB.leStatut(affaire.getStatut()));

		// Responsable d'affaire
		TextView resp = (TextView) rootView.findViewById(R.id.responsableTV);
		resp.setText(affaire.getResponsable().getPrenom() + " " + affaire.getResponsable().getNom());

		// Conducteur de travaux
		TextView cond = (TextView) rootView.findViewById(R.id.conducteurTV);
		cond.setText(affaire.getConducteur().getPrenom() + " " + affaire.getConducteur().getNom());

		// Chef de chantier
		TextView chef = (TextView) rootView.findViewById(R.id.chefTV);
		chef.setText(affaire.getChef().getPrenom() + " " + affaire.getChef().getNom());

		Button backbtn = (Button) rootView.findViewById(R.id.returnbtn);
		backbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), ChantierActivity.class);
				startActivity(intent);
				getActivity().finish();
			}
		});

		Button deco = (Button) rootView.findViewById(R.id.decobtn);
		deco.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), LoginActivity.class);
				startActivity(intent);
				getActivity().finish();
			}
		});

		return rootView;
	}
}
