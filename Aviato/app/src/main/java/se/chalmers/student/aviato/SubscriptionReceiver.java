package se.chalmers.student.aviato;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SubscriptionReceiver extends BroadcastReceiver {

    public static final int REQUEST_CODE = 12345;
    public static final String ACTION = "se.chalmers.student.aviato.alarm";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context,SubscriptionService.class);
        context.startService(i);
    }
}
