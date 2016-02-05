package info.androidhive.slidingmenu;

import java.util.ArrayList;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fr.ineo.gestineo.dto.Affaire;
import fr.ineo.gestineo.json.ListeCommandeRequete;
import info.androidhive.gestineo.R;

public class BdcFragment3 extends Fragment {

	ArrayList<String> arrayList;
	ArrayAdapter<String> arrayAdapter;
	ListView listView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_bdc_tab3, container, false);

		listView = (ListView) rootView.findViewById(R.id.bdc_list_view3);
		SharedPreferences preferences = this.getActivity().getSharedPreferences("mesPrefs", Context.MODE_PRIVATE);
		Gson gson = new Gson();
		String jsonAffaire = preferences.getString("affaire", "");
		Affaire affaire = gson.fromJson(jsonAffaire, Affaire.class);
		Log.e("id affaire", "" + affaire.getId());
		new ListeCommandeRequete().execute(affaire.getId(), listView, container.getContext());
		return rootView;
	}
}