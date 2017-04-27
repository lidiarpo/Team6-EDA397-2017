package se.chalmers.student.aviato;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import se.chalmers.student.aviato.DB.NotificationsCRUD;
import se.chalmers.student.aviato.DB.NotificationsDbHelper;

/**
 * Created by gryphex on 2017-04-24.
 */

public class NotificationActivity extends Activity {

    public static final int NOTIFICATION_READ = 1;
    public static final int NOTIFICATION_NOT_READ = 0;

    private ListView mNotificationListView;
    private NotificationsCRUD mNotCrud;
    private Notification mSelItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        // The listview to populate
        mNotificationListView = (ListView) findViewById(R.id.lvNotifications);

        NotificationsDbHelper dbHelper = new NotificationsDbHelper(this);
        mNotCrud = new NotificationsCRUD(dbHelper);

        // Fill the list with notifications in the database
        List<Notification> mList = mNotCrud.readNotifications();

        NotificationArrayAdapter listAdapter =
                new NotificationArrayAdapter(NotificationActivity.this,
                        R.layout.notification_list,
                        mList);
        mNotificationListView.setAdapter(listAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        mNotificationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                mSelItem = (Notification) adapter.getItemAtPosition(position);

                TextView text = (TextView) v.findViewById(R.id.tvNotifications);

                // Change the design of the notification if pressed
                if (mSelItem.getRead() == NOTIFICATION_NOT_READ) {
                    text.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorWhite));
                    text.setTypeface(Typeface.DEFAULT);
                    mSelItem.setRead(NOTIFICATION_READ);
                    mNotCrud.updateNotification(mSelItem);
                } else {
                    text.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorFlightItem));
                    text.setTypeface(Typeface.DEFAULT_BOLD);
                    mSelItem.setRead(NOTIFICATION_NOT_READ);
                    mNotCrud.updateNotification(mSelItem);
                }
            }
        });

        mNotificationListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mSelItem = (Notification) parent.getItemAtPosition(position);

                // Remove the notification if read and long-pressed
                if (mSelItem.getRead() == NOTIFICATION_READ){
                    mNotCrud.deleteNotification(mSelItem.getFlightId());
                    restart();
                    return true;
                }

                return false;
            }
        });
    }

    

    /**
     * Method to restart the activity.
     */
    public void restart() {
        this.recreate();
    }
}
