// playcabbage android app
// Copyright Chisholm Institite - Diploma of Digital and Interactive Games ICA50211 2013
// Trainer: Rachael Colley - rachael.colley@chisholm.edu.au

package playcabbage.chisholm.app.settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import playcabbage.chisholm.app.bean.AccountBean;
import playcabbage.chisholm.app.bean.CollectibleBean;
import playcabbage.chisholm.app.bean.LeaderBoardBean;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

public class CabbageApp extends Application {

	public static final String PREFS_NAME = "cabbageprefs";
	private SharedPreferences cabbagePrefs;
	
	public enum PREF_KEY_NAMES {
		USERNAME,EMAILADDRESS,PLAYERID,ACCOUNTID,FIRSTNAME,LASTNAME,AVAILABLE,JAFFA
	}
	
	private String PREF_NAME_USERNAME = "username";
	private String PREF_NAME_EMAIL = "emailaddress";
	private String PREF_NAME_PLAYERID = "playerid";
	private String PREF_NAME_ACCOUNTID = "accountid";
	private String PREF_NAME_FIRSTNAME = "firstname";
	private String PREF_NAME_LASTNAME = "lastname";
	private String PREF_NAME_AVAILABLE = "available";
	private String PREF_NAME_JAFFA = "jaffa";

	private AccountBean ab;
	private int rank = 0;
	private List<LeaderBoardBean> leaderBoardList;
	private List<CollectibleBean> collectibleList;

	private Map<Integer,String> collectibleImageMap;
	private ArrayList<Integer> itemRefArray;

	private Map<Integer,CollectibleBean> collectibleResourceIDAndBeanMap;

	private List<CollectibleBean> collectedByPlayerBeans;
	private List<String> collectedByPlayerNames;

	private boolean updateleaderBoard;
	private boolean updateItemLists;



	public boolean isUpdateleaderBoard() {
		return updateleaderBoard;
	}

	public void setUpdateleaderBoard(boolean updateleaderBoard) {
		this.updateleaderBoard = updateleaderBoard;
	}

	public boolean isUpdateItemLists() {
		return updateItemLists;
	}

	public void setUpdateItemLists(boolean updateItemLists) {
		this.updateItemLists = updateItemLists;
	}

	public void setSettings(PREF_KEY_NAMES prefKeyName, Object prefKeyValue) {

		if (prefKeyValue != null) {

			cabbagePrefs = getSharedPreferences(PREFS_NAME,0);
			SharedPreferences.Editor editor = cabbagePrefs.edit();

			if (prefKeyName == PREF_KEY_NAMES.USERNAME) {
				editor.putString(PREF_NAME_USERNAME, prefKeyValue.toString());
			}

			if (prefKeyName == PREF_KEY_NAMES.EMAILADDRESS) {
				editor.putString(PREF_NAME_EMAIL, prefKeyValue.toString());
			}

			if (prefKeyName == PREF_KEY_NAMES.PLAYERID) {
				editor.putInt(PREF_NAME_PLAYERID, (Integer)prefKeyValue);
			}

			if (prefKeyName == PREF_KEY_NAMES.ACCOUNTID) {
				editor.putInt(PREF_NAME_ACCOUNTID, (Integer)prefKeyValue);
			}

			if (prefKeyName == PREF_KEY_NAMES.FIRSTNAME) {
				editor.putString(PREF_NAME_FIRSTNAME, prefKeyValue.toString());
			}

			if (prefKeyName == PREF_KEY_NAMES.LASTNAME) {
				editor.putString(PREF_NAME_LASTNAME, prefKeyValue.toString());
			}

			if (prefKeyName == PREF_KEY_NAMES.AVAILABLE) {
				editor.putBoolean(PREF_NAME_AVAILABLE, (Boolean)prefKeyValue);
			}
			
			if (prefKeyName == PREF_KEY_NAMES.JAFFA) {
				editor.putString(PREF_NAME_JAFFA, prefKeyValue.toString());
			}

			editor.commit();
		}
	}

	public List<CollectibleBean> getCollectedByPlayerBeans() {
		return collectedByPlayerBeans;
	}

	public void setCollectedByPlayerBeans(
			List<CollectibleBean> collectedByPlayerBeans) {
		this.collectedByPlayerBeans = collectedByPlayerBeans;
		if (collectedByPlayerBeans != null) {
			collectedByPlayerNames = new ArrayList<String>();
			for (CollectibleBean c : collectedByPlayerBeans) {
				collectedByPlayerNames.add(c.getCollectiblename());
			}
		}
	}

	public List<String> getCollectedByPlayerNames() {
		return collectedByPlayerNames;
	}

	public Object getSettings(PREF_KEY_NAMES prefKeyName, Object defaultValue) {

		if (prefKeyName != null) {

			cabbagePrefs = getSharedPreferences(PREFS_NAME,0);

			if (prefKeyName == PREF_KEY_NAMES.USERNAME) {
				return cabbagePrefs.getString(PREF_NAME_USERNAME, defaultValue.toString());
			}

			if (prefKeyName == PREF_KEY_NAMES.EMAILADDRESS) {
				return cabbagePrefs.getString(PREF_NAME_EMAIL, defaultValue.toString());
			}

			if (prefKeyName == PREF_KEY_NAMES.PLAYERID) {
				return cabbagePrefs.getInt(PREF_NAME_PLAYERID, (Integer) defaultValue);
			}

			if (prefKeyName == PREF_KEY_NAMES.ACCOUNTID) {
				return cabbagePrefs.getInt(PREF_NAME_ACCOUNTID, (Integer) defaultValue);
			}

			if (prefKeyName == PREF_KEY_NAMES.FIRSTNAME) {
				return cabbagePrefs.getString(PREF_NAME_FIRSTNAME, defaultValue.toString());
			}

			if (prefKeyName == PREF_KEY_NAMES.LASTNAME) {
				return cabbagePrefs.getString(PREF_NAME_LASTNAME, defaultValue.toString());
			}

			if (prefKeyName == PREF_KEY_NAMES.AVAILABLE) {
				return cabbagePrefs.getBoolean(PREF_NAME_AVAILABLE, (Boolean) defaultValue);
			}
			
			if (prefKeyName == PREF_KEY_NAMES.JAFFA) {
				return cabbagePrefs.getString(PREF_NAME_JAFFA, defaultValue.toString());
			}

		}

		return null;

	}

