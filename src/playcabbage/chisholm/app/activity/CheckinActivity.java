// playcabbage android app
// Copyright Chisholm Institite - Diploma of Digital and Interactive Games ICA50211 2013
// Trainer: Rachael Colley - rachael.colley@chisholm.edu.au

package playcabbage.chisholm.app.activity;

import playcabbage.chisholm.app.Cabbage.R;
import playcabbage.chisholm.app.aSyncTask.CheckinPlayerTask;
import playcabbage.chisholm.app.bean.CollectibleBean;
import playcabbage.chisholm.app.iHelper.IAsyncHelper;
import playcabbage.chisholm.app.nfc.MimeType;
import playcabbage.chisholm.app.settings.CabbageApp;
import playcabbage.chisholm.app.settings.CabbageApp.PREF_KEY_NAMES;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class CheckinActivity extends Activity implements IAsyncHelper {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkin);

		if (getIntent().getExtras().getString("superid") != null) {

			//Log.d(getClass().getSimpleName() + " intent was", getIntent().getExtras().getString("superid"));
			doCheckin(getIntent().getExtras().getString("superid"));
			displayData(getIntent().getExtras().getString("superid"));

		} else if(getIntent().getType() != null && getIntent().getType().equals(MimeType.NFC_DEMO)) {

			//Log.d(getClass().getSimpleName() + " intent was", "from NFC");
			String urlstring = readNFCIntent(getIntent());

			if (userHasSettings()) {

				//Log.d(getClass().getSimpleName() + " user settings", "true");
				doCheckin(urlstring);
				displayData(urlstring);
			} else {

				//Log.d(getClass().getSimpleName() + " user settings", "false");
				goBackToMain(urlstring);
			}
		}


	}

	private void displayData(String superid) {
		//Log.d(getClass().getSimpleName(), "displayData");
		TextView tview = (TextView)findViewById(R.id.tview);
		tview.setText(superid);
	}

	private String readNFCIntent(Intent intent) {
		//Log.d(getClass().getSimpleName(), "readNFCIntent");

		String s = null;

		if(intent.getType() != null && intent.getType().equals(MimeType.NFC_DEMO)) {
			Parcelable[] rawMsgs = getIntent().getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
			NdefMessage msg = (NdefMessage) rawMsgs[0];
			NdefRecord cardRecord = msg.getRecords()[0];
			String superid = new String(cardRecord.getPayload());
			s = superid;
		}

		return s;
	}

	private void doCheckin(String superid) {
		//Log.d(getClass().getSimpleName(), "doCheckin");

		int pid = (Integer)((CabbageApp)this.getApplication()).getSettings(PREF_KEY_NAMES.PLAYERID, 0);
		CheckinPlayerTask cpt = new CheckinPlayerTask(pid, superid, this);  
		cpt.execute();
	}

	private boolean userHasSettings() {
		boolean returnVal = false;
		if (((CabbageApp)this.getApplication()).getSettings(PREF_KEY_NAMES.EMAILADDRESS, "none").equals("none")) {
			returnVal = false;
		} else {
			returnVal = true;
		}
		return returnVal;
	}

	public void goBackToMainClick(View view) {
		goBackToMain(null);
	}

	public void goBackToMain(String extra) {
		//Log.d(getClass().getSimpleName(), "goBackToMain");

		Intent i = new Intent(this,MainActivity.class);
		if (extra != null) {
			i.putExtra("superid", extra);
			//Log.d(getClass().getSimpleName(), "goBackToMain extra was " + extra);
		}
		startActivity(i);
		//Log.d(getClass().getSimpleName()+ "after start before finish ","HERE");
		finish();
	}

	@Override
	public void onPause() {
		super.onPause();
		//Log.d(getClass().getSimpleName(),"onPause");
		finish();
	}

	@Override
	public void onStop() {
		super.onStop();
		//Log.d(getClass().getSimpleName(),"onStop");;
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_checkin, menu);
		return true;
	}

	@Override
	public void onAsyncTaskComplete(Object returnFromTaskObject,
			String returnDataTag) {
		
		if (returnDataTag.equals("checkinplayer")) {
			if (returnFromTaskObject != null) {
				
				//Log.d(getClass().getSimpleName()+ "onAsyncTaskComplete ","return object Not null");
				CollectibleBean cb = (CollectibleBean)returnFromTaskObject;
				if (cb != null) {
					
					TextView congrats = (TextView)findViewById(R.id.congrats);
					congrats.setText("Congratulations!");
					
					TextView tview = (TextView)findViewById(R.id.tview);
					tview.setText(cb.getCollectiblename());
					
					// NOTIFY
					((CabbageApp)this.getApplication()).setUpdateleaderBoard(true);
					((CabbageApp)this.getApplication()).setUpdateItemLists(true);
				}
			} else {
				//Log.d(getClass().getSimpleName()+ "onAsyncTaskComplete ","return object NULL");

				TextView congrats = (TextView)findViewById(R.id.congrats);
				congrats.setText("Nothing here to collect this time...");
				
				TextView tview = (TextView)findViewById(R.id.tview);
				tview.setText("...maybe next time.");
				
			}

			try {
				Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
				Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
				r.play();
			} catch (Exception e) {}

			//Log.d("HERE","HERE");
		}

	}

}
