package se.chalmers.student.aviato.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import se.chalmers.student.aviato.R;


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
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String defaultValue = "10";
        int counter = 0;
        int listLimit = Integer.valueOf(sharedPreferences.getString("list_notifications_limit", defaultValue));
        Notification n = mItems.get(position);
        if(n != null && counter < listLimit) {
            String fId = n.getFlightId();
            String nText = n.getText();
            text.setText(fId + " " + nText);

            // Set the design for the notification
            if (n.getRead() == NotificationActivity.NOTIFICATION_NOT_READ) {
                text.setBackgroundColor(mContext.getResources().getColor(R.color.colorFlightItem));
                text.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                text.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));
            }
            counter++;
        }

        return mView;
    }

}
