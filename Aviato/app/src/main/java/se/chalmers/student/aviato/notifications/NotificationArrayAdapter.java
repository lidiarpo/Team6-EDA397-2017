package se.chalmers.student.aviato.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import se.chalmers.student.aviato.DB.FlightsDbHelper;
import se.chalmers.student.aviato.DB.SubscriptionsCRUD;
import se.chalmers.student.aviato.R;
import se.chalmers.student.aviato.flights.Flight;


public class NotificationArrayAdapter extends ArrayAdapter<Notification>{

    private Context mContext;
    private int mId;
    private List<Notification> mItems ;
    private static String TAG = "NotificationArrayAdapt";
    private FlightsDbHelper mDbHelper;
    private SubscriptionsCRUD subscriptionsCRUD;

    public NotificationArrayAdapter(Context context, int textViewResourceId , List<Notification> list) {
        super(context, textViewResourceId, list);
        mContext = context;
        mId = textViewResourceId;
        mItems = list ;
        mDbHelper = new FlightsDbHelper(mContext);
        subscriptionsCRUD = new SubscriptionsCRUD(mDbHelper);
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
            Flight flight = subscriptionsCRUD.retrieveFlight(fId);
            if (flight!=null) {
                text.setText(flight.get("carrierFsCode") + flight.get("flightNumber") + " " + nText);
            }else{
                text.setText(nText);
            }

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
