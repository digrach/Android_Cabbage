// playcabbage android app
// Copyright Chisholm Institite - Diploma of Digital and Interactive Games ICA50211 2013
// Trainer: Rachael Colley - rachael.colley@chisholm.edu.au

package playcabbage.chisholm.app.utility;

import java.util.List;
import java.util.Map;

import playcabbage.chisholm.app.Cabbage.R;
import playcabbage.chisholm.app.bean.CollectibleBean;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	private Map<Integer,CollectibleBean> m;
	private Integer[] mThumbIds;
	private List<CollectibleBean> cbList;
	private List<Integer> imgRefArray;
	private List<String> collectedByPlayerNames;
	List<CollectibleBean> collectedByPlayerBeans;

	public ImageAdapter(Context c, 
			List<CollectibleBean> cBeanList, 
			List<Integer> imageRefArray, 
			List<CollectibleBean> collectedByPlayerBeanList, 
			List<String> collectedByPlayerNamesList) {
		
		mContext = c;
		cbList = cBeanList;
		imgRefArray = imageRefArray;
		mThumbIds = imgRefArray.toArray(new Integer[imgRefArray.size()]);
		collectedByPlayerBeans =  collectedByPlayerBeanList;
		collectedByPlayerNames = collectedByPlayerNamesList;
	}

	public int getCount() {
		return mThumbIds.length;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}


	//http://www.piwai.info/android-adapter-good-practices/
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {




		//Log.d("POSITION ", Integer.toString(position));

		ImageView bananaView;
		TextView phoneView;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext)
					.inflate(R.layout.activity_display_collectible_grid, parent, false);
			bananaView = (ImageView) convertView.findViewById(R.id.banana);
			phoneView = (TextView) convertView.findViewById(R.id.phone);
			convertView.setTag(R.id.banana, bananaView);
			convertView.setTag(R.id.phone, phoneView);
		} else {
			bananaView = (ImageView) convertView.getTag(R.id.banana);
			phoneView = (TextView) convertView.getTag(R.id.phone);
		}

		Integer iiiii = mThumbIds[position];

		String currentName = cbList.get(position).getCollectiblename();
		phoneView.setText(currentName);
		bananaView.setImageResource(mThumbIds[position]);


//		if (!collectedByPlayerNames.contains(currentName)) {
//			ColorMatrix cmat = new ColorMatrix();
//			cmat.setSaturation(0.00001f);
//			bananaView.setColorFilter(new ColorMatrixColorFilter(cmat));
//		}
		
		if (!collectedByPlayerNames.contains(currentName)) {
			ColorMatrix cmat = new ColorMatrix();
			//cmat.setSaturation(0.00001f);
			bananaView.setImageAlpha(50);
		}



		//		Integer iiiii = mThumbIds[position];
		//		phoneView.setText(cbList.get(position).getCollectiblename());
		//		bananaView.setImageResource(mThumbIds[position]);
		//		bananaView.setColorFilter(new ColorMatrixColorFilter(cmat));

		return convertView;
	}

}
