package info.androidhive.slidingmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import info.androidhive.gestineo.R;

public class BdcFragment extends Fragment {

	private FragmentTabHost mTabHost;

	public BdcFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_bdc, container, false);

		mTabHost = (FragmentTabHost) rootView.findViewById(android.R.id.tabhost);
		mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

		mTabHost.addTab(mTabHost.newTabSpec("fragmenta").setIndicator("Créer un bon de commande"), BdcFragment1.class,
				null);
		mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator("Demande de personnel"), BdcFragment2.class,
				null);
		mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator("Consulter bons de commandes"),
				BdcFragment3.class, null);
		mTabHost.addTab(mTabHost.newTabSpec("fragmentd").setIndicator("Consulter demandes de personnel"),
				BdcFragment4.class, null);
		return rootView;
	}
}
