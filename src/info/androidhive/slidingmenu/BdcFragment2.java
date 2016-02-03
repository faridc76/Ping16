package info.androidhive.slidingmenu;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import info.androidhive.gestineo.R;

public class BdcFragment2 extends Fragment implements View.OnClickListener {
	
	public BdcFragment2(){}
	
	public Button button1;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_bdc_tab2, container, false);
        button1 = (Button)rootView.findViewById(R.id.button1);
        button1.setOnClickListener(this);
        return rootView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}