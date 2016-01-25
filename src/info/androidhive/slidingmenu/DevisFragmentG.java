package info.androidhive.slidingmenu;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import info.androidhive.gestineo.R;

public class DevisFragmentG extends Fragment {
	
	public DevisFragmentG(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_devis_tab_g, container, false);
        
        return rootView;
	}
}