package se.chalmers.student.aviato.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import se.chalmers.student.aviato.R;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);

        Preference shareButton = findPreference(getString(R.string.share_button_title));
        shareButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                final String mailSubject = getString(R.string.email_subject);
                final String mailBody = getString(R.string.email_body);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject=" + mailSubject + "&body=" + mailBody);
                intent.setData(data);
                startActivity(intent);
                return true;
            }
        });

        Preference credits = findPreference(getString(R.string.credits_title));
        credits.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("https://github.com/bertogs/Team6-EDA397-2017/");
                intent.setData(data);
                startActivity(intent);
                return true;
            }
        });
    }
}
