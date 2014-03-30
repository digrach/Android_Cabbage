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

import playcabbage.chisholm.app.bean.CollectibleBean;
import playcabbage.chisholm.app.json.JsonFactory;


import android.app.Activity;
import android.os.AsyncTask;

public class GetLiveCollectiblesAtCheckpointTask extends AsyncTask<Void,Void,String> {

	private Activity initiatingActivity;
	private String superid;
	private int checkpointid;

	public GetLiveCollectiblesAtCheckpointTask(String superid, Activity initiatingActivity) {
		this.superid = superid;
		this.initiatingActivity = initiatingActivity;
	}

	public GetLiveCollectiblesAtCheckpointTask(int checkpointid, Activity initiatingActivity) {
		this.checkpointid = checkpointid;
		this.initiatingActivity = initiatingActivity;
	}

	@Override
	protected String doInBackground(Void... arg0) {
		//Log.d("START GetLiveCollectiblesAtCheckpointTask", "START GetLiveCollectiblesAtCheckpointTask");

		StringBuilder str = new StringBuilder();
		String url; 
		HttpClient client = new DefaultHttpClient();

		List<NameValuePair> nvList = new ArrayList<NameValuePair>();

		if (superid != null) {
			BasicNameValuePair bnvp1 = new BasicNameValuePair("superid", superid);
			nvList.add(bnvp1);
			url = "http://playcabbage.chisholm.edu.au/rest/livecollectibles/getbyurlstring";
			//Log.d("Getting by superid ", superid);
		} else {
			String cpid = Integer.toString(checkpointid);
			BasicNameValuePair bnvp1 = new BasicNameValuePair("checkpointid", cpid);
			nvList.add(bnvp1);
			url = "http://playcabbage.chisholm.edu.au/rest/livecollectibles/getbycheckpointid";
			//Log.d("Getting by checkpointid ", cpid);
		}
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
		//Log.d("GetLiveCollectiblesAtCheckpointTask", str.toString());
		String returnResponse = str.toString();
		return returnResponse;
	}

	@Override
	protected void onPostExecute(String s) {

		JsonFactory jf = new JsonFactory();
		List<CollectibleBean> cbList = jf.fromCollectibleBeanRealListJson(s);

		// Now return to the onAsyncTaskCompleted method in the initiating IAsyncHelper activity....
		//     NextActivityType activityInstanceName = (NextActivityType)initiatingActivity;
		//     activityInstanceName.onAsyncTaskComplete(cbList, "livecollectiblesatcheckpoint");

	}


}
