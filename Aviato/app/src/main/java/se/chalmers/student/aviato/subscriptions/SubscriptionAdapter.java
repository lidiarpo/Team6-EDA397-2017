package se.chalmers.student.aviato.subscriptions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import se.chalmers.student.aviato.R;
import se.chalmers.student.aviato.flights.Flight;

import static se.chalmers.student.aviato.Utilities.API_DATE_FORMAT;
import static se.chalmers.student.aviato.Utilities.VIEW_DATE_FORMAT;


public class SubscriptionAdapter extends ArrayAdapter<Flight> {
    public SubscriptionAdapter(Context context, ArrayList<Flight> flights) {
        super(context, 0, flights);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        SimpleDateFormat format = new SimpleDateFormat(API_DATE_FORMAT);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat viewFormat = new SimpleDateFormat(VIEW_DATE_FORMAT);
        Calendar cal = Calendar.getInstance();
        viewFormat.setTimeZone(cal.getTimeZone());

        // Get data item for this position
        Flight flight = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_subscription, parent, false);
        }

        // Lookup view for data population
        TextView subArrTime = (TextView) convertView.findViewById(R.id.subArrValue);
        TextView subDepTime = (TextView) convertView.findViewById(R.id.subDepValue);
        TextView subDepAirport = (TextView) convertView.findViewById(R.id.subDepAirport);
        TextView subArrAirport = (TextView) convertView.findViewById(R.id.subArrAirport);
        TextView subStatus = (TextView) convertView.findViewById(R.id.subStatus);
        TextView subCarrierCode = (TextView) convertView.findViewById(R.id.subCarrierCode);
        TextView subFlightNumber = (TextView) convertView.findViewById(R.id.subFlightNumber);

        RelativeLayout rlEntry = (RelativeLayout) convertView.findViewById(R.id.rlSingleSubscriptionEntry);

        if(position % 2 == 0){
            rlEntry.setBackgroundResource(R.color.colorFlightItem);
        }

        // Populate the data into the template view using the data object
        if(flight != null){

            try {
                subDepTime.setText(viewFormat.format(format.parse(flight.get("departureDate"))));
                subArrTime.setText(viewFormat.format(format.parse(flight.get("departureDate"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            subDepAirport.setText(flight.get("departureAirportFsCode"));
            subArrAirport.setText(flight.get("arrivalAirportFsCode"));
            subStatus.setText(flight.get("status"));
            subCarrierCode.setText(flight.get("carrierFsCode"));
            subFlightNumber.setText(flight.get("flightNumber"));
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
