package se.chalmers.student.aviato;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gryphex on 2017-04-24.
 */

public class NotificationActivity extends Activity {

    ListView mNotificationListView;

    //TODO: onListViewItemSelected method to enable removal of notifications

    //TODO: Have items bolded if they aren't read

    //TODO: "Mark all notifications as read" button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        // The listview to populate
        mNotificationListView = (ListView) findViewById(R.id.lvNotifications);
        List<String> mList = new ArrayList<>();

        // TODO: Fill mList with the notifications existing in the database (currently under develpment)

        NotificationArrayAdapter listAdapter =
                new NotificationArrayAdapter(NotificationActivity.this,
                        R.layout.notification_list,
                        mList);
        mNotificationListView.setAdapter(listAdapter);
    }

    @Override
    public void onStart(){
        super.onStart();

        mNotificationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                NotificationActivity selItem = (NotificationActivity) adapter.getItemAtPosition(position);

            }
        });
    }
}
