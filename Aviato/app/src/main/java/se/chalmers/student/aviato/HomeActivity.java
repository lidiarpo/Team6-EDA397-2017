package se.chalmers.student.aviato;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //add flight image button to homescreen
        Button flightBtn = (Button) findViewById(R.id.flight_button);

        flightBtn.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v){
               Intent loadFlightActivity = new Intent(HomeActivity.this, FlightActivity.class);
               startActivity(loadFlightActivity);

           }
        });
    }
}
