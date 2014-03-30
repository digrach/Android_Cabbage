// playcabbage android app
// Copyright Chisholm Institite - Diploma of Digital and Interactive Games ICA50211 2013
// Trainer: Rachael Colley - rachael.colley@chisholm.edu.au

package playcabbage.chisholm.app.activity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

import playcabbage.chisholm.app.Cabbage.R;
import playcabbage.chisholm.app.aSyncTask.GetAccountAsynchTask;
import playcabbage.chisholm.app.bean.AccountBean;
import playcabbage.chisholm.app.iHelper.IAsyncHelper;
import playcabbage.chisholm.app.settings.CabbageApp;
import playcabbage.chisholm.app.settings.CabbageApp.PREF_KEY_NAMES;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity implements IAsyncHelper {

	/**
	 * A dummy authentication store containing known user names and passwords.
	 * TODO: remove after connecting to a real authentication system.
	 */
	private static final String[] DUMMY_CREDENTIALS = new String[] {
		"foo@example.com:hello", "bar@example.com:world" };

	/**
	 * The default email to populate the email field with.
	 */
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// Values for email and password at the time of the login attempt.
	private String mEmail;
	private String mPassword;

	// UI references.
	private EditText mEmailView;
	private EditText mPasswordView;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Log.d(getClass().getSimpleName() + "onCreate", "start");

		setContentView(R.layout.activity_login);

		// Set up the login form.
		mEmail = getIntent().getStringExtra(EXTRA_EMAIL);
		mEmailView = (EditText) findViewById(R.id.email);
		mEmailView.setText(mEmail);

		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordView
		.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int id,
					KeyEvent keyEvent) {
				if (id == R.id.login || id == EditorInfo.IME_NULL) {
					attemptLogin();
					return true;
				}
				return false;
			}
		});

		mLoginFormView = findViewById(R.id.login_form);
		mLoginStatusView = findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		//getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {

		///////// Stops the attempt being re-initiated if the user keeps clicking login.
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		mEmailView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		mEmail = mEmailView.getText().toString();
		mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			mPasswordView.setError(getString(R.string.error_field_required));
			focusView = mPasswordView;
			cancel = true;
		} else if (mPassword.length() < 4) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(mEmail)) {
			mEmailView.setError(getString(R.string.error_field_required));
			focusView = mEmailView;
			cancel = true;
		} else if (!mEmail.contains("@")) {
			mEmailView.setError(getString(R.string.error_invalid_email));
			focusView = mEmailView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
			showProgress(true);
			mAuthTask = new UserLoginTask(this,mEmail);
			mAuthTask.execute((Void) null);
		}
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
			.alpha(show ? 1 : 0)
			.setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mLoginStatusView.setVisibility(show ? View.VISIBLE
							: View.GONE);
				}
			});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
			.alpha(show ? 0 : 1)
			.setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mLoginFormView.setVisibility(show ? View.GONE
							: View.VISIBLE);
				}
			});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}


	}

	public void showLoginAttemptResult(Boolean accountExists) {
		LinearLayout ll = new LinearLayout(this);
		TextView label = new TextView(this);
		label.setText("account exists: " + accountExists);
		ll.addView(label);
		setContentView(ll);
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
		private String jaffaappkey;
		
		private Activity activity;
		private String userName;

		public UserLoginTask(Activity a, String uName) {
			jaffaappkey = (String)((CabbageApp)a.getApplication()).getSettings(PREF_KEY_NAMES.JAFFA, "none");
			activity = a;
			userName= uName;
		}

		@Override
		protected Boolean doInBackground(Void... params) {

			StringBuilder str = new StringBuilder();

			String url = "http://playcabbage.chisholm.edu.au/rest/accounts/verifyaccount";

			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			List<NameValuePair> nvList = new ArrayList<NameValuePair>();
			BasicNameValuePair bnvp1 = new BasicNameValuePair("emailaddress", mEmail);
			BasicNameValuePair bnvp2 = new BasicNameValuePair("password", mPassword);
			BasicNameValuePair bnvp3 = new BasicNameValuePair("jaffaappkey", jaffaappkey);

			// We can add more
			nvList.add(bnvp1);
			nvList.add(bnvp2);
			nvList.add(bnvp3);
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
			boolean exists = Boolean.parseBoolean(returnResponse);
			//Log.d("ConvResponse", Boolean.toString(exists));
			return exists; 
		}


		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			showProgress(false);

			if (success) {
				//Log.d(getClass().getSimpleName() + "onPostExecute", "returning from login");
				LoginActivity la = (LoginActivity)activity;
				la.onAsyncTaskComplete(success, "login");	
			} else {
				mPasswordView
				.setError(getString(R.string.error_incorrect_password));
				mPasswordView.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}

	@Override
	public void onAsyncTaskComplete(Object returnFromTaskObject,
			String returnDataTag) {

		//		Log.d(getClass().getSimpleName() + "onAsyncTaskComplete", "start");
		//		Log.d(getClass().getSimpleName() + "returnDataTag", returnDataTag.toString());
		//		Log.d(getClass().getSimpleName() + "returnFromTaskObject", returnFromTaskObject.toString());


		if (returnDataTag.equals("login")) {

			boolean success = (Boolean) returnFromTaskObject;
			if (success) {

				GetAccountAsynchTask asynctaskything = new GetAccountAsynchTask(mEmail,this);
				asynctaskything.execute();

			}
		}

		if (returnDataTag.equals("accountbean")) {
			AccountBean ab = (AccountBean) returnFromTaskObject;

			if (ab != null) {
				
				//Log.d(getClass().getSimpleName() + " Accountbean", "not null");

				((CabbageApp)this.getApplication()).setAccountBean(ab);
				((CabbageApp)this.getApplication()).setSettings(PREF_KEY_NAMES.EMAILADDRESS, mEmail);
				((CabbageApp)this.getApplication()).setSettings(PREF_KEY_NAMES.USERNAME, mEmail);
				((CabbageApp)this.getApplication()).setSettings(PREF_KEY_NAMES.PLAYERID, ab.getPlayerid());
				((CabbageApp)this.getApplication()).setSettings(PREF_KEY_NAMES.ACCOUNTID, ab.getAccountid());
				((CabbageApp)this.getApplication()).setSettings(PREF_KEY_NAMES.FIRSTNAME, ab.getFirstname());
				((CabbageApp)this.getApplication()).setSettings(PREF_KEY_NAMES.LASTNAME, ab.getLastname());
				((CabbageApp)this.getApplication()).setSettings(PREF_KEY_NAMES.AVAILABLE, ab.isAvailable());
				
//				String jaffaVal = Integer.toString(ab.getPlayerid()) + 
//				
//				((CabbageApp)this.getApplication()).setSettings(PREF_KEY_NAMES.JAFFA, ab.isAvailable());
				

				if(getIntent().getStringExtra("superid") != null) {
					
					//Log.d(getClass().getSimpleName() + " onAsyncTaskComplete has extras", getIntent().getStringExtra("superid"));
					
					Intent i = new Intent(this,CheckinActivity.class);
					i.putExtra("superid", getIntent().getStringExtra("superid"));
					startActivity(i);
					finish();
				} else {
					
					//Log.d(getClass().getSimpleName() + " onAsyncTaskComplete has no extras", "none");

					Intent myIntent = new Intent(this, PlayerSummary.class);
					myIntent.putExtra("username", mEmail);
					startActivity(myIntent);
					finish();
				}


			}

		}

	}
	
//	private void writeAuthKey() throws IOException {
//		
//		String e = (String)((CabbageApp)this.getApplication()).getSettings(PREF_KEY_NAMES.EMAILADDRESS,"none");
//		int l = e.length();
//		int m = e.length();
//		int r = l*m;
//		int k = r/2 * 1;
//		String s = e.substring(0,1) + Integer.toString(k);
//		
//		String FILENAME = "cabbagetxt";
//		String string = s;
//
//		FileOutputStream fos = openFileOutput(FILENAME, this.MODE_PRIVATE);
//		fos.write(string.getBytes());
//		fos.close();
//	}
}
