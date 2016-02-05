package info.androidhive.slidingmenu.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import fr.ineo.gestineo.dto.DemandeItem;
import info.androidhive.gestineo.R;

public class DemandeAdapter extends ArrayAdapter<DemandeItem> {

	public DemandeAdapter(Context context, ArrayList<DemandeItem> demandeItem) {
		super(context, 0, demandeItem);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get the data item for this position
		DemandeItem demandeItem = getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_bdc_list_item, parent, false);
		}
		// Lookup view for data population
		TextView tvCommanditaire = (TextView) convertView.findViewById(R.id.list_item_commanditaire);
		TextView tvTache = (TextView) convertView.findViewById(R.id.list_item_text);
		TextView tvStatut = (TextView) convertView.findViewById(R.id.list_item_statut);

		// Populate the data into the template view using the data object
		tvStatut.setText(String.valueOf(demandeItem.getStatut()));
		tvTache.setText(demandeItem.getTache());
		tvCommanditaire.setText(demandeItem.getAuteur());
		// Return the completed view to render on screen
		return convertView;
	}

}
