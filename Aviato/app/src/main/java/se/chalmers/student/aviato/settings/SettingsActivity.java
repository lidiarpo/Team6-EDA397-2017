package se.chalmers.student.aviato.settings;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import se.chalmers.student.aviato.R;


public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
