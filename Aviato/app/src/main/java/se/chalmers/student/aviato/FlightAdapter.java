package se.chalmers.student.aviato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class FlightAdapter extends ArrayAdapter<Flight> {
    public FlightAdapter(Context context, ArrayList<Flight> flights) {
        super(context, 0, flights);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get data item for this position
        Flight flight = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_flight, parent, false);
        }


        // Lookup view for data population
        TextView tvSource = (TextView) convertView.findViewById(R.id.tvSource);
        TextView tvDestination = (TextView) convertView.findViewById(R.id.tvDestination);
        TextView tvArrTime = (TextView) convertView.findViewById(R.id.tvArrTime);
        TextView tvDepTime = (TextView) convertView.findViewById(R.id.tvDepTime);
        TextView tvAirline = (TextView) convertView.findViewById(R.id.tvAirline);
        RelativeLayout rlEntry = (RelativeLayout) convertView.findViewById(R.id.rlSingleFlightEntry);

        if (position % 2 == 0) {
            rlEntry.setBackgroundResource(R.color.colorFlightItem);
        }

        // Populate the data into the template view using the data object
        if (flight != null) {
            tvSource.setText(flight.get("departureAirportFsCode"));
            tvDestination.setText(flight.get("arrivalAirportFsCode"));
            tvArrTime.setText(flight.get("arrivalDate"));
            tvDepTime.setText(flight.get("departureDate"));
            tvAirline.setText(flight.get("carrierFsCode") + flight.get("flightNumber"));
        }

        // Return the completed view to render on screen
        return convertView;

    }
}
