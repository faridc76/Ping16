package info.androidhive.slidingmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import fr.ineo.gestineo.json.CheckLoginRequete;
import info.androidhive.gestineo.R;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void openMainMenu(View view){
		
		EditText matricule = (EditText) findViewById(R.id.EditText02);
		EditText password = (EditText) findViewById(R.id.EditText01);
		
		// Pour tester le matricule
		new CheckLoginRequete().execute(matricule.getText().toString(), password.getText().toString(), this.getApplicationContext());
		// Il faudra mettre le checkLogin � la place !
		
		//Intent intent = new Intent(this, MainActivity.class);
		//startActivity(intent);
	}
	public void openRegister(View view){
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
	}
}
