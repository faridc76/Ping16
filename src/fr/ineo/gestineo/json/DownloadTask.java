package fr.ineo.gestineo.json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.PowerManager;
import android.widget.Toast;

/**
 * Classe permettant de t�l�charger un fichier d'une affaire.
 * Il faut fournir en param�tre le contexte et le ProgressDialog
 * qui permettra de montrer l'avancement du t�l�chargement.
 * 
 * @author Ping16
 *
 */

public class DownloadTask extends AsyncTask<String, Integer, String> {

    private Context context;
    private PowerManager.WakeLock mWakeLock;
    private ProgressDialog progressDialog;
	public final static String DOCUMENT = "http://faridchouakria.free.fr/documents/";

    public DownloadTask(Context context, ProgressDialog progressDialog) {
        this.context = context;
        this.progressDialog = progressDialog;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
        mWakeLock.acquire();
        progressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
        progressDialog.setIndeterminate(false);
        progressDialog.setMax(100);
        progressDialog.setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        mWakeLock.release();
        progressDialog.dismiss();
        if (result != null)
            Toast.makeText(context, "Download error: " + result, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, "File downloaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... sUrl) {
    	String affaireName = sUrl[0];
    	String fileName = sUrl[1];
    	
    	File repPrincipal = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/GestIneo");
    	repPrincipal.mkdirs();
    	System.out.println(repPrincipal.getAbsolutePath());

    	File repAffaire = new File(repPrincipal, affaireName);
    	repAffaire.mkdirs();
    	System.out.println(repAffaire.getAbsolutePath());

    	File file = new File(repAffaire, fileName);
    	System.out.println(file.getAbsolutePath());

        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        try {
        	System.out.println(DOCUMENT + sUrl[0] + "/" + sUrl[1]);
            URL url = new URL(DOCUMENT + sUrl[0] + "/" + sUrl[1]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            // expect HTTP 200 OK, so we don't mistakenly save error report
            // instead of the file
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "Server returned HTTP " + connection.getResponseCode()
                        + " " + connection.getResponseMessage();
            }

            // this will be useful to display download percentage
            // might be -1: server did not report the length
            int fileLength = connection.getContentLength();

            // download the file
            input = connection.getInputStream();
            output = new FileOutputStream(file);

            byte data[] = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                // allow canceling with back button
                if (isCancelled()) {
                    input.close();
                    return null;
                }
                total += count;
                // publishing the progress....
                if (fileLength > 0) // only if total length is known
                    publishProgress((int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }
        } catch (Exception e) {
            return e.toString();
        } finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }

            if (connection != null)
                connection.disconnect();
        }
        return null;
    }


}