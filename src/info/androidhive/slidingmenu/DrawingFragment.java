package info.androidhive.slidingmenu;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import com.google.gson.Gson;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import fr.ineo.gestineo.dto.Affaire;
import fr.ineo.gestineo.json.UploadTask;
import info.androidhive.gestineo.R;

public class DrawingFragment extends Fragment implements OnClickListener {

	// custom drawing view
	private DrawingView drawView;
	// buttons
	private ImageButton currPaint, drawBtn, eraseBtn, newBtn, saveBtn;
	// sizes
	private float smallBrush, mediumBrush, largeBrush;

	// Url
	private String adresse;

	public DrawingFragment(String adresse) {
		this.adresse = adresse;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_drawing, container, false);

		// get drawing view
		drawView = (DrawingView) rootView.findViewById(R.id.drawing);
		// get the palette and first color button
		/*
		 * LinearLayout paintLayout =
		 * (LinearLayout)rootView.findViewById(R.id.paint_colors); currPaint =
		 * (ImageButton)paintLayout.getChildAt(0);
		 * currPaint.setImageDrawable(getResources().getDrawable(R.drawable.
		 * paint_pressed)); currPaint.setOnClickListener(this);
		 */

		try {
			URL url = new URL(adresse);
			Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
			Drawable d = new BitmapDrawable(getResources(), bmp);
			drawView.setBackground(d);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// sizes from dimensions
		smallBrush = getResources().getInteger(R.integer.small_size);
		mediumBrush = getResources().getInteger(R.integer.medium_size);
		largeBrush = getResources().getInteger(R.integer.large_size);

		// draw button
		drawBtn = (ImageButton) rootView.findViewById(R.id.draw_btn);
		drawBtn.setOnClickListener(this);

		// set initial size
		drawView.setBrushSize(mediumBrush);

		// erase button
		eraseBtn = (ImageButton) rootView.findViewById(R.id.erase_btn);
		eraseBtn.setOnClickListener(this);

		// new button
		newBtn = (ImageButton) rootView.findViewById(R.id.new_btn);
		newBtn.setOnClickListener(this);

		// save button
		saveBtn = (ImageButton) rootView.findViewById(R.id.save_btn);
		saveBtn.setOnClickListener(this);

		return rootView;
	}

	// user clicked paint
	public void paintClicked(View view) {
		// use chosen color

		// set erase false
		drawView.setErase(false);
		drawView.setBrushSize(drawView.getLastBrushSize());

		if (view != currPaint) {
			ImageButton imgView = (ImageButton) view;
			String color = view.getTag().toString();
			drawView.setColor(color);
			// update ui
			imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
			currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
			currPaint = (ImageButton) view;
		}
	}

