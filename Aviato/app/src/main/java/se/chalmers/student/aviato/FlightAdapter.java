package se.chalmers.student.aviato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import android.widget.TextView;


/**
 * Created by gryphex on 2017-04-04.
 */

public class FlightAdapter extends ArrayAdapter<Flight> {
    public FlightAdapter(Context context, ArrayList<Flight> flights) {
        super(context, 0, flights);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
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

        // Populate the data into the template view using the data object
        tvSource.setText(flight.SourceAirport);
        tvDestination.setText(flight.DestinationAirport);
        tvArrTime.setText(flight.ArrTime);
        tvDepTime.setText(flight.DepTime);
        tvAirline.setText(flight.AirlineName);

        // Return the completed view to render on screen
        return convertView;
    }
}
