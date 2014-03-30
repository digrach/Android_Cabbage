// playcabbage android app
// Copyright Chisholm Institite - Diploma of Digital and Interactive Games ICA50211 2013
// Trainer: Rachael Colley - rachael.colley@chisholm.edu.au

package playcabbage.chisholm.app.json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import playcabbage.chisholm.app.bean.AccountBean;
import playcabbage.chisholm.app.bean.CollectibleBean;
import playcabbage.chisholm.app.bean.LeaderBoardBean;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


public class JsonFactory {

	/////////////////////////////
	public String toAccountBeanJson(AccountBean accountBean) {
		AccountBean obj = accountBean;
		Gson gson = new Gson();
		String j = gson.toJson(obj);
		return j;
	}
	public AccountBean fromAccountBeanJson(String accountBeanJson) {
		Gson gson = new Gson();
		AccountBean ab = gson.fromJson(accountBeanJson, AccountBean.class);
		return ab;
	}
	/////////////////////////////

	//////////////////////////////
	public String toLeaderBoardBeanListJson(List<LeaderBoardBean> listOfLeaderBoardBeanJson) {
		String j = null;
		Gson gson = new Gson();
		j = gson.toJson(listOfLeaderBoardBeanJson);
		return j;
	}
	public List<LeaderBoardBean> fromLeaderBoardBeanListJson(String leaderBoardBeanListJson) {
		ArrayList<LeaderBoardBean> l = new ArrayList<LeaderBoardBean>();
		Type type = new TypeToken<Collection<LeaderBoardBean>>(){}.getType();
		Gson gson = new Gson();
		l = gson.fromJson(leaderBoardBeanListJson,type);
		return l;
	}
	///////////////////////////////

	////////////////////////////////
	public String toCollectibleBeanRealListJson(List<CollectibleBean> listOfCollectibleBean) {
		String j = null;
		Gson gson = new Gson();
		j = gson.toJson(listOfCollectibleBean);
		return j;
	}
	public List<CollectibleBean> fromCollectibleBeanRealListJson(String collectibleBeanListJson) {
		ArrayList<CollectibleBean> l = new ArrayList<CollectibleBean>();
		Type type = new TypeToken<Collection<CollectibleBean>>(){}.getType();
		Gson gson = new Gson();
		l = gson.fromJson(collectibleBeanListJson,type);
		return l;
	}
	///////////////////////////////////

	///////////////////////////////////
	public String toCollectibleBeanJson(CollectibleBean collectibleBean) {
		
		CollectibleBean obj = collectibleBean;
		Gson gson = new Gson();
		String j = gson.toJson(obj);
		return j;
	}
	public CollectibleBean fromCollectibleBeanJson(String collectibleBeanJson) {
		Gson gson = new Gson();
		CollectibleBean cb = gson.fromJson(collectibleBeanJson, CollectibleBean.class);
		return cb;
	}
	////////////////////////////////////

	////////////////////////////////////
	public String toBooleanJson(boolean b) {
		String j = null;
		Gson gson = new Gson();
		j = gson.toJson(b);
		return j;
	}
	public boolean fromBooleanJson(String booleanJson) {
		Boolean b = null;
		Gson gson = new Gson();
		b = gson.fromJson(booleanJson,boolean.class);
		return b;
	}
	/////////////////////////////////////////

	///////////////////////////////////////
	public String toIntJson(int i) {
		String j = null;
		Gson gson = new Gson();
		j = gson.toJson(i);
		return j;
	}
	public int fromIntJson(String intJson) {
		int i = 0;
		Gson gson = new Gson();
		i = gson.fromJson(intJson,int.class);
		return i;
	}
	///////////////////////////////////////////////

	///////////////////////////////////////
	public String toIntArrayJson(int[] i) {
		String j = null;
		Gson gson = new Gson();
		j = gson.toJson(i);
		return j;
	}
	public String toIntArrayJson(String[] i) {
		String j = null;
		Gson gson = new Gson();
		j = gson.toJson(i);
		return j;
	}
	public int[] fromIntArrayJson(String intArrayJson) {
		int i[] = null;
		Gson gson = new Gson();
		i = gson.fromJson(intArrayJson,int[].class);
		return i;
	}
	///////////////////////////////////////////////
	
	///////////////////////////////////////////////////
	public String[] fromStringArrayJson(String stringArrayJson) {
		String s[] = null;
		Gson gson = new Gson();
		s = gson.fromJson(stringArrayJson,String[].class);
		return s;
	}
	
	public String toStringArrayJson(String[] stringArray) {
		String j = null;
		Gson gson = new Gson();
		j = gson.toJson(stringArray);
		return j;
	}
	//////////////////////////////////////////////////////////////

	public String fromStringJson(String stringJson) {
		String s = null;
		Gson gson = new Gson();
		s = gson.fromJson(stringJson,String.class);
		return s;
	}
	public String toStringJson(String string) {
		String j = null;
		Gson gson = new Gson();
		j = gson.toJson(string);
		return j;
	}





	
}