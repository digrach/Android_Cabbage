package playcabbage.chisholm.app.activity;

import playcabbage.chisholm.app.Cabbage.R;
import playcabbage.chisholm.app.Cabbage.R.layout;
import playcabbage.chisholm.app.Cabbage.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DisplayLeaderBoardListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_leader_board_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_leader_board_list,
				menu);
		return true;
	}

}
