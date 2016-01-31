package fr.ineo.gestineo.json;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.PowerManager;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Classe permettant d'upload un fichier dans une affaire gr�ce 
 * � upload_fichier.php. Il faut fournir en param�tre le contexte
 * et le ProgressDialog qui permettra de montrer l'avancement de 
 * l'upload.
 * 
 * @author Ping16
 *
 */
public class UploadTask extends AsyncTask<String, Integer, String> {

    private Context context;
    private PowerManager.WakeLock mWakeLock;
    private ProgressDialog progressDialog;

    public UploadTask(Context context, ProgressDialog progressDialog) {
        this.context = context;
        this.progressDialog = progressDialog;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // On lock l'application pendant l'upload
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
        mWakeLock.acquire();
        progressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
        // Au cours de l'upload, on modifie l'�tat de la barre d'avancement
        progressDialog.setIndeterminate(false);
        progressDialog.setMax(100);
        progressDialog.setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
    	// On d�lock l'application
        mWakeLock.release();
        progressDialog.dismiss();
        if(result.equals("true"))
            Toast.makeText(context, "Upload termin�", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, "Echec de l'upload", Toast.LENGTH_LONG).show();
    }

    @Override
    protected String doInBackground(String... sUrl) {
    	// R�cup�ration des param�tres
        String affaireName = sUrl[0];
        String fileName = sUrl[1];

        String boundary = "boundaryFile";
        String lineEnd = "\r\n";
        String twoHyphens = "--";

        String result = "";
        // On va choisir le fichier dont le path correspond � celui de l'affaire et du fichier choisi
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/GestIneo/" + affaireName, fileName);

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStream.read(bytes);
            fileInputStream.close();

            URL url = new URL("http://faridchouakria.free.fr/webservices/upload_fichier.php");
            
            // D�but du post
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            OutputStream outputStream = connection.getOutputStream();

            DataOutputStream dos = new DataOutputStream(outputStream);

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"affaire\"" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(affaireName + lineEnd);

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"fichier\"; filename=\"" + fileName + "\"" + lineEnd);
            dos.writeBytes(lineEnd);

            int bufferLength = 1024;
            for (int i = 0; i < bytes.length; i += bufferLength) {
                int progress = (int)((i / (float) bytes.length) * 100);
                publishProgress(progress);
                if (bytes.length - i >= bufferLength) {
                    outputStream.write(bytes, i, bufferLength);
                } else {
                    outputStream.write(bytes, i, bytes.length - i);
                }
            }
            publishProgress(100);

            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            dos.flush();
            dos.close();
            
            // Fin du post
            
            // On lit la r�ponse
            
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                System.out.println("ligne " + ligne);
                result += ligne;
            }

            JSONObject obj = new JSONObject(result);
            result = obj.getString("result");
            inputStream.close();
            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return e.toString();
        }
        return result;
    }


}