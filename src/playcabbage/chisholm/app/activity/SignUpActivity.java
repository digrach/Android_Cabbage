// playcabbage android app
// Copyright Chisholm Institite - Diploma of Digital and Interactive Games ICA50211 2013
// Trainer: Rachael Colley - rachael.colley@chisholm.edu.au

package playcabbage.chisholm.app.activity;

import playcabbage.chisholm.app.Cabbage.R;

import playcabbage.chisholm.app.aSyncTask.NewAccountTask;
import playcabbage.chisholm.app.iHelper.IAsyncHelper;
import playcabbage.chisholm.app.settings.CabbageApp;
import playcabbage.chisholm.app.settings.CabbageApp.PREF_KEY_NAMES;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



public class SignUpActivity extends Activity implements IAsyncHelper {

	public String userName;
	public String email;
	public String password;
	public String firstName;
	public String lastName;
	public Activity activity;

	public String textSucceed = "Success!";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
	}

	public void assignInputs(View view){
		EditText pwText = (EditText)findViewById(R.id.inputPassword);
		password = pwText.getText().toString();
		EditText emailText = (EditText)findViewById(R.id.inputEmail);
		email = emailText.getText().toString();
		EditText userNameText = (EditText)findViewById(R.id.userName);
		userName = userNameText.getText().toString();
		EditText firstNameText = (EditText)findViewById(R.id.inputFirstName);
		firstName = firstNameText.getText().toString();
		EditText lastNameText = (EditText)findViewById(R.id.inputLastName);
		lastName = lastNameText.getText().toString();
		//Log.d("SignUp", "assigned variables");
		submitPressed();
	}

	public void submitPressed(){
		//Log.d("SignUp", "submitPressed Ran");
		NewAccountTask na = new NewAccountTask( userName, firstName, lastName,  email, password, this);

		na.execute();
		//Log.d("SignUp", "executed asyc");
	}

	public void runTest(){
		TextView userNameText = (TextView) findViewById(R.id.userName);
		userNameText.setText(firstName);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}

	@Override
	public void onAsyncTaskComplete(Object returnFromTaskObject, String returnDataTag) {
		if (returnDataTag.equals("newplayerid")) {
			int newplayerid = (Integer)returnFromTaskObject;
			if (newplayerid != 0) {
				((CabbageApp)this.getApplication()).setSettings(PREF_KEY_NAMES.USERNAME, email);
				((CabbageApp)this.getApplication()).setSettings(PREF_KEY_NAMES.EMAILADDRESS, email);
				((CabbageApp)this.getApplication()).setSettings(PREF_KEY_NAMES.PLAYERID, newplayerid);

				Intent i = new Intent(this,LoginActivity.class);
				if(getIntent().getStringExtra("superid") != null) {
					i.putExtra("superid", getIntent().getStringExtra("superid"));
				}
				startActivity(i);

			} else {

				EditText successText = (EditText)findViewById(R.id.successText);
				successText.setText("Failed");
			}	
		}
	}


}