	//	public int getPlayerScore() {
	//		int returnScore = 0;
	//		for (LeaderBoardBean b : leaderBoardList) {
	//			if (b.getPlayer_id() == (Integer) getSettings(PREF_KEY_NAMES.PLAYERID,0)) {
	//				returnScore = b.getPoints();
	//			}
	//		}
	//		return returnScore;
	//	}

	public Map<Integer, CollectibleBean> getCollectibleResourceIDAndBeanMap() {
		return collectibleResourceIDAndBeanMap;
	}

	public void setCollectibleResourceIDAndBeanMap(
			Map<Integer, CollectibleBean> collectibleResourceIDAndBeanMap) {
		this.collectibleResourceIDAndBeanMap = collectibleResourceIDAndBeanMap;
	}

	public ArrayList<Integer> getItemRefArray() {
		return itemRefArray;
	}

	public void setItemRefArray(ArrayList<Integer> itemRefArray) {
		this.itemRefArray = itemRefArray;
	}

	public AccountBean getAccountBean(){
		return ab;
	}

	public void setAccountBean(AccountBean incomingAB){
		this.ab = incomingAB;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public List<LeaderBoardBean> getLeaderBoardList() {
		return leaderBoardList;
	}

	public void setLeaderBoardList(List<LeaderBoardBean> leaderBoardList) {
		this.leaderBoardList = leaderBoardList;
	}

	public List<CollectibleBean> getCollectibleList() {
		return collectibleList;
	}

	public void setCollectibleList(List<CollectibleBean> collectibleList) {

		//this.collectibleList = collectibleList;
		this.collectibleList = sortCollectibleList(collectibleList);
	}

	private List<CollectibleBean> sortCollectibleList(List<CollectibleBean> collectibleList) {
		Set<String> s = new TreeSet<String>();
		for (CollectibleBean cb : collectibleList) {
			s.add(cb.getCollectiblename());
		}
		List<String> l = new ArrayList<String>(s);

		//collectedByPlayerNames = l;

		List<CollectibleBean> newList = new ArrayList<CollectibleBean>();
		for (String ss : l) {

			for (CollectibleBean c : collectibleList) {
				if (ss.equals(c.getCollectiblename())) {
					newList.add(c);
					break;
				}
			}

		}


		return newList;
	}

	public void buildItemNameList(){
		collectibleImageMap = new HashMap<Integer,String>();
		itemRefArray = new ArrayList<Integer>(); 
		collectibleResourceIDAndBeanMap = new HashMap<Integer,CollectibleBean>();

		//		Log.d("cblist", Integer.toString(collectibleList.size()));
		//		Log.d("*** item at cblist 0", collectibleList.get(0).getCollectiblename());

		for (int x = 0; x < collectibleList.size(); x++){

			String s = collectibleList.get(x).getImagefilename();
			String s2 = s.substring(0,(s.length()-4));
			String item = "@drawable/"+s2;
			int imageResource = getResources().getIdentifier(item, null, getPackageName());

			CollectibleBean currentBean = collectibleList.get(x);
			//Log.d("doodlevvvvvvvv",s);

			if (imageResource != 0){
				//				Log.d("doodle",s);
				//				Log.d("doodle2",(Integer.toString(imageResource)));
				collectibleImageMap.put(imageResource,s );
				//Log.d("Doodledoo",Integer.toString(x));
				collectibleResourceIDAndBeanMap.put(imageResource,currentBean);
				//itemRefArray.set(x, imageResource);
				itemRefArray.add(imageResource);
			} else {
				s = "noimageavailable.png";


				s2 = s.substring(0,(s.length()-4));
				item = "@drawable/"+s2;
				//Log.d("item",item);
				imageResource = getResources().getIdentifier(item, null, getPackageName());
				//				Log.d("doodle",s);
				//				Log.d("doodle2",(Integer.toString(imageResource)));
				//				Log.d("Doodledoo",Integer.toString(x));
				collectibleImageMap.put(imageResource, s);
				collectibleResourceIDAndBeanMap.put(imageResource,currentBean);
				itemRefArray.add(imageResource);

			}
			//Log.d("doodley", Integer.toString(collectibleResourceIDAndBeanMap.size()));
		}
		//String item = "@drawable/myresource.png";



		//		imageview= (ImageView)findViewById(R.id.imageView);
		//		Drawable res = getResources().getDrawable(imageResource);
		//		imageView.setImageDrawable(res);
	}
	// set
	//((MyApplication) this.getApplication()).setSomeVariable("foo");



	public Map<Integer,String> getCollectibleImageMap() {
		return collectibleImageMap;
	}

	public void setCollectibleImageMap(Map<Integer,String> collectibleImageMap) {
		this.collectibleImageMap = collectibleImageMap;
	}

	// get
	//String s = ((MyApplication) this.getApplication()).getSomeVariable();

}
