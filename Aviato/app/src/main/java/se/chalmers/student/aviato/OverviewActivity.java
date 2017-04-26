package se.chalmers.student.aviato;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.util.HashMap;
import java.util.StringTokenizer;


public class OverviewActivity extends Activity {

    private String flight;

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

        setContentView(R.layout.activity_overview);

        createView();

        final Button btnSubscription = (Button) findViewById(R.id.btnSubscribe);
        btnSubscription.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //TODO: Add action on click to Subscribe button


            }
        });


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            flight = extras.getString("flight");

            Log.d("Flight", flight);

            //tvAirlineName.setText("TEST!!!!");

            HashMap<String, String> data = new HashMap<String, String>();
            StringTokenizer tokenizer = new StringTokenizer(flight, ",");
            while (tokenizer.hasMoreTokens()) {
                StringTokenizer s = new StringTokenizer(tokenizer.nextToken(), "='");
                String attribute = s.nextToken();
                String value = s.nextToken();

                //  Log.d("Attribute", attribute);
                //  Log.d("Value", value);

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
        //tvFlightNumber = (TextView) findViewById(R.id.tvFlightNumber);
        tvSource = (TextView) findViewById(R.id.tvSource);
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

    //Populate activity_overview xml

    private void populateOverview(HashMap<String, String> flight) {

        tvAirlineName.setText(flight.get("carrierFsCode") + " - " + flight.get("flightNumber"));
        //tvFlightNumber.setText(flight.get("flightNumber"));
        tvStatus.setText(flight.get("status"));
        tvSource.setText(flight.get("departureAirportFsCode"));
        //tvDepFrom.setText(flight.get("departureAirportName"));
        //tvDepTime.setText(flight.get("departureDate"));
        tvDepGate.setText(flight.get("Flight{departureGate"));
        tvDepTerminal.setText(flight.get("departureTerminal"));
        tvDestination.setText(flight.get("arrivalAirportFsCode"));
        //tvArrFrom.setText(flight.get("ArrivalAirportName"));
        //tvArrTime.setText(flight.get("arrivalDate"));
        tvArrGate.setText(flight.get("arrivalGate"));
        tvArrTerminal.setText(flight.get("arrivalTerminal"));

        String departureTime = flight.get("departureDate");
        String depTime = departureTime.substring(11,16);

        tvDepTime.setText(depTime);

        String arrivalTime = flight.get("arrivalDate");
        String arrTime = arrivalTime.substring(11,16);

        tvArrTime.setText(arrTime);

        Log.d("depTime", depTime);

        Log.d("departureDate", flight.get("departureDate"));

        Log.d("Arrival Date", flight.get("arrivalDate") );

    }

}
