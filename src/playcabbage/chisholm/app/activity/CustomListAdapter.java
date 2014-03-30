package playcabbage.chisholm.app.activity;

import java.util.List;

import android.R;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter {

    private Context mContext;
    private int iid;
    private List <String>items ;

    public CustomListAdapter(Context context, int textViewResourceId , List<String> list ) 
    {
    	
        super(context, textViewResourceId, list);           
        mContext = context;
        iid = textViewResourceId;
        items = list ;
        
       // Log.d(getClass().getSimpleName() + " CustomListAdapter", "HERE");
        //Log.d(getClass().getSimpleName() + " list size:", Integer.toString(items.size()));
    }

    @Override
    public View getView(int position, View v, ViewGroup parent)
    {
    	
    	//Log.d(getClass().getSimpleName() + " getView", "HERE");
        //View mView = v ;
        if(v == null){
           // LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	//LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        	v = LayoutInflater.from(mContext)
//					.inflate(R.layout.ac, parent, false);
        	
        	
        	//v = vi.inflate(iid, null);
        	
        	// TextView text = (TextView) v.findViewById(R.id.textView);
        	
//        	LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			v = inflater.inflate(R.layout.activity_list_item, null);
        }

        //TextView text = (TextView) mView.findViewById(iid);
       // TextView text = (TextView) v.findViewById(R.id.);
       // TextView text = (TextView) v.findViewById(iid.textView);

//        if(items.get(position) != null )
//        {
//            text.setTextColor(Color.WHITE);
//           
//            text.setText("bla" + items.get(position));
//            text.setBackgroundColor(Color.RED); 
//            int color = Color.argb( 200, 255, 64, 64 );
//                text.setBackgroundColor( color );
//
//        }

        return v;
    }

}
