package info.androidhive.slidingmenu;

import fr.ineo.gestineo.dao.db.AffaireDB;
import fr.ineo.gestineo.dao.db.UtilisateurDB;
import fr.ineo.gestineo.dto.DemandeDePersonnel;
import fr.ineo.gestineo.dto.Utilisateur;
import info.androidhive.gestineo.R;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;


public class DemandeActivity extends Activity {

	
	public DemandeActivity() {
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demande);

		SharedPreferences sharedPreferences = getSharedPreferences("mesPrefs", MODE_PRIVATE);
		Gson gson = new Gson();
		String jsonDemande = sharedPreferences.getString("demande", "");
		DemandeDePersonnel demande = gson.fromJson(jsonDemande, DemandeDePersonnel.class);
		
		//Commenditaire
        TextView commenditaire = (TextView) this.findViewById(R.id.demandeurTV);
        //commenditaire.setText(demande.getUtilisateur().getNom());
        
        //Lieu
        TextView lieu = (TextView) this.findViewById(R.id.objetTV);
        lieu.setText(demande.getObjet());
        
        //Tache
        TextView budget = (TextView) this.findViewById(R.id.tacheTV);
        budget.setText(demande.getTache());
        
        //Duree
        TextView statut = (TextView) this.findViewById(R.id.dureeTV);
        statut.setText(demande.getDuree());
        

	}
}