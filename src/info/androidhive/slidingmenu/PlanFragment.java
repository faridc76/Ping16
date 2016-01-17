package info.androidhive.slidingmenu;
import android.app.Fragment;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import info.androidhive.gestineo.R;

public class PlanFragment extends Fragment {
	
	public PlanFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_plan, container, false);
        /*Canvas c = holder.lockCanvas();
        c.drawARGB(255,0,0,0);
        onDraw(c);
        holder.unlockCanvasAndPost(c);*/
         
        return rootView;
	}
}