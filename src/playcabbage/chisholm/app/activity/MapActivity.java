package playcabbage.chisholm.app.activity;

import playcabbage.chisholm.app.Cabbage.R;
import playcabbage.chisholm.app.aSyncTask.GetMapDataTask;
import playcabbage.chisholm.app.iHelper.IAsyncHelper;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MapActivity extends Activity implements IAsyncHelper{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		getMapData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

	private void getMapData() {
		GetMapDataTask mapTask = new GetMapDataTask(0,this);
		mapTask.execute();
	}

	@Override
	public void onAsyncTaskComplete(Object returnFromTaskObject,
			String returnDataTag) {
		if(returnDataTag.equals("mapdata")) {

			String[] dataArray = (String[])returnFromTaskObject;
			//Log.d("DataArray length", Integer.toString(dataArray.length));
			startMap(dataArray);

		}
	}

	private void startMap(String[] mapData) {
		int[] CategoryColors  = {0xFF000000,0xFFB0171F,0xFFFF3E96,0xFF0000FF,0xFF00BFFF,0xFF00C957};
		String[] catShortNames = {"c","r","e","p","b","n"};
		int[] colorArray = new int[mapData.length];

		for (int x = 0; x < mapData.length; x ++) {

			if (mapData[x] != null) {
				
				for (int i = 0; i < catShortNames.length; i ++) {

					if (mapData[x].equals(catShortNames[i])) {
						colorArray[x] = CategoryColors[i];
					}
				} 
				
			}  else {
//				colorArray[x] = 0x00FFFFFF;
				colorArray[x] = 0xFF7F00FF;
			}
		}
		
		//Log.d("colorArray length", Integer.toString(colorArray.length));

		ImageView iv = (ImageView)findViewById(R.id.iv);

		int panelWidth = 153;
		int panelHeight = 88;

		Bitmap bitmap = Bitmap.createBitmap(panelWidth, panelHeight, Bitmap.Config.ARGB_8888);
		bitmap.setPixels(colorArray, 0, panelWidth, 0, 0, panelWidth, panelHeight);

		iv.setDrawingCacheEnabled(true);
		iv.setAdjustViewBounds(true);
		iv.setVisibility(View.VISIBLE);
		//iv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		iv.setImageBitmap(Bitmap.createScaledBitmap(bitmap, panelWidth*2, panelHeight*2, false));

//		ImageView popView = new ImageView(this);
//		LayoutParams lp = new LayoutParams(10, 10);
//		this.addContentView(popView, lp);
		
		
		

	}
	
	private void makeMap(String[] mapData) {
		int[] CategoryColors  = {0xFF000000,0xFFB0171F,0xFFFF3E96,0xFF0000FF,0xFF00BFFF,0xFF00C957};
		String[] catShortNames = {"c","r","e","p","b","n"};
		int[] colorArray = new int[mapData.length];

		for (int x = 0; x < mapData.length; x ++) {

			if (mapData[x] != null) {
				
				for (int i = 0; i < catShortNames.length; i ++) {

					if (mapData[x].equals(catShortNames[i])) {
						colorArray[x] = CategoryColors[i];
					}
				} 
				
			}  else {
				
				colorArray[x] = 0x00FFFFFF;
			}
		}
	}

}
