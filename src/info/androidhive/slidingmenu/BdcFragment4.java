package info.androidhive.slidingmenu;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

import fr.ineo.gestineo.dto.Utilisateur;
import fr.ineo.gestineo.json.ListeAffaireRequete;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import info.androidhive.gestineo.R;

public class BdcFragment4 extends Fragment {
	
	ArrayList<String> arrayList;
	ArrayAdapter<String> arrayAdapter;
	ListView listView;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_bdc_tab4, container, false);
        
        listView = (ListView) rootView.findViewById(R.id.bdc_list_view4);
        String[] items = {"Demande personnel 1","Demande personnel 2","Demande personnel 3","Demande personnel 4","Demande personnel 5","Demande personnel 6","Demande personnel 7","Demande personnel 8"};
        arrayList = new ArrayList<>(Arrays.asList(items));
        arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.fragment_bdc_list_item, R.id.list_item_text, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        		
        		//do smtg
        		/*FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                XXFragment df = new XXFragment();
                ft.replace(R.id.frame_container, df);
                ft.addToBackStack(null);
                ft.commit();*/
        		
        	}
		});
         
        return rootView;
	}
}