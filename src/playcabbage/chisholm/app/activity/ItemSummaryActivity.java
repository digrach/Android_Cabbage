// playcabbage android app
// Copyright Chisholm Institite - Diploma of Digital and Interactive Games ICA50211 2013
// Trainer: Rachael Colley - rachael.colley@chisholm.edu.au

package playcabbage.chisholm.app.activity;


import java.util.Iterator;
import java.util.List;

import playcabbage.chisholm.app.Cabbage.R;
import playcabbage.chisholm.app.aSyncTask.CheckinPlayerTask;
import playcabbage.chisholm.app.bean.CollectibleBean;
import playcabbage.chisholm.app.iHelper.IAsyncHelper;
import playcabbage.chisholm.app.nfc.MimeType;
import playcabbage.chisholm.app.settings.CabbageApp;
import playcabbage.chisholm.app.settings.CabbageApp.PREF_KEY_NAMES;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemSummaryActivity extends Activity implements IAsyncHelper {

	private int itemRef;
	private CollectibleBean item;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_summary);
		
		//Log.d(getClass().getSimpleName() + " onCreate", "start");
		
		itemRef = getIntent().getExtras().getInt("ITEM");
		item = new CollectibleBean(); 
		assignData(itemRef);
		
//		ActivityManager m = (ActivityManager) getSystemService(this.ACTIVITY_SERVICE );
//        List<RunningTaskInfo> runningTaskInfoList =  m.getRunningTasks(1);
//        Iterator<RunningTaskInfo> itr = runningTaskInfoList.iterator();
//        while(itr.hasNext())
//        {
//            RunningTaskInfo runningTaskInfo = (RunningTaskInfo)itr.next();
//            int id = runningTaskInfo.id;
//            CharSequence desc= runningTaskInfo.description;
//            String topActivity = runningTaskInfo.topActivity.getShortClassName();
//            int numOfActivities = runningTaskInfo.numActivities;
//            String bottomActivity = runningTaskInfo.baseActivity.getShortClassName();
//            
//            Log.d("d1", Integer.toString(id));
//            //Log.d("d2", desc.toString());
//            Log.d("d3", topActivity);
//            Log.d("d4", Integer.toString(numOfActivities));
//            Log.d("d5", bottomActivity);
//        }

	}

//	public void assignData(CollectibleBean cb){
//		item = cb;
//		displayData(true);
//	}

	private int findBeanReference(CollectibleBean cb){
		int returnRef = 0;
		List<CollectibleBean> l = ((CabbageApp)this.getApplication()).getCollectibleList();
		for (int x = 0; x < l.size(); x++) {
			if (l.get(x).getCollectiblename().equals(cb.getCollectiblename())) {
				returnRef = x;
			}
		}
		return returnRef;
	}
	
	public void seeMap(View view) {
		Intent i = new Intent(this, MapActivity.class);
		startActivity(i);
	}

	public void assignData(int itemReference){
		item = ((CabbageApp)this.getApplication()).getCollectibleList().get(itemReference);
		
		boolean isCollected = false;
		List<CollectibleBean> l = ((CabbageApp)this.getApplication()).getCollectedByPlayerBeans();
		for (CollectibleBean c : l) {
			if (c.getCollectiblename().equals(item.getCollectiblename())) {
				isCollected = true;
			}
		}
		
		
		displayData(false,isCollected);
	}

	public void displayData(boolean isAward, boolean isCollected) {

		String awardText = "YOU HAVE BEEN AWARDED ";

		if (item != null) {
			//Log.d("ItemShouldBe",item.getCollectiblename());
			EditText itemNameText = (EditText) findViewById(R.id.itemName);

			if (isAward == true) {
				//Log.d("ItemShouldBe","Item awarded");
				itemNameText.setText(awardText + item.getCollectiblename());
			} else {
				itemNameText.setText(item.getCollectiblename());

			}

			ImageView icon = (ImageView) findViewById(R.id.itemIcon);
			icon.setImageResource(((CabbageApp)this.getApplication()).getItemRefArray().get(findBeanReference(item)));

			TextView itemValue = (TextView) findViewById(R.id.itemValue);
			itemValue.setText("Value: "+item.getValue());

			TextView desc = (TextView) findViewById(R.id.itemDesc);
			desc.setText(item.getCollectibledescription());

			TextView locDesc = (TextView) findViewById(R.id.itemLocDesc);
			locDesc.setText(item.getLocationdescription());
			
			TextView itemCollected = (TextView) findViewById(R.id.itemCollected);
			
			if (isCollected == true) {
				itemCollected.setText("You have collected this item!");
			} else {
				itemCollected.setText("You have not collected this item yet!");

			}
			
			
		} else {
			EditText itemNameText = (EditText) findViewById(R.id.itemName);
			itemNameText.setText("NOTHING HERE TO COLLECT THIS TIME");
		}

	}


	public void openItemList(View view){
		Intent i = new Intent(this, ItemListActivity.class);
		startActivity(i);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.item_summary, menu);
		return true;
	}

	@Override
	public void onAsyncTaskComplete(Object returnFromTaskObject,
			String returnDataTag) {
//		if (returnDataTag.equals("checkinplayer")) {
//			CollectibleBean cb = (CollectibleBean) returnFromTaskObject;
//			assignData(cb);
//		}
			
	}
	
	
	}


