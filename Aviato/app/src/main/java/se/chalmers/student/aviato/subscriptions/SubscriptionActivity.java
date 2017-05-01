package se.chalmers.student.aviato.subscriptions;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//For Testing Purposes
import se.chalmers.student.aviato.DB.FlightsDbHelper;
import se.chalmers.student.aviato.DB.SubscriptionsCRUD;
import se.chalmers.student.aviato.R;

public class SubscriptionActivity extends Activity {
    ListView subscriptionlistView;
    public ArrayList listOfSubscriptions;
    String flightIDtoDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        subscriptionlistView = (ListView) findViewById(R.id.lvSubscriptionContainer);
        //If the listView is empty then change the view
        this.subscriptionlistView.setEmptyView(findViewById(R.id.emptyElement_subscription));

        //Adding/Reading subscription
        FlightsDbHelper dbHelper = new FlightsDbHelper(this);
        SubscriptionsCRUD helpCrud = new SubscriptionsCRUD(dbHelper);

        // The listview to populate
        subscriptionlistView = (ListView) findViewById(R.id.lvSubscriptionContainer);
        List mList = helpCrud.readSubscriptions();
        listOfSubscriptions = new ArrayList(mList);


        SubscriptionAdapter adapter = new SubscriptionAdapter(this, listOfSubscriptions);
        subscriptionlistView.setAdapter(adapter);

    }
    //Method when delete subscription button is pressed
    public void onClick(View v) {
        //find the position of the flight in the view
        View parentRow = (View) v.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        String flightObjToString =  listOfSubscriptions.get(position).toString();

        Pattern pattern = Pattern.compile("flightId='(.*?)'");
        Matcher matcher = pattern.matcher(flightObjToString);
        if (matcher.find())
        {
            //System.out.println(matcher.group(1));
            flightIDtoDelete = matcher.group(1);
            //Delete subscription from helpdb
            FlightsDbHelper dbHelper2 = new FlightsDbHelper(this);
            SubscriptionsCRUD helpCrud2 = new SubscriptionsCRUD(dbHelper2);
            helpCrud2.deleteSubscription(flightIDtoDelete);

            //Refresh View after deletion
            List mList = helpCrud2.readSubscriptions();
            listOfSubscriptions = new ArrayList(mList);
            SubscriptionAdapter adapter = new SubscriptionAdapter(this, listOfSubscriptions);
            subscriptionlistView.setAdapter(adapter);
            Toast.makeText(SubscriptionActivity.this, "This Subscription has been removed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(SubscriptionActivity.this, "This Subscription could NOT been removed", Toast.LENGTH_SHORT).show();
        }

    }

}
