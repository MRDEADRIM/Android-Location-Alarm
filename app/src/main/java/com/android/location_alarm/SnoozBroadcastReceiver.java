package com.android.location_alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.VIBRATOR_SERVICE;

public class SnoozBroadcastReceiver extends BroadcastReceiver {

    String snooz="", snoozn;
    int i;
    @Override
    public void onReceive(Context context, Intent intent) {


        SharedPreferences sharedPreferences = context.getSharedPreferences("snooz", MODE_PRIVATE);
        snooz = sharedPreferences.getString("snooz", "");

        Log.d("snooz111",snooz);

        SharedPreferences sharedPreferencesn = context.getSharedPreferences("snoozn", MODE_PRIVATE);
        snoozn = sharedPreferencesn.getString("snoozn", "");
        try {
            i = Integer.parseInt(snoozn);
        }catch(Exception e){
            Toast.makeText(context, "snooz off", Toast.LENGTH_SHORT).show();
        }

        if(snooz.equals("1") && i <= 3 ) {
                Intent i = new Intent(context.getApplicationContext(), Snooz.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

                Log.d("snoozn", String.valueOf(snoozn));


                Vibrator vibrator = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(2000);


            } else {
                Toast.makeText(context, "nnnnnnnnnnnnn", Toast.LENGTH_SHORT).show();
            }
    }
}

