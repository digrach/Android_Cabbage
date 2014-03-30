// playcabbage android app
// Copyright Chisholm Institite - Diploma of Digital and Interactive Games ICA50211 2013
// Trainer: Rachael Colley - rachael.colley@chisholm.edu.au

package playcabbage.chisholm.app.activity;

import java.util.ArrayList;
import java.util.List;

import playcabbage.chisholm.app.Cabbage.R;

import playcabbage.chisholm.app.bean.LeaderBoardBean;
import playcabbage.chisholm.app.settings.CabbageApp;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;



public class LeaderBoardActivity extends Activity {

	private ListView lv;
	private ListAdapter listAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leader_board);

		//Log.d(getClass().getSimpleName() + " onCreate", "HERE");
		populateListView();
	}

	public void populateListView(){
		//Log.d(getClass().getSimpleName() + " populateListView", "HERE");
		//create the list of items
		List<LeaderBoardBean> lb = ((CabbageApp) this.getApplication()).getLeaderBoardList();
		ArrayList<String> lbList = new ArrayList<String>();
		//build the adapter

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this,							//activity context
				R.layout.leaderboard_player,	//layout to use
				lbList);						//items to be displayed
		//configure list view

		lv = (ListView) findViewById(R.id.listViewLb);
//		
		if (lb != null){
			for (int x = 0; x < lb.size(); x++){
				lbList.add(lb.get(x).getUsername() +" "+ "has" +" " + lb.get(x).getPoints());
			}
			lv.setAdapter(adapter);
		}
		
		
		
		//ListAdapter listAdapter = new CustomListAdapter(this , R.layout.leaderboard_player , lbList);
		
		
//		ListAdapter listAdapter = new CustomListAdapter(this , R.id.textViewx , lbList);
//		lv = (ListView) findViewById(R.id.listViewLb);
//		lv.setAdapter(listAdapter);
		
//		listAdapter = new CustomListAdapter(LeaderBoardActivity.this , R.layout.activity_display_leader_board_list , lbList);
//		lv = (ListView) findViewById(R.id.listViewLb);
//		lv.setAdapter(listAdapter);
		
//		lv = (ListView) findViewById(R.id.listViewLb);
//		listAdapter = new CustomListAdapter(this , R.layout.activity_display_leader_board_list , lbList);
//		lv.setAdapter(listAdapter);
	}


	//Navigation intents

	public void openPlayerSummary(View view){
		finish();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.leader_board, menu);
		return true;
	}

}
