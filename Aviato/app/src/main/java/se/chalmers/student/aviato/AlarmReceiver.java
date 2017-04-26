package se.chalmers.student.aviato;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("AlarmReceiver", "Alarm triggered");
        // Play the standard alarm
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringtone = RingtoneManager.getRingtone(context, uri);
        ringtone.play();

        // Vibrate for half a second
        Vibrator v = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);

        //TO-DO: Display NotificationActivity?
    }
}
