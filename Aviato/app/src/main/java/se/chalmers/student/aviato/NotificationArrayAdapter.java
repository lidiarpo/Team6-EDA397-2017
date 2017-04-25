package se.chalmers.student.aviato;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


/**
 * Created by gryphex on 2017-04-25.
 */

public class NotificationArrayAdapter extends ArrayAdapter<String>{

    private Context mContext;
    private int mId;
    private List<String> mItems ;

    public NotificationArrayAdapter(Context context, int textViewResourceId , List<String> list) {
        super(context, textViewResourceId, list);
        mContext = context;
        mId = textViewResourceId;
        mItems = list ;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        View mView = v;
        if(mView == null){
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(mId, null);
        }
        Log.w("View", "In the getView method");
        TextView text = (TextView) mView.findViewById(R.id.tvNotifications);
        if(mItems.get(position) != null )
        {
            text.setText(mItems.get(position));
            text.setBackgroundColor(mContext.getResources().getColor(R.color.colorFlightItem));

        }

        return mView;
    }

}
