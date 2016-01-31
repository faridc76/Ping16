package info.androidhive.slidingmenu;
import info.androidhive.gestineo.R;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fr.ineo.gestineo.dto.Utilisateur;
import fr.ineo.gestineo.json.ListeAffaireRequete;


public class ChantierActivity extends Activity {
	
	ArrayList<String> arrayList;
	ArrayAdapter<String> arrayAdapter;
	ListView listView;
	
	public ChantierActivity(){}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.activity_chantier, container, false);
        listView = (ListView) rootView.findViewById(R.id.chantier_list_view);
        
        SharedPreferences sharedPreferences = getSharedPreferences("mesPrefs", MODE_PRIVATE);
		Gson gson = new Gson();
		String jsonUtilisateur = sharedPreferences.getString("utilisateur", "");
		Utilisateur utilisateur = gson.fromJson(jsonUtilisateur, Utilisateur.class);
		new ListeAffaireRequete().execute(utilisateur.getId(), listView, this.getApplicationContext());
        //String[] items = {"Angers","Amiens","Aix-en-Provence","Bordeaux","Caen","Dijon","Elbeuf","Fontainebleau","Grenoble","Issy-les-Moulineaux","Lille","Massy","Marseilles","Paris","Rouen","Versailles"};
        //arrayList = new ArrayList<>(Arrays.asList(items));  
        //arrayAdapter = new ArrayAdapter<String>(this.getApplicationContext(), R.layout.fragment_chantier_list_item, R.id.list_item_text, arrayList);
        //listView.setAdapter(arrayAdapter);
        /**listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        		System.out.println("id : " + id);
        		System.out.println("position : " + position);
        	}
		});*/
        
        return rootView;
	}
}