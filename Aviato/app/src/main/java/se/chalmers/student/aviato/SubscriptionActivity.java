package se.chalmers.student.aviato;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import se.chalmers.student.aviato.DB.FlightsDbHelper;
import se.chalmers.student.aviato.DB.SubscriptionsCRUD;



public class SubscriptionActivity extends Activity {

    ListView subscriptionlistView;
    public ArrayList<Flight> listOfStrings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        subscriptionlistView = (ListView) findViewById(R.id.lvSubscriptionContainer);

        //Adding subscription to the helpDB for testing purposes
        FlightsDbHelper dbHelper = new FlightsDbHelper(this);
        SubscriptionsCRUD helpCrud = new SubscriptionsCRUD(dbHelper);


        // The listview to populate
        subscriptionlistView = (ListView) findViewById(R.id.lvSubscriptionContainer);
        List mList = helpCrud.readSubscriptions();
        listOfStrings = new ArrayList(mList);

        SubscriptionAdapter adapter = new SubscriptionAdapter(this, listOfStrings);
        subscriptionlistView.setAdapter(adapter);

    }

    public void onClick(View v) {
        //find the position of the subscription
        View parentRow = (View) v.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        Object subscriptionToDelete = listOfStrings.get(position);

        //TODO: find subscriptionToDelete flightID attribute Value
        //Object value = subscriptionToDelete.flightAttributes.get();
       // String flightIdValue = subscriptionToDelete.get("flightID");

        //TODO: Delete from database
        //FlightsDbHelper dbHelper2 = new FlightsDbHelper(this);
        //SubscriptionsCRUD helpCrud2 = new SubscriptionsCRUD(dbHelper2);
        //helpCrud2.deleteSubscription(flightIdValue);

        //TODO: Refresh View after deletion
        Toast.makeText(SubscriptionActivity.this, "This Subscription has been removed", Toast.LENGTH_SHORT).show();
    }

}
