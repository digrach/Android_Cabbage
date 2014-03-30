// playcabbage android app
// Copyright Chisholm Institite - Diploma of Digital and Interactive Games ICA50211 2013
// Trainer: Rachael Colley - rachael.colley@chisholm.edu.au

package playcabbage.chisholm.app.activity;

import java.util.List;
import java.util.Map;

import playcabbage.chisholm.app.Cabbage.R;
import playcabbage.chisholm.app.aSyncTask.GetAllLiveCollectiblesTask;
import playcabbage.chisholm.app.aSyncTask.GetPlayerCollectiblesTask;
import playcabbage.chisholm.app.bean.CollectibleBean;
import playcabbage.chisholm.app.iHelper.IAsyncHelper;
import playcabbage.chisholm.app.settings.CabbageApp;
import playcabbage.chisholm.app.settings.CabbageApp.PREF_KEY_NAMES;
import playcabbage.chisholm.app.utility.ImageAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;


public class ItemListActivity extends Activity implements IAsyncHelper {
	
	private List<CollectibleBean> cbList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//		GetAllLiveCollectiblesTask collectibleTask = new GetAllLiveCollectiblesTask(this);
//		collectibleTask.execute();
		setContentView(R.layout.activity_item_list);
		
		reloadPage();
	}
	
	public void reloadPage() {
		GetAllLiveCollectiblesTask collectibleTask = new GetAllLiveCollectiblesTask(this);
		collectibleTask.execute();
	}

	public void buildGrid(List<CollectibleBean> cbList,List<CollectibleBean> playerCollected){
		((CabbageApp) this.getApplication()).setCollectibleList(cbList);
		((CabbageApp) this.getApplication()).setCollectedByPlayerBeans(playerCollected);
		((CabbageApp) this.getApplication()).buildItemNameList();
		Map<Integer,CollectibleBean> mm = ((CabbageApp)this.getApplication()).getCollectibleResourceIDAndBeanMap();
		//Log.d("***buildGrid", Integer.toString(mm.size()));
		
		for (Map.Entry<Integer, CollectibleBean> e : mm.entrySet()) {
			Integer key = e.getKey();
			CollectibleBean value = e.getValue();
			//			Log.d("***count", Integer.toString(count));
			//			Log.d("***key", Integer.toString(key));
			//			Log.d("***value", value.getCollectiblename());
			
		}

		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this,((CabbageApp)this.getApplication()).getCollectibleList(),((CabbageApp)this.getApplication()).getItemRefArray(),((CabbageApp)this.getApplication()).getCollectedByPlayerBeans(),((CabbageApp)this.getApplication()).getCollectedByPlayerNames()));

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				this.openItemSummary(v,position);
				//Toast.makeText(ItemList.this, "" + position, Toast.LENGTH_SHORT).show();
			}

			public void openItemSummary(View view,int position){
				Intent intentItemSummary = new Intent(getApplicationContext(), ItemSummaryActivity.class);
				intentItemSummary.putExtra("ITEM", position);
				startActivity(intentItemSummary);
			}

		});

	}


	//Navigation intents

	public void openPlayerSummary(View view){
		finish();
	}

	public void openItemSummary(View view){
		Intent intentItemSummary = new Intent(getApplicationContext(), ItemSummaryActivity.class);
		startActivity(intentItemSummary);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.collected_items, menu);
		return true;
	}

	@Override
	public void onAsyncTaskComplete(Object returnFromTaskObject,
			String returnDataTag) {

		//cbList = null;

		if (returnDataTag.equals("livecollectibles")) {		
			//Log.d(getClass().getSimpleName(),"livecollectibles" );
			cbList = (List<CollectibleBean>) returnFromTaskObject;
			
			if (cbList != null) {
				Log.d(getClass().getSimpleName(),"cbList not null 1" );
			}
			
			GetPlayerCollectiblesTask t = new GetPlayerCollectiblesTask((Integer)((CabbageApp)this.getApplication()).getSettings(PREF_KEY_NAMES.PLAYERID, 0),this);
			t.execute();
		}

		if (returnDataTag.equals("playercollectibles")) {	
			////
			//Log.d(getClass().getSimpleName(),"playercollectibles" );

			List<CollectibleBean> playerCollected = (List<CollectibleBean>) returnFromTaskObject;
			if (cbList != null) {
				Log.d(getClass().getSimpleName(),"cbList not null 2" );
				buildGrid(cbList,playerCollected);
			}
		}
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		//Log.d(getClass().getSimpleName(),"onResume");
		if (((CabbageApp) this.getApplication()).isUpdateleaderBoard()) {
			reloadPage();
			((CabbageApp) this.getApplication()).setUpdateItemLists(false);
		}
		//check
	}

}
