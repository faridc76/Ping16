package info.androidhive.slidingmenu;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import info.androidhive.gestineo.R;

public class ContactFragment extends Fragment {

	ArrayList<String> arrayList;
	ArrayAdapter<String> arrayAdapter;
	ListView listView;

	public ContactFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_devis_list, container, false);

		listView = (ListView) rootView.findViewById(R.id.devis_list_view);
		String[] items = { "Albert Dupont", "Benoit Roux", "Christian Petit", "Damien Bellenger", "Etienne Petrel",
				"François Hollande", "Gerrard Dubois", "Henry Durant", "Iliasse Romane", "Kevin Giroux",
				"Thierry Leroy" };
		arrayList = new ArrayList<>(Arrays.asList(items));
		arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.fragment_devis_list_item, R.id.list_item_text,
				arrayList);
		listView.setAdapter(arrayAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				FragmentManager fm = getFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				ChatFragment df = new ChatFragment();
				ft.replace(R.id.frame_container, df);
				ft.addToBackStack(null);
				ft.commit();
			}
		});

		return rootView;
	}
}