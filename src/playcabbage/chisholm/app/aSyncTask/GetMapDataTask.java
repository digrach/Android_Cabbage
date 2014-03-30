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

import playcabbage.chisholm.app.activity.MapActivity;
import playcabbage.chisholm.app.json.JsonFactory;


import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

public class GetMapDataTask extends AsyncTask<Void,Void,String> {

	private String levelNumber;
	private Activity initiatingActivity;

	public GetMapDataTask(int levelNumber, Activity initiatingActivity) {
		this.levelNumber = Integer.toString(levelNumber);
		this.initiatingActivity = initiatingActivity;
	}

	@Override
	protected String doInBackground(Void... arg0) {
		//Log.d("START GetMapDataTaskTask", "START GetMapDataTaskTask");

		StringBuilder str = new StringBuilder();
		String url = "http://playcabbage.chisholm.edu.au/rest/maps/getmapascellcategoryshortnames";
		HttpClient client = new DefaultHttpClient();

		List<NameValuePair> nvList = new ArrayList<NameValuePair>();
		BasicNameValuePair bnvp1 = new BasicNameValuePair("levelnumber", levelNumber);
		nvList.add(bnvp1);
		//Log.d("Getting by levelnumber ", levelNumber);

		/// POST ///
		HttpPost post = new HttpPost(url);

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
		//Log.d("GetMapDataTaskTask", str.toString());
		String returnResponse = str.toString();
		return returnResponse;
	}

	@Override
	protected void onPostExecute(String s) {

		JsonFactory jf = new JsonFactory();
		String stringArray[] = jf.fromStringArrayJson(s);

		// Now return to the onAsyncTaskCompleted method in the initiating IAsyncHelper activity....
		     MapActivity activityInstanceName = (MapActivity)initiatingActivity;
		     activityInstanceName.onAsyncTaskComplete(stringArray, "mapdata");

	}


}
