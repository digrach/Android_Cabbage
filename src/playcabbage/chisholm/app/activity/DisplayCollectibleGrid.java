// playcabbage android app
// Copyright Chisholm Institite - Diploma of Digital and Interactive Games ICA50211 2013
// Trainer: Rachael Colley - rachael.colley@chisholm.edu.au

package playcabbage.chisholm.app.activity;

import playcabbage.chisholm.app.Cabbage.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DisplayCollectibleGrid extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_collectible_grid);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.display_collectible_grid, menu);
		return true;
	}
	
	
}
