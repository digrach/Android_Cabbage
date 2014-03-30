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

import playcabbage.chisholm.app.activity.LoginActivity;
import playcabbage.chisholm.app.bean.AccountBean;
import playcabbage.chisholm.app.json.JsonFactory;
import playcabbage.chisholm.app.settings.CabbageApp;
import playcabbage.chisholm.app.settings.CabbageApp.PREF_KEY_NAMES;

import android.app.Activity;
import android.os.AsyncTask;

public class GetAccountAsynchTask extends AsyncTask<Void,Void,String> {
	private String jaffaappkey;

	private String email;
	private Activity activity;

	public GetAccountAsynchTask(String emailAddress,Activity activity){
		jaffaappkey = (String)((CabbageApp)activity.getApplication()).getSettings(PREF_KEY_NAMES.JAFFA, "none");
		
		email = emailAddress;
		this.activity = activity;
	}

	@Override
	protected String doInBackground(Void... params) {

		StringBuilder str = new StringBuilder();

		String url = "http://playcabbage.chisholm.edu.au/rest/accounts/getaccount";

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> nvList = new ArrayList<NameValuePair>();
		BasicNameValuePair bnvp1 = new BasicNameValuePair("emailaddress", email);
		BasicNameValuePair bnvp2 = new BasicNameValuePair("jaffaappkey", jaffaappkey);
		//Log.d("emailaddress",email);

		nvList.add(bnvp1);
		nvList.add(bnvp2);

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
			//Log.d("StringBuilder", str.toString());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		String returnResponse = str.toString();       
		//Log.d("ConvResponse", Boolean.toString(exists));

		return returnResponse;

	}
	@Override
	protected void onPostExecute(String s) {

		JsonFactory jf = new JsonFactory();
		AccountBean ab = jf.fromAccountBeanJson(s);
		
		// Now return to the onAsyncTaskCompleted method in the initiating IAsyncHelper activity....
		LoginActivity la = (LoginActivity)activity;
		//         Log.d("postex",Integer.toString(ab.getPlayerid()));
		//         Log.d("postex",ab.getUsername());
		la.onAsyncTaskComplete(ab, "accountbean");
	}





}
