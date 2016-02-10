package info.androidhive.slidingmenu;

import java.util.List;

import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fr.ineo.gestineo.dao.db.AffaireDB;
import fr.ineo.gestineo.dto.Affaire;
import info.androidhive.gestineo.R;

public class DevisFragmentList extends Fragment {

	List<String> arrayList;
	ArrayAdapter<String> arrayAdapter;
	ListView listView;
	AffaireDB dao;

	public DevisFragmentList() {
		dao = new AffaireDB();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_devis_list, container, false);
		Gson gson = new Gson();
		SharedPreferences preferences = this.getActivity().getSharedPreferences("mesPrefs", Context.MODE_PRIVATE);

		// On recupere l'affairee et l'utilisateur de la session
		String jsonAffaire = preferences.getString("affaire", "");
		final Affaire affaire = gson.fromJson(jsonAffaire, Affaire.class);
		listView = (ListView) rootView.findViewById(R.id.devis_list_view);
		/**
		 * String[] items = {"Devis A","Devis B","Devis C","Devis D","Devis E"};
		 * arrayList = new ArrayList<>(Arrays.asList(items));
		 */
		Log.e("nomAffaire", affaire.getNom());
		arrayList = dao.listeDocumentDossier(affaire.getNom(), "devis");
		Log.e("liste plan", arrayList.toString());

		arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.fragment_devis_list_item, R.id.list_item_text,
				arrayList);
		listView.setAdapter(arrayAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				/**
				 * FragmentManager fm = getFragmentManager();
				 * FragmentTransaction ft = fm.beginTransaction(); DevisFragment
				 * df = new DevisFragment(); ft.replace(R.id.frame_container,
				 * df); ft.addToBackStack(null); ft.commit();
				 */
				String url = "http://faridchouakria.free.fr/documents/" + affaire.getNom() + "/devis/"
						+ arrayList.get(position);
				Log.e("url", url);
				Uri uri = Uri.parse(url);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});

		return rootView;
	}
}