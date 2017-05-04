package se.chalmers.student.aviato.settings;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import se.chalmers.student.aviato.R;

/**
 * Created by gryphex on 2017-05-03.
 */

public class SettingsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
