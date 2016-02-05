package info.androidhive.slidingmenu;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import fr.ineo.gestineo.dao.db.DemandeDePersonnelDB;
import fr.ineo.gestineo.dto.Affaire;
import fr.ineo.gestineo.dto.DemandeDePersonnel;
import fr.ineo.gestineo.dto.Utilisateur;
import info.androidhive.gestineo.R;

public class BdcFragment2 extends Fragment implements View.OnClickListener {

	public Button envoyer;
	private Affaire affaire;
	private Utilisateur utilisateur;
	private View rootView;
	DemandeDePersonnelDB dao;

	public BdcFragment2() {
		dao = new DemandeDePersonnelDB();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_bdc_tab2, container, false);
		envoyer = (Button) rootView.findViewById(R.id.button1);

		Gson gson = new Gson();
		SharedPreferences preferences = this.getActivity().getSharedPreferences("mesPrefs", Context.MODE_PRIVATE);

		// On recupere l'affairee et l'utilisateur de la session
		String jsonUtilisateur = preferences.getString("utilisateur", "");
		String jsonAffaire = preferences.getString("affaire", "");

		affaire = gson.fromJson(jsonAffaire, Affaire.class);
		utilisateur = gson.fromJson(jsonUtilisateur, Utilisateur.class);

		// Personne à contacter
		TextView nomUtilisateur = (TextView) rootView.findViewById(R.id.TextView05);
		nomUtilisateur.setText(utilisateur.getPrenom() + " " + utilisateur.getNom());

		// adresse livraison
		TextView adresseLivraison = (TextView) rootView.findViewById(R.id.TextView06);
		adresseLivraison.setText(affaire.getLieu());

		// Telephone
		TextView numUtilisateur = (TextView) rootView.findViewById(R.id.TextView07);
		numUtilisateur.setText(utilisateur.getNumero());

		envoyer.setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onClick(View v) {
		DemandeDePersonnel ddp = new DemandeDePersonnel();
		ddp.setAffaire(affaire);
		ddp.setUtilisateur(utilisateur);

		// Objet
		EditText objet = (EditText) rootView.findViewById(R.id.EditText02);
		ddp.setObjet(objet.getText().toString());

		// marque
		EditText tache = (EditText) rootView.findViewById(R.id.EditText03);
		ddp.setTache(tache.getText().toString());

		// reference
		EditText duree = (EditText) rootView.findViewById(R.id.EditText04);
		ddp.ajoutDuree(duree.getText().toString());

		if (!ddp.estDemandeComplete()) {
			Toast.makeText(getActivity(), String.valueOf("Veuillez remplir tous les champs"), Toast.LENGTH_SHORT)
					.show();
		} else if (ddp.getDuree() == 0) {
			Toast.makeText(getActivity(), String.valueOf("La durée ne peut pas être nul"), Toast.LENGTH_SHORT).show();
		} else if (dao.demanderDuPersonnel(ddp)) {
			Toast.makeText(getActivity(), String.valueOf("Commande effectuée"), Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getActivity(),
					String.valueOf("Un probleme est survenu lors de l'enregistrement de votre commande"),
					Toast.LENGTH_SHORT).show();
		}

	}
}