	@Override
	public void onClick(View view) {

		/*
		 * ImageButton[] buttons = new ImageButton[12]; Boolean colorPressed =
		 * false;
		 * 
		 * for(int i=0; i<buttons.length; i++) {
		 * 
		 * String buttonID = "c" + (i+1); int resID =
		 * getResources().getIdentifier(buttonID, "id",
		 * getActivity().getPackageName()); if(view.getId()==resID) colorPressed
		 * = true; }
		 * 
		 * if(colorPressed){ //use chosen color
		 * 
		 * //set erase false drawView.setErase(false);
		 * drawView.setBrushSize(drawView.getLastBrushSize());
		 * 
		 * if(view!=currPaint){ ImageButton imgView = (ImageButton)view; String
		 * color = view.getTag().toString(); drawView.setColor(color); //update
		 * ui imgView.setImageDrawable(getResources().getDrawable(R.drawable.
		 * paint_pressed));
		 * currPaint.setImageDrawable(getResources().getDrawable(R.drawable.
		 * paint)); currPaint=(ImageButton)view; } colorPressed = false; }
		 */
		if (view.getId() == R.id.draw_btn) {
			// draw button clicked
			final Dialog brushDialog = new Dialog(getActivity());
			brushDialog.setTitle("Brush size:");
			brushDialog.setContentView(R.layout.brush_chooser);
			// listen for clicks on size buttons
			ImageButton smallBtn = (ImageButton) brushDialog.findViewById(R.id.small_brush);
			smallBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					drawView.setErase(false);
					drawView.setBrushSize(smallBrush);
					drawView.setLastBrushSize(smallBrush);
					brushDialog.dismiss();
				}
			});
			ImageButton mediumBtn = (ImageButton) brushDialog.findViewById(R.id.medium_brush);
			mediumBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					drawView.setErase(false);
					drawView.setBrushSize(mediumBrush);
					drawView.setLastBrushSize(mediumBrush);
					brushDialog.dismiss();
				}
			});
			ImageButton largeBtn = (ImageButton) brushDialog.findViewById(R.id.large_brush);
			largeBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					drawView.setErase(false);
					drawView.setBrushSize(largeBrush);
					drawView.setLastBrushSize(largeBrush);
					brushDialog.dismiss();
				}
			});
			// show and wait for user interaction
			brushDialog.show();
		} else if (view.getId() == R.id.erase_btn) {
			// switch to erase - choose size
			final Dialog brushDialog = new Dialog(getActivity());
			brushDialog.setTitle("Eraser size:");
			brushDialog.setContentView(R.layout.brush_chooser);
			// size buttons
			ImageButton smallBtn = (ImageButton) brushDialog.findViewById(R.id.small_brush);
			smallBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					drawView.setErase(true);
					drawView.setBrushSize(smallBrush);
					brushDialog.dismiss();
				}
			});
			ImageButton mediumBtn = (ImageButton) brushDialog.findViewById(R.id.medium_brush);
			mediumBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					drawView.setErase(true);
					drawView.setBrushSize(mediumBrush);
					brushDialog.dismiss();
				}
			});
			ImageButton largeBtn = (ImageButton) brushDialog.findViewById(R.id.large_brush);
			largeBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					drawView.setErase(true);
					drawView.setBrushSize(largeBrush);
					brushDialog.dismiss();
				}
			});
			brushDialog.show();
		} else if (view.getId() == R.id.new_btn) {
			// new button
			AlertDialog.Builder newDialog = new AlertDialog.Builder(getActivity());
			newDialog.setTitle("New drawing");
			newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
			newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					drawView.startNew();
					dialog.dismiss();
				}
			});
			newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			newDialog.show();
		} else if (view.getId() == R.id.save_btn) {
			// save drawing
			AlertDialog.Builder saveDialog = new AlertDialog.Builder(getActivity());
			saveDialog.setTitle("Save drawing");
			saveDialog.setMessage("Save drawing to device Gallery?");
			saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// save drawing
					drawView.setDrawingCacheEnabled(true);
					// attempt to save
					String imgSaved = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),
							drawView.getDrawingCache(), UUID.randomUUID().toString() + ".png", "drawing");
					
					//test enregistrement sur internet
					
					SharedPreferences preferences = getActivity().getSharedPreferences("mesPrefs", Context.MODE_PRIVATE);
					Gson gson = new Gson();
					String jsonAffaire = preferences.getString("affaire", "");
					Affaire affaire = gson.fromJson(jsonAffaire, Affaire.class);
					
					ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
					mProgressDialog.setMessage("Uploading...");
					mProgressDialog.setIndeterminate(false);
					mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
					mProgressDialog.setProgress(0);
					mProgressDialog.show();
					
					final UploadTask uploadTask = new UploadTask(getActivity(), mProgressDialog);
					uploadTask.execute(affaire.getNom(), "test.jpg");
					
					//Fin test
					
					
					// feedback
					if (imgSaved != null) {
						Toast savedToast = Toast.makeText(getActivity(), "Drawing saved to Gallery!",
								Toast.LENGTH_SHORT);
						savedToast.show();
					} else {
						Toast unsavedToast = Toast.makeText(getActivity(), "Oops! Image could not be saved.",
								Toast.LENGTH_SHORT);
						unsavedToast.show();
					}
					drawView.destroyDrawingCache();
				}
			});
			saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			saveDialog.show();
		}
	}
}