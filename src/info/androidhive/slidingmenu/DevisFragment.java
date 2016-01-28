package info.androidhive.slidingmenu;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import info.androidhive.gestineo.R;

public class DevisFragment extends Fragment {
	private FragmentTabHost mTabHost;
	
	public DevisFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_devis, container, false);
         
        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("fragmenta").setIndicator("Métré du devis"),
                DevisFragmentA.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator("Détail du métré"),
        		DevisFragmentB.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator("Remises"),
        		DevisFragmentC.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentd").setIndicator("Coefficients"),
        		DevisFragmentD.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmente").setIndicator("Edition client"),
                DevisFragmentE.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentf").setIndicator("Situation de travaux"),
        		DevisFragmentF.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentg").setIndicator("Nomenclature"),
        		DevisFragmentG.class, null);

        return rootView;
	}
}