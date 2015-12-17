package info.androidhive.slidingmenu;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ChantierFragment extends Fragment {
	
	ArrayList<String> arrayList;
	ArrayAdapter<String> arrayAdapter;
	ListView listView;
	
	public ChantierFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_chantier, container, false);
        listView = (ListView) rootView.findViewById(R.id.chantier_list_view);
        String[] items = {"Angers","Amiens","Aix-en-Provence","Bordeaux","Caen","Dijon","Elbeuf","Fontainebleau","Grenoble","Issy-les-Moulineaux","Lille","Massy","Marseilles","Paris","Rouen","Versailles"};
        arrayList = new ArrayList<>(Arrays.asList(items));
        arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.list_item_text, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        		//do stuff
        	}
		});
        
        return rootView;
	}
}