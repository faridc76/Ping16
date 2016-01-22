package info.androidhive.slidingmenu;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import info.androidhive.gestineo.R;

public class DevisFragmentB extends Fragment {
	
	public DevisFragmentB(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_devis_tab_b, container, false);
        
        return rootView;
	}
}