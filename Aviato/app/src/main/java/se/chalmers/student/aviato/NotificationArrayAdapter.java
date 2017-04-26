package se.chalmers.student.aviato;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import static se.chalmers.student.aviato.NotificationActivity.NOTIFICATION_NOT_READ;


/**
 * Created by gryphex on 2017-04-25.
 */

public class NotificationArrayAdapter extends ArrayAdapter<Notification>{

    private Context mContext;
    private int mId;
    private List<Notification> mItems ;

    public NotificationArrayAdapter(Context context, int textViewResourceId , List<Notification> list) {
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

        // Populate the view with one textView per notification in the database
        TextView text = (TextView) mView.findViewById(R.id.tvNotifications);
        Notification n = mItems.get(position);
        if(n != null ) {
            String fId = n.getFlightId();
            String nText = n.getText();
            text.setText(fId + " " + nText);

            // Set the design for the notification
            if (n.getRead() == NOTIFICATION_NOT_READ) {
                text.setBackgroundColor(mContext.getResources().getColor(R.color.colorFlightItem));
                text.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                text.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));
            }
        }

        return mView;
    }

}
