package se.chalmers.student.aviato;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.StringTokenizer;

import se.chalmers.student.aviato.DB.FlightsDbHelper;
import se.chalmers.student.aviato.DB.SubscriptionsCRUD;

import static android.R.id.message;


public class OverviewActivity extends Activity {

    private String flight;
    HashMap<String, String> data;
    FlightsDbHelper mDbHelper;
    SubscriptionsCRUD subscriptionsCRUD;



    TextView tvAirlineName;
    TextView tvStatus;
    TextView tvFlightNumber;
    TextView tvSource;
    TextView tvDepFrom;
    TextView tvDepTime;
    TextView tvDepGate;
    TextView tvDepTerminal;
    TextView tvDestination;
    TextView tvArrFrom;
    TextView tvArrTime;
    TextView tvArrGate;
    TextView tvArrTerminal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.flight_overview);
        createView();



        final Button btnSubscription = (Button) findViewById(R.id.btnSubscribe);
        btnSubscription.setClickable(true);
        btnSubscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "You are Subscribed to this flight";
                int duration = Toast.LENGTH_SHORT;

                // Perform action on click
                if(v.getId() == R.id.btnSubscribe)
                {
                    //Log.d("Context value:","working");
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    mDbHelper = new FlightsDbHelper(context);
                    Log.d("Context value:", mDbHelper +"");
                    subscriptionsCRUD = new SubscriptionsCRUD(mDbHelper);
                    subscriptionsCRUD.addSubscription(getFlightObject());
                }

            }
        });

        Bundle extras = getIntent().getExtras();
        if(extras != null) {

            flight = extras.getString("flight");
            Log.d("Flight", flight);

            //tvAirlineName.setText("TEST!!!!");

            //HashMap<String, String> data = new HashMap<String, String>();
            data = new HashMap<String, String>();
            StringTokenizer tokenizer = new StringTokenizer(flight, ",");
            while(tokenizer.hasMoreTokens()) {
                StringTokenizer s = new StringTokenizer(tokenizer.nextToken(), "='");
                String attribute = s.nextToken();
                String value = s.nextToken();
                Log.d("Attribute", attribute);
                Log.d("Value", value);

                data.put(attribute, value);

            }

            populateOverview(data);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    private void createView() {

        tvAirlineName = (TextView) findViewById(R.id.tvAirlineName);
        tvStatus = (TextView) findViewById(R.id.tvStatus);
        tvFlightNumber = (TextView) findViewById(R.id.tvFlightNumber);
        tvSource= (TextView) findViewById(R.id.tvSource);
        tvDepFrom = (TextView) findViewById(R.id.tvDepFrom);
        tvDepTime = (TextView) findViewById(R.id.tvDepTime);
        tvDepGate = (TextView) findViewById(R.id.tvDepGate);
        tvDepTerminal = (TextView) findViewById(R.id.tvDepTerminal);
        tvDestination = (TextView) findViewById(R.id.tvDestination);
        tvArrFrom = (TextView) findViewById(R.id.tvArrFrom);
        tvArrTime = (TextView) findViewById(R.id.tvArrTime);
        tvArrGate = (TextView) findViewById(R.id.tvArrGate);
        tvArrTerminal = (TextView) findViewById(R.id.tvArrTerminal);
    }


    private void populateOverview(HashMap<String, String> flight) {

        tvAirlineName.setText(flight.get("carrierFsCode"));
        tvFlightNumber.setText(flight.get("flightNumber"));
        tvStatus.setText(flight.get("status"));
        tvSource.setText(flight.get("departureAirportFsCode"));
        //tvDepFrom.setText(flight.get("departureAirportName"));
        tvDepTime.setText(flight.get("departureDate"));
        tvDepGate.setText(flight.get("Flight{departureGate"));
        tvDepTerminal.setText(flight.get("departureTerminal"));
        tvDestination.setText(flight.get("arrivalAirportFsCode"));
        //tvArrFrom.setText(flight.get("ArrivalAirportName"));
       tvArrTime.setText(flight.get("arrivalDate"));
       tvArrGate.setText(flight.get("arrivalGate"));
       tvArrTerminal.setText(flight.get("arrivalTerminal"));

    }
    public Flight getFlightObject(){
        Flight flightOverview = new Flight();
        flightOverview.set("flightId",data.get("flightId"));
        flightOverview.set("carrierFsCode",data.get("carrierFsCode"));
        flightOverview.set("flightNumber",data.get("flightNumber"));
        flightOverview.set("departureAirportFsCode",data.get("departureAirportFsCode"));
        flightOverview.set("arrivalAirportFsCode",data.get("arrivalAirportFsCode"));
        flightOverview.set("departureDate",data.get("departureDate"));
        flightOverview.set("arrivalDate",data.get("arrivalDate"));
        flightOverview.set("status",data.get("status"));
        flightOverview.set("flightType",data.get("flightType"));
        flightOverview.set("flightDurations",data.get("flightDurations"));
        flightOverview.set("departureTerminal",data.get("departureTerminal"));
        flightOverview.set("departureGate",data.get("Flight{departureGate"));
        flightOverview.set("arrivalTerminal",data.get("arrivalTerminal"));
        flightOverview.set("arrivalGate",data.get("arrivalGate"));


        return flightOverview;

    }

}
