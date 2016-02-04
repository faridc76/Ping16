package info.androidhive.slidingmenu;
import android.support.v4.app.Fragment;

import java.io.File;

import com.aspose.cells.ImageFormat;
import com.aspose.cells.ImageOrPrintOptions;
import com.aspose.cells.SheetRender;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.google.gson.Gson;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import fr.ineo.gestineo.dto.Affaire;
import fr.ineo.gestineo.json.DownloadTask;
import fr.ineo.gestineo.json.UploadTask;
import info.androidhive.gestineo.R;

public class CodisFragment extends Fragment implements OnClickListener {

	private ImageButton drawBtn, newBtn, saveBtn;
    private ProgressDialog mProgressDialog;
    private Affaire affaire;
    
	public CodisFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_codis, container, false);

		SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mesPrefs", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonAffaire = sharedPreferences.getString("affaire", "");
        affaire = gson.fromJson(jsonAffaire, Affaire.class);

		//draw button
		drawBtn = (ImageButton)rootView.findViewById(R.id.draw_btn);
		drawBtn.setOnClickListener(this);

		//new button
		newBtn = (ImageButton)rootView.findViewById(R.id.new_btn);
		newBtn.setOnClickListener(this);

		//save button
		saveBtn = (ImageButton)rootView.findViewById(R.id.save_btn);
		saveBtn.setOnClickListener(this);

		return rootView;
	}

	@Override
	public void onClick(View view) {
		if(view.getId()==R.id.new_btn){
			// instantiate it within the onCreate method
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("Downloading ...");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(true);

            // execute this when the downloader must be fired
            final DownloadTask downloadTask = new DownloadTask(this.getActivity(), mProgressDialog);
            downloadTask.execute(affaire.getNom(), "codis.xlsx");

            mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    downloadTask.cancel(true);
                }
            });
		}
		else if(view.getId()==R.id.draw_btn){
			File repPrincipal = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/GestIneo");
	    	File repAffaire = new File(repPrincipal, affaire.getNom());
			File codisFile = new File(repAffaire, "codis.xlsx");
			if(codisFile.exists()) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
		        intent.setDataAndType(new Uri.Builder().appendPath(codisFile.getAbsolutePath()).build(), "application/vnd.ms-excel");
		        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        try {
		                startActivity(intent);
		        } catch (ActivityNotFoundException e) {
		                e.printStackTrace();
		        }
			}
		}
		else if(view.getId()==R.id.save_btn){
			mProgressDialog = new ProgressDialog(this.getActivity());
            mProgressDialog.setMessage("Uploading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setProgress(0);
            mProgressDialog.show();

            // execute this when the downloader must be fired
            final UploadTask uploadTask = new UploadTask(this.getActivity(), mProgressDialog);
            uploadTask.execute(affaire.getNom(), "codis.xlsx");

            mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    uploadTask.cancel(true);
                }
            });
		}
	}
}
