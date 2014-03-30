// playcabbage android app
// Copyright Chisholm Institite - Diploma of Digital and Interactive Games ICA50211 2013
// Trainer: Rachael Colley - rachael.colley@chisholm.edu.au

package playcabbage.chisholm.app.activity;

import java.util.List;

import playcabbage.chisholm.app.Cabbage.R;

import playcabbage.chisholm.app.aSyncTask.CheckinPlayerTask;
import playcabbage.chisholm.app.aSyncTask.GetLeaderBoardTask;
import playcabbage.chisholm.app.aSyncTask.GetPlayerLeaderBoardRankTask;
import playcabbage.chisholm.app.bean.CollectibleBean;
import playcabbage.chisholm.app.bean.LeaderBoardBean;
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
import android.widget.EditText;

public class PlayerSummary extends Activity implements IAsyncHelper {

	private EditText userNameText;
	private EditText userRankText;
	private EditText userRankText2;
	private EditText userScoreText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_summary);

		//Log.d(getClass().getSimpleName() + "onCreate", "start");



		userNameText = (EditText) findViewById(R.id.editText1);
		userNameText.setText(((CabbageApp) this.getApplication()).getSettings(PREF_KEY_NAMES.USERNAME, "none").toString());

		//		GetPlayerLeaderBoardRankTask rankTask = new GetPlayerLeaderBoardRankTask((Integer)((CabbageApp) this.getApplication()).getSettings(PREF_KEY_NAMES.PLAYERID, 0),this);
		//		rankTask.execute();

		reloadPage();

	}

	public void reloadPage() {
		GetPlayerLeaderBoardRankTask rankTask = new GetPlayerLeaderBoardRankTask((Integer)((CabbageApp) this.getApplication()).getSettings(PREF_KEY_NAMES.PLAYERID, 0),this);
		rankTask.execute();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.player_summary, menu);
		return true;
	}

	public void populateLeaderBoard(List<LeaderBoardBean> lbList){
		if (lbList !=null){

			for (int x =0; x < ((CabbageApp) this.getApplication()).getLeaderBoardList().size(); x++ ){
				if (((CabbageApp) this.getApplication()).getLeaderBoardList().get(x).getPlayer_id() == ((Integer)((CabbageApp) this.getApplication()).getSettings(PREF_KEY_NAMES.PLAYERID, 0))){
					userScoreText = (EditText) findViewById(R.id.scoreText);
					userScoreText.setText("Score: "+Integer.toString(((CabbageApp) this.getApplication()).getLeaderBoardList().get(x).getPoints()));
				}

			}
		}


	}

	//Navigation intents

	public void openLeaderBoard(View view){
		//Log.d(getClass().getSimpleName() + " openLeaderBoard", "HERE");
		Intent intentLeaderBoard = new Intent(getApplicationContext(), LeaderBoardActivity.class);
		startActivity(intentLeaderBoard);
	}

	public void openItemList(View view){
		Intent intentItemList = new Intent(getApplicationContext(), ItemListActivity.class);
		startActivity(intentItemList);
	}

	public void openPlayerSummary(View view){
		Intent intentPlayerSummary = new Intent(getApplicationContext(), LeaderBoardActivity.class);
		startActivity(intentPlayerSummary);
	} ///////////// should this be here at all?

	// ----------------------------------------- Navigation end ---------------------------------------

	@Override
	public void onAsyncTaskComplete(Object returnFromTaskObject,
			String returnDataTag) {

		if (returnDataTag.equals("rank")) {
			int rank = (Integer) returnFromTaskObject;
			((CabbageApp) this.getApplication()).setRank(rank);
			userRankText = (EditText) findViewById(R.id.rankText);
			userRankText2 = (EditText) findViewById(R.id.rankText2);
			if (((CabbageApp) this.getApplication()).getRank() != 0){
				userRankText.setText(Integer.toString(((CabbageApp) this.getApplication()).getRank()));
				userRankText2.setText("Rank: " + Integer.toString(((CabbageApp) this.getApplication()).getRank()));
			}else {
				userRankText.setText("NO RANK");
				userRankText2.setText("NO RANK");
			}
			GetLeaderBoardTask scoreTask = new GetLeaderBoardTask(this);
			scoreTask.execute();
		}

		if (returnDataTag.equals("leaderboard")) {

			List<LeaderBoardBean> l = (List<LeaderBoardBean>)returnFromTaskObject;
			((CabbageApp) this.getApplication()).setLeaderBoardList(l);
			populateLeaderBoard(((CabbageApp) this.getApplication()).getLeaderBoardList());

		}


	}

	@Override
	public void onResume() {
		super.onResume();
		//Log.d(getClass().getSimpleName(),"onResume");
		if (((CabbageApp) this.getApplication()).isUpdateleaderBoard()) {
			reloadPage();
			((CabbageApp) this.getApplication()).setUpdateleaderBoard(false);
		}
		//check
	}

	//	@Override
	//	public void onPause() {
	//		super.onPause();
	//		Log.d(getClass().getSimpleName(),"onPause");
	//		finish();
	//	}
	//
	//	@Override
	//	public void onStop() {
	//		super.onStop();
	//		Log.d(getClass().getSimpleName(),"onStop");;
	//		finish();
	//	}

}
