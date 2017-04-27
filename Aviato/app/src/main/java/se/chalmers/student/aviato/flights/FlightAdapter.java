package se.chalmers.student.aviato.flights;

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

import static se.chalmers.student.aviato.Utilities.API_DATE_FORMAT;
import static se.chalmers.student.aviato.Utilities.VIEW_DATE_FORMAT;

/**
 * Created by gryphex on 2017-04-04.
 */

public class FlightAdapter extends ArrayAdapter<Flight> {
    public FlightAdapter(Context context, ArrayList<Flight> flights) {
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_flight, parent, false);
        }



        // Lookup view for data population
        TextView tvSource = (TextView) convertView.findViewById(R.id.tvSource);
        TextView tvDestination = (TextView) convertView.findViewById(R.id.tvDestination);
        TextView tvArrTime = (TextView) convertView.findViewById(R.id.tvArrTime);
        TextView tvDepTime = (TextView) convertView.findViewById(R.id.tvDepTime);
        TextView tvAirline = (TextView) convertView.findViewById(R.id.tvAirline);
        RelativeLayout rlEntry = (RelativeLayout) convertView.findViewById(R.id.rlSingleFlightEntry);

        if(position % 2 == 0){
            rlEntry.setBackgroundResource(R.color.colorFlightItem);
        }

        // Populate the data into the template view using the data object
        if(flight != null){
            tvSource.setText(flight.get("departureAirportFsCode"));
            tvDestination.setText(flight.get("arrivalAirportFsCode"));
            String arrivalDate = null;
            String departureDate = null;
            try {
                arrivalDate = viewFormat.format(format.parse(flight.get("arrivalDate")));
                departureDate = viewFormat.format(format.parse(flight.get("departureDate")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tvArrTime.setText(arrivalDate);
            tvDepTime.setText(departureDate);
            tvAirline.setText(flight.get("carrierFsCode") + flight.get("flightNumber"));
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
