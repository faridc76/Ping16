package info.androidhive.slidingmenu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import info.androidhive.gestineo.R;

public class CodisBtnFragment extends Fragment {

	public CodisBtnFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_codisbtn, container, false);

		Button loadbtn = (Button) rootView.findViewById(R.id.loadcodisbtn);
		loadbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("http://faridchouakria.free.fr/documents/ACN-060123/codis.xlsx");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});

		return rootView;
	}
}