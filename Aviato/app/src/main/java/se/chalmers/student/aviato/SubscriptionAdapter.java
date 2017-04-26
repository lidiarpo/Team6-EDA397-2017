package se.chalmers.student.aviato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class SubscriptionAdapter extends ArrayAdapter<Flight> {
    public SubscriptionAdapter(Context context, ArrayList<Flight> flights) {
        super(context, 0, flights);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
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
            subArrTime.setText(flight.get("arrivalDate"));
            subDepTime.setText(flight.get("departureDate"));
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
