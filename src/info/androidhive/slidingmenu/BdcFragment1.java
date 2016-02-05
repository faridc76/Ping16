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
import fr.ineo.gestineo.dao.db.CommandeDB;
import fr.ineo.gestineo.dto.Affaire;
import fr.ineo.gestineo.dto.Commande;
import fr.ineo.gestineo.dto.Utilisateur;
import info.androidhive.gestineo.R;

public class BdcFragment1 extends Fragment implements View.OnClickListener {

	private Button envoyer;
	private View rootView;

	private Affaire affaire;
	private Utilisateur utilisateur;

	private CommandeDB dao;

	public BdcFragment1() {
		dao = new CommandeDB();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_bdc_tab1, container, false);
		Gson gson = new Gson();
		SharedPreferences preferences = this.getActivity().getSharedPreferences("mesPrefs", Context.MODE_PRIVATE);

		// On recupere l'affairee et l'utilisateur de la session
		String jsonUtilisateur = preferences.getString("utilisateur", "");
		String jsonAffaire = preferences.getString("affaire", "");

		affaire = gson.fromJson(jsonAffaire, Affaire.class);
		utilisateur = gson.fromJson(jsonUtilisateur, Utilisateur.class);

		// Numero affaire
		TextView numAffaire = (TextView) rootView.findViewById(R.id.TextView07);
		numAffaire.setText(affaire.getId() + "");

		// Nom affaire
		TextView nomAffaire = (TextView) rootView.findViewById(R.id.TextView08);
		nomAffaire.setText(affaire.getNom());

		// Telephone
		TextView numUtilisateur = (TextView) rootView.findViewById(R.id.TextView09);
		numUtilisateur.setText(utilisateur.getNumero());

		// Personne à contacter
		TextView nomUtilisateur = (TextView) rootView.findViewById(R.id.TextView10);
		nomUtilisateur.setText(utilisateur.getPrenom() + " " + utilisateur.getNom());

		// adresse livraison
		TextView adresseLivraison = (TextView) rootView.findViewById(R.id.TextView11);
		adresseLivraison.setText(affaire.getLieu());

		envoyer = (Button) rootView.findViewById(R.id.button1);
		envoyer.setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onClick(View v) {
		// On crée une commande
		Commande c = new Commande();
		// On ajoute l'affaire et lutilisateur
		c.setAffaire(affaire);
		c.setUtilisateur(utilisateur);
		// On recupere les information des formulaire

		// Obeservation
		EditText observation = (EditText) rootView.findViewById(R.id.EditText04);
		c.setObservation(observation.getText().toString());

		// marque
		EditText marque = (EditText) rootView.findViewById(R.id.EditText05);
		c.setMarque(marque.getText().toString());

		// reference
		EditText reference = (EditText) rootView.findViewById(R.id.EditText06);
		c.setReference(reference.getText().toString());

		// reference
		EditText designiation = (EditText) rootView.findViewById(R.id.EditText07);
		c.setDesignation(designiation.getText().toString());

		// La quantite
		EditText quantite = (EditText) rootView.findViewById(R.id.EditText08);
		c.ajoutQuantite(quantite.getText().toString());

		if (!c.estCommandeComplete()) {
			Toast.makeText(getActivity(), String.valueOf("Veuillez remplir tous les champs"), Toast.LENGTH_SHORT)
					.show();
		} else if (c.getQuantite() == 0) {
			Toast.makeText(getActivity(), String.valueOf("La quantité ne peut pas être nul"), Toast.LENGTH_SHORT)
					.show();
		} else if (dao.passerCommande(c)) {
			Toast.makeText(getActivity(), String.valueOf("Commande effectuée"), Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getActivity(),
					String.valueOf("Un probleme est survenu lors de l'enregistrement de votre commande"),
					Toast.LENGTH_SHORT).show();
		}

	}
}