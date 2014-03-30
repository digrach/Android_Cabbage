// playcabbage android app
// Copyright Chisholm Institite - Diploma of Digital and Interactive Games ICA50211 2013
// Trainer: Rachael Colley - rachael.colley@chisholm.edu.au

package playcabbage.chisholm.app.activity;

import java.util.UUID;

import playcabbage.chisholm.app.Cabbage.R;

import playcabbage.chisholm.app.aSyncTask.CheckinPlayerTask;
import playcabbage.chisholm.app.aSyncTask.CreateNewJaffaAppKeyTask;
import playcabbage.chisholm.app.iHelper.IAsyncHelper;
import playcabbage.chisholm.app.nfc.MimeType;
import playcabbage.chisholm.app.settings.CabbageApp;
import playcabbage.chisholm.app.settings.CabbageApp.PREF_KEY_NAMES;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity implements IAsyncHelper {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		keyCheck();
		//		startApp();

		//Log.d(getClass().getSimpleName() + "onCreate", "start");
		//Log.d(getClass().getSimpleName() + "USERNAME", ((CabbageApp)this.getApplication()).getSettings(PREF_KEY_NAMES.USERNAME, "none").toString());

		//		// If the setting exists, go straight to player summary.
		//		if (! ((CabbageApp)this.getApplication()).getSettings(PREF_KEY_NAMES.USERNAME, "none").equals("none")) {
		//			Log.d(getClass().getSimpleName(), "onCreate" + "settings exist");
		//			//Log.d(getClass() + "USERNAME", ((CabbageApp)this.getApplication()).getSettings(PREF_KEY_NAMES.USERNAME, "none").toString());
		//
		//			//Log.d("Returned",intent.getType());
		//			if (getIntent().getStringExtra("superid") != null) {
		//				Log.d(getClass().getSimpleName(), "onCreate extras" + "exist: " + getIntent().getStringExtra("superid"));
		//
		//				Intent chIntent = new Intent(this,CheckinActivity.class);
		//				chIntent.putExtra("superid", getIntent().getStringExtra("superid"));
		//				startActivity(chIntent);
		//			} else {
		//				Log.d(getClass().getSimpleName(), "onCreate - no extras");
		//
		//				Intent intent1 = new Intent(getApplicationContext(), PlayerSummary.class);
		//				String username = ((CabbageApp)this.getApplication()).getSettings(PREF_KEY_NAMES.USERNAME, "none").toString();
		//				intent1.putExtra("username", username);
		//				startActivity(intent1);
		//			}
		//		}


	}

	public void startApp() {
		//Log.d(getClass().getSimpleName(), "startApp");
		// If the setting exists, go straight to player summary.
		if (! ((CabbageApp)this.getApplication()).getSettings(PREF_KEY_NAMES.USERNAME, "none").equals("none")) {
			//Log.d(getClass().getSimpleName(), "onCreate" + "settings exist");
			//Log.d(getClass() + "USERNAME", ((CabbageApp)this.getApplication()).getSettings(PREF_KEY_NAMES.USERNAME, "none").toString());

			//Log.d("Returned",intent.getType());
			if (getIntent().getStringExtra("superid") != null) {
				//Log.d(getClass().getSimpleName(), "onCreate extras" + "exist: " + getIntent().getStringExtra("superid"));

				Intent chIntent = new Intent(this,CheckinActivity.class);
				chIntent.putExtra("superid", getIntent().getStringExtra("superid"));
				startActivity(chIntent);
			} else {
				//Log.d(getClass().getSimpleName(), "onCreate - no extras");

				Intent intent1 = new Intent(getApplicationContext(), PlayerSummary.class);
				String username = ((CabbageApp)this.getApplication()).getSettings(PREF_KEY_NAMES.USERNAME, "none").toString();
				intent1.putExtra("username", username);
				startActivity(intent1);
			}
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		//menu.getItem(0).setIntent(intent1);
		//menu.getItem(1).setIntent(intent2);
		//menu.getItem(2).setIntent(intent3);
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void openSignUp(View view){
		//Log.d(getClass().getSimpleName(), "openSignUp");
		Intent intent1 = new Intent(getApplicationContext(), SignUpActivity.class);
		if(getIntent().getStringExtra("superid") != null) {
			//Log.d(getClass().getSimpleName() + " openSignUp has extras", getIntent().getStringExtra("superid"));
			intent1.putExtra("superid", getIntent().getStringExtra("superid"));
		}
		startActivity(intent1);
		finish();
	}
	//	public void goToSignUp(String extra){
	//		Intent intent1 = new Intent(getApplicationContext(), SignUpActivity.class);
	//		startActivity(intent1);
	//	}
	public void openWhatIs(View view){
		//Log.d(getClass().getSimpleName(), "openWhatIs");
		Intent intent1 = new Intent(getApplicationContext(), WhatIsActivity.class);
		if(getIntent().getStringExtra("superid") != null) {
			//Log.d(getClass().getSimpleName() + " openWhatIs has extras", getIntent().getStringExtra("superid"));
			intent1.putExtra("superid", getIntent().getStringExtra("superid"));
		}
		startActivity(intent1);
	}
	public void openLogIn(View view){
		//Log.d(getClass().getSimpleName(), "openLogIn");

		Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
		if(getIntent().getStringExtra("superid") != null) {
			//Log.d(getClass().getSimpleName() + " openLogIn has extras", getIntent().getStringExtra("superid"));
			intent1.putExtra("superid", getIntent().getStringExtra("superid"));
		}
		startActivity(intent1);
		finish();
	}

	@Override
	public void onAsyncTaskComplete(Object returnFromTaskObject, String returnDataTag) {
		// N/A

		//Log.d(getClass().getSimpleName() + " onAsyncTaskComplete", "returnDataTag");

		if (returnDataTag.equals("jaffaappkey")) {
			Boolean success = (Boolean) returnFromTaskObject;
			if (success == true) {
				startApp();
			} else {
				finish();
			}
		}

	}

	@Override
	public void onPause() {
		super.onPause();
		//Log.d(getClass().getSimpleName(),"onPause");
		//super.finish();
		finish();
		//return;
	}

	@Override
	public void onStop() {
		super.onStop();
		//Log.d(getClass().getSimpleName(),"onStop");

		//super.finish();
		finish();
		//return;
	}

	public void keyCheck() {
		//Log.d(getClass().getSimpleName(), "keyCheck");
		if (((CabbageApp)this.getApplication()).getSettings(PREF_KEY_NAMES.JAFFA, "none").equals("none")) {
			//Log.d(getClass().getSimpleName(), "keyCheck NONE");
			UUID uniqueKey = UUID.randomUUID(); 
			String k = uniqueKey.toString();
			((CabbageApp)this.getApplication()).setSettings(PREF_KEY_NAMES.JAFFA, k);
			CreateNewJaffaAppKeyTask jak = new CreateNewJaffaAppKeyTask(this);
			jak.execute();
		} else {
			//Log.d(getClass().getSimpleName(), "keyCheck STARTING APP");
			startApp();
		}

	}
}
