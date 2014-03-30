// playcabbage android app
// Copyright Chisholm Institite - Diploma of Digital and Interactive Games ICA50211 2013
// Trainer: Rachael Colley - rachael.colley@chisholm.edu.au

package playcabbage.chisholm.app.aSyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import playcabbage.chisholm.app.activity.CheckinActivity;
import playcabbage.chisholm.app.activity.ItemSummaryActivity;
import playcabbage.chisholm.app.activity.MainActivity;
import playcabbage.chisholm.app.bean.CollectibleBean;
import playcabbage.chisholm.app.json.JsonFactory;
import playcabbage.chisholm.app.settings.CabbageApp;
import playcabbage.chisholm.app.settings.CabbageApp.PREF_KEY_NAMES;


import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

public class CreateNewJaffaAppKeyTask extends AsyncTask<Void,Void,String> {
	private String jaffaappkey;
	
	private Activity initiatingActivity;

	public CreateNewJaffaAppKeyTask(Activity initiatingActivity) {
		jaffaappkey = (String)((CabbageApp)initiatingActivity.getApplication()).getSettings(PREF_KEY_NAMES.JAFFA, "none");
		this.initiatingActivity = initiatingActivity;
	}

	@Override
	protected String doInBackground(Void... arg0) {
		//Log.d("START CreateNewJaffaAppKey", "START CreateNewJaffaAppKey");

		StringBuilder str = new StringBuilder();
		String url = "http://playcabbage.chisholm.edu.au/rest/jaffaauth/newjaffaauth";
		HttpClient client = new DefaultHttpClient();
		/// POST ///
		HttpPost post = new HttpPost(url);
		List<NameValuePair> nvList = new ArrayList<NameValuePair>();
		BasicNameValuePair bnvp1 = new BasicNameValuePair("jaffaappkey", jaffaappkey);
		nvList.add(bnvp1);
		try {
			post.setEntity(new UrlEncodedFormEntity(nvList));
			HttpResponse resp = client.execute(post);
			InputStream is  = resp.getEntity().getContent();          
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while((line = reader.readLine()) != null) {
				str.append(line);
			}
			is.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//Log.d("CreateNewJaffaAppKey", str.toString());
		String returnResponse = str.toString();
		return returnResponse;
	}

	@Override
	protected void onPostExecute(String s) {

		JsonFactory jf = new JsonFactory();
		boolean success = jf.fromBooleanJson(s);

		// Now return to the onAsyncTaskCompleted method in the initiating IAsyncHelper activity....
		MainActivity activityInstanceName = (MainActivity)initiatingActivity;
		activityInstanceName.onAsyncTaskComplete(success, "jaffaappkey");

	}


}
